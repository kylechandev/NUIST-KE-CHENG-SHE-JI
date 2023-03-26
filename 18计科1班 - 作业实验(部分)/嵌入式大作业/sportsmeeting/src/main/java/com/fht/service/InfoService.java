package com.fht.service;

import com.fht.entity.Competition;
import com.github.pagehelper.Page;


public interface InfoService {

    //**********************************************成绩名次统计**********************************************
    //添加
    Page<Competition> getAllAndScore(int page, int limit, int isTeam);

    //**********************************************团队成绩名次统计**********************************************
            //添加  未完成
    Page<Competition> getTeamScore(int page, int limit);
}
