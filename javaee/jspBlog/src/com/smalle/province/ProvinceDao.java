package com.smalle.province;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProvinceDao {
	private static String url = "jdbc:mysql://127.0.0.1:3306/smalle?characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "root";
	
	/**
	 * 连接数据库
	 * <BR>
	 * @method getConnection 
	 * @author oldinaction
	 * @date 2014年11月9日-下午3:45:03
	 * @return Connection <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 列举所有的省
	 * <BR>
	 * @method findProvinces 
	 * @author oldinaction
	 * @date 2014年11月9日-下午3:21:41
	 * @return List<HashMap<String,Object>> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<HashMap<String, Object>> findProvinces() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		List<HashMap<String, Object>> maps = null;
		try {
			String sql = "SELECT id,name FROM tm_province order by sort asc";
			connection = getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("name", rs.getString("name"));
				maps.add(map);
			}
			return maps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null) rs.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException sql){
				sql.printStackTrace();
			}
		}	
	}
	
	/**
	 * 列举相应省下面的市级
	 * <BR>
	 * @method findCites 
	 * @author oldinaction
	 * @date 2014年11月9日-下午3:35:27
	 * @param provinceId
	 * @return List<HashMap<String,Object>> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<HashMap<String, Object>> findCites(int provinceId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<HashMap<String, Object>> maps = null;
		try {
			String sql = "SELECT id,name FROM tm_city WHERE province_id = ?";
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, provinceId);
			rs = statement.executeQuery();
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("name", rs.getString("name"));
				maps.add(map);
			}
			return maps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null) rs.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException sql){
				sql.printStackTrace();
			}
		}	
	}
	
	/**
	 * 列举相应市下面的区级
	 * <BR>
	 * @method findAreas 
	 * @author oldinaction
	 * @date 2014年11月9日-下午3:44:25
	 * @param CityId
	 * @return List<HashMap<String,Object>> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<HashMap<String, Object>> findAreas(int CityId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<HashMap<String, Object>> maps = null;
		try {
			String sql = "SELECT id,name FROM tm_area WHERE city_id = ?";
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, CityId);
			rs = statement.executeQuery();
			maps = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = null;
			while(rs.next()) {
				map = new HashMap<String, Object>();
				map.put("id", rs.getInt("id"));
				map.put("name", rs.getString("name"));
				maps.add(map);
			}
			return maps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				if(rs!=null) rs.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException sql){
				sql.printStackTrace();
			}
		}	
	}
	
	
	public static void main(String[] args) {
		System.out.println(getConnection());
		
		System.out.println(findProvinces());
		
		System.out.println(findCites(420000));
		
		System.out.println(findAreas(420100));
	}
	
}
