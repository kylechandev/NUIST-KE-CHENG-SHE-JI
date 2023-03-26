package com.fht.service.impl;

import com.fht.dao.CompetitionMapper;
import com.fht.dao.CompetitionPeopleMapper;
import com.fht.dao.UserCompetitionMapper;
import com.fht.entity.Competition;
import com.fht.entity.CompetitionPeople;
import com.fht.entity.CompetitionTeamPeople;
import com.fht.entity.UserCompetition;
import com.fht.service.ProjectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private CompetitionMapper competitionMapper;
    @Autowired
    private UserCompetitionMapper userCompetitionMapper;
    @Autowired
    private CompetitionPeopleMapper competitionPeopleMapper;
    //**********************************************项目管理**********************************************
    //添加
    @Override
    public int addCompetition(Competition competition) {
        return competitionMapper.insert(competition);
    }

    //分页查询
    @Override
    public Page<Competition> getCompetition(int page, int limit, Competition competition) {
        Page<Competition> pageinfo = PageHelper.startPage(page, limit);
        List<Competition> info = competitionMapper.selectAll(competition);
        return pageinfo;
    }
    //查询项目根据时间升序
    @Override
    public Page<Competition> getByTimeAsc(int page, int limit) {
        Page<Competition> pageinfo = PageHelper.startPage(page, limit);
        List<Competition> info = competitionMapper.getByTimeAsc();
        return pageinfo;
    }
    //查询根据id
    @Override
    public Competition getById(Competition competition) {
        Competition info = competitionMapper.selectByPrimaryKey(competition.getId());
        return info;
    }
    //批量删除
    @Override
    public int delCompetitions(String[] ids) {
        return competitionMapper.batchDelete(ids);
    }
    //更新
    @Override
    public int updateCompetition(Competition role) {
        return competitionMapper.updateByPrimaryKey(role);
    }


    //**********************************************报名管理和成绩管理**********************************************
    //分页查询全部信息
    @Override
    public Page<UserCompetition> getUserCompetition(int page, int limit, UserCompetition userCompetition, Integer currentSessionId) {
        Page<UserCompetition> pageinfo = PageHelper.startPage(page, limit);
        List<UserCompetition> info = userCompetitionMapper.selectAllExtension(userCompetition,currentSessionId);
        return pageinfo;
    }
    //批量删除
    @Override
    public int delUserCompetitions(String[] ids,Integer currentSessionId) {
        return userCompetitionMapper.batchDelete(ids,currentSessionId);
    }
    //更新
    @Override
    public int updateUserCompetition(UserCompetition userCompetition,Integer currentSessionId) {
        return userCompetitionMapper.updateByPrimaryKey(userCompetition,currentSessionId);
    }
    //添加
    @Override
    public int addUserCompetition(UserCompetition userCompetition,Integer currentSessionId) {
        Integer limitCapita = competitionMapper.selectByPrimaryKey(userCompetition.getCid()).getCapita();
        Integer applyCapita = Integer.valueOf(userCompetitionMapper.getApplyCapita(userCompetition,currentSessionId).getApplyCapita());
        System.out.println(limitCapita);
        System.out.println(applyCapita);
        if (applyCapita>=limitCapita)
            throw new RuntimeException("超过项目限制人数");
        return userCompetitionMapper.insert(userCompetition,currentSessionId);
    }
    //查询详细信息
    @Override
    public UserCompetition getUserCompetitionDetail(UserCompetition userCompetition,Integer currentSessionId) {
        UserCompetition info1 = userCompetitionMapper.getApplyCapita(userCompetition,currentSessionId);
        UserCompetition info2 = userCompetitionMapper.getIsApply(userCompetition,currentSessionId);
        info1.setIsApply(info2.getIsApply());
        return info1;
    }
    //团队项目更新
    @Override
    public void updateByTeamNameAndCid(UserCompetition userCompetition,Integer currentSessionId) {
        String ids = userCompetitionMapper.getIdByTeamNameAndCid(userCompetition.getTeam(),userCompetition.getCid(),currentSessionId);
        UserCompetition temp = new UserCompetition();
        temp.setGrade(userCompetition.getGrade());
        temp.setScore(userCompetition.getScore());
        for (String item : ids.split(",")) {
            temp.setId(Integer.valueOf(item));
            userCompetitionMapper.updateByPrimaryKey(temp, currentSessionId);
        }
    }
    //分页查询  全部带有报名团队信息的条目
    @Override
    public Page<UserCompetition> getAllWithPersonalInfo(int page, int limit, UserCompetition userCompetition,Integer currentSessionId) {
        Page<UserCompetition> pageinfo = PageHelper.startPage(page, limit);
        List<UserCompetition> info = userCompetitionMapper.getAllWithPersonalInfo(userCompetition,currentSessionId);
        return pageinfo;
    }
    //分页查询  全部带有个人报名信息的条目
    @Override
    public Page<UserCompetition> getAllWithTeamInfo(int page, int limit, UserCompetition userCompetition,Integer currentSessionId) {
        Page<UserCompetition> pageinfo = PageHelper.startPage(page, limit);
        List<UserCompetition> info = userCompetitionMapper.getAllWithTeamInfo(userCompetition,currentSessionId);
        return pageinfo;
    }
    //**********************************************查看已报名信息**********************************************
    //查询报名信息
    @Override
    public Page<UserCompetition> getAllByUid(int page, int limit, UserCompetition uid,Integer currentSessionId) {
        Page<UserCompetition> pageinfo = PageHelper.startPage(page, limit);
        List<UserCompetition> data = userCompetitionMapper.getAllByUid(uid,currentSessionId);
        return pageinfo;
    }
    //**********************************************人员列表**********************************************
    //查询报名信息
    @Override
    public Page<CompetitionPeople> getAllPeople(int page, int limit,Integer currentSessionId) {
        Page<CompetitionPeople> pageinfo = PageHelper.startPage(page, limit);
        List<CompetitionPeople> all = competitionPeopleMapper.getAll();
        return pageinfo;
    }
    //**********************************************团队人员列表**********************************************
    //查询报名信息
    @Override
    public Page<CompetitionTeamPeople> getAllTeamPeople(int page, int limit,Integer currentSessionId) {
        Page<CompetitionTeamPeople> pageinfo = PageHelper.startPage(page, limit);
        List<CompetitionTeamPeople> all = competitionPeopleMapper.getAllTeamPeople();
        return pageinfo;
    }
}
