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

import bean.ArticleListBean;
import bean.UserBean;
import logic.ArticleLogic;
import logic.CommentLogic;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(BoardServlet.class);
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
		
		try {
			ArticleLogic logic = new ArticleLogic();
			ArticleListBean articleList = new ArticleListBean();
			articleList.setArticleList(logic.getArticleList());
			request.setAttribute("articleList", articleList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/article_list.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("method call.");
		
		try {
			String operationDiv = request.getParameter("operationDiv");
			
			if ("1".equals(operationDiv)) {
				// 記事登録
				HttpSession session = request.getSession();
				UserBean user = (UserBean) session.getAttribute("userData");
				
				int userId = user.getUserId();
				String header = request.getParameter("header");
				String body = request.getParameter("body");
				
				ArticleLogic logic = new ArticleLogic();
				logic.entryArticle(userId, header, body);
				
			} else if ("2".equals(operationDiv)) {
				// 記事削除
				int articleId = Integer.parseInt(request.getParameter("articleId"));
				
				ArticleLogic logic = new ArticleLogic();
				logic.deleteArticle(articleId);
				
			} else if ("3".equals(operationDiv)) {
				// コメント登録
				HttpSession session = request.getSession();
				UserBean user = (UserBean) session.getAttribute("userData");
				
				int userId = user.getUserId();
				int articleId = Integer.parseInt(request.getParameter("articleId"));
				String body = request.getParameter("comment");
				
				CommentLogic logic = new CommentLogic();
				logic.entryComment(userId, articleId, body);
			}
			
			response.sendRedirect("BoardServlet");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
