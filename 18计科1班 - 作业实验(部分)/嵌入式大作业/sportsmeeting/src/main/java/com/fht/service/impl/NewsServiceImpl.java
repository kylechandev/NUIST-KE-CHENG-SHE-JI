package com.fht.service.impl;

import com.fht.dao.NewsMapper;
import com.fht.entity.News;
import com.fht.service.NewsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    //添加
    @Override
    public int addNews(News news) {
        return newsMapper.insert(news);
    }

    //分页查询权限
    @Override
    public Page<News> getNews(int page, int limit) {
        Page<News> pageinfo = PageHelper.startPage(page, limit);
        List<News> info = newsMapper.selectAll();
        return pageinfo;
    }

    //批量删除
    @Override
    public int delNewss(String[] ids) {
        return newsMapper.batchDelete(ids);
    }

    //更新
    @Override
    public int updateNews(News news) {
        return newsMapper.updateByPrimaryKey(news);
    }
    //根据id查询
    @Override
    public News getById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }

}
