package com.yunpan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yunpan.bean.FileBean;

import cn.smalle.dao.core.ConnectionUtil;

public class FileDao {
	
	/**
	 * 保存上传的文件
	 * <BR>
	 * @method saveFile 
	 * @author oldinaction
	 * @date 2014年12月9日-下午4:14:33
	 * @return boolean <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static boolean saveFile(FileBean fileBean){
		
		String sql = "INSERT INTO yun_file(file_name,file_ext,new_name,size,size_string,width,height,is_delete,status,description,user_id,folder_id,url)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, fileBean.getFileName());
			preparedStatement.setString(2, fileBean.getFileExt());
			preparedStatement.setString(3, fileBean.getNewName());
			preparedStatement.setInt(4, fileBean.getSize());
			preparedStatement.setString(5, fileBean.getSizeString());
			preparedStatement.setInt(6, fileBean.getWidth());
			preparedStatement.setInt(7, fileBean.getHeight());
			preparedStatement.setInt(8, fileBean.getIsDelete());
			preparedStatement.setInt(9, fileBean.getStatus());
			preparedStatement.setString(10, fileBean.getDescription());
			preparedStatement.setInt(11, fileBean.getUserId());
			preparedStatement.setInt(12, fileBean.getFolderId());
			preparedStatement.setString(13, fileBean.getUrl());
			int count = preparedStatement.executeUpdate();
			return count > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			ConnectionUtil.closePreparedStatement(preparedStatement);
			ConnectionUtil.closeConnection(connection);
		}		
	}
	
	
	public static void main(String[] args) {
		FileBean fileBean = new FileBean();
		fileBean.setFileName("name");
		fileBean.setFileExt(".jpg");
		fileBean.setNewName("newname");
		fileBean.setSize(10);
		fileBean.setSizeString("10KB");
		fileBean.setWidth(0);
		fileBean.setHeight(0);
		fileBean.setIsDelete(0);
		fileBean.setStatus(1);
		fileBean.setDescription("desc");
		fileBean.setUserId(1);
		fileBean.setFolderId(1);
		fileBean.setUrl("/abc");
		saveFile(fileBean);
	}
	
	
	
	
}
