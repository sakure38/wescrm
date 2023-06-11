package com.we.scrm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.we.scrm.common.page.Pagination;
import com.we.scrm.dao.TagsDao;
import com.we.scrm.domain.Tags;


@Service
public class TagsService extends AbstractService<Tags>{

	@Autowired
	private TagsDao entityDao;

	public Tags getById(Long id){
		return entityDao.getById(id);
	}

	public List<Tags> queryAll(Tags queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Tags> queryPage(Tags queryEntity ,Pagination<Tags> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Tags> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Tags entity){
		if(StringUtils.isEmpty(entity.getUsername())){
			entity.setUsername("SYSTEM");
		}
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(Tags entity){
		entityDao.update(entity);
	}

	public void delete(Tags entity){
		entityDao.delete(entity);
	}



}

