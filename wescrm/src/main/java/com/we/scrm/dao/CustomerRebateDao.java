package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.CustomerRebate;


public interface CustomerRebateDao {

	public CustomerRebate getById(Long id);

	public List<CustomerRebate> queryAll(CustomerRebate queryEntity);

	public Integer getTotalItemsCount(CustomerRebate queryEntity);

	public List<CustomerRebate> queryPage(CustomerRebate queryEntity , Pagination<CustomerRebate> page);

	public void create(CustomerRebate entity);

	public void update(CustomerRebate entity);

	public void delete(CustomerRebate entity);



}

