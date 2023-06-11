package com.we.scrm.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.WerunUser;

/**
*@author QiCong
*@version 2021-05-19
**/

@Controller
public class WerunController extends AbstractController{
	
	String active = "werun";
	
	@RequestMapping("/werun/user")
	public ModelAndView werunUser(WerunUser queryEntity,Pagination<WerunUser> page) {
		Map<String,Object> model = new HashMap<String,Object>();
		page = werunUserService.queryPage(queryEntity, page);
		model.put("page", page);
		return prepareModelAndView("werunUser", active, model);
	}
	
}

