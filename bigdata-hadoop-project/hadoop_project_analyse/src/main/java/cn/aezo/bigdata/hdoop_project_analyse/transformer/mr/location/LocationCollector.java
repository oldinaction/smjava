package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.location;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.aezo.bigdata.hdoop_project_analyse.common.GlobalConstants;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsLocationDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.base.BaseDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.BaseStatsValueWritable;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce.LocationReducerOutputValue;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.IOutputCollector;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.IDimensionConverter;
import org.apache.hadoop.conf.Configuration;

public class LocationCollector implements IOutputCollector {

    @Override
    public void collect(Configuration conf, BaseDimension key, BaseStatsValueWritable value, PreparedStatement pstmt, IDimensionConverter converter) throws SQLException, IOException {
        StatsLocationDimension locationDimension = (StatsLocationDimension) key;
        LocationReducerOutputValue locationReducerOutputValue = (LocationReducerOutputValue) value;

        int i = 0;
        pstmt.setInt(++i, converter.getDimensionIdByValue(locationDimension.getStatsCommon().getPlatform()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(locationDimension.getStatsCommon().getDate()));
        pstmt.setInt(++i, converter.getDimensionIdByValue(locationDimension.getLocation()));
        pstmt.setInt(++i, locationReducerOutputValue.getUvs());
        pstmt.setInt(++i, locationReducerOutputValue.getVisits());
        pstmt.setInt(++i, locationReducerOutputValue.getBounceNumber());
        pstmt.setString(++i, conf.get(GlobalConstants.RUNNING_DATE_PARAMES));
        pstmt.setInt(++i, locationReducerOutputValue.getUvs());
        pstmt.setInt(++i, locationReducerOutputValue.getVisits());
        pstmt.setInt(++i, locationReducerOutputValue.getBounceNumber());

        pstmt.addBatch();
        
        
    }

}
