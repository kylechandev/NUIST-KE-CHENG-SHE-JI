package com.fht.dao;

import com.fht.entity.Session;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Session)表数据库访问层
 *
 * @author halt
 * @since 2020-04-11 11:56:06
 */
public interface SessionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Session queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Session> queryAll();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param session 实例对象
     * @return 对象列表
     */
    List<Session> queryAllByCondition(Session session);

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 影响行数
     */
    int insert(Session session);

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 影响行数
     */
    int update(Session session);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 初始化运动会届数
     * @return
     */
    int initSession();

}