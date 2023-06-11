package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Category;


public interface CategoryDao {

	public Category getById(Long id);

	public List<Category> queryAll(Category queryEntity);

	public Integer getTotalItemsCount(Category queryEntity);

	public List<Category> queryPage(Category queryEntity , Pagination<Category> page);

	public void create(Category entity);

	public void update(Category entity);

	public void delete(Category entity);



}

