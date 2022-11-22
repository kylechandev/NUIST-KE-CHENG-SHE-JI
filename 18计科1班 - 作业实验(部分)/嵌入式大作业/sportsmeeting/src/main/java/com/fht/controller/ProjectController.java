package com.fht.controller;


import com.fht.common.bean.R;
import com.fht.entity.Competition;
import com.fht.service.ProjectService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    //*************************************比赛项目管理*************************************
    //分页查询
    @RequestMapping(value = "/competition/get", method = RequestMethod.GET)
    public R getCompetition(int page, int limit, Competition competition) {
        Page<Competition> pageInfo = projectService.getCompetition(page, limit, competition);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }
    //查询项目根据时间升序
    @RequestMapping(value = "/competition/getByTimeAsc", method = RequestMethod.GET)
    public R getByTimeAsc(int page, int limit) {
        Page<Competition> info = projectService.getByTimeAsc(page,limit);
        return R.ok().put("data", info).put("count", info.getTotal());
    }
    //查询项目根据id
    @RequestMapping(value = "/competition/getById", method = RequestMethod.GET)
    public R getById(Competition competition) {
        Competition info = projectService.getById(competition);
        return R.ok().put("data", info);
    }
    //增加
    @RequestMapping(value = "/competition/add", method = RequestMethod.POST)
    public R addCompetition(@Valid Competition competition, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String info = bindingResult.getFieldError().getDefaultMessage();
            return R.error().put("msg", info);
        }
        int flag = projectService.addCompetition(competition);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //删除
    @RequestMapping(value = "/competition/del", method = RequestMethod.POST)
    public R addCompetition(String ids) {
        int flag = projectService.delCompetitions(ids.split(","));
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/competition/save", method = RequestMethod.POST)
    public R updateCompetition(Competition competition) {
        System.out.println(competition);
        int flag = projectService.updateCompetition(competition);
        if (flag == 0)
            return R.error();
        return R.ok();
    }


}
