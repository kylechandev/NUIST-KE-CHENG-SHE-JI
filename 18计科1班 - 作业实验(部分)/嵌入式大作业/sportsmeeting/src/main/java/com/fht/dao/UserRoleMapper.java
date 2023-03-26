package com.fht.dao;

import com.fht.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByUid(@Param("uid") Integer uid);

    int insert(UserRole record);

    List<UserRole> selectAll();

    UserRole selectUserRole(String id);

    int batchDeleteByUid(String[] ids);

    int batchInsert(List<UserRole> userRoles);

    List<UserRole> getByUid(String id);
}