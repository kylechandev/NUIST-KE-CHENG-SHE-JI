package com.fht.common.util;
import java.io.*;
import java.net.URL;
import java.util.Map;import io.netty.util.internal.StringUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.ResourceUtils;import javax.servlet.http.HttpServletResponse;import static freemarker.template.Configuration.VERSION_2_3_24;public class FileUtil {
    public static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 生成word文件(全局可用)
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param fileFullPath 要生成的文件全路径
     */
    @SuppressWarnings("unchecked")
    public static void create(Map dataMap, String templateName, String fileFullPath) {
        logger.info("【createWord】：==>方法进入");
        logger.info("【fileFullPath】：==>" + fileFullPath);
        logger.info("【templateName】：==>" + templateName);
        try {
            // 创建配置实例
            Configuration configuration = new Configuration(VERSION_2_3_24);
            logger.info("【创建配置实例】：==>");            // 设置编码
            configuration.setDefaultEncoding("UTF-8");
            logger.info("【设置编码】：==>");            // 设置处理空值
            configuration.setClassicCompatible(true);            // 设置错误控制器
            configuration.setClassForTemplateLoading(FileUtil.class,"/ftl");            //创建文件
            File file = new File(fileFullPath);
            // 如果输出目标文件夹不存在，则创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            // 生成文件
            template.process(dataMap, out);            // 清空缓存
            out.flush();
            // 关闭流
            out.close();        } catch (Exception e) {
            logger.info("【生成word文件出错】：==>" + e.getMessage());
            e.printStackTrace();
        }
    }    /**
     * 下载生成的文件(全局可用)
     * @param fullPath 全路径
     * @param response
     */
    public static void downLoadFile(String fullPath, HttpServletResponse response) {
        logger.info("【downLoadFile:fullPath】：==>" + fullPath);
        InputStream inputStream = null;
        OutputStream outputStream = null;        try {
            //创建文件
            File file = new File(fullPath);
            String fileName = file.getName();            //读文件流
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);            //清空响应
            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setContentType("application/vnd.ms-excel");
            // response.setContentType("application/msword");
            response.setHeader("Content-Disposition","attachment; filename=" + new String(fileName.getBytes(), "ISO8859-1"));
            response.setHeader("Content-Length", "" + file.length());            //写文件流
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }        }
    }
    /**
     * 下载生成的文件(全局可用)
     * @param response
     */
    public static void downLoadFile(String filename,InputStream inputStream, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            //读文件流
            int filesize = inputStream.available();
            byte[] buffer = new byte[filesize];
            inputStream = new BufferedInputStream(inputStream);
            inputStream.read(buffer);            //清空响应
            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/octet-stream; charset=utf-8");
//            response.setContentType("application/vnd.ms-excel");
            // response.setContentType("application/msword");
            response.setHeader("Content-Disposition","attachment; filename=" + new String(filename.getBytes(), "ISO8859-1"));
            response.setHeader("Content-Length", "" + String.valueOf(filesize));            //写文件流
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }        }
    }
    /**
     * 下载网络文件到本地(主要用于下载简历附件)
     * @param urlAddress 网络url地址,为空时直接返回
     * @param fileFullPath 文件全路径
     */
    public static void createFromUrl(String urlAddress,String fileFullPath) {
        logger.info("【service:开始下载网络文件】:==> 网上文件地址：" + urlAddress + "文件保存路径:" + fileFullPath);        if(StringUtil.isNullOrEmpty(urlAddress)) {
            return ;
        }        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream =null;
        try {            URL url = new URL(urlAddress);
            dataInputStream = new DataInputStream(url.openStream());//打开网络输入流
            //创建文件
            File file = new File(fileFullPath);
            // 如果输出目标文件夹不存在，则创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);//建立一个新的文件
            byte[] buffer = new byte[1024];
            int length;
            while((length = dataInputStream.read(buffer))>0){//开始填充数据
                fileOutputStream.write(buffer,0,length);
            }            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(dataInputStream!=null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }            try {
                if(fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }    /**
     * 从网上或本地获得图片的base64码(主要用于插入生成word中的图片)
     * @param urlAddress 网络路径,二选一,目前有问题
     * @param pathAddress 本地路径，二选一
     * @return 返回base64码或null
     */
    public static String getImageBase(String urlAddress,String pathAddress) {
        byte[] buffer = null;
        InputStream inputStream = null;
        String imageCodeBase64 = null;        try {
            if(!StringUtil.isNullOrEmpty(urlAddress)){
                URL url = new URL(urlAddress);
                inputStream = new DataInputStream(url.openStream());//打开网络输入流
                buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
            }else if(!StringUtil.isNullOrEmpty(pathAddress)){
                inputStream = new BufferedInputStream(new FileInputStream(new File(pathAddress)));//读文件流
                buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
            }else {
                return null;
            }            imageCodeBase64 = Base64.encodeBase64String(buffer);
//            System.out.println(imageCodeBase64);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageCodeBase64;
    }
    /**
     * 删除文件夹（强制删除）
     *
     * @param path
     */
    public static void deleteAllFilesOfDir(File path) {
        if (null != path) {
            if (!path.exists())
                return;
            if (path.isFile()) {
                boolean result = path.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = path.delete();
                }
            }
            File[] files = path.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }
            path.delete();
        }
    }    public static String  getPath(String subdirectory){
        //获取跟目录---与jar包同级目录的upload目录下指定的子目录subdirectory
        File upload = null;
        try {
            //本地测试时获取到的是"工程目录/target/upload/subdirectory
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            upload = new File(path.getAbsolutePath(),subdirectory);
            if(!upload.exists()) upload.mkdirs();//如果不存在则创建目录
            String realPath = upload + "/";
            return realPath;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取服务器路径发生错误！");
        }
    }}