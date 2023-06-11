package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Product;


public interface ProductDao {

	public Product getById(Long id);

	public Product getByPid(String pid);

	public List<Product> queryAll(Product queryEntity);

	public Integer getTotalItemsCount(Product queryEntity);

	public List<Product> queryPage(Product queryEntity , Pagination<Product> page);

	public void create(Product entity);

	public void update(Product entity);

	public void delete(Product entity);

	public void updateStatus(Product entity);

}

