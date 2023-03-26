package com.fht.dao;

import com.fht.entity.Permission;
import com.fht.entity.RolePermission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int deleteByRoleId( @Param("roleId") Integer roleId);

    int insert(RolePermission record);

    List<RolePermission> selectAll();

    RolePermission selectPermission(String id);

    int batchDelete(String[] ids);

    int batchInsert(List<RolePermission> rolePermissions);

    List<RolePermission>  getByRoleId(String id);

}