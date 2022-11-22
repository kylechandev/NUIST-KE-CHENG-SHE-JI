package com.fht.controller;

import com.fht.common.bean.R;
import com.fht.entity.Competition;
import com.fht.entity.Session;
import com.fht.service.SessionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Session)表控制层
 *
 * @author halt
 * @since 2020-04-11 11:56:09
 */
@RestController
@RequestMapping("/session")
public class SessionController {
    /**
     * 服务对象
     */
    @Autowired
    private SessionService sessionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public Session selectOne(@PathVariable Integer id) {
        return this.sessionService.queryById(id);
    }
    @GetMapping("/get")
    public R select(int page, int limit) {
        Page<Session> sessions = this.sessionService.queryAllByLimit(page,limit);
        return R.ok().put("data",sessions).put("count", sessions.getTotal());
    }

    @PostMapping("/del/{id}")
    public R del(@PathVariable Integer id) {
        this.sessionService.deleteById(id);
        return R.ok();
    }
    @PostMapping("/save")
    public R update(Session session) {
        this.sessionService.update(session);
        return R.ok();
    }

    @PostMapping("/add")
    public R add(Session session) {
        this.sessionService.insert(session);
        return R.ok();
    }
    @PostMapping("/current/{id}")
    public R add(@PathVariable Integer id) {
        sessionService.setCurrentSessionOfSportsMeeting(id);
        return R.ok();
    }

}