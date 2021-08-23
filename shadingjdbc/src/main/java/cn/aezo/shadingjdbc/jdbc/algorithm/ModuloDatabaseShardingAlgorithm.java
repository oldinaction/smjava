package cn.aezo.shadingjdbc.jdbc.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

public final class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    
	/**
     *  select * from t_order where user_id = 10
     *          └── select * from ds_0.t_order where user_id = 10
     *  select * from t_order where user_id = 11
     *          └── select * from ds_1.t_order where user_id = 11
     */
    public String doEqualSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        for (String each : dataSourceNames) {
            if (each.endsWith(shardingValue.getValue() % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }
    
    public Collection<String> doInSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(dataSourceNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String dataSourceName : dataSourceNames) {
                if (dataSourceName.endsWith(value % 2 + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }
    
    public Collection<String> doBetweenSharding(final Collection<String> dataSourceNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(dataSourceNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : dataSourceNames) {
                if (each.endsWith(i % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
