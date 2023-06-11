package com.we.scrm.wechat.mp;

import com.we.scrm.wechat.common.AccessToken;
import com.we.scrm.wechat.common.util.WechatCacheUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class MpClient {

    public static AccessToken getAccessToken(String appid, String appsecret){
        AccessToken accessToken = WechatCacheUtil.getMpAccessToken(appid);
        if(null == accessToken){
            String url = String.format(MpApi.ACCESS_TOKEN, appid, appsecret);
            accessToken = new RestTemplate().getForObject(url, AccessToken.class);
            accessToken.setCreateAt(System.currentTimeMillis()/1000);
            WechatCacheUtil.setMpAccessToken(appid, accessToken);
        }
        return accessToken;
    }

    //获取用户列表
    public static JSONArray getOpenidArray(String appId, String appSecret, String nextOpenid) {
        AccessToken accessToken = getAccessToken(appId, appSecret);
        String url = "";
        if(StringUtils.isBlank(nextOpenid)){
            url = String.format(MpApi.GET_OPENID_ARRAY, accessToken.getAccess_token());
        }else{
            url = String.format(MpApi.GET_OPENID_ARRAY + "&next_openid=%s", accessToken.getAccess_token(), nextOpenid);
        }
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        if(jsObj.containsKey("data")){
            JSONArray openidArr = jsObj.getJSONObject("data").getJSONArray("openid");
            return openidArr;
        }
        return null;
    }

    //获取单个用户信息
    public static JSONObject getUser(String appId, String appSecret, String openid) {
        AccessToken accessToken = getAccessToken(appId, appSecret);
        String url = String.format(MpApi.GET_USER_INFO, accessToken.getAccess_token(), openid);
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        return jsObj;
    }

}
