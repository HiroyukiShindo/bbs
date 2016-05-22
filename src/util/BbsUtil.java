package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.StringJoiner;

import logic.UserLogic;

public class BbsUtil {
	
	public static String getUserFullName(String firstName, String lastName) throws Exception {
		StringJoiner result = new StringJoiner("");
		result.add(lastName).add(" ").add(firstName);
		return result.toString();
	}
	
	public static String getUserFullName(int userId) throws Exception {
		UserLogic logic = new UserLogic();
		return logic.getUser(userId).getUserName();
	}
	
	public static boolean toBoolFromInteger(int i) throws Exception {
		return i == 1 ? true : false;
	}
	
	public static String formatDate(Date date) throws Exception {
		SimpleDateFormat result = new SimpleDateFormat("yyyy'年'MM'月'dd'日 'HH'時'mm'分'ss'秒'");
		return result.format(date);
	}
}
