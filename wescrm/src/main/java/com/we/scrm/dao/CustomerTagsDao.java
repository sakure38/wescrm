package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.CustomerTags;


public interface CustomerTagsDao {

	public CustomerTags getById(Long id);

	public List<CustomerTags> getCustomerTags(CustomerTags queryEntity);

	public List<CustomerTags> queryAll(CustomerTags queryEntity);

	public Integer getTotalItemsCount(CustomerTags queryEntity);

	public List<CustomerTags> queryPage(CustomerTags queryEntity , Pagination<CustomerTags> page);

	public void create(CustomerTags entity);

	public void update(CustomerTags entity);

	public void delete(CustomerTags entity);

	public void batchCreate(List<CustomerTags> list);

	public void deleteByCustomer(Long customerId);

}

