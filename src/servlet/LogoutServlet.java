package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.UserBean;
import logic.AuthLogic;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
		
		try {
			HttpSession session = request.getSession();
			UserBean user = (UserBean) session.getAttribute("userData");
			AuthLogic logic = new AuthLogic();
			logic.logout(user.getUserId());
			session.setAttribute("userData", null);
			response.sendRedirect("LoginServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
