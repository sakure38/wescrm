package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.Resource;
import com.we.scrm.service.ResourceService;
import com.we.scrm.dao.ResourceDao;


@Service
public class ResourceService extends AbstractService<Resource>{

	@Autowired
	private ResourceDao entityDao;

	public Resource getById(Long id){
		return entityDao.getById(id);
	}

	public List<Resource> queryAll(Resource queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Resource> queryPage(Resource queryEntity ,Pagination<Resource> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Resource> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Resource entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(Resource entity){
		entityDao.update(entity);
	}

	public void delete(Resource entity){
		entityDao.delete(entity);
	}



}

