-- 查询mysql数据保存位置
-- /opt/homebrew/var/mysql/
show global variables like "%datadir%"


-- 查询二进制日志位置
show global variables like '%log_bin%'
show  variables  like  '%general%'

-- 查看数据字典
SELECT
    t.TABLE_SCHEMA AS 库名,
    t.TABLE_NAME AS 表名,
    t.COLUMN_NAME AS 字段名,
    t.COLUMN_TYPE AS 数据类型,
    CASE IFNULL(t.COLUMN_DEFAULT,'Null') 
        WHEN '' THEN '空字符串' 
        WHEN 'Null' THEN 'NULL' 
        ELSE t.COLUMN_DEFAULT END  AS 默认值,
    CASE t.IS_NULLABLE WHEN 'YES' THEN '是' ELSE '否' END AS 是否允许为空,
    t.COLUMN_COMMENT AS 字段说明
FROM information_schema.COLUMNS t 
WHERE t.TABLE_SCHEMA='xuanke' AND t.TABLE_NAME != 'not_selected_2006_2007'; -- AND t.TABLE_NAME='classes'
