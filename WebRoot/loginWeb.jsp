<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>login</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<!-- Bootstrap -->
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

		<!-- Theme -->
		<link rel="stylesheet" href="./css/style-red.css">
		<link rel="stylesheet" href="./css/style-red-my.css">
	</head>

	<body class="bg-login">
		<form id="login-form" action="UserLogin" method="post">
			<div class="login-box">
				<div class="login-logo">
					<h1>
						<a href=""><img src="" alt="">
						</a>
					</h1>
				</div>
				<div class="login-form">
					<ul>
						<li class="user">
							<input placeholder="用户名或邮箱" name="username"
								id="LoginForm_username" type="text">
						</li>
						<li class="pwd">
							<input placeholder="密码" name="password" id="LoginForm_password"
								type="password">
						</li>
					</ul>

					<div class="login-opt clearfix">
						<input class="login-btn" type="submit" name="yt0" value="登录">
						<label class="remember-me">
							<% String loginerror = (String)session.getAttribute("loginerror"); %>
							<% if(loginerror!=null){ %>
							<div><%=loginerror %></div>
							<% } %>
						</label>
					</div>
				</div>
			</div>

		</form>

		<script type="text/javascript" src="./登陆界面_files/jquery-1.8.2.min.js"></script>

<<<<<<< HEAD
<!-- <script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="/apps/yuqing/theme/scripts/jquery-1.8.2.min.js"><\/script>')</script><script type="text/javascript" src="./��½����_files/jquery-1.8.2.min.js"></script>
 
<script type="text/javascript" src="./js/sentimentSummarize/jquery.simInput.js"></script>
    <script type="text/javascript">
        $(function(){
            $('[data-toggle="checkbox"]').simInput();
        });
 
    </script>
    -->
<style> 
.login-error li { padding-left: 0px; }
=======
		<script type="text/javascript"
			src="./js/sentimentSummarize/jquery.simInput.js"></script>
		<script type="text/javascript">
	$(function() {
		$('[data-toggle="checkbox"]').simInput();
	});
</script>
		<style>
.login-error li {
	padding-left: 0px;
}
>>>>>>> FETCH_HEAD
</style>
	</body>
</html>
