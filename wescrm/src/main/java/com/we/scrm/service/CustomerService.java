package com.we.scrm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.we.scrm.common.enums.CustomerLevelEnum;
import com.we.scrm.common.enums.RebateTypeEnum;
import com.we.scrm.common.enums.UserTypeEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.dao.CustomerDao;
import com.we.scrm.domain.Customer;


@Service
public class CustomerService extends AbstractService<Customer>{

	@Autowired
	private CustomerDao entityDao;

	public Customer getById(Long id){
		return entityDao.getById(id);
	}

	public Customer getByCopenid(String copenid){
		return entityDao.getByCopenid(copenid);
	}

	public Customer getByOpenid(String openid){
		return entityDao.getByOpenid(openid);
	}

	public Customer getByMOpenid(String openid){
		return entityDao.getByMOpenid(openid);
	}

	public List<Customer> queryAll(Customer queryEntity){
		return entityDao.queryAll(queryEntity);
	}
	
	public List<Customer> queryRankSalers(String orderField){
		return entityDao.queryRankSalers(orderField);
	}

	public Pagination<Customer> queryPage(Customer queryEntity ,Pagination<Customer> page){
		if(null != queryEntity.getLevel() && queryEntity.getLevel() == -1){
			queryEntity.setLevel(null);
		}
		if(null != queryEntity.getRebateType() && queryEntity.getRebateType() == -1){
			queryEntity.setRebateType(null);
		}

//		queryEntity.setUserId(ShiroContext.getUserId());
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Customer> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Customer entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void createDefaultCustomer(Customer customer){
		if(null == customer.getUserId()){
			customer.setUserId(1L);//系统管理员
		}
		customer.setMoney(new BigDecimal(0));
		customer.setPoint(0);
		customer.setUserCount(0);
		customer.setOrderMoney(new BigDecimal(0));
		customer.setRebateMoney(new BigDecimal(0));
		customer.setRemark("");

		customer.setLevel(CustomerLevelEnum.NORMAL.getCode());
		customer.setRebateType(RebateTypeEnum.NORMAL.getCode());
		if(null == customer.getParentId()) {
			customer.setParentId(0l);
			customer.setParentName(UserTypeEnum.SYSTEM.name());
		}
		customer.setCreateAt(new Date());
		entityDao.create(customer);
	}

	public void update(Customer entity){
		entityDao.update(entity);
	}

	public void delete(Customer entity){
		entityDao.delete(entity);
	}



}

