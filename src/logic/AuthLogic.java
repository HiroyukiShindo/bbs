package logic;

import bean.UserBean;
import dao.UserDao;

public class AuthLogic {
	
	public UserBean auth(String loginId, String loginPassword) throws Exception {
		UserBean result = null;
		UserDao userDao = null;
		
		try {
			userDao = new UserDao();
			
			if (userDao.auth(loginId, loginPassword)) {
				userDao = new UserDao();
				result = userDao.getUser(loginId, loginPassword);
				
				if (result.getLoginFlg() == 0) {
					userDao = new UserDao();
					userDao.updateLoginFlg(result.getUserId(), 1);
				}
			}
			
		} catch (Exception e) {
			throw new Exception();
		}
		return result;
	}
	
	public void logout(int userId) throws Exception {
		try {
			UserDao userDao = new UserDao();
			userDao.updateLoginFlg(userId, 0);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return;
	}
}
