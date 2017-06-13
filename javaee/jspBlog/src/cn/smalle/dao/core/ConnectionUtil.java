package cn.smalle.dao.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	public static final String Driver = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/jspblog?characterEncoding=utf8";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	/**
	 * 获取数据库连接
	 * <BR>
	 * @method getConnection 
	 * @author oldinaction
	 * @date 2014年11月23日-上午11:17:56
	 * @return Connection <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(Driver);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭数据库连接
	 * <BR>
	 * @method closeConnection 
	 * @author oldinaction
	 * @date 2014年11月23日-下午2:01:43
	 * @param connection void <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if(preparedStatement != null) preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
}
