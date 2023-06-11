package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.WerunUser;
import com.we.scrm.service.WerunUserService;
import com.we.scrm.dao.WerunUserDao;


@Service
public class WerunUserService extends AbstractService<WerunUser>{

	@Autowired
	private WerunUserDao entityDao;

	public WerunUser getById(Long id){
		return entityDao.getById(id);
	}

	public WerunUser getByOpenid(String openid){
		return entityDao.getByOpenid(openid);
	}

	public List<WerunUser> queryAll(WerunUser queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<WerunUser> queryPage(WerunUser queryEntity ,Pagination<WerunUser> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<WerunUser> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(WerunUser entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(WerunUser entity){
		entityDao.update(entity);
	}

	public void delete(WerunUser entity){
		entityDao.delete(entity);
	}



}

