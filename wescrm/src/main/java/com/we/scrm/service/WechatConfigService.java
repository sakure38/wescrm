package com.we.scrm.service;

import java.util.Date;
import java.util.List;
import com.we.scrm.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.service.WechatConfigService;
import com.we.scrm.dao.WechatConfigDao;


@Service
public class WechatConfigService extends AbstractService<WechatConfig>{

	@Autowired
	private WechatConfigDao entityDao;

	public WechatConfig getById(Long id){
		return entityDao.getById(id);
	}

	public WechatConfig getWechatConfig(){
		return entityDao.getWechatConfig();
	}

	public List<WechatConfig> queryAll(WechatConfig queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<WechatConfig> queryPage(WechatConfig queryEntity ,Pagination<WechatConfig> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<WechatConfig> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(WechatConfig entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(WechatConfig entity){
		entityDao.update(entity);
	}

	public void delete(WechatConfig entity){
		entityDao.delete(entity);
	}



}

