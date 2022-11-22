package com.fht.common.config;

import com.fht.entity.Permission;
import com.fht.entity.Role;
import com.fht.entity.User;
import com.fht.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    AccountService accountService;
    /**
     * 授权  用权限时才触发
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthorizationException {
        String userName=((User)SecurityUtils.getSubject().getPrincipal()).getUsername();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Set<String> roles=new HashSet<String>();
        List<Role> rolesByUserName = accountService.getRolesByUserName(userName);
        for(Role role:rolesByUserName) {
            if (role!=null)
            roles.add(role.getContent());
        }
        List<Permission> permissionsByUserName = accountService.getPermissionsByUserName(userName);
        for(Permission permission:permissionsByUserName) {
            if (permission!=null)
            info.addStringPermission(permission.getContent());
        }
        info.setRoles(roles);
//        System.out.println(info.getStringPermissions());
//        System.out.println(info.getRoles());
        return info;
    }

    /**
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        // 获取用户的Token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        User user= accountService.getFullByUsername(token.getUsername());
        if (user == null)
            throw  new AccountException("用户名或者密码错误");
        if (user.getEnable() == 0){
            throw  new AccountException("用户已经被禁用");
        }
        User principal = user;
        String credentials =user.getPassword();
        //2.判断密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,getName());
    }

}
