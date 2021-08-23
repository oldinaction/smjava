package cn.aezo.hadoop.mapreduce.a02_topn;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TGroupingComparator  extends WritableComparator {

    public  TGroupingComparator(){
        super(TKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // 因为Map中按照年分区，从而同一年份的数据会进入到一个reduceTask
        // 而实际需要计算某个月份，因此需先按着年-月分组在走后续的reduce算法
        TKey k1 = (TKey)a;
        TKey k2 = (TKey)b;
        int c1 = Integer.compare(k1.getYear(), k2.getYear());
        if(c1 == 0 ){
            return  Integer.compare(k1.getMonth(), k2.getMonth());
        }
        return c1;
    }
}
