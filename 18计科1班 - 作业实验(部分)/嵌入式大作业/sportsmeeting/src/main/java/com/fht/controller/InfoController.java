package com.fht.controller;


import com.fht.common.bean.R;
import com.fht.entity.Competition;
import com.fht.service.InfoService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    //*************************************比赛成绩信息*************************************
    //分页查询
    @RequestMapping(value = "/score/get", method = RequestMethod.GET)
    public R get(int page, int limit, Competition competition) {
        Page<Competition> pageInfo = infoService.getAllAndScore(page, limit,0);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }
    @RequestMapping(value = "/teamScore/get", method = RequestMethod.GET)
    public R getteamScore(int page, int limit, Competition competition) {
        Page<Competition> pageInfo = infoService.getTeamScore(page, limit);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }
}
