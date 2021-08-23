package cn.aezo.shadingjdbc.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import cn.aezo.shadingjdbc.jdbc.algorithm.ModuloDatabaseShardingAlgorithm;
import cn.aezo.shadingjdbc.jdbc.algorithm.ModuloTableShardingAlgorithm;

import com.dangdang.ddframe.rdb.sharding.api.HintManager;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;
import com.dangdang.ddframe.rdb.transaction.soft.api.SoftTransactionManager;
import com.dangdang.ddframe.rdb.transaction.soft.api.config.NestedBestEffortsDeliveryJobConfiguration;
import com.dangdang.ddframe.rdb.transaction.soft.api.config.SoftTransactionConfiguration;
import com.dangdang.ddframe.rdb.transaction.soft.bed.BEDSoftTransaction;
import com.dangdang.ddframe.rdb.transaction.soft.constants.SoftTransactionType;
import com.google.common.base.Optional;

public final class Main {
	
	private static boolean useNestedJob = true;
    
	/*
	db0
	  ├── t_order_0               user_id为偶数   order_id为偶数
	  ├── t_order_1               user_id为偶数   order_id为奇数
	  ├── t_order_item_0          user_id为偶数   order_id为偶数
	  └── t_order_item_1          user_id为偶数   order_id为奇数
	db1
	  ├── t_order_0               user_id为奇数   order_id为偶数
	  ├── t_order_1               user_id为奇数   order_id为奇数
	  ├── t_order_item_0          user_id为奇数   order_id为偶数
	  └── t_order_item_1          user_id为奇数   order_id为奇数
	*/
    public static void main(final String[] args) throws SQLException {
    	System.out.println("--------------");
        DataSource dataSource = getShardingDataSource();
        
        printSimpleSelect(dataSource);
        
        System.out.println("--------------");
        printGroupBy(dataSource);
        
        System.out.println("--------------");
        printHintSimpleSelect(dataSource);
        
        System.out.println("--------------");
        //updateFailure(dataSource);
        
        System.out.println("--------------");
        //insert(dataSource);
        
        System.out.println("--------------");
        //delete(dataSource);
        
    }
    
    private static void insert(final DataSource dataSource) throws SQLException {
        String orderSql = "INSERT INTO t_order (order_id, user_id, status) VALUES (?, ?, ?)";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(orderSql)) {
        	preparedStatement.setInt(1, 1010);
        	preparedStatement.setInt(2, 10);
        	preparedStatement.setString(3, "INSERT");
        	int count = preparedStatement.executeUpdate();
        	System.out.println("INSERT " + count);
        }
    }
    
    private static void delete(final DataSource dataSource) throws SQLException {
        String orderSql = "DELETE `t_order` WHERE `order_id` = ? AND `user_id` = ?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(orderSql)) {
        	preparedStatement.setInt(1, 1010);
        	preparedStatement.setInt(2, 10);
        	int count = preparedStatement.executeUpdate();
        	System.out.println("DELETE " + count);
        }
    }
    
    // 查询
    private static void printSimpleSelect(final DataSource dataSource) throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 1001);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3));
                }
            }
        }
    }
    
    // 查询
    private static void printGroupBy(final DataSource dataSource) throws SQLException {
        String sql = "SELECT o.user_id, COUNT(*) FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id GROUP BY o.user_id";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)
                ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("user_id: " + rs.getInt(1) + ", count: " + rs.getInt(2));
            }
        }
    }
    
    // 基于暗示的分片建
    private static void printHintSimpleSelect(final DataSource dataSource) throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id";
        try (
                HintManager hintManager = HintManager.getInstance();
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            hintManager.addDatabaseShardingValue("t_order", "user_id", 12); // 添加数据源分片键值
            hintManager.addTableShardingValue("t_order", "order_id", 1001);
            try (ResultSet rs = preparedStatement.executeQuery()) {
            	System.out.println("item  order  user");
                while (rs.next()) {
                	System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3));
                }
            }
        }
    }
    
	// 柔性事物(最大努力送达型)(http://www.zhihu.com/question/31813039/answer/53437637)
    private static void updateFailure(final DataSource dataSource) throws SQLException {
        String sql1 = "UPDATE t_order SET status='UPDATE_1' WHERE user_id=10 AND order_id=1000";
        String sql2 = "UPDATE t_order SET not_existed_column=1 WHERE user_id=1 AND order_id=?";
        String sql3 = "UPDATE t_order SET status='UPDATE_2' WHERE user_id=10 AND order_id=1000";
        SoftTransactionManager transactionManager = new SoftTransactionManager(getSoftTransactionConfiguration(dataSource));
		transactionManager.init();
        BEDSoftTransaction transaction = (BEDSoftTransaction) transactionManager.getTransaction(SoftTransactionType.BestEffortsDelivery);
        Connection conn = null;
        try {
	        conn = dataSource.getConnection();
            transaction.begin(conn);
            
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setObject(1, 1000);
            PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
        } finally {
			transaction.end();
			if (conn != null) {
                conn.close();
            }
        }
    }
    
    // 设置数据源策略与表策略
    private static ShardingDataSource getShardingDataSource() {
    	// 设置逻辑库名表名、实际库名表名，供分片算法使用
        DataSourceRule dataSourceRule = new DataSourceRule(createDataSourceMap());
        TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1")).dataSourceRule(dataSourceRule).build();
        TableRule orderItemTableRule = TableRule.builder("t_order_item").actualTables(Arrays.asList("t_order_item_0", "t_order_item_1")).dataSourceRule(dataSourceRule).build();
        
        ShardingRule shardingRule = ShardingRule.builder()
        		.dataSourceRule(dataSourceRule)
        		.tableRules(Arrays.asList(orderTableRule, orderItemTableRule))
        			// 绑定表
                .bindingTableRules(Collections.singletonList(new BindingTableRule(Arrays.asList(orderTableRule, orderItemTableRule))))
                	// 设置数据源策略(user_id为分片键，在分片算法中利用分片键将逻辑库名表名转换为实际库名表名)
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDatabaseShardingAlgorithm()))
                	// 设置表策略
                .tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm())).build();
        return new ShardingDataSource(shardingRule);
    }
    
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(2);
        result.put("ds_0", createDataSource("ds_0"));
        result.put("ds_1", createDataSource("ds_1"));
        return result;
    }
    
    // DBCP
    private static DataSource createDataSource(final String dataSourceName) {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("root");
        return result;
    }
    
    private static DataSource createTransactionLogDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl("jdbc:mysql://localhost:3306/trans_log");
        result.setUsername("root");
        result.setPassword("root");
        return result;
    }
    
    private static SoftTransactionConfiguration getSoftTransactionConfiguration(final DataSource dataSource) {
        SoftTransactionConfiguration result = new SoftTransactionConfiguration(dataSource);
        if (useNestedJob) {
            result.setBestEffortsDeliveryJobConfiguration(Optional.of(new NestedBestEffortsDeliveryJobConfiguration()));
        }
        result.setTransactionLogDataSource(createTransactionLogDataSource());
        return result;
    }
}
