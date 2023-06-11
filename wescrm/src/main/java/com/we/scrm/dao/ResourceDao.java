package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Resource;


public interface ResourceDao {

	public Resource getById(Long id);

	/**
	 *根据hash获取
	 **/
	public Resource getByHash(String hash);

	public List<Resource> queryAll(Resource queryEntity);

	public Integer getTotalItemsCount(Resource queryEntity);

	public List<Resource> queryPage(Resource queryEntity , Pagination<Resource> page);

	public void create(Resource entity);

	public void update(Resource entity);

	public void delete(Resource entity);



}

