package cn.aezo.shadingjdbc.yaml;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.dangdang.ddframe.rdb.sharding.config.yaml.api.YamlShardingDataSource;

public final class YamlWithDefaultDataSourceMain {
    
    // CHECKSTYLE:OFF
    public static void main(final String[] args) throws Exception {
    // CHECKSTYLE:ON
        YamlShardingDataSource dataSource =  new YamlShardingDataSource(
            new File(YamlWithDefaultDataSourceMain.class.getResource("withDefaultDataSource.yaml").getFile()));
        printJoinSelect(dataSource);
        printGroupBy(dataSource);
    }
    
    private static void printJoinSelect(final DataSource dataSource) throws SQLException {
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
    
    private static void printGroupBy(final DataSource dataSource) throws SQLException {
        String sql = "SELECT o.user_id, COUNT(*) FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id GROUP BY o.user_id";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("user_id: " + rs.getInt(1) + ", count: " + rs.getInt(2));
            }
        }
    }
}
