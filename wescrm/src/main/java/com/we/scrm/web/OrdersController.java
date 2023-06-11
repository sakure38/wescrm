package com.we.scrm.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.we.scrm.common.page.Pagination;
import com.we.scrm.domain.Orders;

/**
*@author QiCong
*@version 2021-05-15
**/
@Controller
public class OrdersController extends AbstractController{
	
	String active = "order";
	
	@RequestMapping("/orders/page")
	public ModelAndView ordersPage(Orders queryEntity,Pagination<Orders> page) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("queryEntity", queryEntity);
		page = ordersService.queryPage(queryEntity, page);
		model.put("page", page);
		return prepareModelAndView("ordersPage", active, model);
	}
	
	@RequestMapping("/orders/info")
	public ModelAndView ordersInfo(Long id) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("entity", this.ordersService.getById(id));
		return prepareModelAndView("ordersInfo", active, model);
	}
	
}
