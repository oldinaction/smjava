package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.au;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.aezo.bigdata.hdoop_project_analyse.common.GlobalConstants;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsUserDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.base.BaseDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.BaseStatsValueWritable;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce.MapWritableValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.IOutputCollector;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.IDimensionConverter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;

public class HourlyActiveUserCollector implements IOutputCollector {

    @Override
    public void collect(Configuration conf, BaseDimension key, BaseStatsValueWritable value, PreparedStatement pstmt, IDimensionConverter converter) throws SQLException, IOException {
        StatsUserDimension statsUser = (StatsUserDimension) key;
        MapWritableValue mapWritableValue = (MapWritableValue) value;
        MapWritable map = mapWritableValue.getValue();

        // hourly_active_user
        int i = 0;
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getStatsCommon().getPlatform()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getStatsCommon().getDate()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(statsUser.getStatsCommon().getKpi())); // 根据kpi

        // 设置每个小时的情况
        for (i++; i < 28; i++) {
            int v = ((IntWritable)map.get(new IntWritable(i - 4))).get();
            pstmt.setInt(i, v);
            pstmt.setInt(i + 25, v);
        }

        pstmt.setString(i, conf.get(GlobalConstants.RUNNING_DATE_PARAMES));
        pstmt.addBatch();
    }

}
