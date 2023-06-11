package com.we.scrm.wechat.corp;

public class CorpApi {

    //token接口
    public static final String ACCESS_TOKEN = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    //获取部门接口
    public static final String DEPARTMENT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s";

    //获取部门员工
    public static  final String USER_SIMPLELIST = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=%s&department_id=%d&fetch_child=1";

    //发送消息
    public static  final String MESSAGE_SEND ="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";

    //添加部门接口
    public static final String DEPARTMENT_CREATE = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=%s";

    //修改部门接口
    public static final String DEPARTMENT_UPDATE = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=%s";

    //获取员工用户
    public static final String USER_GET = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s";

    //获取私域客户列表
    public static final String EXTERNAL_CONTACT_LIST = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?access_token=%s&userid=%s";

    //获取私域客户信息
    public static final String EXTERNAL_CONTACT_GET = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=%s&external_userid=%s&cursor=";

}
