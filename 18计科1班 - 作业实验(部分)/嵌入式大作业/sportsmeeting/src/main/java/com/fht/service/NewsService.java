package com.fht.service;

import com.fht.entity.News;
import com.github.pagehelper.Page;

public interface NewsService {
    //添加
    int addNews(News news);

    //分页查询权限
    Page<News> getNews(int page, int limit);

    //批量删除
    int delNewss(String[] ids);

    //更新
    int updateNews(News news);

    //根据id查询
    News getById(Integer id);
}
