package cn.aezo.bigdata.hdoop_project_analyse.transformer.mr.au;

import java.io.IOException;

import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.dim.StatsUserDimension;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.map.TimeOutputValue;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * combine类
 */
public class ActiveUserCombine extends Reducer<StatsUserDimension, TimeOutputValue, StatsUserDimension, TimeOutputValue> {
    @Override
    protected void reduce(StatsUserDimension key, Iterable<TimeOutputValue> values, Context context) throws IOException, InterruptedException {
        for (TimeOutputValue tov : values) {
            context.write(key, tov);
        }
    }
}
