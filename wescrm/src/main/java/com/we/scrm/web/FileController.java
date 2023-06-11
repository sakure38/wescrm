package com.we.scrm.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.we.scrm.bean.DownloadBean;
import com.we.scrm.service.AttachmentService;

@Controller
public class FileController extends AbstractController {
	
	protected static final String ID = "{id:[0-9]{1,17}}";
	
	@Autowired
	AttachmentService attachmentService;
	
	String maxAge = "max-age=" + 3600 * 24 * 365;

	@GetMapping("/file/attachment/" + ID)
	public void process(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, '0', response);
	}

	@GetMapping("/file/attachment/" + ID + "/0")
	public void process0(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, '0', response);
	}

	@GetMapping("/file/attachment/" + ID + "/l")
	public void processL(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 'l', response);
	}

	@GetMapping("/file/attachment/" + ID + "/m")
	public void processM(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 'm', response);
	}

	@GetMapping("/file/attachment/" + ID + "/s")
	public void processS(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 's', response);
	}
	
	void process(long id, char size, HttpServletResponse response) throws IOException {
		DownloadBean bean = attachmentService.downloadAttachment(id, size);
		response.setContentType(bean.mime);
		response.setContentLength(bean.data.length);
		response.setHeader("Cache-Control", maxAge);
		ServletOutputStream output = response.getOutputStream();
		output.write(bean.data);
		output.flush();
	}
	
}
