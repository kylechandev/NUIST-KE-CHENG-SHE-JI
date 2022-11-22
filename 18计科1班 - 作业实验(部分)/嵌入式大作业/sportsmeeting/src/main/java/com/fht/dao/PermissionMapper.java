package com.fht.dao;

import com.fht.entity.Permission;
import com.fht.entity.Role;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    int batchDelete(String[] ids);

    List<Permission> selectPermissionsByUserName(String username);

    List<Permission> getPermissionByRoleId(String id);
}