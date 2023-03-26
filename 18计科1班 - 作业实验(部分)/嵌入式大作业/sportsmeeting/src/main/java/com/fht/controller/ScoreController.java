package com.fht.controller;


import com.fht.common.bean.R;
import com.fht.entity.CompetitionPeople;
import com.fht.entity.CompetitionTeamPeople;
import com.fht.entity.UserCompetition;
import com.fht.service.ProjectService;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@RestController
public class ScoreController {
    @Autowired
    ProjectService projectService;

    Integer currentSessionId;
    @ModelAttribute
    public void getCurrentSessionId(HttpSession session) throws Exception {
         currentSessionId = (Integer) session.getAttribute("currentSessionId");
    }
    //*************************************报名管理*************************************
    //分页查询
    @RequestMapping(value = "/userCompetition/get", method = RequestMethod.GET)
    public R getUserCompetition(int page, int limit, UserCompetition userCompetition,Session session) {
        Page<UserCompetition> pageInfo = projectService.getUserCompetition(page, limit, userCompetition,currentSessionId);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //删除
    @RequestMapping(value = "/userCompetition/del", method = RequestMethod.POST)
    public R addUserCompetition(String ids, Session session) {
        int flag = projectService.delUserCompetitions(ids.split(","),currentSessionId);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/userCompetition/save", method = RequestMethod.POST)
    public R updateUserCompetition(UserCompetition userCompetition, Session session) {
        System.out.println(userCompetition);
        int flag = projectService.updateUserCompetition(userCompetition,currentSessionId);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //个人项目成绩分页查询
    @RequestMapping(value = "/userCompetition/personal/get", method = RequestMethod.GET)
    public R getPersonalUserCompetition(int page, int limit, UserCompetition userCompetition, Session session) {
        Page<UserCompetition> pageInfo = projectService.getAllWithPersonalInfo(page, limit, userCompetition,currentSessionId);

        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //团队项目成绩分页查询
    @RequestMapping(value = "/userCompetition/team/get", method = RequestMethod.GET)
    public R getTeamUserCompetition(int page, int limit, UserCompetition userCompetition, Session session) {
        Page<UserCompetition> pageInfo = projectService.getAllWithTeamInfo(page, limit, userCompetition,currentSessionId);
        Map<String, UserCompetition> result = new HashMap<>();
        for (UserCompetition value : pageInfo.getResult()) {
            result.put(value.getTeam(), value);
        }
        //降序排序按照成绩
        Comparator<UserCompetition> comparator = (object1, object2) -> {
            // return 1: 长的被换到后面；-1: 短的被换到后面
            if (object1.getScore()==null || object2.getScore()==null )
                return 0;
            return object1.getScore() < object2.getScore() ? 1 : -1;
        };
        List<UserCompetition> list = new ArrayList<>();
        list.addAll(result.values());
        list.sort(comparator);

        System.out.println(list);
        return R.ok().put("data", list).put("count", pageInfo.getTotal());
    }

    //团队成绩更新
    @RequestMapping(value = "/userCompetition/team/save", method = RequestMethod.POST)
    public R updateTeamUserCompetition(UserCompetition userCompetition, Session session) {
        projectService.updateByTeamNameAndCid(userCompetition,currentSessionId);
        return R.ok();
    }


    //**********************************************项目报名**************************************************
    //查询详细信息
    @RequestMapping(value = "/userCompetition/getDetail", method = RequestMethod.GET)
    public R getUserCompetitionDetail(UserCompetition userCompetition, Session session) {
        UserCompetition Info = projectService.getUserCompetitionDetail(userCompetition,currentSessionId);
        return R.ok().put("data", Info);
    }

    //增加
    @RequiresPermissions("/userCompetition/add")
    @RequestMapping(value = "/userCompetition/add", method = RequestMethod.POST)
    public R addUserCompetition(@Valid UserCompetition userCompetition, BindingResult bindingResult, Session session) {
        int flag = 0;
        if (bindingResult.hasErrors()) {
            String info = bindingResult.getFieldError().getDefaultMessage();
            return R.error().put("msg", info);
        }
        try {
            flag = projectService.addUserCompetition(userCompetition,currentSessionId);
        } catch (DuplicateKeyException e) {
            return R.error().put("msg", "该项目已经报名过");
        } catch (RuntimeException e) { e.printStackTrace();
            return R.error().put("msg", e.getMessage());

        }
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //*************************************查看已报名*************************************
    //查询已报名项目
    @RequestMapping(value = "/userCompetition/getByUid", method = RequestMethod.GET)
    public R getAllByUid(int page, int limit, UserCompetition userCompetition, Session session) {
        Page<UserCompetition> pageInfo = projectService.getAllByUid(page, limit, userCompetition,currentSessionId);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //*************************************人员列表*************************************
    //查询
    @RequestMapping(value = "/peopleList/get", method = RequestMethod.GET)
    public R getAllPeople(int page, int limit, Session session) {
        Page<CompetitionPeople> allPeople = projectService.getAllPeople(page, limit,currentSessionId);
        return R.ok().put("data", allPeople).put("count", allPeople.getTotal());
    }

    //*************************************团队人员列表*************************************
    //查询
    @RequestMapping(value = "/teamPeopleList/get", method = RequestMethod.GET)
    public R getAllTeamPeople(int page, int limit, Session session) {
        Page<CompetitionTeamPeople> allPeople = projectService.getAllTeamPeople(page, limit,currentSessionId);
        return R.ok().put("data", allPeople).put("count", allPeople.getTotal());
    }
}
