package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.base.BaseDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.BaseStatsValueWritable;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.IDimensionConverter;
import org.apache.hadoop.conf.Configuration;

/**
 * 自定义的配合自定义output进行具体sql输出的类
 */
public interface IOutputCollector {

    /**
     * 具体执行统计数据插入的方法
     * 
     * @param conf
     * @param key
     * @param value
     * @param pstmt
     * @param converter
     * @throws SQLException
     * @throws IOException
     */
    public void collect(Configuration conf, BaseDimension key, BaseStatsValueWritable value, PreparedStatement pstmt, IDimensionConverter converter) throws SQLException, IOException;
}
