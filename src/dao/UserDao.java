package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

/**
 * ユーザーテーブルにアクセスするDAOクラス。
 * 
 * @author hiroyuki
 */
public class UserDao {
	private final String url = "jdbc:mysql://localhost:3306/training_bbs";
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	/**
	 * ユーザーテーブルに対し、認証処理を実行する。<br>
	 * 認証に成功した場合はtrueを、失敗した場合はfalseを返却する。
	 * 
	 * @param id ログインID
	 * @param password ログインパスワード
	 * @return 認証結果（true:成功 false:失敗）
	 * @throws SQLException データベースアクセス例外。
	 */
	public boolean auth(String id, String password) throws SQLException {
		boolean result = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			sql = "SELECT * FROM t_user WHERE login_id=? AND login_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = true;
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
	 * ユーザーテーブルに対し、ユーザー情報取得処理を実行する。<br>
	 * ユーザー情報Beanを返却する。
	 * 
	 * @param id ログインID
	 * @param password ログインパスワード
	 * @return ユーザー情報Bean
	 * @throws SQLException データベースアクセス例外。
	 */
	public UserBean getUser(String id, String password) throws SQLException {
		UserBean result = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			sql = "SELECT * FROM t_user WHERE login_id=? AND login_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result = new UserBean(rs);
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
	 * ユーザーテーブルに対し、ユーザー情報取得処理を実行する。<br>
	 * ユーザー情報Beanを返却する。
	 * 
	 * @param id ユーザーID
	 * @return ユーザー情報Bean
	 * @throws SQLException データベースアクセス例外。
	 */
	public UserBean getUser(int id) throws SQLException {
		UserBean result = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			sql = "SELECT * FROM t_user WHERE user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result = new UserBean(rs);
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
	 * ユーザーテーブルに対し、ユーザーのログイン中フラグを更新する。
	 * 
	 * @param userId ユーザーID
	 * @param loginFlg ログインフラグ
	 * @return なし
	 * @throws SQLException データベースアクセス例外。
	 */
	public void updateLoginFlg(int userId, int loginFlg) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "13897982");
			conn.setAutoCommit(false);
			sql = "UPDATE t_user SET login_flg=? WHERE user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, loginFlg);
			ps.setInt(2, userId);
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
	}
}
