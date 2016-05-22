package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
		
		try {
			AuthLogic authLogic = new AuthLogic();
			UserBean user = authLogic.auth((String) request.getParameter("id"), (String) request.getParameter("password"));
			
			if (user != null && user.getLoginFlg() == 0) {
				log.info("ログイン成功");
				HttpSession session = request.getSession();
				session.setAttribute("userData", user);
				response.sendRedirect("BoardServlet");
				
			} else if (user != null && user.getLoginFlg() == 1) {
				log.info("ログイン失敗: ログイン中");
				request.setAttribute("errMessage", "既にログインしています。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
				
			} else {
				log.info("ログイン失敗: ログイン情報不正");
				request.setAttribute("errMessage", "ユーザーIDかパスワード、もしくはその両方が誤りです。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
