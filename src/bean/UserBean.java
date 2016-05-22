package bean;

import java.sql.ResultSet;

import util.BbsUtil;

public class UserBean {
	
	private int userId;
	
	private String loginId;
	
	private String loginPassword;
	
	private int loginFlg;
	
	private String userName;
	
	private boolean administratorFlg;
	
	private String registedDate;
	
	private String updatedDate;
	
	public UserBean(ResultSet rs) throws Exception {
		this.setUserId(rs.getInt("user_id"));
		this.setLoginId(rs.getString("login_id"));
		this.setLoginPassword(rs.getString("login_password"));
		this.setLoginFlg(rs.getInt("login_flg"));
		this.setUserName(BbsUtil.getUserFullName(rs.getString("first_name"), rs.getString("last_name")));
		this.setAdministratorFlg(BbsUtil.toBoolFromInteger(rs.getInt("administrator_flg")));
		this.setRegistedDate(BbsUtil.formatDate(rs.getDate("regist_date")));
		this.setUpdatedDate(BbsUtil.formatDate(rs.getDate("update_date")));
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}
	
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	public int getLoginFlg() {
		return loginFlg;
	}
	
	public void setLoginFlg(int loginFlg) {
		this.loginFlg = loginFlg;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean isAdministratorFlg() {
		return administratorFlg;
	}

	public void setAdministratorFlg(boolean administratorFlg) {
		this.administratorFlg = administratorFlg;
	}

	public String getRegistedDate() {
		return registedDate;
	}

	public void setRegistedDate(String registedDate) {
		this.registedDate = registedDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
}
