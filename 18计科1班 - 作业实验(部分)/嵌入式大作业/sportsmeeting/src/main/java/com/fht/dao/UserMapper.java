package com.fht.dao;

import com.fht.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int batchDelete(String[] ids);

    int insert(User record);

    int updateEnableById(@Param("id")int id, @Param("enable")int enable);

    int updateByPrimaryKey(User record);
    int updatePassByUsername( @Param("username")String username, @Param("password")String password);
    int updatePassById( @Param("id")String id,  @Param("password")String password);

    List<User> selectAll();
    List<User> selectAllBase();
    List<User> fuzzySelectByCondition(User user);
    User selectByCondition(User user);
    User selectByPrimaryKey(Integer id);

    User selectFullByUsername(String username);
}