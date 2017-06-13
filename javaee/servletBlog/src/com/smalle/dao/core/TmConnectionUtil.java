package com.smalle.dao.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * 数据库链接类
 *********************************************************************
 * 提前导包:如果是web工程,则将jar包放在WEB-INF下的lib文件夹中					**
 * 		 (如:mysql-connector-java-5.0.8-bin.jar)						**
 * 条件:本地有数据库blog(127.0.0.1:3306/servletBlog 用户名:root,密码:root)	**
 * blog中有表user,user中有:											**
 * id	account	password											**
 * 1	smalle	123456												**
 *********************************************************************
 * ConnectionUtil<BR>
 * 创建人:oldinaction <BR>
 * 时间：2014年10月19日-下午9:02:44 <BR>
 * @version 1.0.0
 *
 */
public class TmConnectionUtil {
	/**
	 * 数据库连接
	 * 
	 * 方法名：getConnection<BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月19日-下午9:03:13 <BR> 
	 * @return Connection<BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 * DriverManager.getConnection("jdbc:mysql://ip地址:端口号/数据库名", 数据库的用户名, 数据库的密码)
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			 Class.forName("com.mysql.jdbc.Driver"); //是mysql唯一标示符
			 connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servletBlog?characterEncoding=utf8", "root", "root");
			 return connection; //connection的值如com.mysql.jdbc.Connection@39617189
		} catch (Exception e) {
			return null; //如果打印connection不为空则表示连接数据库成功
		}
	}
	
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
				hashMap.put("account", rs.getString("account"));
				hashMap.put("password", rs.getString("password"));
				hashMap.put("description",rs.getString("description"));
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
	
	public static void main(String[] args) {
		HashMap<String, Object> map = findUser("smalle", "123456");
		if(null != map) {
			System.out.println("map" + map);
		}		
	}
	
}

