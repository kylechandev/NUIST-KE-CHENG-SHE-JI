package com.fht.dao;

import java.util.List;
import com.fht.entity.News;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    News selectByPrimaryKey(Integer id);

    List<News> selectAll();

    int updateByPrimaryKey(News record);

    int batchDelete(String[] ids);
}