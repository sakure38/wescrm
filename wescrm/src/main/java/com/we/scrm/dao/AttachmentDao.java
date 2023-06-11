package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Attachment;


public interface AttachmentDao {

	public Attachment getById(Long id);

	public List<Attachment> queryAll(Attachment queryEntity);

	public Integer getTotalItemsCount(Attachment queryEntity);

	public List<Attachment> queryPage(Attachment queryEntity , Pagination<Attachment> page);

	public void create(Attachment entity);

	public void update(Attachment entity);

	public void delete(Attachment entity);



}

