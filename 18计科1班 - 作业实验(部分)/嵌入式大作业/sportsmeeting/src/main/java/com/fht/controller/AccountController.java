package com.fht.controller;

import com.fht.common.bean.R;
import com.fht.common.util.PassTest;
import com.fht.entity.*;
import com.fht.service.AccountService;
import com.github.pagehelper.Page;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    Integer currentSessionId;
    @ModelAttribute
    public void getCurrentSessionId(HttpSession session) throws Exception {
        currentSessionId = (Integer) session.getAttribute("currentSessionId");
    }
    //*************************************用户管理*************************************
    //增加
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public R addUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String info = bindingResult.getFieldError().getDefaultMessage();
            return R.error().put("msg", info);
        }
        int flag = 0;
        try {
            flag = accountService.addUser(user);
        } catch (DuplicateKeyException e) {
            return R.error().put("msg", "用户可能已存在");
        }
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //删除
    @RequestMapping(value = "/user/del", method = RequestMethod.POST)
    public R addUser(String ids) {
        int flag = accountService.delUsers(ids.split(","),currentSessionId);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public R updateUser(User user) {
        System.out.println(user);
        int flag = accountService.updateUsers(user);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //分页查询用户
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public R getUserByPage(int page, int limit) {
        Page<User> pageInfo = accountService.getUserByPage(page, limit);
        System.out.println(pageInfo.getResult());
        System.out.println(pageInfo.getResult());
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //分页查询用户
    @RequestMapping(value = "/user/getBase", method = RequestMethod.GET)
    public R selectAllBase(int page, int limit) {
        Page<User> pageInfo = accountService.selectAllBase(page, limit);
        System.out.println(pageInfo.getResult());
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //更新当前用户密码   根据session的用户名
    @RequestMapping(value = "/user/updatePass", method = RequestMethod.POST)
    public R updatePassByName(HttpSession session, String password0, String password1) {
        int flag = 0;
        String username = session.getAttribute("username").toString();
        User user = accountService.getFullByUsername(username);
        if (!user.getPassword().equals(PassTest.hashAlgorith(password0, username)))
            return R.error().put("msg", "原始密码错误");
        flag = accountService.updatePassByUsername(username, password1);
        if (flag == 0)
            return R.error();
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return R.ok();
    }


    //更新用户状态 启用禁用
    @RequestMapping(value = "/user/updateEnable", method = RequestMethod.POST)
    public R updatePass(int id, int enable) {
        int flag = accountService.updateEnableById(id, enable);
        if (flag == 0)
            return R.error();
        if (enable == 0)
            LoginController.singleUseLogin(id + "");
        return R.ok();
    }

    //条件模糊查询用户
    @RequestMapping(value = "/user/fuzzySelect", method = RequestMethod.GET)
    public R fuzzySelectByCondition(int page, int limit, User user) {
        System.out.println(user);
        Page<User> pageInfo = accountService.fuzzySelectByCondition(page, limit, user);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //根据用户名查询用户
    @RequestMapping(value = "/user/get/ById", method = RequestMethod.GET)
    public R getByUsername(Integer id) {
        User result = accountService.getById(id);
        return R.ok().put("data", result);
    }

    //更新用户密码
    @RequestMapping(value = "/user/updatePass/ById", method = RequestMethod.POST)
    public R updatePassById(String id, String password) {
        int flag = accountService.updatePassById(id, password);
        if (flag == 0)
            return R.error();
        LoginController.singleUseLogin(id);
        return R.ok();
    }
    //*************************************角色权限关系管理*************************************

    //查询全部角色根据用户id
    @RequestMapping(value = "/userRole/get", method = RequestMethod.GET)
    public R getRole(String id) {
        List<Role> roles = accountService.getUserRoleByUsername(id);
        return R.ok().put("data", roles);
    }

    //查询全部权限根据角色id
    @RequestMapping(value = "/rolePermission/get", method = RequestMethod.GET)
    public R getPermission(String id) {
        List<Permission> permission = accountService.getPermissionByRoleId(id);
        return R.ok().put("data", permission);
    }

    //保存用户角色
    @RequestMapping(value = "/userRole/save", method = RequestMethod.POST)
    public R saveUserRole(@RequestBody List<UserRole> userRoles, @RequestParam Integer uid) {
        System.out.println(userRoles.size());
        int n = accountService.saveUserRole(userRoles, uid);
        return R.ok().put("count", n);
    }

    //保存角色权限
    @RequestMapping(value = "/rolePermission/save", method = RequestMethod.POST)
    public R saveRolePermission(@RequestBody List<RolePermission> rolePermissions, @RequestParam Integer roleId) {
        int n = accountService.saveRolePermission(rolePermissions, roleId);
        return R.ok().put("count", n);
    }

    //*************************************角色管理*************************************
    //查询全部角色
    @RequestMapping(value = "/role/getAll", method = RequestMethod.GET)
    public R getAllRole() {
        List<Role> info = accountService.getAllRole();
        return R.ok().put("data", info);
    }

    //分页查询角色
    @RequestMapping(value = "/role/get", method = RequestMethod.GET)
    public R getRole(int page, int limit) {
        Page<Role> pageInfo = accountService.getRole(page, limit);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //增加
    @RequestMapping(value = "/role/add", method = RequestMethod.POST)
    public R addRole(@Valid Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String info = bindingResult.getFieldError().getDefaultMessage();
            return R.error().put("msg", info);
        }
        System.out.println(role);
        int flag = accountService.addRole(role);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //删除
    @RequestMapping(value = "/role/del", method = RequestMethod.POST)
    public R addRole(String ids) {
        int flag = accountService.delRoles(ids.split(","));
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public R updateRole(Role role) {
        System.out.println(role);
        int flag = accountService.updateRole(role);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //*************************************权限管理*************************************
    //查询全部权限
    @RequestMapping(value = "/permission/getAll", method = RequestMethod.GET)
    public R getAllPermission() {
        List<Permission> info = accountService.getAllPermission();
        return R.ok().put("data", info);
    }

    //分页查询权限
    @RequestMapping(value = "/permission/get", method = RequestMethod.GET)
    public R getPermission(int page, int limit) {
        Page<Permission> pageInfo = accountService.getPermission(page, limit);
        return R.ok().put("data", pageInfo).put("count", pageInfo.getTotal());
    }

    //增加
    @RequestMapping(value = "/permission/add", method = RequestMethod.POST)
    public R addPermission(@Valid Permission permission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String info = bindingResult.getFieldError().getDefaultMessage();
            return R.error().put("msg", info);
        }
        int flag = accountService.addPermission(permission);
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //删除
    @RequestMapping(value = "/permission/del", method = RequestMethod.POST)
    public R delPermission(String ids) {
        int flag = accountService.delPermissions(ids.split(","));
        if (flag == 0)
            return R.error();
        return R.ok();
    }

    //更新
    @RequestMapping(value = "/permission/save", method = RequestMethod.POST)
    public R updatePermission(Permission permission) {
        System.out.println(permission);
        int flag = accountService.updatePermission(permission);
        if (flag == 0)
            return R.error();
        return R.ok();
    }
}
