package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Orders;


public interface OrdersDao {

	public Orders getById(Long id);

	public List<Orders> queryAll(Orders queryEntity);

	public Integer getTotalItemsCount(Orders queryEntity);

	public List<Orders> queryPage(Orders queryEntity , Pagination<Orders> page);

	public void create(Orders entity);

	public void update(Orders entity);

	public void delete(Orders entity);



}

