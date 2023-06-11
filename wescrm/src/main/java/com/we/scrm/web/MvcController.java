package com.we.scrm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.we.scrm.domain.Customer;

@Controller
public class MvcController extends AbstractController {

    String active = "index";

    @GetMapping("/")
    public ModelAndView index(String orderField){

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("active", "index");
        
        if("佣金榜".equals(orderField)) {
    		orderField = "rebate_money";
        }else if("积分榜".equals(orderField)) {
        	orderField = "point";
        }else if("拉新榜".equals(orderField)) {
        	orderField = "user_count";
        }else {
        	orderField = "order_money";
        }
        
        List<Customer> rankingSalers = this.customerService.queryRankSalers(orderField);
        JSONArray salers = new JSONArray();//名字
        JSONArray moneyArr = new JSONArray();//消费榜
        JSONArray rebateMoneyArr = new JSONArray();//佣金榜
        JSONArray pointArr = new JSONArray();//积分榜
        JSONArray newUserArr = new JSONArray();//拉新榜
        for(Customer customer : rankingSalers) {
        	salers.add(customer.getName());
        	moneyArr.add(customer.getOrderMoney());
        	rebateMoneyArr.add(customer.getRebateMoney());
        	pointArr.add(customer.getPoint());
        	newUserArr.add(customer.getUserCount());
        }
        mv.addObject("salers", salers.toJSONString());
        mv.addObject("moneyArr", moneyArr.toJSONString());
        mv.addObject("rebateMoneyArr", rebateMoneyArr.toJSONString());
        mv.addObject("pointArr", pointArr.toJSONString());
        mv.addObject("newUserArr", newUserArr.toJSONString());
        return mv;
    }

    @GetMapping("/sales")
    public ModelAndView salers(String orderField) {
        Map<String,Object> model = new HashMap<String,Object>();
        return prepareModelAndView("sales", "sales", model);
    }

}
