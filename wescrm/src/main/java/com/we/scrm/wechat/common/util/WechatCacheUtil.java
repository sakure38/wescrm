package com.we.scrm.wechat.common.util;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.we.scrm.wechat.common.AccessToken;

public class WechatCacheUtil {

    //公众号token
    static final String MP_ACCESS_TOKEN_KEY = "_mp_access_token_";
    //企业微信token
    static final String CORP_ACCESS_TOKEN_KEY = "_corp_access_token_";
    //小程序token
    static final String MAPP_ACCESS_TOKEN_KEY = "_mapp_access_token_";

    //小程序openid对应的session key
    static final String OPENID_SESSION_KEY = "_openid_session_key_";

    //模拟一个内存缓存，存储token
    private static Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    //模拟一个内存缓存，存储openid对应的sessionkey
    private static Map<String, String> sessionCache = new ConcurrentHashMap<String, String>();

    public static AccessToken getMpAccessToken(String appId) {
        String cacheKey = MP_ACCESS_TOKEN_KEY + appId;
        if(cache.get(cacheKey) != null) {
            AccessToken accessToken = (AccessToken)cache.get(cacheKey);
            long now = System.currentTimeMillis()/1000;
            //时间没过期
            if(now - accessToken.getCreateAt() < accessToken.getExpires_in()) {
                return accessToken;
            }
        }
        return null;
    }

    public static void setMpAccessToken(String appId, AccessToken accessToken) {
        String cacheKey = MP_ACCESS_TOKEN_KEY + appId;
        cache.put(cacheKey, accessToken);
    }

    //企业微信：每个应用(agent)的token
    public static AccessToken getCorpAccessToken(String agentId, String corpId) {
        String cacheKey = CORP_ACCESS_TOKEN_KEY + agentId + "_" + corpId;
        if(cache.get(cacheKey) != null) {
            AccessToken accessToken = (AccessToken)cache.get(cacheKey);
            long now = System.currentTimeMillis()/1000;
            //时间没过期
            if(now - accessToken.getCreateAt() < accessToken.getExpires_in()) {
                return accessToken;
            }
        }
        return null;
    }

    public static void setCorpAccessToken(String agentId, String corpId, AccessToken accessToken) {
        String cacheKey = CORP_ACCESS_TOKEN_KEY + agentId + "_" + corpId;
        cache.put(cacheKey, accessToken);
    }


    //微信小程序缓存session_key
    public static String getSessionKey(String openid) {
        String cacheKey = openid + OPENID_SESSION_KEY;
        return sessionCache.get(cacheKey);
    }

    public static void setSessionKey(String openid, String sessionKey) {
        String cacheKey = openid + OPENID_SESSION_KEY;
        sessionCache.put(cacheKey, sessionKey);
    }



}
