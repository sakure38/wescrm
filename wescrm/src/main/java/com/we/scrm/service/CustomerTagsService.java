package com.we.scrm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.CustomerTags;
import com.we.scrm.service.CustomerTagsService;
import com.we.scrm.dao.CustomerTagsDao;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerTagsService extends AbstractService<CustomerTags>{

	@Autowired
	private CustomerTagsDao entityDao;

	public CustomerTags getById(Long id){
		return entityDao.getById(id);
	}

	public List<CustomerTags> queryAll(CustomerTags queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public List<CustomerTags> getCustomerTags(Long customerId){
		CustomerTags queryEntity = new CustomerTags();
		queryEntity.setCustomerId(customerId);
		return entityDao.getCustomerTags(queryEntity);
	}

	@Transactional
	public void updateCustomerTags(Long customerId, List<String> tags) {
		//直接删除所有的，再批量创建
		entityDao.deleteByCustomer(customerId);
		List<CustomerTags> customerTags = new ArrayList<CustomerTags>();
		for(String id : tags) {
			CustomerTags tag = new CustomerTags();
			tag.setTagId(Long.parseLong(id));
			tag.setCustomerId(customerId);
			tag.setCreateAt(new Date());
			customerTags.add(tag);
		}
		entityDao.batchCreate(customerTags);
	}

	public Pagination<CustomerTags> queryPage(CustomerTags queryEntity ,Pagination<CustomerTags> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<CustomerTags> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(CustomerTags entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(CustomerTags entity){
		entityDao.update(entity);
	}

	public void delete(CustomerTags entity){
		entityDao.delete(entity);
	}



}

