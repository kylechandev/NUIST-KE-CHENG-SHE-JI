package com.fht.controller;

import com.fht.common.bean.R;
import com.fht.entity.User;
import com.fht.service.AccountService;
import com.fht.service.SessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;


/*
 * 登陆的controller
 */
@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R login(String username, String password,HttpSession session) {
        // 1.验证用户输入 出现异常由全局异常Controller处理
        if (username==null || password == null || username=="" || password=="" )
            throw new AccountException("用户名和密码不能为空");
        // 2.获得当前Subject登录
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(false);
        currentUser.login(token);
        // 3.单用户登录判断   登录成功，清除在线用户session  getPrincipal获取存入的身份信息
        User user =(User)currentUser.getPrincipal();
        singleUseLogin(user.getId().toString());
        // 4.设置当前用户session信息 获取当前运动会届数
         com.fht.entity.Session currentSessionOfSportsMeeting = sessionService.getCurrentSessionOfSportsMeeting();
        session.setAttribute("id",user.getId());
        session.setAttribute("username",user.getUsername());
        session.setAttribute("realName",user.getRealname());
        session.setAttribute("currentSession",currentSessionOfSportsMeeting.getDescription());
        session.setAttribute("currentSessionId",currentSessionOfSportsMeeting.getId());
        session.setAttribute("roles",((User) currentUser.getPrincipal()).getUserRole().getDescription());
        return R.ok();
    }
    //退出系统
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
    /**
     * @功能描述：   单用户登录,根据当前用户名对比session中的id
     *               清除当前用户以前登录时保存的session会话
     * @param id
     */
    public static void singleUseLogin(String id){
        // 1.获取shiro的sessionManager   需要在shiro配置中定义MemorySessionDAO和DefaultWebSessionManager管理
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        // 2.获取所有已登录用户的session列表
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        if (sessions.size() > 0) {
            System.out.println("仅允许单用户登录,开始判断遗留用户信息~"+sessions);
            for(Session onlineSession:sessions){
                // 3. 清除当前用户以前登录时保存的session会话
                System.out.println(onlineSession.getAttribute("id"));
                System.out.println(id);
                if (onlineSession.getAttribute("id")!=null&&id.equals(onlineSession.getAttribute("id").toString())) {
                    sessionManager.getSessionDAO().delete(onlineSession);
                    System.out.println("清理用户["+onlineSession.getAttribute("username")+"],SessionId为["+onlineSession.getId()+"]的Session信息!");
                }
            }
        } else {
            System.out.println("无可清理用户信息~");
        }
    }
}