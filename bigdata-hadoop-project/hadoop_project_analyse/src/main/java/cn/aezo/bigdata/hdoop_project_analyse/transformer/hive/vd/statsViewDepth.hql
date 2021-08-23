-- 1. 在hive中创建hbase的event_log对应表
CREATE EXTERNAL TABLE event_logs(key string, pl string, en string, s_time bigint, p_url string, u_ud string, u_sd string)
ROW FORMAT SERDE 'org.apache.hadoop.hive.hbase.HBaseSerDe'
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
with serdeproperties('hbase.columns.mapping'=':key,log:pl,log:en,log:s_time,log:p_url,log:u_ud,log:u_sd')
tblproperties('hbase.table.name'='eventlog');

-- 2. 创建mysql在hive中的对应表，hive中的表，执行HQL之后分析的结果保存该表，然后通过sqoop工具导出到mysql
CREATE TABLE `stats_view_depth` (`platform_dimension_id` bigint ,`data_dimension_id` bigint , `kpi_dimension_id` bigint , `pv1` bigint , `pv2` bigint , `pv3` bigint , `pv4` bigint , `pv5_10` bigint , `pv10_30` bigint , `pv30_60` bigint , `pv60_plus` bigint , `created` string);

-- 3. hive创建临时表:把hql分析之后的中间结果存放到当前的临时表。
CREATE TABLE `stats_view_depth_tmp`(`pl` string, `date` string, `col` string, `ct` bigint);

-- 4. 编写UDF(platformdimension & datedimension)<需要注意，要删除DimensionConvertClient类中所有FileSystem关闭的操作>
-- 5. 上传transformer-0.0.1.jar到hdfs的/sxt/transformer文件夹中
-- 6. 创建hive的function
#create function platform_convert as 'com.sxt.transformer.hive.PlatformDimensionUDF' using jar 'hdfs://sxt/sxt/transformer/transformer-0.0.1.jar';  
create function date_convert as 'com.sxt.transformer.hive.DateDimensionUDF' using jar 'hdfs://sxt/sxt/transformer/transformer-0.0.1.jar';  


-- 7. hql编写(统计用户角度的浏览深度)<注意：时间为外部给定>
from (
select pl, from_unixtime(cast(s_time/1000 as bigint),'yyyy-MM-dd') as day, u_ud, (case when count(p_url) = 1 then "pv1" when count(p_url) = 2 then "pv2" when count(p_url) = 3 then "pv3" when count(p_url) = 4 then "pv4" when count(p_url) >= 5 and count(p_url) <10 then "pv5_10" when count(p_url) >= 10 and count(p_url) <30 then "pv10_30" when count(p_url) >=30 and count(p_url) <60 then "pv30_60"  else 'pv60_plus' end) as pv 
from event_logs 
where en='e_pv' and p_url is not null and pl is not null and s_time >= unix_timestamp('2016-06-08','yyyy-MM-dd')*1000 and s_time < unix_timestamp('2016-06-09','yyyy-MM-dd')*1000
group by pl, from_unixtime(cast(s_time/1000 as bigint),'yyyy-MM-dd'), u_ud
) as tmp
insert overwrite table stats_view_depth_tmp select pl,day,pv,count(distinct u_ud) as ct where u_ud is not null group by pl,day,pv;

--把临时表的多行数据，转换一行

with tmp as 
(
select pl,`date` as date1,ct as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv1' union all
select pl,`date` as date1,0 as pv1,ct as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv2' union all
select pl,`date` as date1,0 as pv1,0 as pv2,ct as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv3' union all
select pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,ct as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv4' union all
select pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,ct as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv5_10' union all
select pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,ct as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv10_30' union all
select pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,ct as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv30_60' union all
select pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,ct as pv60_plus from stats_view_depth_tmp where col='pv60_plus' union all

select 'all' as pl,`date` as date1,ct as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv1' union all
select 'all' as pl,`date` as date1,0 as pv1,ct as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv2' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,ct as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv3' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,ct as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv4' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,ct as pv5_10,0 as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv5_10' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,ct as pv10_30,0 as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv10_30' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,ct as pv30_60,0 as pv60_plus from stats_view_depth_tmp where col='pv30_60' union all
select 'all' as pl,`date` as date1,0 as pv1,0 as pv2,0 as pv3,0 as pv4,0 as pv5_10,0 as pv10_30,0 as pv30_60,ct as pv60_plus from stats_view_depth_tmp where col='pv60_plus'
)
from tmp
insert overwrite table stats_view_depth select platform_convert(pl),date_convert(date1),5,sum(pv1),sum(pv2),sum(pv3),sum(pv4),sum(pv5_10),sum(pv10_30),sum(pv30_60),sum(pv60_plus),'2016-06-08' group by pl,date1;

-- 9. sqoop脚步编写(统计会话角度)
sqoop export --connect jdbc:mysql://node3:3306/report --username root --password 123 --table stats_view_depth --export-dir /hive/bigdater.db/stats_view_depth/* --input-fields-terminated-by "\\01" --update-mode allowinsert --update-key platform_dimension_id,data_dimension_id,kpi_dimension_id

-- 10. shell脚步编写
