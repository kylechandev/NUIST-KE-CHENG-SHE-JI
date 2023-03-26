package com.fht.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Value("${shiro.web.enabled}")
    Boolean shiroIsEnable;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        //判断shiro是否已经启用
        if (shiroIsEnable && SecurityUtils.getSubject().isAuthenticated())
            return "redirect:/main";
        return "index";
    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String defaultLogin() {
        return "main";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news() {
        return "news";
    }

    //匹配fragment下的所有页面
    @RequestMapping(value = "/page/{path}", method = RequestMethod.GET)
    public String page(@PathVariable String path) {
        return "fragment/" + path;
    }

}
