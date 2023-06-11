package com.we.scrm.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.common.util.TreeNode;
import com.we.scrm.common.util.TreeViewUtil;
import com.we.scrm.domain.WechatConfig;
import com.we.scrm.wechat.common.ErrCode;
import com.we.scrm.wechat.corp.CorpClient;
import com.we.scrm.wechat.corp.bean.CorpDepartment;
import com.we.scrm.wechat.corp.bean.MessageText;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class CorpController extends  AbstractController{

    String active = "corpDept";

    @GetMapping("/corp/department")
    public ModelAndView corpDepartment(){
        Map<String,Object> model = new HashMap<String,Object>();
        WechatConfig wechatConfig = this.wechatConfigService.getWechatConfig();
        JSONArray jsonArray = CorpClient.getDepartmentList(wechatConfig.getAgentid(),wechatConfig.getCorpid(),wechatConfig.getCorsecret(),0);
        jsonArray = prepareTreeView(jsonArray);
        model.put("departmentTree", jsonArray);
        return prepareModelAndView("corpDepartment", active, model);
    }

    @GetMapping("/corp/getUsers")
    public ModelAndView getUsers(Integer deptId){
        Map<String,Object> model = new HashMap<String,Object>();
        WechatConfig wechatConfig = this.wechatConfigService.getWechatConfig();
        JSONArray jsonArray = CorpClient.getUserSimpleList(wechatConfig.getAgentid(),wechatConfig.getCorpid(),wechatConfig.getCorsecret(),deptId);
        model.put("userList", jsonArray);
        return prepareModelAndView("corpDepartmentUser", active, model);
    }

    @PostMapping("/corp/sendMsg")
    @ResponseBody
    public  String sendMsg(String touser, String toparty, String message){
        WechatConfig wechatConfig = this.wechatConfigService.getWechatConfig();
        if(StringUtils.isBlank(message)){
            return JsonView.failureJson("消息内容不能为空");
        }
        MessageText text = new MessageText();
        text.prepareContent(message);
        text.setAgentid(Integer.parseInt(wechatConfig.getAgentid()));

        JSONObject resultJson = null;
        if(StringUtils.isNotBlank(touser)){
            text.setTouser(touser);
            resultJson = CorpClient.messageSend(wechatConfig.getAgentid(),wechatConfig.getCorpid(),wechatConfig.getCorsecret(),text);
        }else if(StringUtils.isNotBlank(toparty)){
            text.setToparty(toparty);
            resultJson = CorpClient.messageSend(wechatConfig.getAgentid(),wechatConfig.getCorpid(),wechatConfig.getCorsecret(),text);
        }else{
            return JsonView.failureJson("请选择发送的目标对象");
        }
        return JsonView.renderJson(resultJson);
    }

    JSONArray prepareTreeView(JSONArray jsonArray){
        Map<Integer,TreeNode> map = new LinkedHashMap<Integer,TreeNode>();
        for(int i = 0; i < jsonArray.size() ; i++){
            JSONObject jsObj = jsonArray.getJSONObject(i);
            TreeNode node = new TreeNode();
            node.setId(jsObj.getInteger("id"));
            node.setName(jsObj.getString("name"));
            node.setNameEn(jsObj.getString("name_en"));
            node.setOrder(jsObj.getInteger("order"));
            node.setParentId(jsObj.getInteger("parentid"));
            map.put(jsObj.getInteger("id"), node);
        }
        return TreeViewUtil.getTreeJson(map);
    }


    @PostMapping("/corp/deptmerge")
    @ResponseBody
    public String doDeptMerge(String name,Integer deptId,Integer type) {
        if(StringUtils.isBlank(name)) {
            return JsonView.failureJson("部门名称不能为空");
        }
        if(null == deptId) {
            return JsonView.failureJson("请先选择部门 or 上级部门");
        }
        WechatConfig config = wechatConfigService.getWechatConfig();
        CorpDepartment dept = new CorpDepartment();
        dept.setName(name);

        JSONObject returnObj = null;
        if(2 == type) {//修改
            dept.setId(deptId);
            returnObj = CorpClient.updateDepartment(config.getAgentid(), config.getCorpid(), config.getCorsecret(), dept);
        }else if(1 == type) {//添加
            dept.setParentid(deptId);
            returnObj = CorpClient.createDepartment(config.getAgentid(), config.getCorpid(), config.getCorsecret(), dept);
        }
        if(returnObj.getInteger(ErrCode.KEY) == 0) {
            return JsonView.successJson();
        }else {
            return JsonView.failureJson(returnObj.getString(ErrCode.MSG));
        }
    }

    @PostMapping("/corp/syncDepartment")
    @ResponseBody
    public String syncDepartment() {
        WechatConfig config = wechatConfigService.getWechatConfig();
        JSONArray jsonArray = CorpClient.getDepartmentList(config.getAgentid(), config.getCorpid(), config.getCorsecret(), 0);
        departmentService.syncDepartment(jsonArray);
        return JsonView.successJson();
    }

    @PostMapping("/corp/syncCorpUser")
    @ResponseBody
    public String syncCorpUser(String userid) {
        WechatConfig config = wechatConfigService.getWechatConfig();
        JSONObject jsObj = CorpClient.getUser(config.getAgentid(), config.getCorpid(), config.getCorsecret(), userid);
        if(null != authUserService.getByUsername(userid)){
            return JsonView.failureJson("用户已经同步，到用户管理中查看");
        }
        authUserService.syncCorpUser(jsObj);
        return JsonView.successJson();
    }

}
