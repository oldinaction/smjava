package com.smalle.dao.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;

import com.smalle.dao.core.TmConnectionUtil;
import com.smalle.dao.user.UserDao;

/**
 * 根据主键ID查询具体的日记信息(内容和评论)
 * <BR>
 * TmCommentDao <BR>
 * @author oldinaction
 * @date 2014年10月25日-下午6:20:35
 * @version 1.0.0
 *
 */
public class TmCommentDao {
	/**
	 * 根据主键ID查询具体的日记内容信息
	 * <BR>
	 * @method findLogs 
	 * @author oldinaction
	 * @date 2014年10月25日-下午4:27:44
	 * @param userId
	 * @return List<HashMap<String,Object>> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static HashMap<String, Object> getLog(int id ,int userId){
		String sql  = "SELECT * FROM log WHERE id = " + id +" and user_id=" + userId;
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
				hashMap.put("title", rs.getString("title"));
				hashMap.put("content", rs.getString("content")); //content在数据库中为text类型,此处就用getString
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("updatetime",rs.getTimestamp("updatetime"));
				hashMap.put("user_id",rs.getInt("user_id"));
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
	 * 评论业务，获去每一条评论相关的信息
	 * <BR>
	 * @method main 
	 * @author oldinaction
	 * @date 2014年10月25日-下午6:20:16
	 * @param args void <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<HashMap<String, Object>> findComments(int logId ,int userId){
		//String sql  = "SELECT tml.id,tml.content,tml.createtime,tml.updatetime,tu.id as userId,tu.nickname,tu.headerpic FROM tm_comment_log tml LEFT JOIN tm_user tu ON tml.user_id = tu.id WHERE log_id = " + logID可以查出评论人的昵称和头像，不要下面的再次查询
		String sql  = "SELECT * FROM comment_log WHERE log_id = " + logId + " and user_id=" + userId;
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
				hashMap.put("content", rs.getString("content"));
				hashMap.put("user_id",rs.getInt("user_id"));
				hashMap.put("log_id",rs.getInt("log_id"));
				hashMap.put("createtime",rs.getTimestamp("createtime"));
				hashMap.put("updatetime",rs.getTimestamp("updatetime"));
				//根据user_id获取用户信息的集合，并查出用户的nickname和headerpic放到评论信息的集合中
				HashMap<String, Object> userMap = UserDao.findUser(rs.getInt("user_id"));
				hashMap.put("nickname", userMap.get("nickname"));
				hashMap.put("headerPic", userMap.get("headerPic"));
				
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
	
	/**
	 * 保存评论
	 * <BR>
	 * @method saveComment 
	 * @author oldinaction
	 * @date 2014年10月28日-上午10:56:47
	 * @return boolean <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static boolean saveComment(String content, int userId, int logId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null; //导入java.sql.PreparedStatement;
		try {
			connection = TmConnectionUtil.getConnection();
			String sql = "INSERT INTO comment_log(content,user_id,log_id) VALUES (?,?,?)"; //索引是从1开始的
			preparedStatement = connection.prepareStatement(sql); //预处理块,这样数据安全，防止sql注入
			preparedStatement.setString(1, content); //第一个参数代表第一个问号
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, logId);
			int count = preparedStatement.executeUpdate();
			return count > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection!= null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}	
	}

	
	public static void main(String[] args) {
		HashMap<String, Object> logMap = getLog(1, 1);
		if(null != logMap) {
			System.out.println("logMap===" + logMap);
		}
		
		List<HashMap<String, Object>> commnets = findComments(1, 1);
		if(null != commnets) {
			System.out.println("commnets===" + commnets);
		}
		
		saveComment("多浪费是看得见dsf", 1, 1);
	}
}

