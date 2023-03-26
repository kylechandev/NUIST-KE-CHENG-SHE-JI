package com.fht.controller;


import com.fht.common.bean.R;
import com.fht.entity.News;
import com.fht.service.NewsService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class NewsController {
    @Autowired
    NewsService newsService;
    //*************************************新闻公告管理*************************************
    //分页查询
    @RequestMapping(value = "/news/get", method = RequestMethod.GET)
    public R getNews(int page, int limit, News news) {
        Page<News> pageInfo = newsService.getNews(page, limit);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //删除
    @RequestMapping(value = "/news/del", method = RequestMethod.POST)
    public R delNews(String ids) {
        int flag = newsService.delNewss(ids.split(","));
        if (flag == 0)
            return R.error();
        return R.ok();
    }
    //增加
    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public R addNews(News news, HttpSession session) {
        news.setUid((Integer) session.getAttribute("id"));
        int flag = newsService.addNews(news);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/news/save", method = RequestMethod.POST)
    public R updateNews(News news) {
        System.out.println(news);
        int flag = newsService.updateNews(news);
        if (flag == 0)
            return R.error();
        return R.ok();
    }
    //查询根据id
    @RequestMapping(value = "/news/getById", method = RequestMethod.GET)
    public R getById(Integer id) {
        News info = newsService.getById(id);
        System.out.println(info);
        return R.ok().put("data",info);
    }



}
