package logic;

import java.util.List;

import bean.ArticleBean;
import dao.ArticleDao;
import dao.CommentDao;

public class ArticleLogic {
	
	public List<ArticleBean> getArticleList() throws Exception {
		List<ArticleBean> result = null;
		
		try {
			ArticleDao dao = new ArticleDao();
			result = dao.getArticleList();
			
			for (ArticleBean e : result) {
				CommentLogic logic = new CommentLogic();
				e.setCommentList(logic.getCommentList(e.getArticleId()));
			}
			
		} catch (Exception e) {
			throw new Exception();
		}
		return result;
	}
	
	public void entryArticle(int userId, String header, String body) throws Exception {
		try {
			ArticleBean article = new ArticleBean();
			article.setUserId(userId);
			article.setHeader(header);
			article.setBody(body);
			
			ArticleDao dao = new ArticleDao();
			dao.entryArticle(article);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return;
	}
	
	public void deleteArticle(int articleId) throws Exception {
		try {
			// 記事に紐づくコメント全削除
			CommentDao commentDao = new CommentDao();
			commentDao.deleteComment(articleId);
			
			// 記事削除
			ArticleDao articleDao = new ArticleDao();
			articleDao.deleteArticle(articleId);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return;
	}
}
