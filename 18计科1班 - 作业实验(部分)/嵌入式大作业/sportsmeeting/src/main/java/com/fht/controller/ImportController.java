package com.fht.controller;

import com.fht.common.bean.R;
import com.fht.common.util.FileUtil;
import com.fht.common.util.PoiUtil;
import com.fht.entity.User;
import com.fht.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
public class ImportController {
    public static String serverpath = System.getProperty("user.dir");
@Autowired
    AccountService accountService;
    @RequestMapping(value = "/user/import", method = RequestMethod.POST)
    public R upload(String path) throws Exception {
        List<User> users = PoiUtil.parseUserExcel(serverpath +File.separator+ path);
        FileUtil.deleteAllFilesOfDir(new File(serverpath +File.separator+ path));
        Map map=accountService.batchUser(users);
//        System.out.println(users);
        return R.ok().put("msg","成功"+map.get("success")+"个，"+"失败"+map.get("fail")+"个");

    }

}
