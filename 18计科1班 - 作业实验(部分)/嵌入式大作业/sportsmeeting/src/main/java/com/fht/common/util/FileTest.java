package com.fht.common.util;

import com.fht.entity.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTest {
    public static void main(String[] args) throws IOException {
//        createPeopleWord();
        //String BASE64 = FileUtil.getImageString("D://doc//friend.jpg");
        //System.out.println(BASE64);
    }
    //文件路径
    public static String path = System.getProperty("user.dir");
    /**
     * 参赛人员名单
     */
    public static String createPeopleWord(List<CompetitionPeople> lists, List<CompetitionTeamPeople> teamPeople) throws IOException {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
//        dataMap.put("bt","参赛");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
//        dataMap.put("date",sdf.format(new Date()));
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CompetitionPeople i : lists) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("competition", i.getCompetitionName());
            map.put("people", i.getPeople());
            list.add(map);
        }
        List<Map<String, Object>> teamlist = new ArrayList<Map<String, Object>>();
        for (CompetitionTeamPeople i : teamPeople) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("competition", i.getCompetitionName());
            List<Map<String, Object>> list0 = new ArrayList<Map<String, Object>>();
            for (TeamPeople j : i.getTeamPeople()) {
                Map<String, Object> map0 = new HashMap<String, Object>();
                map0.put("team", j.getTeam());
                map0.put("people", j.getPeople());
                list0.add(map0);
            }
            map.put("itemlist", list0);
            teamlist.add(map);
        }
        dataMap.put("list", list);
        dataMap.put("teamlist", teamlist);
        System.out.println(dataMap.get("teamlist"));
        //文件名称
        String fileName = "参赛人员名单" + System.currentTimeMillis() + ".doc";
        String filePath = path +File.separator+ fileName;
        /** 生成word */
        FileUtil.create(dataMap, "p.ftl", filePath);
        return filePath;
    }

    /**
     * 比赛日程表
     */
    public static String createCompetitionWord(List<Competition> lists) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Competition i : lists) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", i.getName() + "(" + i.getKind() + ")");
            map.put("site", i.getSite());
            System.out.println(i.getTime());
            map.put("time", sdf.format(i.getTime()));
            map.put("description", i.getDescription());
            list.add(map);
        }
        dataMap.put("list", list);
        //文件名称
        String fileName = "\\比赛日程表" + System.currentTimeMillis() + ".doc";
        String filePath = path +File.separator+ fileName;
        /** 生成word */
        FileUtil.create(dataMap, "competition.ftl", filePath);
        return filePath;
    }

    /**
     * 比赛成绩
     */
    public static String createScoreExcel(List<Competition> lists) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        /** 组装数据 */
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Competition i : lists) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", i.getName() + "(" + i.getKind() + ")");
            List<Map<String, Object>> list0 = new ArrayList<Map<String, Object>>();
            for (UserCompetition j : i.getUserCompetition()) {
                Map<String, Object> map0 = new HashMap<String, Object>();
                map0.put("realname", j.getRealname());
                map0.put("grade", j.getGrade());
                map0.put("team", j.getTeam());
                map0.put("score", j.getScore());
                list0.add(map0);
            }
            map.put("ll", list0);
            list.add(map);
        }
        dataMap.put("list", list);
        //文件名称
        String fileName = "\\比赛成绩表" + System.currentTimeMillis() + ".xls";
        String filePath = path +File.separator+ fileName;
        /** excel */
        FileUtil.create(dataMap, "score.ftl", filePath);
        return filePath;
    }

}