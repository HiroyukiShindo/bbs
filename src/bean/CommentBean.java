package bean;

import java.sql.ResultSet;

import util.BbsUtil;

public class CommentBean {
	
	private int commentId;
	
	private int userId;
	
	private String userName;
	
	private int articleId;
	
	private String comment;
	
	private String registedDate;
	
	private String updatedDate;
	
	public CommentBean() {
	}
	
	public CommentBean(ResultSet rs) throws Exception {
		this.setCommentId(rs.getInt("comment_id"));
		this.setUserId(rs.getInt("user_id"));
		this.setUserName(BbsUtil.getUserFullName(this.getUserId()));
		this.setArticleId(rs.getInt("article_id"));
		this.setComment(rs.getString("comment_body"));
		this.setRegistedDate(BbsUtil.formatDate(rs.getDate("regist_date")));
		this.setUpdatedDate(BbsUtil.formatDate(rs.getDate("update_date")));
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
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
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
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
