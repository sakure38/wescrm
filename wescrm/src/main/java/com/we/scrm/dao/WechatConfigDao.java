package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.WechatConfig;


public interface WechatConfigDao {

	public WechatConfig getById(Long id);

	public WechatConfig getWechatConfig();

	public List<WechatConfig> queryAll(WechatConfig queryEntity);

	public Integer getTotalItemsCount(WechatConfig queryEntity);

	public List<WechatConfig> queryPage(WechatConfig queryEntity , Pagination<WechatConfig> page);

	public void create(WechatConfig entity);

	public void update(WechatConfig entity);

	public void delete(WechatConfig entity);



}

