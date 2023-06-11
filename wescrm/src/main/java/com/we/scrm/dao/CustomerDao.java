package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Customer;


public interface CustomerDao {

	public Customer getById(Long id);

	public Customer getByCopenid(String copenid);

	public Customer getByOpenid(String openid);

	public Customer getByMOpenid(String openid);

	public List<Customer> queryAll(Customer queryEntity);
	
	public List<Customer> queryRankSalers(String orderField);

	public Integer getTotalItemsCount(Customer queryEntity);

	public List<Customer> queryPage(Customer queryEntity , Pagination<Customer> page);

	public void create(Customer entity);

	public void update(Customer entity);

	public void delete(Customer entity);



}

