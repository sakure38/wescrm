package com.we.scrm.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.enums.StatusEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Category;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.Orders;
import com.we.scrm.domain.Product;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.domain.WerunUser;
import com.we.scrm.web.AbstractController;
import com.we.scrm.web.api.vo.OrderVO;
import com.we.scrm.web.api.vo.UserVO;
import com.we.scrm.web.api.vo.WeRunVO;
import com.we.scrm.wechat.mapp.AppClient;

@Controller
@RequestMapping("/api")
public class MAppApiController extends AbstractController{

    @PostMapping("/mapp/getRecommend")
    @ResponseBody
    public String getRecommend(){
        Map<String, Object> model = new HashMap<String, Object>();
        Product queryEntity = new Product();
        queryEntity.setRecommend(StatusEnum.ENABLE.getCode());
        queryEntity.setStatus(StatusEnum.ENABLE.getCode());
        List<Product> recommendList = this.productService.queryAll(queryEntity);
        model.put("recommendList", recommendList);
        return JsonView.successJson(model);
    }

    @PostMapping("/mapp/queryProducts")
    @ResponseBody
    public String queryProducts(Product queryEntity, Pagination<Product> page){
        queryEntity.setStatus(StatusEnum.ENABLE.getCode());
        queryEntity.setRecommend(StatusEnum.DISABLE.getCode());

        if(null != queryEntity.getCategory() && 0 == queryEntity.getCategory()){
            queryEntity.setCategory(null);
        }

        page = this.productService.queryPage(queryEntity, page);
        return JsonView.successJson(page);
    }

    @PostMapping("/mapp/getProduct")
    @ResponseBody
    public String getProduct(Product queryEntity){
        if(null != queryEntity.getId()){
            return JsonView.successJson(this.productService.getById(queryEntity.getId()));
        }
        return JsonView.failureJson("没有此产品");
    }


    @PostMapping("/mapp/queryCategory")
    @ResponseBody
    public String queryCategory(){
        List<Category> categoryList = this.categoryService.queryAll(new Category());
        return JsonView.successJson(categoryList);
    }

    @PostMapping("/mapp/createOrder")
    @ResponseBody
    public String createOrder(OrderVO orderVO){
        if(!orderVO.valid()){
            return JsonView.failureJson("session key valid fail");
        }

        if(StringUtils.isBlank(orderVO.getPid()) || null == orderVO.getCount()){
            return JsonView.failureJson("params are error");
        }

        Product product = this.productService.getByPid(orderVO.getPid());
        if(null != product){
            Orders order = this.ordersService.createOrder(orderVO, product);
            if(null == order){
                return JsonView.failureJson("创建失败");
            }
        }else{
            return JsonView.failureJson("产品不存在");
        }

        return JsonView.successJson();
    }


    @PostMapping("/mapp/getUserInfo")
    @ResponseBody
    public String getUserInfo(UserVO user){
        if(!user.valid()){
            JsonView.failureJson("session key valid fail");
        }
        Customer customer = this.customerService.getByMOpenid(user.getOpenid());
        return JsonView.successJson(customer);
    }

    @PostMapping("/mapp/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(UserVO user){
        if(!user.valid()){
            JsonView.failureJson("session key valid fail");
        }
        Customer customer = this.customerService.getByMOpenid(user.getOpenid());

        customer.setName(user.getName());
        customer.setNickname(user.getNickname());
        customer.setGender(user.getGender());
        customer.setBank(user.getBank());
        customer.setBankNo(user.getBankNo());
        customer.setCard(user.getCard());
        customer.setCardNo(user.getCardNo());
        customer.setEmail(user.getEmail());
        customer.setMobile(user.getMobile());
        customer.setWxid(user.getWxid());

        this.customerService.update(customer);
        return JsonView.successJson();
    }

    @PostMapping("/mapp/getOrders")
    @ResponseBody
    public String getOrders(OrderVO orderVO, Pagination<Orders> page){
        if(!orderVO.valid()){
            JsonView.failureJson("session key valid fail");
        }

        Customer customer = this.customerService.getByMOpenid(orderVO.getOpenid());
        if(null != customer){
            Orders queryEntity = new Orders();
            if(null != orderVO.getStatus() &&  0 != orderVO.getStatus()){
                queryEntity.setStatus(orderVO.getStatus());
            }
            queryEntity.setCustomerId(customer.getId());
            page = this.ordersService.queryPage(queryEntity, page);
            return JsonView.successJson(page);
        }
        return JsonView.failureJson("此用户不存在");
    }

    @PostMapping("/mapp/getOrderById")
    @ResponseBody
    public String getOrderById(OrderVO orderVo){
        if(!orderVo.valid()) {
            return JsonView.failureJson("sesseion key valid fail");
        }
        JSONObject jsObj = new JSONObject();
        Customer customer = this.customerService.getByMOpenid(orderVo.getOpenid());
        if(null != customer) {
            Orders order = new Orders();
            order.setId(orderVo.getId());
            order = this.ordersService.getById(orderVo.getId());
            jsObj.put("customer", customer);
            jsObj.put("order", order);
        }
        return JsonView.successJson(jsObj);
    }

    @PostMapping("/mapp/encryptWeRunData")
    @ResponseBody
    public String encryptWeRunData(String encryptedData, String iv, String code){

        WechatConfig config = this.wechatConfigService.getWechatConfig();
        JSONObject jsObj = AppClient.encryptedStepList(config.getMappid(), config.getMappsecret(), code, encryptedData, iv);
        if(null != jsObj){
            String openid = jsObj.getString("openid");
            JSONArray stepList = jsObj.getJSONArray("stepInfoList");
            JSONObject step = (JSONObject)stepList.get(stepList.size() - 1);

            Integer todayStep = step.getInteger("step");
            WerunUser werunUser = this.werunStepsService.updateTodayStep(openid, todayStep);
            step.put("werunUser",werunUser);
            return JsonView.successJson(step);
        }
        return JsonView.failureJson("获取数据失败");
    }

    @PostMapping("/mapp/giveSteps")
    @ResponseBody
    public String giveSteps(WeRunVO weRunVO){
        String openid = weRunVO.getOpenid();
        String toOpenid = weRunVO.getToOpenid();
        WerunUser werunUser = this.werunUserService.getByOpenid(openid);
        WerunUser toWerunUser = this.werunUserService.getByOpenid(toOpenid);

        if(null != werunUser && null != toWerunUser){
            toWerunUser.setSteps(toWerunUser.getSteps() + weRunVO.getSteps());
            werunUser.setSteps(werunUser.getSteps() - weRunVO.getSteps());
            this.werunUserService.update(toWerunUser);
            this.werunUserService.update(werunUser);
        }else{
            return JsonView.failureJson("数据错误");
        }
        return JsonView.successJson();
    }

    //兑换积分
    @PostMapping(value = "/mapp/changePoint")
    @ResponseBody
    public String changePoint(WeRunVO werunVo) {
        Integer pointRate = 100;
        if(!werunVo.valid()) {
            return JsonView.failureJson("sesseion key valid failure");
        }
        String openid = werunVo.getOpenid();
        Customer customer = this.customerService.getByMOpenid(openid);
        if(null != customer) {
            Integer step = werunVo.getSteps();
            Integer modStep = step/pointRate;//取整数
            customer.setPoint(customer.getPoint() + modStep);
            this.customerService.update(customer);

            WerunUser werunUser = this.werunUserService.getByOpenid(openid);
            if(null != werunUser) {
                werunUser.setSteps(werunUser.getSteps() - modStep*pointRate);
                this.werunUserService.update(werunUser);
            }
        }
        return JsonView.successJson();
    }

}
