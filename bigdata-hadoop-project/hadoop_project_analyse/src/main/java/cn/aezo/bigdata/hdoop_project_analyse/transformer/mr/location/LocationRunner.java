package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.location;

import cn.aezo.bigdata.hdoop_project_analyse.common.EventLogConstants;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsLocationDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.map.TextsOutputValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce.LocationReducerOutputValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.TransformerBaseRunner;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

/**
 * 统计location维度信息的入口类
 */
public class LocationRunner extends TransformerBaseRunner {
    private static final Logger logger = Logger.getLogger(LocationRunner.class);

    public static void main(String[] args) {
        LocationRunner runner = new LocationRunner();
        runner.setupRunner("location", LocationRunner.class, LocationMapper.class, LocationReducer.class, StatsLocationDimension.class, TextsOutputValue.class, StatsLocationDimension.class, LocationReducerOutputValue.class);
        try {
            runner.startRunner(args);
        } catch (Exception e) {
            logger.error("执行异常", e);
            throw new RuntimeException("执行location维度统计出现异常", e);
        }
    }

    @Override
    protected Filter fetchHbaseFilter() {
        FilterList list = new FilterList();
        String[] columns = new String[] { EventLogConstants.LOG_COLUMN_NAME_PLATFORM, // 平台
                EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME, // 服务器时间戳
                EventLogConstants.LOG_COLUMN_NAME_UUID, // 用户id
                EventLogConstants.LOG_COLUMN_NAME_SESSION_ID, // 会话id
                EventLogConstants.LOG_COLUMN_NAME_COUNTRY, // 国家
                EventLogConstants.LOG_COLUMN_NAME_PROVINCE, // 省份
                EventLogConstants.LOG_COLUMN_NAME_CITY, // 城市
                EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME, // 事件名称
        };
        list.addFilter(this.getColumnFilter(columns));
        // 过滤只需要pageview事件
        list.addFilter(new SingleColumnValueFilter(LocationMapper.family,
                Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME), CompareOp.EQUAL,
                Bytes.toBytes(EventLogConstants.EventEnum.PAGEVIEW.alias)));
        return list;
    }

}
