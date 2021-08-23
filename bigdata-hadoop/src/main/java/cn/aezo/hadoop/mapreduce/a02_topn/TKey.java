package cn.aezo.hadoop.mapreduce.a02_topn;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// 自定义类型必须实现接口：序列化、反序列化、比较器
public class TKey implements WritableComparable<TKey> {
    private int year ;
    private int month;
    private int day;
    // 温度
    private int wd;
    // 地区
    private String location;

    @Override
    public int compareTo(TKey that) {
        // 为了让案例中使用sortComparator，此处先按正序排（实际此处按照降序排之后，就无需额外定义排序器）
        int c1 = Integer.compare(this.year, that.getYear());
        if(c1 == 0){
            int c2 = Integer.compare(this.month, that.getMonth());
            if(c2 == 0) {
                return Integer.compare(this.day,that.getDay());
            }
            return  c2;
        }
        return c1;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        // 一次把数据写入到字节数组
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeInt(wd);
        out.writeUTF(location);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        // 按顺序从字节数组中读取数据
        this.year = in.readInt();
        this.month=in.readInt();
        this.day=in.readInt();
        this.wd=in.readInt();
        this.location = in.readUTF();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
