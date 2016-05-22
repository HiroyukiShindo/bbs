package logic;

import bean.UserBean;
import dao.UserDao;

public class UserLogic {
	
	public UserBean getUser(int userId) throws Exception {
		UserBean result = null;
		
		try {
			UserDao userDao = new UserDao();
			result = userDao.getUser(userId);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return result;
	}
}
