package com.fht.service;

import com.fht.entity.Competition;
import com.fht.entity.CompetitionPeople;
import com.fht.entity.CompetitionTeamPeople;
import com.fht.entity.UserCompetition;
import com.github.pagehelper.Page;
;

public interface ProjectService {

    //**********************************************项目管理**********************************************
    //添加
    int addCompetition(Competition role);

    //分页查询用户角色
    Page<Competition> getCompetition(int page, int limit, Competition competition);

    //查询项目根据时间升序
    Page<Competition> getByTimeAsc(int page, int limit);

    //查询根据id
    Competition getById(Competition competition);

    //批量删除
    int delCompetitions(String[] ids);

    //更新
    int updateCompetition(Competition role);



    //**********************************************报名管理**********************************************
    //添加
    int addUserCompetition(UserCompetition userCompetition,Integer currentSessionId);

    //分页查询用户角色
    Page<UserCompetition> getUserCompetition(int page, int limit, UserCompetition userCompetition,Integer currentSessionId);

    //查询详细信息
    UserCompetition getUserCompetitionDetail(UserCompetition userCompetition,Integer currentSessionId);

    //分页查询  全部带有报名团队信息的条目
    Page<UserCompetition> getAllWithPersonalInfo(int page, int limit, UserCompetition userCompetition,Integer currentSessionId);

    //分页查询  全部带有个人报名信息的条目
    Page<UserCompetition> getAllWithTeamInfo(int page, int limit, UserCompetition userCompetition,Integer currentSessionId);

    //批量删除
    int delUserCompetitions(String[] ids,Integer currentSessionId);

    //更新
    int updateUserCompetition(UserCompetition role,Integer currentSessionId);
    //团队项目更新
    void updateByTeamNameAndCid(UserCompetition userCompetition,Integer currentSessionId);
    //**********************************************查看已报名信息**********************************************
    //查询报名信息
    Page<UserCompetition> getAllByUid(int page, int limit, UserCompetition uid,Integer currentSessionId);


    //**********************************************人员列表**********************************************
    //查询报名信息
    Page<CompetitionPeople> getAllPeople(int page, int limit,Integer currentSessionId);

    //**********************************************团队人员列表**********************************************
    //查询报名信息
    Page<CompetitionTeamPeople> getAllTeamPeople(int page, int limit,Integer currentSessionId);
}
