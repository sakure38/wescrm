package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Tags;


public interface TagsDao {

	public Tags getById(Long id);

	public List<Tags> queryAll(Tags queryEntity);

	public Integer getTotalItemsCount(Tags queryEntity);

	public List<Tags> queryPage(Tags queryEntity , Pagination<Tags> page);

	public void create(Tags entity);

	public void update(Tags entity);

	public void delete(Tags entity);



}

