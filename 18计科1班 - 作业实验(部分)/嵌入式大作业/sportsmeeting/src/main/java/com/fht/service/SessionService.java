package com.fht.service;

import com.fht.entity.Session;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * (Session)表服务接口
 *
 * @author halt
 * @since 2020-04-11 11:56:08
 */
public interface SessionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Session queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Page<Session> queryAllByLimit(int page, int limit);

    Session getCurrentSessionOfSportsMeeting();

    boolean setCurrentSessionOfSportsMeeting(Integer id);

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    Session insert(Session session);

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    Session update(Session session);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}