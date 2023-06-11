package com.we.scrm.web;

import com.we.scrm.common.util.IdUtil;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.WechatConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WechatConfigController extends AbstractController {

    String active = "wechatConfig";

    @GetMapping("/wechat/config")
    public ModelAndView config(){
        Map<String,Object> model = new HashMap<String,Object>();
        WechatConfig wechatConfig = wechatConfigService.getWechatConfig();
        if(null == wechatConfig){
            wechatConfig = new WechatConfig();
        }
        model.put("entity", wechatConfig);
        return prepareModelAndView("wechatConfig", active, model);
    }

    @PostMapping("/wechat/mergeConfig")
    @ResponseBody
    public String mergeConfig(WechatConfig wechatConfig){
        if(null == wechatConfig.getId()){
            wechatConfig.setWid(IdUtil.nextStringId());
            this.wechatConfigService.create(wechatConfig);
        }else {
            this.wechatConfigService.update(wechatConfig);
        }
        return JsonView.successJson(wechatConfig);
    }

}

