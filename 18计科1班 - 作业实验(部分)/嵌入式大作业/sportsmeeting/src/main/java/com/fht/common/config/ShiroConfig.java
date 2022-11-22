package com.fht.common.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        //设置加密方式
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        manager.setSessionManager(defaultWebSessionManager());
        return manager;
    }
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        definition.addPathDefinition("/", "anon");
        definition.addPathDefinition("/static/**", "anon");
        definition.addPathDefinition("/news", "anon");
        definition.addPathDefinition("/news/get", "anon");
        definition.addPathDefinition("/news/getById*", "anon");
//        definition.addPathDefinition("/**/*.woff", "anon");
        definition.addPathDefinition("/login", "anon");
        definition.addPathDefinition("/**", "authc");
        return definition;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式
        credentialsMatcher.setHashAlgorithmName("MD5");
        System.out.println("加密方式：MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //此处的设置，true加密用的hex编码，false用的base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    //添加的
    @Bean(name = "memorySessionDAO")
    public MemorySessionDAO memorySessionDAO() {
        MemorySessionDAO memorySessionDAO = new MemorySessionDAO();
        return memorySessionDAO;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        // 设置session过期时间3600s
        defaultWebSessionManager.setGlobalSessionTimeout(3600000L);
        defaultWebSessionManager.setSessionDAO(memorySessionDAO());
//        defaultWebSessionManager.setGlobalSessionTimeout(Integer.valueOf(ParamKey.getUserOverTime())*1000);
        return defaultWebSessionManager;
    }
    //配置shirodialect 用于thymeleaf和shiro配合使用
    @Bean("shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}