<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="bean.*" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<script src="js/jquery1.12.3.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/script.js"></script>
		<title>Servlet BBS</title>
	</head>
	<%-- header --%>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">JavaServlet電子掲示板サンプル</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#addArticleModal">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 記事を書く
					</button>
					<button type="button" class="btn btn-default navbar-btn" data-toggle="modal" data-target="#logoutModal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ログアウト
					</button>
				</li>
			</ul>
		</div>
	</nav>
	<%-- body --%>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<% ArticleListBean articleList = (ArticleListBean) request.getAttribute("articleList"); %>
					<% List<ArticleBean> articles = articleList.getArticleList(); %>
					<% if (articles.size() != 0) { %>
						<% for (int index = 0; index < articles.size(); index++) { %>
							<div class="bs-example" data-example-id="media-list">
								<ul class="media-list">
									<li class="media">
										<div class="media-left">
											<a href="#">
												<img class="media-object" data-src="holder.js/64x64" alt="64x64" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTRjYmZhMTM3OSB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1NGNiZmExMzc5Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMy4yMjY1NjI1IiB5PSIzNi41MzI4MTI1Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true" style="width: 64px; height: 64px;">
											</a>
										</div>
										<div class="media-body">
											<h4><%= articles.get(index).getHeader() %></h4>
											<p class="media-article-text"><%= articles.get(index).getBody().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;") %></p>
											<p class="text-muted text-right" style="margin-top: 0px; margin-bottom: -5px;">
												<small>登録者:<%= articles.get(index).getUserName() %> / 登録日時:<%= articles.get(index).getRegistedDate() %></small>
											</p>
											<hr>
											<p class="text-right">
												<button class="btn btn-sm btn-success btn-add-comment" type="button">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> コメントする
												</button>
												<button class="btn btn-sm btn-danger btn-delete-article" type="button">
													<span class="glyphicon glyphicon-trash	" aria-hidden="true"></span> 記事を削除する
												</button>
												<input type="hidden" value="<%= articles.get(index).getArticleId() %>" />
												<input type="hidden" value="<%= articles.get(index).getHeader() %>" />
											</p>
											<% List<CommentBean> comments = articles.get(index).getCommentList(); %>
											<% if (comments.size() != 0) { %>
												<% for (int indexx = 0; indexx < comments.size(); indexx++) { %>
													<div class="media media-comment-div">
														<div class="media-left">
														<a href="#">
															<img class="media-object" data-src="holder.js/64x64" alt="64x64" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNTRjYmZhMDQ5NiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1NGNiZmEwNDk2Ij48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMy4yMjY1NjI1IiB5PSIzNi41MzI4MTI1Ij42NHg2NDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true" style="width: 64px; height: 64px;">
														</a>
														</div>
														<div class="media-body">
															<p class="media-comment-text"><%= comments.get(indexx).getComment().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;") %></p>
														</div>
														<p class="text-muted text-right" style="margin-top: 0px; margin-bottom: -5px;">
															<small>登録者:<%= comments.get(index).getUserName() %> / 登録日時:<%= comments.get(index).getRegistedDate() %></small>
														</p>
													</div>
												<% } %>
											<% } else { %>
												<p class="text-muted">コメントはありません。</p>
											<% } %>
										</div>
									</li>
								</ul>
							</div>
							<hr class="media-divide">
						<% } %>
					<% } else { %>
						<div class="no-media-notification">
							<p class="lead text-center">投稿された記事がありません。</p>
						</div>
					<% } %>
				</div>
			</div>
		</div>
		<%-- footer --%>
		<nav class="navbar navbar-default navbar-fixed-bottom">
			<div class="container">
				<ul class="nav navbar-nav navbar-left">
					<li><p class="text-muted">&copy; 2016 AMS Inc.</p></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<% UserBean user = (UserBean) session.getAttribute("userData"); %>
						<p class="text-muted">Signed in as <%= user.getUserName() %></p>
					</li>
				</ul>
			</div>
		</nav>
		<%-- 記事投稿モーダル --%>
		<div class="modal fade" id="addArticleModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">記事を書く</h4>
					</div>
					<form method="POST" action="./BoardServlet">
						<input type="hidden" name="operationDiv" value="1">
						<div class="modal-body">
							<div class="form-group">
								<label>タイトル：</label>
								<input type="text" name="header" class="form-control">
							</div>
							<div class="form-group">
								<label>記事本文：</label>
								<textarea name="body" class="form-control" rows="10"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							<button type="submit" class="btn btn-primary">投稿する</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%-- コメント投稿モーダル --%>
		<div class="modal fade" id="addCommentModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">コメントを書く</h4>
					</div>
					<form method="POST" action="./BoardServlet">
						<input type="hidden" name="operationDiv" value="3">
						<input type="hidden" name="articleId" id="parentArticleId">
						<div class="modal-body">
							<div class="form-group">
								<label>コメント：</label>
								<textarea name="comment" class="form-control" rows="10"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							<button type="submit" class="btn btn-success">コメントする</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%-- 記事削除モーダル --%>
		<div class="modal fade" id="deleteArticleModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">記事を削除する</h4>
					</div>
					<form method="POST" action="./BoardServlet">
						<input type="hidden" name="operationDiv" value="2">
						<input type="hidden" name="articleId" id="deleteArticleId">
						<div class="modal-body">
							<h4>確認</h4>
							<p>
								タイトル「<span id="deleteArticleTitle"></span>」の記事を削除します。<br>
								よろしいですか？
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							<button type="submit" class="btn btn-danger">削除する</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%-- ログアウト確認モーダル --%>
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title">ログアウト</h4>
					</div>
					<form method="POST" action="./LogoutServlet">
						<div class="modal-body">
							<h4>確認</h4>
							<p>
								ログアウトします。<br>
								よろしいですか？
							</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">閉じる</button>
							<button type="submit" class="btn btn-default">ログアウトする</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$(".btn-add-comment").click(function () {
					$("#parentArticleId").val($(this).next().next().val());
					$("#addCommentModal").modal();
				});
				
				$(".btn-delete-article").click(function () {
					$("#deleteArticleId").val($(this).next().val());
					$("#deleteArticleTitle").text($(this).next().next().val());
					$("#deleteArticleModal").modal();
				});
			});
		</script>
	</body>
</html>