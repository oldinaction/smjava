package cn.smalle.dao.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.smalle.bean.Content;
import cn.smalle.dao.core.ConnectionUtil;

public class ContentDao {
	
	/**
	 * 保存内容
	 * <BR>
	 * @method saveContent 
	 * @author oldinaction
	 * @date 2014年11月23日-下午2:03:50
	 * @param content
	 * @return boolean <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static boolean saveContent(Content content) {
		String sql = "INSERT INTO blog_content(title,content,user_id,tag,updatetime,status,is_delete)  VALUES (?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, content.getTitle());
			preparedStatement.setString(2, content.getContent());
			preparedStatement.setInt(3, content.getUserId());
			preparedStatement.setString(4, content.getTag());
			preparedStatement.setString(5, "2010-12-12 12:12:12");
			preparedStatement.setInt(6, content.getStatus());
			preparedStatement.setInt(7, content.getIsDelete());	
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
	
	/**
	 * 查询内容<BR>
	 * sortFlag取值：ASC顺序 DESC逆序
	 * <BR>
	 * @method findContents 
	 * @author oldinaction
	 * @date 2014年11月27日-上午9:53:44
	 * @param start
	 * @param size
	 * @param sortFlag
	 * @return List<Content> <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static List<Content> findContents(int start, int size, String sortFlag) {
		String sql = "SELECT * FROM blog_content ORDER BY createtime " + sortFlag + " LIMIT ?,?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Content> contents = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, size);
			rs = preparedStatement.executeQuery();
			contents = new ArrayList<Content>();
			Content content = null;
			while(rs.next()) {
				content = new Content();
				content.setId(rs.getInt("id"));
				content.setTitle(rs.getString("title"));
				content.setContent(rs.getString("content"));
				content.setUserId(rs.getInt("user_id"));
				content.setTag(rs.getString("tag"));
				content.setCreatetime(rs.getTimestamp("createtime"));
				content.setUpdatetime(rs.getTimestamp("updatetime"));
				content.setStatus(rs.getInt("status"));
				content.setIsDelete(rs.getInt("is_delete"));
				contents.add(content);
			}
			return contents;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionUtil.closeResultSet(rs);
			ConnectionUtil.closePreparedStatement(preparedStatement);
			ConnectionUtil.closeConnection(connection);
		}	

	}
	
	/**
	 * 删除内容
	 * <BR>
	 * @method deleteContent 
	 * @author oldinaction
	 * @date 2014年12月2日-上午10:19:08
	 * @param id
	 * @return boolean <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static boolean deleteContent(Integer id) {
		String sql = "DELETE FROM blog_content WHERE id = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
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
	
	
	
	//测试
	public static void main(String[] args) {
		//saveContent方法测试
		Content content = new Content();
		content.setTitle("测试文章标题a");
		content.setContent("内容b");
		content.setUserId(1);
		content.setTag("标签");
		content.setStatus(1);
		content.setIsDelete(0);
		saveContent(content);
		
		//findContents测试
		System.out.println(findContents(0, 10, "DESC"));
		
		//deleteContent测试
		System.out.println(deleteContent(23));
	}
}
