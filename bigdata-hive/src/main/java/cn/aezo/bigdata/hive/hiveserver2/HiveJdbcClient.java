package cn.aezo.bigdata.hive.hiveserver2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJdbcClient {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = DriverManager.getConnection("jdbc:hive2://node03:10000/default", "test", "");
        Statement stmt = conn.createStatement();
        String sql = "select * from psn";
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            // 1-smalle
            System.out.println(res.getString(1) + "-" + res.getString("name"));
        }
    }
}
