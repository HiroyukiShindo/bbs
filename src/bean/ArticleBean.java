package bean;

import java.sql.ResultSet;
import java.util.List;

import util.BbsUtil;

public class ArticleBean {
	
	private int articleId;
	
	private int userId;
	
	private String userName;
	
	private String header;
	
	private String body;
	
	private String registedDate;
	
	private String updatedDate;
	
	private List<CommentBean> commentList;
	
	public ArticleBean() {
	}
	
	public ArticleBean(ResultSet rs) throws Exception {
		this.setArticleId(rs.getInt("article_id"));
		this.setUserId(rs.getInt("user_id"));
		this.setUserName(BbsUtil.getUserFullName(this.getUserId()));
		this.setHeader(rs.getString("text_header"));
		this.setBody(rs.getString("text_body"));
		this.setRegistedDate(BbsUtil.formatDate(rs.getDate("regist_date")));
		this.setUpdatedDate(BbsUtil.formatDate(rs.getDate("update_date")));
	}
	
	public int getArticleId() {
		return articleId;
	}
	
	public void setArticleId(int articleId) {
		this.articleId = articleId;
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
	
	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
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
	
	public List<CommentBean> getCommentList() {
		return commentList;
	}
	
	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}
}
