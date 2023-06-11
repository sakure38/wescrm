package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.CustomerRebate;
import com.we.scrm.service.CustomerRebateService;
import com.we.scrm.dao.CustomerRebateDao;


@Service
public class CustomerRebateService extends AbstractService<CustomerRebate>{

	@Autowired
	private CustomerRebateDao entityDao;

	public CustomerRebate getById(Long id){
		return entityDao.getById(id);
	}

	public List<CustomerRebate> queryAll(CustomerRebate queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<CustomerRebate> queryPage(CustomerRebate queryEntity ,Pagination<CustomerRebate> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<CustomerRebate> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(CustomerRebate entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(CustomerRebate entity){
		entityDao.update(entity);
	}

	public void delete(CustomerRebate entity){
		entityDao.delete(entity);
	}



}

