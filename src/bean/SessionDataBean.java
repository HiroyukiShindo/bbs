package bean;

public class SessionDataBean {
	
	private int userId;
	
	private String userName;
	
	private boolean administratorFlg;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
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
}
