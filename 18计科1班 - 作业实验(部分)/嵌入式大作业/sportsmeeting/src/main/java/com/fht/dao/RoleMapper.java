package com.fht.dao;

import com.fht.entity.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    int batchDelete(String[] ids);

    List<Role> selectRolesByUserName(String username);
}