<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registerWeb.jsp' starting page</title>
    
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
		<form id="login-form" action="UserRegister" method="post">
			<div class="register-box">
				<div class="login-logo">
					<h1>
						<a href=""><img src="" alt="">
						</a>
					</h1>
				</div>
				<div class="login-form">
					<ul>
						<li class="user">
							<input placeholder="ÓÃ»§Ãû»òÓÊÏä" name="username"
								id="register_username" type="text">
						</li>
						<li class="pwd">
							<input placeholder="ÃÜÂë" name="password" id="register_password"
								type="password">
						</li>
						<li class="pwd">
							<input placeholder="ÃÜÂë" name="rePassword" id="register_rePassword"
								type="password">
						</li>
					</ul>

					<div class="login-opt clearfix">
						<input class="login-btn" type="submit" name="register" value="×¢²á">
						<label class="remember-me">
							<%-- <% String loginerror = (String)session.getAttribute("loginerror"); %>
							<% if(loginerror!=null){ %>
							<div><%=loginerror %></div>
							<% } %> --%>
						</label>
					</div>
				</div>
			</div>

		</form>
<style> 
.login-error li { padding-left: 0px; }
</style>
	</body>
</html>
