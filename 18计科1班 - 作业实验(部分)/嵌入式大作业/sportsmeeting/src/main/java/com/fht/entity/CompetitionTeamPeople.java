package com.fht.entity;

import lombok.Data;

import java.util.List;
@Data
public class CompetitionTeamPeople {
    String cid;
    String competitionName;
    List<TeamPeople> teamPeople;

}
