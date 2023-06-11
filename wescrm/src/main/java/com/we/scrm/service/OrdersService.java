package com.we.scrm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.we.scrm.common.enums.OrderStatusEnum;
import com.we.scrm.common.enums.PayMethodEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.DateUtil;
import com.we.scrm.common.util.IdUtil;
import com.we.scrm.dao.CustomerDao;
import com.we.scrm.dao.CustomerRebateDao;
import com.we.scrm.dao.OrdersDao;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.CustomerRebate;
import com.we.scrm.domain.Orders;
import com.we.scrm.domain.Product;
import com.we.scrm.web.api.vo.OrderVO;


@Service
public class OrdersService extends AbstractService<Orders>{

	@Autowired
	private OrdersDao entityDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerRebateDao customerRebateDao;


	public Orders getById(Long id){
		return entityDao.getById(id);
	}

	public List<Orders> queryAll(Orders queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Orders> queryPage(Orders queryEntity ,Pagination<Orders> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Orders> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Orders entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(Orders entity){
		entityDao.update(entity);
	}

	public void delete(Orders entity){
		entityDao.delete(entity);
	}

	@Transactional
	public Orders createOrder(OrderVO orderVo, Product product) {
		Orders order = new Orders();
		order.setProductId(product.getPid());
		order.setProductName(product.getName());
		order.setProductPrice(product.getPrice());
		order.setProductRebate(product.getRebate());
		order.setProductPicture(product.getPicture());
		order.setPayMethod(PayMethodEnum.WECHAT.getCode());
		order.setStatus(OrderStatusEnum.PAYED.getCode());
		order.setPayTime(new Date());
		order.setPoint(0);
		order.setProductCount(orderVo.getCount());
		order.setAddress(orderVo.getAddress());
		order.setCreateAt(new Date());
		order.setInvoice(orderVo.getInvoice());
		order.setMoney(product.getPrice().multiply(new BigDecimal(orderVo.getCount())));
		order.setOrderId(IdUtil.nextStringId());
		order.setRemark(orderVo.getRemark());
		if(StringUtils.isNotBlank(orderVo.getShippingTime())) {
			order.setShippingTime(DateUtil.COMMON.getTextDate(orderVo.getShippingTime()));
		}

		//获取当前客户
		Customer customer = this.customerDao.getByMOpenid(orderVo.getOpenid());
		if(null != customer) {
			if(customer.getParentId() == 0) {//上级分销是系统默认值,更新
				if(StringUtils.isNotBlank(orderVo.getParentOpenid())) {
					Customer parentCustomer = this.customerDao.getByMOpenid(orderVo.getParentOpenid());
					if(null != parentCustomer) {
						customer.setParentId(parentCustomer.getId());
						customer.setParentName(parentCustomer.getName());
						customer.setUserId(parentCustomer.getUserId());
						//拉新+1
						parentCustomer.setUserCount(parentCustomer.getUserCount()+1);
						this.customerDao.update(parentCustomer);
					}
				}
			}

			order.setCustomerId(customer.getId());//客户
			order.setRebateCustomerId(customer.getParentId());//上级
			order.setUserId(customer.getUserId());//隶属员工
			order.setCreateAt(new Date());
			this.entityDao.create(order);

			customer.setOrderMoney(customer.getOrderMoney().add(order.getMoney()));//总金额
			customer.setOrderTime(new Date());//最新成单时间
			this.customerDao.update(customer);

			//佣金记录
			CustomerRebate customerRebate = new CustomerRebate();
			customerRebate.setCustomerId(customer.getParentId());
			customerRebate.setMoney(order.getMoney().multiply(product.getRebate().divide(new BigDecimal(100))));//返佣金额
			customerRebate.setRebate(product.getRebate());//返佣比例
			customerRebate.setCreateAt(new Date());
			customerRebate.setParentId(0L);//多级分销的时候使用
			customerRebate.setOrderId(order.getId());
			customerRebate.setUserId(customer.getUserId());
			this.customerRebateDao.create(customerRebate);

			Customer parentCustomer = this.customerDao.getById(customer.getParentId());
			if(null != parentCustomer) {
				//返佣给上级
				parentCustomer.setRebateMoney(parentCustomer.getRebateMoney().add(customerRebate.getMoney()));
				this.customerDao.update(parentCustomer);
			}
			return order;
		}
		return null;
	}

}

