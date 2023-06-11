package com.we.scrm.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.we.scrm.common.enums.UserStatusEnum;
import com.we.scrm.common.page.Pagination;
import com.we.scrm.common.shiro.ShiroContext;
import com.we.scrm.common.util.JsonView;
import com.we.scrm.domain.AuthUser;

@Controller
public class AuthUserController extends  AbstractController {

    String active = "authUser";

    @RequestMapping("/authUser/page")
    public ModelAndView authUserPage(AuthUser queryEntity, Pagination<AuthUser> page) {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("queryEntity", queryEntity);
        page = this.authUserService.queryPage(queryEntity, page);
        model.put("page", page);
        model.put("status", UserStatusEnum.toMap());
        return prepareModelAndView("authUserPage", active, model);
    }

    @GetMapping(value = "/authUser/get")
    @ResponseBody
    public String getUser(Long id){
        AuthUser user = authUserService.getById(id);
        return JsonView.successJson(user);
    }

    @PostMapping(value = "/authUser/merge")
    @ResponseBody
    public String mergeUser(AuthUser entity){
        entity.setUsername(null);
        entity.setName(null);
        authUserService.update(entity);
        return JsonView.successJson();
    }

    @GetMapping("/login")
    public ModelAndView login(Integer f) {
        ModelAndView mv = new ModelAndView("authLogin");
        if(Integer.valueOf(1).equals(f)) {
            mv.addObject("errmsg", "注册成功，请登录");
        }else if(Integer.valueOf(2).equals(f)) {
            mv.addObject("errmsg", "用户名或密码错");
        }
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("identiryCode") String identiryCode,
            HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("authLogin");

        if(password.length() != 64){
            mv.addObject("errmsg", "密码错误");
            return mv;
        }
        //验证码
        Object sessionCode = ShiroContext.getSession().getAttribute(ShiroContext.IDENTIFY_CODE);
        if(null == sessionCode || !identiryCode.equalsIgnoreCase(sessionCode.toString())){
            mv.addObject("errmsg", "验证码错误");
            return mv;
        }
        //校验密码
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            return new ModelAndView("redirect:/login?f=2");
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/logout")
    public ModelAndView logout(Integer f) {
        ShiroContext.logout();
        return new ModelAndView("redirect:/");
    }

}
