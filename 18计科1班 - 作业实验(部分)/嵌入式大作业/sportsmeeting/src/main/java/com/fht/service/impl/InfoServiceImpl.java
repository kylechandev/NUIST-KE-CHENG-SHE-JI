package com.fht.service.impl;

import com.fht.dao.CompetitionMapper;
import com.fht.dao.CompetitionPeopleMapper;
import com.fht.entity.Competition;
import com.fht.service.InfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InfoServiceImpl implements InfoService {
    @Autowired
    private CompetitionMapper competitionMapper;
    @Autowired
    private CompetitionPeopleMapper competitionPeopleMapper;

    //**********************************************成绩名次统计**********************************************
    //添加
    @Override
    public Page<Competition> getAllAndScore(int page, int limit,int isTeam) {
        Page<Competition> pageinfo = PageHelper.startPage(page, limit);
        competitionMapper.getAllAndScore(isTeam);
        return pageinfo;
    }
//**********************************************团队成绩名次统计**********************************************
        //添加  未完成
        @Override
        public Page<Competition> getTeamScore(int page, int limit) {
            Page<Competition> pageinfo = PageHelper.startPage(page, limit);
            competitionMapper.getAllAndScore(null);
            return pageinfo;
    }


}
