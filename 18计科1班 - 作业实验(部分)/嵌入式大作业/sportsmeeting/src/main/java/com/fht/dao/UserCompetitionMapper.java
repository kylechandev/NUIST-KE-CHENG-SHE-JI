package com.fht.dao;

import com.fht.entity.UserCompetition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCompetitionMapper {
    int deleteByPrimaryKey(@Param("id")Integer id, @Param("currentSessionId")Integer currentSessionId);
    int updateByPrimaryKey(@Param("id")String id,@Param("currentSessionId")Integer currentSessionId);
    int batchDeleteByUid(@Param("ids")String[] ids,@Param("currentSessionId")Integer currentSessionId);
    int batchDelete(@Param("ids")String[] ids,@Param("currentSessionId")Integer currentSessionId);

    UserCompetition selectByPrimaryKey(@Param("id")Integer id,@Param("currentSessionId")Integer currentSessionId);
    String getIdByTeamNameAndCid(@Param("teamname") String teamname, @Param("cid") Integer cid,@Param("currentSessionId")Integer currentSessionId);
    List<UserCompetition> selectAll();
    List<UserCompetition> getScoreRankByCid(@Param("id")Integer cid,@Param("currentSessionId")Integer currentSessionId);

    int updateByPrimaryKey(@Param("record")UserCompetition record,@Param("currentSessionId")Integer currentSessionId);
    int insert(@Param("record")UserCompetition record,@Param("currentSessionId")Integer currentSessionId);
    List<UserCompetition> getAllByUid(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
    UserCompetition getApplyCapita(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
    UserCompetition getIsApply(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
    List<UserCompetition> selectAllExtension(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
    List<UserCompetition> getAllWithTeamInfo(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
    List<UserCompetition> getAllWithPersonalInfo(@Param("userCompetition")UserCompetition userCompetition,@Param("currentSessionId")Integer currentSessionId);
}