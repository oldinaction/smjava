package cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.reduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import cn.aezo.bigdata.hdoop_project_analyse.common.KpiType;
import cn.aezo.bigdata.hdoop_project_analyse.transformer.model.value.BaseStatsValueWritable;
import org.apache.hadoop.io.WritableUtils;

public class InboundReduceValue extends BaseStatsValueWritable {
    private KpiType kpi;
    private int uvs;
    private int visit;

    public int getUvs() {
        return uvs;
    }

    public void setUvs(int uvs) {
        this.uvs = uvs;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public void setKpi(KpiType kpi) {
        this.kpi = kpi;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.uvs);
        out.writeInt(this.visit);
        WritableUtils.writeEnum(out, this.kpi);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.uvs = in.readInt();
        this.visit = in.readInt();
        this.kpi = WritableUtils.readEnum(in, KpiType.class);
    }

    @Override
    public KpiType getKpi() {
        return this.kpi;
    }

}
