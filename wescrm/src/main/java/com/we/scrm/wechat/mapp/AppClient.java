package com.we.scrm.wechat.mapp;

import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.util.BizDataCrypt;

public class AppClient {

    //登录凭证校验
    public static JSONObject jscode2session(String mappId, String mappSecret,String jsCode){
        String url = String.format(AppApi.JSCODE2SESSION, mappId, mappSecret, jsCode);
        String str = new RestTemplate().getForObject(url, String.class);
        return JSONObject.parseObject(str);
    }

    //步数的加密数据进行解密
    public static JSONObject encryptedStepList(String mappId, String mappSecret,String code, String encryptedData, String iv){
        JSONObject jscode = jscode2session(mappId, mappSecret, code);
        if(null != jscode &&jscode.containsKey("session_key")){
            String openid = jscode.getString("openid");
            String sessionkey = jscode.getString("session_key");
            JSONObject returnObj = BizDataCrypt.decrypt(encryptedData, sessionkey, iv);
            if(null != returnObj){
                returnObj.put("openid", openid);
                return returnObj;
            }
        }
        return null;
    }

}
