package com.we.scrm.wechat.corp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.wechat.common.AccessToken;
import com.we.scrm.wechat.common.util.WechatCacheUtil;
import com.we.scrm.wechat.corp.bean.CorpDepartment;
import com.we.scrm.wechat.corp.bean.MessageText;
import org.springframework.web.client.RestTemplate;

public class CorpClient {

    public static AccessToken getAccessToken(String agentId, String corpId, String corpSecret){
        AccessToken accessToken = WechatCacheUtil.getCorpAccessToken(agentId, corpId);
        if(null == accessToken){
            String url = String.format(CorpApi.ACCESS_TOKEN, corpId, corpSecret);
            accessToken = new RestTemplate().getForObject(url, AccessToken.class);
            accessToken.setCreateAt(System.currentTimeMillis()/1000);
            WechatCacheUtil.setCorpAccessToken(agentId, corpId, accessToken);
        }
        return accessToken;
    }

    //其他接口
    public static JSONArray getDepartmentList(String agentId, String corpId, String corpSecret, Integer id){
        AccessToken accessToken = getAccessToken(agentId, corpId,corpSecret);
        String url = "";
        if(null != id && id > 0){
            url = String.format(CorpApi.DEPARTMENT_LIST + "&id=%s", accessToken.getAccess_token(), id.toString());
        }else{
            url = String.format(CorpApi.DEPARTMENT_LIST , accessToken.getAccess_token());
        }
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        if(jsObj.containsKey("department")){
            JSONArray department = jsObj.getJSONArray("department");
            return department;
        }
        return null;
    }

    //获取员工的接口
    public static JSONArray getUserSimpleList(String agentId, String corpId, String corpSecret, Integer deptId){
        AccessToken accessToken = getAccessToken(agentId, corpId,corpSecret);
        String url = String.format(CorpApi.USER_SIMPLELIST , accessToken.getAccess_token(), deptId);
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        if(jsObj.containsKey("userlist")){
            return jsObj.getJSONArray("userlist");
        }
        return null;
    }

    //发送消息
    public static JSONObject messageSend(String agentId, String corpId, String corpSecret, MessageText text){
        AccessToken accessToken = getAccessToken(agentId, corpId,corpSecret);
        String url = String.format(CorpApi.MESSAGE_SEND , accessToken.getAccess_token());
        JSONObject jsObj = new RestTemplate().postForObject(url, JSONObject.toJSON(text), JSONObject.class);
        return jsObj;
    }

    //创建部门
    public static JSONObject createDepartment(String agentId, String corpId, String corpSecret, CorpDepartment department) {
        AccessToken accessToken = getAccessToken(agentId, corpId, corpSecret);
        String url = String.format(CorpApi.DEPARTMENT_CREATE, accessToken.getAccess_token());
        JSONObject jsObj = new RestTemplate().postForObject(url, JSONObject.toJSON(department), JSONObject.class);
        return jsObj;
    }

    //修改部门
    public static JSONObject updateDepartment(String agentId, String corpId, String corpSecret, CorpDepartment department) {
        AccessToken accessToken = getAccessToken(agentId, corpId, corpSecret);
        String url = String.format(CorpApi.DEPARTMENT_UPDATE, accessToken.getAccess_token());
        JSONObject jsObj = new RestTemplate().postForObject(url, JSONObject.toJSON(department), JSONObject.class);
        return jsObj;
    }

    //获取员工用户信息
    public static JSONObject getUser(String agentId, String corpId, String corpSecret, String userid) {
        AccessToken accessToken = getAccessToken(agentId, corpId, corpSecret);
        String url = String.format(CorpApi.USER_GET, accessToken.getAccess_token(), userid);
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        return jsObj;
    }

    //获取私域客户列表
    public static JSONArray getExternalContactList(String agentId, String corpId, String corpSecret, String userid) {
        AccessToken accessToken = getAccessToken(agentId, corpId, corpSecret);
        String url = String.format(CorpApi.EXTERNAL_CONTACT_LIST, accessToken.getAccess_token(), userid);
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        if(jsObj.containsKey("external_userid")){
            return jsObj.getJSONArray("external_userid");
        }
        return null;
    }

    //获取私域客户信息, userid 是私域客户的openid
    public static JSONObject getExternalContact(String agentId, String corpId, String corpSecret, String userid) {
        AccessToken accessToken = getAccessToken(agentId, corpId, corpSecret);
        String url = String.format(CorpApi.EXTERNAL_CONTACT_GET, accessToken.getAccess_token(), userid);
        JSONObject jsObj = new RestTemplate().getForObject(url, JSONObject.class);
        return jsObj;
    }

}
