package com.we.scrm.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.we.scrm.bean.AttachmentBean;
import com.we.scrm.bean.DownloadBean;
import com.we.scrm.bean.ImageBean;
import com.we.scrm.common.enums.ResourceEncodingEnum;
import com.we.scrm.common.exception.ApiError;
import com.we.scrm.common.exception.ApiException;
import com.we.scrm.common.util.HashUtil;
import com.we.scrm.common.util.IdUtil;
import com.we.scrm.common.util.ImageUtil;
import com.we.scrm.dao.AttachmentDao;
import com.we.scrm.dao.ResourceDao;
import com.we.scrm.domain.Attachment;
import com.we.scrm.domain.AuthUser;
import com.we.scrm.domain.Resource;

@Service
public class AttachmentService extends AbstractService<Attachment> {
	
	@Autowired
	private AttachmentDao attachDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Transactional
	public Attachment createAttachment(AuthUser user, AttachmentBean bean) {
		bean.validate(true);
		byte[] data = Base64.getDecoder().decode(bean.data);
		ImageBean image = ImageUtil.readImage(data);

		String hash = HashUtil.sha256(bean.data);
		Resource r = this.resourceDao.getByHash(hash);
		if (r == null) {
			r = new Resource();
			r.setId(IdUtil.nextId());
			r.setEncoding(ResourceEncodingEnum.BASE64);
			r.setHash(hash);
			r.setContent(bean.data);
			r.prepareCreate();
			this.resourceDao.create(r);
		}

		Attachment a = new Attachment();
		a.setResourceId(r.getId());
		a.setUserId(user.getId());
		a.setName(bean.name);
		a.setMime(image.mime);
		a.setWidth(image.width);
		a.setHeight(image.height);
		a.setSize(data.length);
		a.prepareCreate();
		this.attachDao.create(a);
		return a;
	}

	//删除附件
	@Transactional
	public void deleteAttachment(AuthUser user, Long id) {
		Attachment a = this.attachDao.getById(id);
		if(null != a) {
			long resourceId = a.getResourceId();
			this.attachDao.delete(a);
			
			Resource resource = new Resource();
			resource.setId(resourceId);
			this.resourceDao.delete(resource);
		}
	}

	public DownloadBean downloadAttachment(Long id, char size) {
		if ("0sml".indexOf(size) == (-1)) {
			throw new ApiException(ApiError.PARAMETER_INVALID, "size", "Invalid size.");
		}
		Attachment a = this.attachDao.getById(id);
		Resource r = this.resourceDao.getById(a.getResourceId());
		if (r == null) {
			throw new ApiException(ApiError.PARAMETER_INVALID, "id", "Resource not found.");
		}
		if (size == '0') {
			return new DownloadBean(a.getMime(), r.decode());
		}
		int originWidth = a.getWidth();
		int targetWidth = originWidth;
		boolean resize = false;
		if (size == 's') {
			if (originWidth > 160) {
				targetWidth = 160;
				resize = true;
			}
		} else if (size == 'm') {
			if (originWidth > 320) {
				targetWidth = 320;
				resize = true;
			}
		} else if (size == 'l') {
			if (originWidth > 640) {
				targetWidth = 640;
				resize = true;
			}
		}
		if (!resize) {
			return new DownloadBean(a.getMime(), r.decode());
		}
		BufferedImage resizedImage = null;
		try (ByteArrayInputStream input = new ByteArrayInputStream(r.decode())) {
			BufferedImage originImage = ImageIO.read(input);
			resizedImage = ImageUtil.resizeKeepRatio(originImage, targetWidth);
		} catch (IOException e) {
			throw new ApiException(ApiError.OPERATION_FAILED, null, "Could not resize image.");
		}
		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			ImageIO.write(resizedImage, "jpeg", output);
			return new DownloadBean("image/jpeg", output.toByteArray());
		} catch (IOException e) {
			throw new ApiException(ApiError.OPERATION_FAILED, null, "Could not resize image.");
		}
	}

	String checkMime(String mime) {
		if (mime == null || mime.isEmpty()) {
			throw new ApiException(ApiError.PARAMETER_INVALID, "mime", "Invalid mime type.");
		}
		int n = mime.indexOf(';');
		if (n >= 0) {
			mime = mime.substring(0, n);
		}
		mime = mime.toLowerCase();
		if (!SUPPORTED_MIME_TYPES.contains(mime)) {
			throw new ApiException(ApiError.PARAMETER_INVALID, "mime", "Unsupported mime type.");
		}
		return mime;
	}

	static final Set<String> SUPPORTED_MIME_TYPES = new HashSet<String>(Arrays.asList("image/jpeg", "image/gif", "image/png"));

}
