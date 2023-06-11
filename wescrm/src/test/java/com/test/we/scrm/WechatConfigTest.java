package com.test.we.scrm;

import com.we.scrm.Application;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.service.WechatConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class WechatConfigTest {

    @Autowired
    private WechatConfigService wechatConfigService;

    @Test
    public void getWechatConfig(){
        WechatConfig wechatConfig = wechatConfigService.getWechatConfig();
        System.out.print(wechatConfig.getAppid());
    }
}
