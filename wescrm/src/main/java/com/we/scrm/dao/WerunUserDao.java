package com.we.scrm.dao;

import java.util.List;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.WerunUser;


public interface WerunUserDao {

	public WerunUser getById(Long id);

	public WerunUser getByOpenid(String openid);

	public List<WerunUser> queryAll(WerunUser queryEntity);

	public Integer getTotalItemsCount(WerunUser queryEntity);

	public List<WerunUser> queryPage(WerunUser queryEntity , Pagination<WerunUser> page);

	public void create(WerunUser entity);

	public void update(WerunUser entity);

	public void delete(WerunUser entity);



}

