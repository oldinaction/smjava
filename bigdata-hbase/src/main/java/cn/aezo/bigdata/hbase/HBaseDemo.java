package cn.aezo.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HBaseDemo {

    Configuration conf = null;
    Connection conn = null;
    //表的管理对象
    Admin admin = null;
    Table table = null;
    //创建表的对象
    TableName tableName = TableName.valueOf("phone");

    @Before
    public void init() throws IOException {
        //创建配置文件对象
        conf = HBaseConfiguration.create();
        //加载zookeeper的配置
        conf.set("hbase.zookeeper.quorum","node01,node02,node03");
        //获取连接
        conn = ConnectionFactory.createConnection(conf);
        //获取对象
        admin = conn.getAdmin();
        //获取数据操作对象
        table = conn.getTable(tableName);
    }

    @After
    public void destory(){
        try {
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTable() throws IOException {
        //定义表描述对象
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
        //定义列族描述对象
        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder("cf".getBytes());
        //添加列族信息给表
        tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
        if(admin.tableExists(tableName)){
            // 必须先禁用表才能删除表
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        //创建表
        admin.createTable(tableDescriptorBuilder.build());
    }

    /**
     * ROW                                                               COLUMN+CELL
     *  1                                                                column=cf:age, timestamp=2021-07-19T23:10:05.370, value=18
     *  1                                                                column=cf:name, timestamp=2021-07-19T23:10:05.370, value=zhangsan
     *  1                                                                column=cf:sex, timestamp=2021-07-19T23:10:05.370, value=man
     */
    @Test
    public void insert() throws IOException {
        // HBase所有的数据需要转成字节数组
        Put put = new Put(Bytes.toBytes("1"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("name"),Bytes.toBytes("zhangsan"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("age"),Bytes.toBytes("18"));
        put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("sex"),Bytes.toBytes("man"));
        table.put(put);
    }

    /**
     * 通过get获取数据
     * @throws IOException
     */
    @Test
    public void get() throws IOException {
        Get get = new Get(Bytes.toBytes("1"));
        //在服务端做数据过滤，挑选出符合需求的列。如果不设置会把当前行的所有列都取出
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("name"));
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("age"));
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("sex"));
        Result result = table.get(get);
        Cell cell1 = result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("name"));
        Cell cell2 = result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("age"));
        Cell cell3 = result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("sex"));
        String name = Bytes.toString(CellUtil.cloneValue(cell1));
        String age = Bytes.toString(CellUtil.cloneValue(cell2));
        String sex = Bytes.toString(CellUtil.cloneValue(cell3));
        System.out.println(name); // zhangsan
        System.out.println(age); // 18
        System.out.println(sex); // man
    }

    /**
     * 获取表中所有的记录
     */
    @Test
    public void scan() throws IOException {
        Scan scan = new Scan();
        // scan.withStartRow();
        // scan.withStopRow();
        ResultScanner rss = table.getScanner(scan);
        for (Result rs: rss) {
            Cell cell1 = rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("name"));
            Cell cell2 = rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("age"));
            Cell cell3 = rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("sex"));
            String name = Bytes.toString(CellUtil.cloneValue(cell1));
            String age = Bytes.toString(CellUtil.cloneValue(cell2));
            String sex = Bytes.toString(CellUtil.cloneValue(cell3));
            System.out.println(name);
            System.out.println(age);
            System.out.println(sex);
        }
    }

    // public void delete() throws IOException {
    //     Delete delete = new Delete("1".getBytes());
    //     table.delete(delete);
    // }

    // ========================== 基于原始数据测试通话记录

    /**
     * 假设有10个用户，每个用户一年产生10000条记录
     *
     * 每插入一条数据hfile格式如下(弊端，重复的key太长，可将所有值合并以对象的形式提交，参考下文ProtoBuf序列化)：
     * K: 15894059762_9223370490667733807/cf:date/1626707671567/Put/vlen=14/seqid=9 V: 20190100002402
     * K: 15894059762_9223370490667733807/cf:dnum/1626707671567/Put/vlen=11/seqid=9 V: 17747324329
     * K: 15894059762_9223370490667733807/cf:length/1626707671567/Put/vlen=2/seqid=9 V: 62
     * K: 15894059762_9223370490667733807/cf:type/1626707671567/Put/vlen=1/seqid=9 V: 0
     */
    @Test
    public void insertManyData() throws Exception {
        List<Put> puts = new ArrayList<>();
        for(int i = 0;i<10;i++){
            String phoneNumber = getNumber("158");
            for(int j = 0 ;j<10000;j++){
                String dnum = getNumber("177");
                String length = String.valueOf(random.nextInt(100));
                String date = getDate("2019");
                String type = String.valueOf(random.nextInt(2));
                // rowKey: 基于电话号码_"时间"降序插入(从而将新的显示在上面)
                String rowKey = phoneNumber + "_" + (Long.MAX_VALUE - sdf.parse(date).getTime());
                Put put = new Put(Bytes.toBytes(rowKey));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("dnum"),Bytes.toBytes(dnum));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("length"),Bytes.toBytes(length));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("date"),Bytes.toBytes(date));
                // type=1主叫, type=0被叫
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("type"),Bytes.toBytes(type));
                puts.add(put);
            }
        }
        table.put(puts);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private String getDate(String s) {
        return s + String.format("%02d%02d%02d%02d%02d",
                random.nextInt(12)+1,random.nextInt(31),
                random.nextInt(24),random.nextInt(60),random.nextInt(60));
    }

    Random random = new Random();
    public String getNumber(String str){
        return str+String.format("%08d",random.nextInt(99999999));
    }

    /**
     * 查询某一个用户3月份的通话记录
     *
     * 17700233072--1--2--20190330224547
     * 17717070538--1--29--20190330223550
     * ...
     * 17716264968--0--56--20190301003236
     * 17730830313--1--65--20190229000453
     */
    @Test
    public void scanByCondition() throws Exception {
        Scan scan = new Scan();
        // 可先从数据查到一个随机生成的电话
        String startRow = "15803261499_"+(Long.MAX_VALUE-sdf.parse("20190331000000").getTime());
        String stopRow = "15803261499_"+(Long.MAX_VALUE-sdf.parse("20190301000000").getTime());
        scan.withStartRow(Bytes.toBytes(startRow));
        scan.withStopRow(Bytes.toBytes(stopRow));
        ResultScanner rss = table.getScanner(scan);
        for (Result rs:rss) {
            System.out.print(Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("dnum")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("type")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("length")))));
            System.out.println("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("date")))));
        }
    }

    /**
     * 查询某个用户所有的主叫电话（type=1）
     * 17799333115--1--71--20191230235100
     * 17751261096--1--16--20191230203001
     * ...
     * 17786256027--1--86--20190100041527
     * 17746066937--1--71--20190100001450
     */
    @Test
    public void getTypeByScanFilter() throws IOException {
        Scan scan = new Scan();
        //创建过滤器集合，HBase提供多种过滤器
        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        //创建过滤器
        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes("cf"),Bytes.toBytes("type"),CompareOperator.EQUAL,Bytes.toBytes("1"));
        filters.addFilter(filter1);
        //前缀过滤器
        PrefixFilter filter2 = new PrefixFilter(Bytes.toBytes("15803261499"));
        filters.addFilter(filter2);
        scan.setFilter(filters);
        ResultScanner rss = table.getScanner(scan);
        for (Result rs:rss) {
            System.out.print(Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("dnum")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("type")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("length")))));
            System.out.println("--"+Bytes.toString(CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("date")))));
        }
    }

    // ===================== 基于ProtoBuf序列化(存储空间更小)测试通话记录

    /**
     * 保存的数据如(scan查看)：
     * 15804800005_9223370459882064807                                     column=cf:phone, timestamp=2021-07-19T23:34:56.798, value=\x0A\x0B17716244196\x12\x0262\x1A\x010"\x0E20191222075831
     * 15804800005_9223370459884573807                                     column=cf:phone, timestamp=2021-07-19T23:34:56.798, value=\x0A\x0B17741530312\x12\x0228\x1A\x011"\x0E20191222071642
     * 15804800005_9223370459894120807                                     column=cf:phone, timestamp=2021-07-19T23:34:56.798, value=\x0A\x0B17726052445\x12\x0251\x1A\x011"\x0E20191222043735
     *
     * hfile查看格式如：
     * K: 15837361825_9223370475391601807/cf:phone/1626708899430/Put/vlen=36/seqid=6 V: \x0A\x0B17797821003\x12\x0237\x1A\x011"\x0E20190625194614
     * K: 15837361825_9223370475413054807/cf:phone/1626708899430/Put/vlen=36/seqid=6 V: \x0A\x0B17704808683\x12\x0258\x1A\x011"\x0E20190625134841
     * K: 15837361825_9223370475418711807/cf:phone/1626708899430/Put/vlen=36/seqid=6 V: \x0A\x0B17770997313\x12\x0247\x1A\x011"\x0E20190625121424
     * K: 15837361825_9223370475420841807/cf:phone/1626708899430/Put/vlen=36/seqid=6 V: \x0A\x0B17790869307\x12\x0256\x1A\x011"\x0E20190625113854
     */
    @Test
    public void insertByProtoBuf() throws ParseException, IOException {
        List<Put> puts = new ArrayList<>();
        for(int i = 0;i<10;i++){
            String phoneNumber = getNumber("158");
            for(int j = 0 ;j<10000;j++){
                String dnum = getNumber("177");
                String length = String.valueOf(random.nextInt(100));
                String date = getDate("2019");
                String type = String.valueOf(random.nextInt(2));
                // rowKey
                String rowKey = phoneNumber+"_"+(Long.MAX_VALUE-sdf.parse(date).getTime());
                // 使用 ProtoBuf 自动生成的类
                Phone.PhoneDetail.Builder builder = Phone.PhoneDetail.newBuilder();
                builder.setDate(date);
                builder.setDnum(dnum);
                builder.setLength(length);
                builder.setType(type);
                Put put = new Put(Bytes.toBytes(rowKey));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("phone"),builder.build().toByteArray());
                puts.add(put);
            }
        }
        table.put(puts);
    }

    @Test
    public void getByProtoBuf() throws IOException {
        // 可先从数据查到一个随机生成的记录
        Get get = new Get("15804800005_9223370459871794807".getBytes());
        Result rs = table.get(get);
        byte[] b = CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("phone")));
        Phone.PhoneDetail phoneDetail = Phone.PhoneDetail.parseFrom(b);
        /**
         * dnum: "17765838241"
         * length: "66"
         * type: "1"
         * date: "20191222104941"
         */
        System.out.println(phoneDetail);
    }
}
