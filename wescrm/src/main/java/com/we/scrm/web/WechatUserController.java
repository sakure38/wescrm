package com.we.scrm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.we.scrm.domain.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.wechat.mp.MpClient;

/**
*@author QiCong
*@version 2021-05-19
*公众号用户管理
**/

@Controller
public class WechatUserController extends AbstractController{
	
	String active = "wechatUser";
	
	@RequestMapping("/wechat/user")
	public ModelAndView wechatUser() {
		Map<String,Object> model = new HashMap<String,Object>();
		
		List<JSONObject> userList = new ArrayList<JSONObject>();
		WechatConfig config = wechatConfigService.getWechatConfig();
		JSONArray array = MpClient.getOpenidArray(config.getAppid(), config.getAppsecret(), null);
		if(null != array) {
			for(int i = 0; i < array.size(); i++) {
				String openid = array.getString(i);
				JSONObject userInfo = MpClient.getUser(config.getAppid(), config.getAppsecret(), openid);
				userList.add(userInfo);
			}
		}
		model.put("userList", userList);
		return prepareModelAndView("wechatUser", active, model);
	}

	/**
	 * for：给微信公众号分配客户
	 */
	@RequestMapping("/wechat/userAssign")
	public ModelAndView wechatUserAssign(AuthUser queryEntity) {
		Map<String,Object> model = new HashMap<String,Object>();
		List<AuthUser> userList = authUserService.queryAll(queryEntity);
		model.put("userList", userList);
		return prepareModelAndView("wechatUserAssign", active, model);
	}
	
	/**
	 * 分配公众号用户为客户
	 */
	@PostMapping("/wechat/assignToCustomer")
	@ResponseBody
	public String assignToCustomer(String openid,Long userid) {
		if(StringUtils.isNotBlank(openid) && null != userid) {
			WechatConfig config = wechatConfigService.getWechatConfig();
			JSONObject userInfo = MpClient.getUser(config.getAppid(), config.getAppsecret(), openid);
			
			//如果有unionid，通过unionid来判断
			if(null != customerService.getByOpenid(openid)) {
				return JsonView.failureJson("此公众号用户已分配");
			}
			Customer customer = new Customer();
			customer.setOpenid(openid);
			customer.setName(userInfo.getString("nickname"));
			customer.setNickname(userInfo.getString("nickname"));
			customer.setUserId(userid);
			
			this.customerService.createDefaultCustomer(customer);
			return JsonView.successJson();
		}else {
			return JsonView.failureJson("请选择要分配的用户");
		}
	}
	
}

