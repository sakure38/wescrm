package com.we.scrm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.we.scrm.common.enums.CustomerLevelEnum;
import com.we.scrm.common.enums.RebateTypeEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.Orders;

@Controller
public class CustomerController extends  AbstractController{

    String active = "customer";

    @RequestMapping("/customer/page")
    public ModelAndView customerPage(Customer queryEntity, Pagination<Customer> page) {
        Map<String, Object> model = new HashMap<String ,Object>();
//        page.setPageSize(3);
        page = this.customerService.queryPage(queryEntity, page);
        model.put("page", page);
        model.put("queryEntity", queryEntity);
        model.put("levels", CustomerLevelEnum.toMap());
        model.put("rebates", RebateTypeEnum.toMap());
        return prepareModelAndView("customerPage", active, model);
    }

    @GetMapping("customer/info")
    public ModelAndView customerInfo(Customer entity) {
        Map<String, Object> model = new HashMap<String ,Object>();
        if(null != entity.getId()){
            model.put("entity", this.customerService.getById(entity.getId()));
            model.put("levels", CustomerLevelEnum.toMap());
            model.put("rebates", RebateTypeEnum.toMap());
        }

        model.put("customerTags",  this.customerTagsService.getCustomerTags(entity.getId()));
        return prepareModelAndView("customerInfo", active, model);
    }

    @PostMapping("/customer/doMerge")
    @ResponseBody
    public String doMerge(Customer entity){
        if(null != entity.getId()){//修改
            this.customerService.update(entity);
        }else{
            JsonView.failureJson("用户ID错误");
        }
        return JsonView.successJson(entity.getId());
    }

    @PostMapping("/customer/saveTags")
    @ResponseBody
    public String saveTags(@RequestParam(name="id") Long id, @RequestParam(name="tags[]") List<String> tags) {
        if(null != id) {
            customerTagsService.updateCustomerTags(id, tags);
        }
        return JsonView.successJson(id);
    }

    /**
     * 此用户的所有的成单
     */
    @RequestMapping("/customer/orders")
    public ModelAndView customerOrders(Orders queryEntity,Pagination<Orders> page) {
        Map<String,Object> model = new HashMap<String,Object>();
        if(null != queryEntity.getCustomerId()) {
            Customer customer = this.customerService.getById(queryEntity.getCustomerId());
            model.put("customer", customer);
        }
        page = ordersService.queryPage(queryEntity, page);
        model.put("page", page);
        return prepareModelAndView("customerOrders", active, model);
    }

}
