package logic;

import java.util.List;

import bean.CommentBean;
import dao.CommentDao;

public class CommentLogic {
	
	public List<CommentBean> getCommentList(int articleId) throws Exception {
		List<CommentBean> result = null;
		
		try {
			CommentDao dao = new CommentDao();
			result = dao.getCommentList(articleId);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return result;
	}
	
	public void entryComment(int userId, int articleId, String body) throws Exception {
		try {
			CommentBean comment = new CommentBean();
			comment.setUserId(userId);
			comment.setArticleId(articleId);
			comment.setComment(body);
			
			CommentDao dao = new CommentDao();
			dao.entryComment(comment);
			
		} catch (Exception e) {
			throw new Exception();
		}
		return;
	}
}
