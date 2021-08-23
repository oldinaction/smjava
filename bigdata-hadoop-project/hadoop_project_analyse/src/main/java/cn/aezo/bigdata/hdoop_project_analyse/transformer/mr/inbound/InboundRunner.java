package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.inbound;

import cn.aezo.bigdata.hdoop_project_analyse.common.EventLogConstants;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsInboundDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.map.TextsOutputValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce.InboundReduceValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.TransformerBaseRunner;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

/**
 * 计算活跃用户和总会话的入口类
 */
public class InboundRunner extends TransformerBaseRunner {
    private static final Logger logger = Logger.getLogger(InboundRunner.class);

    public static void main(String[] args) {
        InboundRunner runner = new InboundRunner();
        runner.setupRunner("inbound", InboundRunner.class, InboundMapper.class, InboundReducer.class, StatsInboundDimension.class, TextsOutputValue.class, StatsInboundDimension.class, InboundReduceValue.class);
        try {
            runner.startRunner(args);
        } catch (Exception e) {
            logger.error("执行异常", e);
            throw new RuntimeException("执行异常", e);
        }
    }

    @Override
    protected Filter fetchHbaseFilter() {
        FilterList list = new FilterList();
        String[] columns = new String[] { EventLogConstants.LOG_COLUMN_NAME_REFERRER_URL, // 前一个页面的url
                EventLogConstants.LOG_COLUMN_NAME_UUID, // uuid
                EventLogConstants.LOG_COLUMN_NAME_SESSION_ID, // 会话id
                EventLogConstants.LOG_COLUMN_NAME_PLATFORM, // 平台名称
                EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME, // 服务器时间
                EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME // 事件名称
        };
        list.addFilter(this.getColumnFilter(columns));
        list.addFilter(new SingleColumnValueFilter(InboundMapper.family,
                Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME), CompareOp.EQUAL,
                Bytes.toBytes(EventLogConstants.EventEnum.PAGEVIEW.alias)));
        return list;
    }

}
