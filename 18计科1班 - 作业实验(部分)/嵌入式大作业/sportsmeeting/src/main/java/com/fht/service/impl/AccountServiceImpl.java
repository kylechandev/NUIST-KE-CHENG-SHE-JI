package com.fht.service.impl;

import com.fht.common.util.PassTest;
import com.fht.dao.*;
import com.fht.entity.*;
import com.fht.service.AccountService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserCompetitionMapper userCompetitionMapper;
    @Autowired
    private AccountService accountService;
    @Value("${sportsmeeting.improt.user.password}")
    private String defaultPass;

    //**********************************************用户管理**********************************************
    //添加一个用户
    @Override
    @Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRES_NEW)
    public int addUser(User user) {
        String encryptionPass = PassTest.hashAlgorith(user.getPassword(), user.getUsername());
        user.setPassword(encryptionPass);
        userMapper.insert(user);
        //插入用户的初始权限  id为2的用户
        UserRole userRole = new UserRole();
        Integer uid = userMapper.selectFullByUsername(user.getUsername()).getId();
        userRole.setRoleId(2);
        userRole.setUid(uid);
        return userRoleMapper.insert(userRole);
    }

    //批量添加用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map batchUser(List<User> data) {
        int fail = 0;
        String encryptionPass = "";
        for (User user : data) {
            user.setPassword(defaultPass);
            try {
                //新开一个业务  失败则回滚
                accountService.addUser(user);
            } catch (DuplicateKeyException e) {
                fail++;
            }
        }
        Map map = new HashMap();
        map.put("success", data.size() - fail);
        map.put("fail", fail);
        return map;
    }

    //批量删除用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delUsers(String[] ids,Integer currentSessionId) {
        userRoleMapper.batchDeleteByUid(ids);
        userCompetitionMapper.batchDeleteByUid(ids,currentSessionId);
        return userMapper.batchDelete(ids);
    }

    //更新一个用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUsers(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    //修改密码  根据id
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePassById(String id, String password) {
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(id));
        return userMapper.updatePassById(id, PassTest.hashAlgorith(password, user.getUsername()));
    }

    //修改密码  根据用户名
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePassByUsername(String username, String password) {
        return userMapper.updatePassByUsername(username, PassTest.hashAlgorith(password, username));
    }

    //修改用户状态  根据id
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEnableById(@Param("id") int id, @Param("enable") int enable) {
        return userMapper.updateEnableById(id, enable);
    }

    //根据用户名查询用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getFullByUsername(String username) {
        User result = userMapper.selectFullByUsername(username);
        return result;
    }

    //根据id查询用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getById(Integer id) {
        User result = userMapper.selectByPrimaryKey(id);
        return result;
    }

    //分页模糊查询用户   根据条件模糊查询用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> fuzzySelectByCondition(int page, int limit, User user) {
        Page<User> pageinfo = PageHelper.startPage(page, limit);
        List<User> info = userMapper.fuzzySelectByCondition(user);
        return pageinfo;
    }

    //分页查询用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> getUserByPage(int page, int limit) {
        Page<User> pageinfo = PageHelper.startPage(page, limit);
        List<User> info = userMapper.selectAll();
        return pageinfo;
    }

    //分页查询用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> selectAllBase(int page, int limit) {
        Page<User> pageinfo = PageHelper.startPage(page, limit);
        List<User> info = userMapper.selectAllBase();
        return pageinfo;
    }

    //*************************************角色权限关系管理*************************************
    //查询全部角色根据用户名
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getUserRoleByUsername(String username) {
        List<Role> roles = roleMapper.selectRolesByUserName(username);
        return roles;
    }

    //查询全部权限根据角色名
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> getPermissionByRoleId(String id) {
        List<Permission> permissions = permissionMapper.getPermissionByRoleId(id);
        return permissions;
    }

    //保存用户角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUserRole(List<UserRole> userRoles, Integer uid) {
        int n = 0;
        userRoleMapper.deleteByUid(uid);
        if (userRoles.size() != 0)
            n = userRoleMapper.batchInsert(userRoles);
        return n;
    }

    //保存角色权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRolePermission(List<RolePermission> rolePermissions, Integer roleId) {
        System.out.println(rolePermissions);
        System.out.println(roleId);
        int n = 0;
        rolePermissionMapper.deleteByRoleId(roleId);
        if (rolePermissions.size() != 0)
            n = rolePermissionMapper.batchInsert(rolePermissions);
        return n;
    }

    //**********************************************角色管理**********************************************
    //添加
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    //查询全部角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getAllRole() {
        List<Role> info = roleMapper.selectAll();
        return info;
    }

    //分页查询角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Role> getRole(int page, int limit) {
        Page<Role> pageinfo = PageHelper.startPage(page, limit);
        List<Role> info = roleMapper.selectAll();
        return pageinfo;
    }

    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delRoles(String[] ids) {
        return roleMapper.batchDelete(ids);
    }

    //更新
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    //**********************************************权限管理**********************************************
    //添加
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addPermission(Permission permission) {
        return permissionMapper.insert(permission);
    }

    //查询全部权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> getAllPermission() {
        List<Permission> info = permissionMapper.selectAll();
        return info;
    }

    //分页查询权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Permission> getPermission(int page, int limit) {
        Page<Permission> pageinfo = PageHelper.startPage(page, limit);
        List<Permission> info = permissionMapper.selectAll();
        return pageinfo;
    }

    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delPermissions(String[] ids) {
        return permissionMapper.batchDelete(ids);
    }

    //更新
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }

    //**********************************************其他管理**********************************************
    //分页查询用户角色
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<UserRole> getUserRole(int page, int limit) {
        Page<UserRole> pageinfo = PageHelper.startPage(page, limit);
        List<UserRole> info = userRoleMapper.selectAll();
        return pageinfo;
    }
    //分页查询角色权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<RolePermission> getRolePermission(int page, int limit) {
        Page<RolePermission> pageinfo = PageHelper.startPage(page, limit);
        List<RolePermission> info = rolePermissionMapper.selectAll();
        return pageinfo;
    }
    //根据用户名查询角色集合
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> getRolesByUserName(String username) {
        return roleMapper.selectRolesByUserName(username);
    }

    //根据用户名查询权限集合
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Permission> getPermissionsByUserName(String username) {
        return permissionMapper.selectPermissionsByUserName(username);
    }
}
