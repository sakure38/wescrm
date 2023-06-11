package com.we.scrm.web.api;

import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.web.AbstractController;
import com.we.scrm.wechat.common.util.WechatCacheUtil;
import com.we.scrm.wechat.mapp.AppClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class JsCodeAuthController extends AbstractController{

    @PostMapping("/jscode2session")
    @ResponseBody
    public String jscode2session(String jscode, String parentOpenid){

        if(StringUtils.isBlank(jscode)){
            return JsonView.failureJson("jscode is not correct");
        }

        WechatConfig wechatConfig = this.wechatConfigService.getWechatConfig();
        JSONObject jsonObject = AppClient.jscode2session(wechatConfig.getMappid(), wechatConfig.getMappsecret(), jscode);
        //后端登录成功
        if(jsonObject.containsKey("session_key")){
            String openid = jsonObject.getString("openid");
            String sessionKey = jsonObject.getString("session_key");
            String unionid = jsonObject.getString("unionid");

            if(null != WechatCacheUtil.getSessionKey(openid)){
                //设置一个过期时间
            }else{
                //openid对应的Customer是没有的，我们要创建一个新的客户 Customer
                //创建Customer的代码，根据 mopenid 获取（之前代码可能有bug）
                if(null == this.customerService.getByMOpenid(openid)){
                    Customer customer = new Customer();
                    customer.setMopenid(openid);
                    customer.setUnionid(unionid);

                    //设置上级分销
                    if(StringUtils.isNotBlank(parentOpenid)){
                        Customer parentCustomer = this.customerService.getByMOpenid(parentOpenid);
                        if(null != parentCustomer){
                            customer.setParentId(parentCustomer.getId());
                            customer.setParentName(parentCustomer.getName());
                            customer.setUserId(parentCustomer.getUserId());
                            parentCustomer.setUserCount(parentCustomer.getUserCount() + 1);
                            this.customerService.update(parentCustomer);
                        }
                    }
                    this.customerService.createDefaultCustomer(customer);
                }
            }
            //缓存起来
            WechatCacheUtil.setSessionKey(openid, sessionKey);
            return JsonView.successJson(jsonObject);
        }
        return JsonView.failureJson("get session key error");
    }

}
