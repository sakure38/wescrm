package com.we.scrm.wechat.mp;

public class MpApi {

    //token接口
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    //获取账号用户信息
    public static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    //获取账号用户列表
    public static final String GET_OPENID_ARRAY = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";



}
