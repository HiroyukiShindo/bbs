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
		<link rel="stylesheet" href="css/login.css">
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
		</div>
	</nav>
	<%-- body --%>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<% String message = (String) request.getAttribute("errMessage"); %>
					<% if (message != null && !message.equals("")) { %>
						<div class="alert alert-danger" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Error!</strong> <%= message %>
						</div>
					<% } %>
					<form class="form-signin" method="POST" action="./LoginServlet">
						<h3 class="form-signin-heading">Please sign in.</h3>
						<label for="inputEmail" class="sr-only">Login ID</label>
						<input type="text" name="id" class="form-control input-sm input-userid" placeholder="Login ID">
						<label for="inputPassword" class="sr-only">Password</label>
						<input type="password" name="password" class="form-control input-sm input-password" placeholder="Password">
						<div class="checkbox">
							<label>
								<input type="checkbox" value="remember-me"> Remember me
							</label>
						</div>
						<button class="btn btn-sm btn-primary btn-block" type="submit">
							<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
						</button>
					</form>
				</div>
			</div>
		</div>
	</body>
	<%-- footer --%>
	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="text-muted text-left">&copy; 2016 AMS Inc.</p>
		</div>
	</nav>
</html>