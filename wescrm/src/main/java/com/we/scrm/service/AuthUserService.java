package com.we.scrm.service;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.HashUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.we.scrm.domain.AuthUser;
import com.we.scrm.service.AuthUserService;
import com.we.scrm.dao.AuthUserDao;


@Service
public class AuthUserService extends AbstractService<AuthUser>{
	String defaultSalt = "j9WYnLUML0srmBe28jTs47FzgoTM6ZCNK8vQrM6T5QERgegyClTc7un5YGktuGfE";
	String defaultPwd = "user123";

	@Autowired
	private AuthUserDao entityDao;

	public AuthUser getById(Long id){
		return entityDao.getById(id);
	}

	public AuthUser getByUsername(String username){
		return entityDao.getByUsername(username);
	}

	public void syncCorpUser(JSONObject jsObj){
		AuthUser authUser = new AuthUser();
		if(StringUtils.isNotBlank(jsObj.getString("name"))){
			authUser.setName(jsObj.getString("name"));
		}
		if(StringUtils.isNotBlank(jsObj.getString("userid"))) {
			authUser.setUsername(jsObj.getString("userid"));
			authUser.setCorpuserid(jsObj.getString("userid"));
		}
		if(StringUtils.isNotBlank(jsObj.getString("mobile"))) {
			authUser.setMobile(jsObj.getString("mobile"));
		}
		if(StringUtils.isNotBlank(jsObj.getString("external_position"))) {
			authUser.setPosition(jsObj.getString("external_position"));
		}
		if(StringUtils.isNotBlank(jsObj.getString("gender"))) {
			authUser.setGender(Integer.parseInt(jsObj.getString("gender")));
		}
		if(StringUtils.isNotBlank(jsObj.getString("avatar"))) {
			authUser.setHeader(jsObj.getString("avatar"));
		}
		authUser.setIp("127.0.0.1");
		authUser.setSalt(defaultSalt);
		String passwd = HashUtil.hmacSha256(defaultPwd, authUser.getUsername());
		passwd = HashUtil.hmacSha256(passwd, defaultSalt);
		authUser.setPassword(passwd);
		authUser.setCreateAt(new Date());
		authUser.setStatus(1);
		authUser.setRole(1);
		this.entityDao.create(authUser);
	}

	public List<AuthUser> queryAll(AuthUser queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public Pagination<AuthUser> queryPage(AuthUser queryEntity ,Pagination<AuthUser> page){
		if(null != queryEntity.getStatus() && -1 == queryEntity.getStatus()){
			queryEntity.setStatus(null);
		}
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<AuthUser> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(AuthUser entity){
		entity.setCreateAt(new Date());
		entityDao.create(entity);
	}

	public void update(AuthUser entity){
		entityDao.update(entity);
	}

	public void delete(AuthUser entity){
		entityDao.delete(entity);
	}



}

