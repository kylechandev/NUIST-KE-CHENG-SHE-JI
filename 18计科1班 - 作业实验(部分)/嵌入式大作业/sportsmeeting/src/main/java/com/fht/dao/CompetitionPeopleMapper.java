package com.fht.dao;

import com.fht.entity.CompetitionPeople;
import com.fht.entity.CompetitionTeamPeople;
import com.fht.entity.TeamPeople;

import java.util.List;

public interface CompetitionPeopleMapper {
    List<CompetitionPeople> getAll();
    List<CompetitionTeamPeople> getAllTeamPeople();
    List<TeamPeople> getTeamPeopleByCid(String cid);


}