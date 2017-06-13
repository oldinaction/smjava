package com.smalle.dao.log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;

import com.smalle.dao.core.TmConnectionUtil;


public class TmLogDao {
	/**
	 * 获取博文信息
	 * <BR>
	 * @method findLogs 
	 * @author oldinaction
	 * @date 2014年10月25日-下午3:29:18
	 * @param userId
	 * @return List<HashMap<String,Object>> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<HashMap<String, Object>> findLogs(int userId){
		String  sql= "select createtime,content,title,id,updatetime,user_id from log where user_id  = " + userId;
		Connection connection = TmConnectionUtil.getConnection(); //1:获取连接对象
		Statement statement = null; //2:获取处理(缓冲区)对象
		ResultSet rs = null; //3:获取结果集 
		List<HashMap<String, Object>> maps = null;
		try {
			maps = new ArrayList<HashMap<String,Object>>();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			HashMap<String, Object> hashMap = null;
			while(rs.next()){ //4:解析结果集
				hashMap = new HashMap<String, Object>();
				hashMap.put("id", rs.getInt("id"));
				hashMap.put("title", rs.getString("title"));
				hashMap.put("content", rs.getString("content")); //content在数据库中为text类型,此处就用getString
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("updatetime",rs.getTimestamp("updatetime"));
				hashMap.put("user_id",rs.getInt("user_id"));
				
				maps.add(hashMap);
			}
			return maps;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally { //5:关闭连接对象
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
		List<HashMap<String, Object>> maps = findLogs(1);
		if(null != maps) {
			System.out.println(maps);
		}
	}
}

