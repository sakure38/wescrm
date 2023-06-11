package com.we.scrm.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.shiro.ShiroContext;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.Customer;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.wechat.corp.CorpClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CorpContactController extends  AbstractController {

    String active = "corpContact";

    @GetMapping("/corpContact")
    public ModelAndView corpContact(){
        Map<String,Object> model = new HashMap<String,Object>();
        WechatConfig config = wechatConfigService.getWechatConfig();
        String corpuserid = ShiroContext.getCorpUserid();//当前登录的用户session中获取

        String agentId = config.getAgentid();
        String corpId = config.getCorpid();
        String corpSecret = config.getCorsecret();
        //请求客户列表
        JSONArray returnArray = new JSONArray();
        JSONArray array = CorpClient.getExternalContactList(agentId, corpId,corpSecret, corpuserid);
        if(null != array){
            for(int i = 0 ; i < array.size(); i++){
                JSONObject obj = CorpClient.getExternalContact(agentId, corpId,corpSecret, array.getString(i));
                returnArray.add(obj.getJSONObject("external_contact"));
            }
        }
        model.put("contactList", returnArray);
        return prepareModelAndView("corpContact", active, model);
    }

    @PostMapping("/corpContact/sync")
    @ResponseBody
    public String corpContactSync(String userid){
        if(StringUtils.isNotBlank(userid)){
            if(null != customerService.getByCopenid(userid)){
                return JsonView.failureJson("此企微客户已经同步");
            }

            WechatConfig config = wechatConfigService.getWechatConfig();
            String agentId = config.getAgentid();
            String corpId = config.getCorpid();
            String corpSecret = config.getCorsecret();
            //获取客户信息，并同步到数据库
            JSONObject obj = CorpClient.getExternalContact(agentId, corpId,corpSecret, userid);
            obj = obj.getJSONObject("external_contact");

            //创建一个Customer
            Customer customer = new Customer();
            customer.setOpenid(userid);
            customer.setUserId(ShiroContext.getUserId());//当前登录用户的主键
            customer.setName(obj.getString("name"));
            customer.setNickname(obj.getString("name"));
            this.customerService.createDefaultCustomer(customer);
            return JsonView.successJson("同步成功");
        }
        return JsonView.failureJson("请先选择客户");
    }

}
