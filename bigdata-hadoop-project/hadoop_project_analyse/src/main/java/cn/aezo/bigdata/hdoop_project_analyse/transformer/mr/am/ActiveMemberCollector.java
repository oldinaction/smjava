package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.am;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.aezo.bigdata.hdoop_project_analyse.common.GlobalConstants;
import cn.aezo.bigdata.hdoop_project_analyse.common.KpiType;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsUserDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.base.BaseDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.BaseStatsValueWritable;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce.MapWritableValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.IOutputCollector;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.IDimensionConverter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;

/**
 * 定义具体的active member kpi的输出类
 */
public class ActiveMemberCollector implements IOutputCollector {

    @Override
    public void collect(Configuration conf, BaseDimension key, BaseStatsValueWritable value, PreparedStatement pstmt, IDimensionConverter converter) throws SQLException, IOException {
        // 第一步: 将key&value进行强制转换
        StatsUserDimension statsUser = (StatsUserDimension) key;
        IntWritable activeMembers = (IntWritable) ((MapWritableValue) value).getValue().get(new IntWritable(-1));

        int i = 0;
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getStatsCommon().getPlatform()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getStatsCommon().getDate()));
        if (KpiType.BROWSER_ACTIVE_MEMBER.name.equals(statsUser.getStatsCommon().getKpi().getKpiName())) {
            // 表示输出结果是统计browser active member的，那么进行browser维度信息设置
            pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getBrowser()));
        }
        pstmt.setInt(++i, activeMembers.get());
        pstmt.setString(++i, conf.get(GlobalConstants.RUNNING_DATE_PARAMES));
        pstmt.setInt(++i, activeMembers.get());

        // 将pstmt添加到批量执行中
        pstmt.addBatch();
    }

}
