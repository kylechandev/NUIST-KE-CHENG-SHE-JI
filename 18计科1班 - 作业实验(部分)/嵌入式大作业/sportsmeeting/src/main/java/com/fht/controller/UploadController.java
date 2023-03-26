package com.fht.controller;

import com.fht.common.bean.R;
import com.fht.common.util.FileTest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class UploadController {
    //文件路径
    public static String imgpath = System.getProperty("user.dir")+File.separator+"static/news-img";
    public static String filepath = System.getProperty("user.dir")+File.separator+"static/tmp-file";

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public R upload(@RequestParam(value = "file") MultipartFile[] files) throws Exception {
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file.isEmpty()) {
                System.out.println("文件为空空");
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!(suffixName.equals(".jpg") || suffixName.equals(".png"))) {
                return R.error();
            }
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(imgpath + File.separator + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            paths[i] = "/static/news-img/"+fileName;
        }
        return R.ok().put("errno",0).put("data",paths);
    }
    @RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
    public R importExcel(@RequestParam(value = "file") MultipartFile[] files) throws Exception {
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file.isEmpty()) {
                System.out.println("文件为空空");
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            if (!(suffixName.equals(".xlsx") || suffixName.equals(".xls")) ) {
                return R.error().put("msg","文件格式不匹配");
            }
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filepath + File.separator + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            paths[i] = "/static/tmp-file/"+fileName;
        }
        return R.ok().put("errno",0).put("data",paths);
    }
}
