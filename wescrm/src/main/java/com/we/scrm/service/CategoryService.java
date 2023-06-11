package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.Category;
import com.we.scrm.service.CategoryService;
import com.we.scrm.dao.CategoryDao;


@Service
public class CategoryService extends AbstractService<Category>{

	@Autowired
	private CategoryDao entityDao;

	public Category getById(Long id){
		return entityDao.getById(id);
	}

	public List<Category> queryAll(Category queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Category> queryPage(Category queryEntity ,Pagination<Category> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Category> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Category entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(Category entity){
		entityDao.update(entity);
	}

	public void delete(Category entity){
		entityDao.delete(entity);
	}



}

