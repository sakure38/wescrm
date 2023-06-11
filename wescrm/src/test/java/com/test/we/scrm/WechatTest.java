package com.test.we.scrm;

import com.we.scrm.Application;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.service.WechatConfigService;
import com.we.scrm.wechat.common.AccessToken;
import com.we.scrm.wechat.corp.CorpClient;
import com.we.scrm.wechat.mp.MpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class WechatTest {

    @Autowired
    private WechatConfigService wechatConfigService;

//    @Test
    public void getCorpAccessToken(){
        WechatConfig wechatConfig = wechatConfigService.getWechatConfig();
        AccessToken token = CorpClient.getAccessToken(wechatConfig.getAgentid(),wechatConfig.getCorpid(),wechatConfig.getCorsecret());
        if(null != token){
            System.out.println(token.getAccess_token());
        }
    }

    @Test
    public void getMpAccessToken(){
        WechatConfig wechatConfig = wechatConfigService.getWechatConfig();
        AccessToken token = MpClient.getAccessToken(wechatConfig.getAppid(), wechatConfig.getAppsecret());
        if(null != token){
            System.out.println(token.getAccess_token());
        }
    }
}
