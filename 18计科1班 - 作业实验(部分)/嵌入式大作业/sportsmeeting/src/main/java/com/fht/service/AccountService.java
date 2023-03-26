package com.fht.service;

import com.fht.entity.*;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface AccountService {
    //修改用户状态  根据id
    int updateEnableById(int id, int enable);

    //根据用户名查询用户
    User getFullByUsername(String username);

    //添加一个用户
    int addUser(User user);

    //添加一个用户
    Map batchUser(List<User> data);

    //批量删除用户
    int delUsers(String[] ids,Integer currentSessionId);

    //更新一个用户
    int updateUsers(User user);

    //修改密码  根据id
    int updatePassById(String id, String password);

    //修改为加密的密码
    int updatePassByUsername(String username, String password);

    //根据id查询用户
    User getById(Integer id);

    Page<User>  fuzzySelectByCondition(int page, int limit, User user);

    //分页查询用户
    Page<User> getUserByPage(int page, int limit);


    //分页查询用户
    Page<User> selectAllBase(int page, int limit);

    //*************************************角色权限关系管理*************************************
    //查询全部角色根据用户名
    List<Role> getUserRoleByUsername(String username);

    //查询全部权限根据角色id
    List<Permission> getPermissionByRoleId(String id);

    //保存用户角色
    int saveUserRole(List<UserRole> userRoles, Integer uid);

    //保存角色权限
    int saveRolePermission(List<RolePermission> rolePermissions, Integer uid);

    //**********************************************角色管理**********************************************
        //分页查询角色
    int addRole(Role role);

    //查询全部角色
    List<Role> getAllRole();

    //查询角色
    Page<Role> getRole(int page, int limit);

    //批量删除
    int delRoles(String[] ids);

    //更新一个用户
    int updateRole(Role role);

    //**********************************************权限管理**********************************************
    //添加
    int addPermission(Permission permission);

    //查询全部权限
    List<Permission> getAllPermission();

    //查询权限
    Page<Permission> getPermission(int page, int limit);

    //批量删除
    int delPermissions(String[] ids);

    //更新
    int updatePermission(Permission permission);

    //查询用户角色
    Page<UserRole> getUserRole(int page, int limit);

    //查询角色权限
    Page<RolePermission> getRolePermission(int page, int limit);

    //根据用户名查询角色集合
    List<Role> getRolesByUserName(String username);

    //根据用户名查询权限集合
    List<Permission> getPermissionsByUserName(String username);
}
