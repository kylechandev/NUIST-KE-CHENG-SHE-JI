package com.fht.service;

import com.fht.common.util.PoiUtil;
import com.fht.dao.*;
import com.fht.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ProjectServiceImplTest {
    @Autowired
    SessionService sessionService;
    @Test
    void Test(){
        Session currentSessionOfSportsMeeting = sessionService.getCurrentSessionOfSportsMeeting();
        System.out.println(currentSessionOfSportsMeeting.toString());
    }
}
//    //更新所有用户密码为123456
//    @Test
//    void t() {
//        String userPassword = "123456";
//        String salt = "";
//        String hashPass = "";
//        List<User> user = userMapper.selectAll();
//        for (User u : user) {
//            salt = u.getUsername();
//            hashPass = PassTest.hashAlgorith(userPassword, salt);
//            userMapper.updatePassByUsername(u.getUsername(), hashPass);
//        }
////        userMapper.updatePassByUsername("admin","123");
//    }
    //    @Test
//    void selectAll() {
//        UserRole userRole = new UserRole();
//        userRole.setUid(1);
//        userRole.setRoleId(8);
//        UserRole userRole1 = new UserRole();
//        userRole1.setUid(1);
//        userRole1.setRoleId(7);
//        List<UserRole> userRoles = new ArrayList<>();
//        userRoles.add(userRole);
//        userRoles.add(userRole1);
//        int n = userRoleMapper.batchInsert(userRoles);
////        PageHelper.startPage(1,10);
////        list = completionService.selectAllByPage(1, 10);
////        System.out.println(list);
////        String jsonString = JSON.toJSONString(list);
////        System.out.println("JSON字符串：" + jsonString);
//        System.out.println(n);
//    }
//
//    @Test
//    void selectByConditio() {
//        System.out.println(accountService.getFullByUsername("admin"));
//    }
//
//    @Test
//    void awdawd() {
//
//        List<Competition> allAndScore = competitionMapper.getAllAndScore();
//        for (Competition c : allAndScore)
//
//        System.out.println(c);
//    }
//
//    @Test
//    void asdas() {
//        User user = new User();
//        user.setUsername("1611645");
//        user.setRealname("1111111");
//        user.setPassword("123456");
//        accountService.addUser(user);
//    }
//

