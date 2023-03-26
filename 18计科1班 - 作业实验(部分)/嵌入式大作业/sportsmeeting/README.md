# sportsmeeting

#### 介绍
高校运动会管理系统

#### 软件架构
软件架构说明


#### 安装教程

1.  打开 release文件夹
2.  导入 sports_meeting.sql数据库文件
4.  修改数据库地址  账号密码
3.  项目打jar包 运行 sportsmeeting-0.0.1-SNAPSHOT.jar
4.  访问127.0.0.1.8081

#### 使用说明

1.  环境要求   mysql数据库    jdk或jre运行环境
2.  数据库地址修改
    源码内：
    在sportsmeeting\src\main\resources\application-dev.properties文件
    注意修改数据库地址 默认是192.168.100.3:3306......
    注意修改数据库地址 若是本机  默认mysql端口   修改为127.0.0.1:3306
    数据库账号密码等
    jar包内：
    打开\BOOT-INF\classes\application-dev.properties文件夹  同上
3. shiro的权限只配置了页面显示，没权限不显示
    后台请求权限只配置了项目报名，没权限无法请求,想添加依次添加就行了
