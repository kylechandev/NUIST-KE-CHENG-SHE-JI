package com.fht.service.impl;

import com.fht.entity.Competition;
import com.fht.service.SessionService;
import com.fht.dao.SessionDao;
import com.fht.entity.Session;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Session)表服务实现类
 *
 * @author halt
 * @since 2020-04-11 11:56:08
 */
@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDao sessionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Session queryById(Integer id) {
        return this.sessionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public Page<Session> queryAllByLimit(int page, int limit) {
        Page<Session> pageinfo = PageHelper.startPage(page, limit);
        List<Session> info = sessionDao.queryAll();
        return pageinfo;
    }

    /**
     * 获取运动会当前届
     * @return
     */
    @Override
    public Session getCurrentSessionOfSportsMeeting() {
        Session session =new Session();
        session.setStatus(1);
        List<Session> info = sessionDao.queryAllByCondition(session);
        return info.get(0);
    }
    /**
     * 设置运动会当前届
     * @return
     */
    @Override
    public boolean setCurrentSessionOfSportsMeeting(Integer id) {
        sessionDao.initSession();
        Session session =new Session();
        session.setStatus(1);
        session.setId(id);
        int info = sessionDao.update(session);
        return sessionDao.update(session)>0;
    }

    /**
     * 新增数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session insert(Session session) {
        this.sessionDao.insert(session);
        return session;
    }

    /**
     * 修改数据
     *
     * @param session 实例对象
     * @return 实例对象
     */
    @Override
    public Session update(Session session) {
        this.sessionDao.update(session);
        return this.queryById(session.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sessionDao.deleteById(id) > 0;
    }
}