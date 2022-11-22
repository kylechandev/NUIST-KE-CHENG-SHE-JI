package com.fht.dao;

import com.fht.entity.Competition;
import java.util.List;

public interface CompetitionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Competition record);

    Competition selectByPrimaryKey(Integer id);

    List<Competition> selectAll(Competition competition);

    List<Competition> getAllAndScore(Integer isTeam);

    List<Competition> getByTimeAsc();

    int updateByPrimaryKey(Competition record);

    int batchDelete(String[] ids);
}