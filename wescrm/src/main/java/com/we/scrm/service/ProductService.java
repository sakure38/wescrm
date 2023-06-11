package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.Product;
import com.we.scrm.service.ProductService;
import com.we.scrm.dao.ProductDao;


@Service
public class ProductService extends AbstractService<Product>{

	@Autowired
	private ProductDao entityDao;

	public Product getById(Long id){
		return entityDao.getById(id);
	}

	public Product getByPid(String pid){
		return entityDao.getByPid(pid);
	}

	public List<Product> queryAll(Product queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<Product> queryPage(Product queryEntity ,Pagination<Product> page){
		if(null != queryEntity.getStatus() && -1 == queryEntity.getStatus()){
			queryEntity.setStatus(null);
		}
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<Product> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(Product entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(Product entity){
		entityDao.update(entity);
	}

	public void delete(Product entity){
		entityDao.delete(entity);
	}

	public void updateStatus(Product entity){
		entityDao.updateStatus(entity);
	}


}

