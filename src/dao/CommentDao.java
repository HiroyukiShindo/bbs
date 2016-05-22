package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CommentBean;

/**
 * コメントテーブルにアクセスするDAOクラス。
 * 
 * @author hiroyuki
 */
public class CommentDao {
	private final String url = "jdbc:mysql://localhost:3306/training_bbs";
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	/**
	 * コメントテーブルに対し、コメント情報リスト取得処理を実行する。
	 * 
	 * @param articleId 記事ID
	 * @return コメント情報Beanリスト
	 * @throws SQLException データベースアクセス例外。
	 */
	public List<CommentBean> getCommentList(int articleId) throws SQLException {
		List<CommentBean> result = new ArrayList<CommentBean>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			sql = "SELECT * FROM t_comment WHERE article_id=? ORDER BY regist_date ASC";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, articleId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(new CommentBean(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	/**
	 * コメントテーブルに対し、コメント情報を登録する。
	 * 
	 * @param comment コメント情報Bean
	 * @return なし
	 * @throws SQLException データベースアクセス例外。
	 */
	public void entryComment(CommentBean comment) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			conn.setAutoCommit(false);
			sql = "INSERT INTO t_comment (user_id,article_id,comment_body,regist_date,update_date) VALUES (?,?,?,NOW(),NOW())";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getUserId());
			ps.setInt(2, comment.getArticleId());
			ps.setString(3, comment.getComment());
			ps.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		}
		return;
	}
	
	/**
	 * コメントテーブルに対し、引数の記事IDに紐付くコメント情報を削除する。
	 * 
	 * @param articleId 記事ID
	 * @return なし
	 * @throws SQLException データベースアクセス例外。
	 */
	public void deleteComment(int articleId) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			conn.setAutoCommit(false);
			sql = "DELETE FROM t_comment WHERE article_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, articleId);
			ps.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			
		} finally {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		}
		return;
	}
}
