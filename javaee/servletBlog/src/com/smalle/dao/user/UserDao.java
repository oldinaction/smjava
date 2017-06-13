package com.smalle.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.smalle.dao.core.TmConnectionUtil;

public class UserDao {
	/**
	 * 根据用户名和密码查询用户信息
	 * 
	 * 方法名：findUser<BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月19日-下午9:04:22 <BR> 
	 * @param account
	 * @param password
	 * @return HashMap<String,Object><BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static HashMap<String, Object> findUser(String account, String password){
		String sql  = "SELECT * FROM user WHERE account = '" + account + "' AND password = '" + password + "'"; //user指对应数据库中的表
		Connection connection = TmConnectionUtil.getConnection(); //1:获取连接对象
		Statement statement = null; //2:获取处理(缓冲区)对象
		ResultSet rs = null; //3:获取结果集 
		HashMap<String, Object> hashMap = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next()){ //4:解析结果集
				hashMap = new HashMap<String, Object>();
				hashMap.put("id", rs.getInt("id"));
				hashMap.put("account", rs.getString("account"));
				hashMap.put("password", rs.getString("password"));
				hashMap.put("nickname",rs.getString("nickname"));
				hashMap.put("headerPic",rs.getString("headerpic"));
				hashMap.put("age",rs.getInt("age"));
				hashMap.put("male",rs.getInt("male"));
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("email",rs.getString("email"));
				hashMap.put("address",rs.getString("address"));
				hashMap.put("description",rs.getString("description"));
				hashMap.put("telephone",rs.getString("telephone"));
				hashMap.put("birthday",rs.getDate("birthday"));
			}
			return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //5:关闭连接对象
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * 方法名：findUser<BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月19日-下午9:04:22 <BR> 
	 * @param account
	 * @param password
	 * @return HashMap<String,Object><BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static HashMap<String, Object> findUser(String account){
		String sql  = "SELECT * FROM user WHERE account = '" + account + "'";
		Connection connection = TmConnectionUtil.getConnection(); //1:获取连接对象
		Statement statement = null; //2:获取处理(缓冲区)对象
		ResultSet rs = null; //3:获取结果集 
		HashMap<String, Object> hashMap = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next()){ //4:解析结果集
				hashMap = new HashMap<String, Object>();
				hashMap.put("id", rs.getInt("id"));
				hashMap.put("account", rs.getString("account"));
				hashMap.put("password", rs.getString("password"));
				hashMap.put("nickname",rs.getString("nickname"));
				hashMap.put("headerPic",rs.getString("headerpic"));
				hashMap.put("age",rs.getInt("age"));
				hashMap.put("male",rs.getInt("male"));
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("email",rs.getString("email"));
				hashMap.put("address",rs.getString("address"));
				hashMap.put("description",rs.getString("description"));
				hashMap.put("telephone",rs.getString("telephone"));
				hashMap.put("birthday",rs.getDate("birthday"));
			}
			return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //5:关闭连接对象
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 根据id来获取用户的信息
	 * <BR>
	 * @method getUserExist 
	 * @author oldinaction
	 * @date 2014年10月25日-下午7:13:57
	 * @param account
	 * @param password
	 * @return boolean <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static HashMap<String, Object> findUser(int id){
		String sql  = "SELECT * FROM user WHERE id = " + id;
		Connection connection = TmConnectionUtil.getConnection(); //1:获取连接对象
		Statement statement = null; //2:获取处理(缓冲区)对象
		ResultSet rs = null; //3:获取结果集 
		HashMap<String, Object> hashMap = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if(rs.next()){ //4:解析结果集
				hashMap = new HashMap<String, Object>();
				hashMap.put("id", rs.getInt("id"));
				hashMap.put("account", rs.getString("account"));
				hashMap.put("password", rs.getString("password"));
				hashMap.put("nickname",rs.getString("nickname"));
				hashMap.put("headerPic",rs.getString("headerpic")); //数据库的的字段名一般用小写
				hashMap.put("age",rs.getInt("age"));
				hashMap.put("male",rs.getInt("male"));
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("email",rs.getString("email"));
				hashMap.put("address",rs.getString("address"));
				hashMap.put("description",rs.getString("description"));
				hashMap.put("telephone",rs.getString("telephone"));
				hashMap.put("birthday",rs.getDate("birthday"));
			}
			return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally { //5:关闭连接对象
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean getUserExist(String account,String password){
		String sql = "select count(1) from tm_user WHERE account ='" + account + "' and 'password' = '" + password + "'";
		//1:获取连接对象
		Connection connection = null;
		//2:获取处理块(缓冲区)对象
		Statement statement = null;
		//3:获取结果集 (select===executeQuery   update insert delete ===executeUpdate)
		ResultSet rs = null;
		try {
			connection = TmConnectionUtil.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			//4:解析结果集(如果结果集返回的是唯一的一条数据就用if，如果结果集有多条就用while,一般都使用我们的集合作为返回值类型List)
			//while(rs.next()){}
			int count = 0;
			if(rs.next()){ //游标
				count = rs.getInt(1); //此处返回值的下标是从1开始，不是0
			}
			System.out.println(sql);
			return count  > 0 ? true :false;
		} catch (SQLException e) {
			return false;
		} finally {
			//5:关闭连接对象(后打开的先关闭,先打开的后关闭)
			try {
				if(rs != null) rs.close();
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		HashMap<String, Object> map = findUser("smalle", "123456");
		if(null != map) {
			System.out.println("map" + map);
		}
		
		HashMap<String, Object> map1 = findUser("smalle");
		if(null != map1) {
			System.out.println("map1" + map1);
		}
		
		HashMap<String, Object> map2 = findUser(1);
		if(null != map2) {
			System.out.println("map2" + map2);
		}
	}
}

