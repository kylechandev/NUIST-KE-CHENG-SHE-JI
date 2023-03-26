/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : SQL Server
 Source Server Version : 14003048
 Source Host           : localhost:1433
 Source Catalog        : 学生数据库
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003048
 File Encoding         : 65001

 Date: 28/03/2021 16:24:34
*/


-- ----------------------------
-- Table structure for C Teacher
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[C Teacher]') AND type IN ('U'))
	DROP TABLE [dbo].[Teacher]
GO

CREATE TABLE [dbo].[Teacher] (
  [Tno] nchar(8) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Tname] nchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Salary] decimal(4)  NULL,
  [title] nchar(4) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[C Teacher] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of C Teacher
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[Teacher] VALUES (N'T001    ', N'张美霞       ', N'5000', NULL)
GO

INSERT INTO [dbo].[Teacher] VALUES (N'T002    ', N'王洪林       ', N'5500', NULL)
GO

INSERT INTO [dbo].[Teacher] VALUES (N'T003    ', N'李丽芬       ', N'4800', NULL)
GO

INSERT INTO [dbo].[Teacher] VALUES (N'T004    ', N'周良水       ', N'6000', NULL)
GO

INSERT INTO [dbo].[Teacher] VALUES (N'T005    ', N'吴翔        ', N'7000', NULL)
GO

COMMIT
GO


-- ----------------------------
-- Table structure for Course
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Course]') AND type IN ('U'))
	DROP TABLE [dbo].[Course]
GO

CREATE TABLE [dbo].[Course] (
  [Cno] nchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Cname] nchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Credit] tinyint  NULL,
  [Semester] smallint  NULL
)
GO

ALTER TABLE [dbo].[Course] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Course
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[Course] VALUES (N'C001      ', N'高等数学                ', N'4', N'1')
GO

INSERT INTO [dbo].[Course] VALUES (N'C002      ', N'大学英语                ', N'3', N'1')
GO

INSERT INTO [dbo].[Course] VALUES (N'C003      ', N'大学英语                ', N'3', N'2')
GO

INSERT INTO [dbo].[Course] VALUES (N'C004      ', N'计算机文化学              ', N'2', N'0')
GO

INSERT INTO [dbo].[Course] VALUES (N'C005      ', N'VB                  ', N'2', N'3')
GO

INSERT INTO [dbo].[Course] VALUES (N'C006      ', N'数据库基础               ', N'4', N'5')
GO

INSERT INTO [dbo].[Course] VALUES (N'C007      ', N'数据结构                ', N'4', N'4')
GO

INSERT INTO [dbo].[Course] VALUES (N'C008      ', N'计算机网络               ', N'4', N'4')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for SC
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[CSC]') AND type IN ('U'))
	DROP TABLE [dbo].[SC]
GO

CREATE TABLE [dbo].[SC] (
  [Sno] nchar(7) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Cno] nchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Grade] smallint  NULL
)
GO

ALTER TABLE [dbo].[SC] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of CSC
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[SC] VALUES (N'0811101', N'C001      ', N'96')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811101', N'C002      ', N'80')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811101', N'C003      ', N'84')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811101', N'C005      ', N'62')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811102', N'C001      ', N'92')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811102', N'C002      ', N'90')
GO

INSERT INTO [dbo].[SC] VALUES (N'0811102', N'C004      ', N'84')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821102', N'C001      ', N'76')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821102', N'C004      ', N'86')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821102', N'C005      ', N'73')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821102', N'C007      ', N'0')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821103', N'C001      ', N'50')
GO

INSERT INTO [dbo].[SC] VALUES (N'0821103', N'C004      ', N'80')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831101', N'C001      ', N'50')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831101', N'C004      ', N'80')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831102', N'C007      ', N'0')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831103', N'C004      ', N'78')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831103', N'C005      ', N'65')
GO

INSERT INTO [dbo].[SC] VALUES (N'0831103', N'C007      ', N'0')
GO

COMMIT
GO


-- ----------------------------
-- Table structure for Student
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Student]') AND type IN ('U'))
	DROP TABLE [dbo].[Student]
GO

CREATE TABLE [dbo].[Student] (
  [Sno] nchar(7) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Sname] nchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Ssex] nchar(2) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Sage] tinyint  NULL,
  [Sdept] nchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[Student] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Student
-- ----------------------------
BEGIN TRANSACTION
GO

INSERT INTO [dbo].[Student] VALUES (N'0811101', N'李勇        ', N'男 ', N'21', N'计算机系                ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0811102', N'刘晨        ', N'男 ', N'20', N'计算机系                ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0811103', N'王敏        ', N'女 ', N'20', N'计算机系                ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0811104', N'张小红       ', N'女 ', N'19', N'计算机系                ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0821101', N'张立        ', N'男 ', N'20', N'信息管理系               ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0821102', N'吴宾        ', N'女 ', N'19', N'信息管理系               ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0821103', N'张海        ', N'男 ', N'20', N'信息管理系               ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0831101', N'钱小平       ', N'女 ', N'21', N'通信工程系               ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0831102', N'王大力       ', N'男 ', N'20', N'通信工程系               ')
GO

INSERT INTO [dbo].[Student] VALUES (N'0831103', N'张姗姗       ', N'女 ', N'19', N'通信工程系               ')
GO

COMMIT
GO


-- ----------------------------
-- Uniques structure for table C Teacher
-- ----------------------------
ALTER TABLE [dbo].[C Teacher] ADD CONSTRAINT [UQ__C Teache__C450026CBE330CC2] UNIQUE NONCLUSTERED ([Tno] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Course
-- ----------------------------
ALTER TABLE [dbo].[Course] ADD CONSTRAINT [PK__Course__C1FE63738D2E84AA] PRIMARY KEY CLUSTERED ([Cno])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Student
-- ----------------------------
ALTER TABLE [dbo].[Student] ADD CONSTRAINT [PK__Student__CA1FE464F624B17F] PRIMARY KEY CLUSTERED ([Sno])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table CSC
-- ----------------------------
ALTER TABLE [dbo].[CSC] ADD CONSTRAINT [FK__CSC__Sno__3A81B327] FOREIGN KEY ([Sno]) REFERENCES [dbo].[Student] ([Sno]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[CSC] ADD CONSTRAINT [FK__CSC__Cno__3B75D760] FOREIGN KEY ([Cno]) REFERENCES [dbo].[Course] ([Cno]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

