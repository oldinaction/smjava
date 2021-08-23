package cn.aezo.bigdata.hdoop_project_analyse.transformer.hive;


import java.io.IOException;

import cn.aezo.bigdata.hdoop_project_analyse.common.DateEnum;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.base.DateDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.IDimensionConverter;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.service.impl.DimensionConverterImpl;
import cn.aezo.bigdata.hdoop_project_analyse.util.TimeUtil;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

/**
 * 操作日期dimension 相关的udf
 */
public class DateDimensionUDF extends UDF {
    private IDimensionConverter converter = new DimensionConverterImpl();

    /**
     * 根据给定的日期（格式为:yyyy-MM-dd）至返回id
     * 
     * @param day
     * @return
     */
    public IntWritable evaluate(Text day) {
        DateDimension dimension = DateDimension.buildDate(TimeUtil.parseString2Long(day.toString()), DateEnum.DAY);
        try {
            int id = this.converter.getDimensionIdByValue(dimension);
            return new IntWritable(id);
        } catch (IOException e) {
            throw new RuntimeException("获取id异常");
        }
    }
}
