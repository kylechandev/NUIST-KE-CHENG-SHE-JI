USE [master]
GO
/****** Object:  Database [XUANKE]    Script Date: 12/29/2020 14:11:27 ******/
CREATE DATABASE [XUANKE] ON  PRIMARY 
( NAME = N'XUANKE', FILENAME = N'D:\daerxiaxueqi\keshe shujuku\123\XUANKE.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'XUANKE_log', FILENAME = N'D:\daerxiaxueqi\keshe shujuku\123\XUANKE_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [XUANKE] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [XUANKE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [XUANKE] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [XUANKE] SET ANSI_NULLS OFF
GO
ALTER DATABASE [XUANKE] SET ANSI_PADDING OFF
GO
ALTER DATABASE [XUANKE] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [XUANKE] SET ARITHABORT OFF
GO
ALTER DATABASE [XUANKE] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [XUANKE] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [XUANKE] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [XUANKE] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [XUANKE] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [XUANKE] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [XUANKE] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [XUANKE] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [XUANKE] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [XUANKE] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [XUANKE] SET  DISABLE_BROKER
GO
ALTER DATABASE [XUANKE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [XUANKE] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [XUANKE] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [XUANKE] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [XUANKE] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [XUANKE] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [XUANKE] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [XUANKE] SET  READ_WRITE
GO
ALTER DATABASE [XUANKE] SET RECOVERY FULL
GO
ALTER DATABASE [XUANKE] SET  MULTI_USER
GO
ALTER DATABASE [XUANKE] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [XUANKE] SET DB_CHAINING OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'XUANKE', N'ON'
GO
USE [XUANKE]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 12/29/2020 14:11:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Student](
	[学号] [varchar](10) NOT NULL,
	[姓名] [varchar](10) NOT NULL,
	[专业名] [varchar](10) NOT NULL,
	[性别] [varchar](10) NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[学号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Stu_Course]    Script Date: 12/29/2020 14:11:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Stu_Course](
	[学号] [varchar](10) NOT NULL,
	[课程号] [varchar](10) NOT NULL,
	[成绩] [int] NULL,
 CONSTRAINT [PK_Stu_Course] PRIMARY KEY CLUSTERED 
(
	[学号] ASC,
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Login]    Script Date: 12/29/2020 14:11:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Login](
	[用户名] [varchar](10) NOT NULL,
	[密码] [varchar](10) NOT NULL,
 CONSTRAINT [PK_Loging] PRIMARY KEY CLUSTERED 
(
	[用户名] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Grade_count]    Script Date: 12/29/2020 14:11:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Grade_count](
	[课程号] [varchar](10) NOT NULL,
	[课程名] [varchar](10) NOT NULL,
	[选课人数] [int] NULL,
	[最高成绩] [int] NULL,
	[最低成绩] [int] NULL,
	[平均成绩] [float] NULL,
	[及格人数] [int] NULL,
 CONSTRAINT [PK_Grade_count] PRIMARY KEY CLUSTERED 
(
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Course]    Script Date: 12/29/2020 14:11:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Course](
	[课程号] [varchar](10) NOT NULL,
	[课程名] [varchar](10) NOT NULL,
	[学分] [int] NULL,
	[课时] [int] NULL,
	[开课学期] [int] NULL,
	[课程简介] [ntext] NULL,
	[是否通过审批] [bit] NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
