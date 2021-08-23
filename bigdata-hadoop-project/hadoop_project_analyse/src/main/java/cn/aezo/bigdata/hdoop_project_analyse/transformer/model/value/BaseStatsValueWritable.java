package cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value;

import cn.aezo.bigdata.hdoop_project_analyse.common.KpiType;
import org.apache.hadoop.io.Writable;

/**
 * 自定义顶级的输出value父类
 */
public abstract class BaseStatsValueWritable implements Writable {
    /**
     * 获取当前value对应的kpi值
     * 
     * @return
     */
    public abstract KpiType getKpi();
}
