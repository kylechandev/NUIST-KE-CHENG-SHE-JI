package com.fht.controller;

import com.fht.common.util.FileTest;
import com.fht.common.util.FileUtil;
import com.fht.common.util.PoiUtil;
import com.fht.entity.Competition;
import com.fht.entity.CompetitionTeamPeople;
import com.fht.entity.UserCompetition;
import com.fht.service.InfoService;
import com.fht.service.ProjectService;
import com.fht.entity.CompetitionPeople;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExportController {
    Logger log =  LoggerFactory.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;
    @Autowired
    InfoService infoService;
    Integer currentSessionId;

    @ModelAttribute
    public void getCurrentSessionId(HttpSession session) throws Exception {
        currentSessionId = (Integer) session.getAttribute("currentSessionId");
    }
    //导出参赛人员表
    @RequestMapping(value = "/peopleWord/export", method = RequestMethod.GET)
    public void exportpeopleList(HttpServletResponse response) throws IOException {
        List<CompetitionPeople> allPeople = projectService.getAllPeople(0,0,currentSessionId);
        List<CompetitionTeamPeople> teamPeople = projectService.getAllTeamPeople(0,0,currentSessionId);
        String path = FileTest.createPeopleWord(allPeople,teamPeople);
        FileUtil.downLoadFile(path,response);
        FileUtil.deleteAllFilesOfDir(new File(path));
        log.info("删除文件"+path);
    }
    //导出参赛人员表
    @RequestMapping(value = "/competitionWord/export", method = RequestMethod.GET)
    public void exportCompetition(HttpServletResponse response) throws IOException {
        List<Competition> allPeople = projectService.getByTimeAsc(0,0);
        String path = FileTest.createCompetitionWord(allPeople);
        FileUtil.downLoadFile(path,response);
        FileUtil.deleteAllFilesOfDir(new File(path));
        log.info("删除临时文件"+path);
    }
    //导出个人比赛成绩表excel
    @RequestMapping(value = "/score/export", method = RequestMethod.GET)
    public void exportScore(HttpServletResponse response) throws IOException {
        List<Competition> info = infoService.getAllAndScore(0, 0,0);
        String path = PoiUtil.createScoreExcel(info);
        FileUtil.downLoadFile(path,response);
        FileUtil.deleteAllFilesOfDir(new File(path));
        log.info("删除临时文件"+path);
    }
    //导出团队比赛成绩表excel
    @RequestMapping(value = "/score/team/export", method = RequestMethod.GET)
    public void exportTeamScore(HttpServletResponse response) throws IOException {
        List<Competition> info = infoService.getAllAndScore(0, 0,1);
        Map<String ,UserCompetition> result= new HashMap<>();
        for (Competition competition : info) {
            for (UserCompetition value1 : competition.getUserCompetition()) {
                if (result.get(value1.getTeam())!=null)
                value1.setRealname(value1.getRealname()+","+result.get(value1.getTeam()).getRealname());
                result.put(value1.getTeam(), value1);
            }
            competition.getUserCompetition().clear();
            for (String key : result.keySet()) {
                competition.getUserCompetition().add(result.get(key));
            }
        }
        info.forEach(value->log.info(value.toString()));
        String path = PoiUtil.createScoreExcel(info);
        FileUtil.downLoadFile(path,response);
        FileUtil.deleteAllFilesOfDir(new File(path));
        log.info("删除临时文件"+path);
    }
    // /导出用户表模板
    @RequestMapping(value = "/userTemplate/export", method = RequestMethod.GET)
    public void UserTemplate(HttpServletResponse response) throws IOException {
        //文件路径
        InputStream in =this.getClass().getClassLoader().getResourceAsStream("ftl/user.xls");
        FileUtil.downLoadFile("user.xls",in,response);
    }
}
