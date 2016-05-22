package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ArticleBean;

/**
 * 記事テーブルにアクセスするDAOクラス。
 * 
 * @author hiroyuki
 */
public class ArticleDao {
	private final String url = "jdbc:mysql://localhost:3306/training_bbs";
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	/**
	 * 記事テーブルに対し、記事情報リスト取得処理を実行する。
	 * 
	 * @param なし
	 * @return 記事情報Beanリスト
	 * @throws SQLException データベースアクセス例外。
	 */
	public List<ArticleBean> getArticleList() throws SQLException {
		List<ArticleBean> result = new ArrayList<ArticleBean>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			sql = "SELECT * FROM t_article ORDER BY regist_date DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(new ArticleBean(rs));
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
	 * 記事テーブルに対し、記事情報を登録する。
	 * 
	 * @param article 記事情報Bean
	 * @return なし
	 * @throws SQLException データベースアクセス例外。
	 */
	public void entryArticle(ArticleBean article) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			conn.setAutoCommit(false);
			sql = "INSERT INTO t_article (user_id,text_header,text_body,regist_date,update_date) VALUES (?,?,?,NOW(),NOW())";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, article.getUserId());
			ps.setString(2, article.getHeader());
			ps.setString(3, article.getBody());
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
	 * 記事テーブルに対し、記事情報を削除する。
	 * 
	 * @param articleId 記事ID
	 * @return なし
	 * @throws SQLException データベースアクセス例外。
	 */
	public void deleteArticle(int articleId) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			conn.setAutoCommit(false);
			sql = "DELETE FROM t_article WHERE article_id=?";
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
