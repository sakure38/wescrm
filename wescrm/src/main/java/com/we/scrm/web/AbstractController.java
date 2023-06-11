package com.we.scrm.web;

import com.we.scrm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class AbstractController {

    //定义servcie引用
    @Autowired
    protected WechatConfigService wechatConfigService;

    @Autowired
    protected DepartmentService departmentService;

    @Autowired
    protected AuthUserService authUserService;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected TagsService tagsService;

    @Autowired
    protected  CustomerTagsService customerTagsService;

    @Autowired
    protected  OrdersService ordersService;

    @Autowired
    protected  ProductService productService;

    @Autowired
    protected  CategoryService categoryService;

    @Autowired
    protected  AttachmentService attachmentService;

    @Autowired
    protected WerunStepsService werunStepsService;

    @Autowired
    protected  WerunUserService werunUserService;

    public ModelAndView prepareModelAndView(String view, String active, Map<String,Object> model){
        ModelAndView mv = new ModelAndView(view, model);
        mv.addObject("active", active);
        return mv;
    }

}
