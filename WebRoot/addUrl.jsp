<%@ page language="java"
	import="java.util.*,com.hhhy.db.beans.*, com.hhhy.db.DBUtils"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Long userid = (Long) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("./loginWeb.jsp");
		return;
	}
	String name = (String) session.getAttribute("name");
	String email = (String) session.getAttribute("name");
	//List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
	/* List<KeyWord> keywords = (List<KeyWord>) session
			.getAttribute("keywords"); */
			
	List<WebSite> urls = (List<WebSite>) session.getAttribute("webs");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>自添加爬虫列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" type="text/css" href="./css/jquery-ui.css">
<script type="text/javascript" src="./js/jquery.js"></script>

<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

<!-- Theme -->
<link rel="stylesheet" href="./css/style-red.css">

<link rel="stylesheet" href="./css/style-red-my.css">

</head>

<body>
	<div class="navbar">
		<a class="appbrand" align="center" href=""><img src="./pic/hhhy.png" width="130" alt=""
			style="position: relative;top:0;"> </a>
		<button class="menu-toggle" type="button"></button>

		<ul class="topnav pull-right inline">
<li><a href="dtAll.jsp">顶贴</a></li>
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a>
			</li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a>
			</li>
		</ul>

	</div>

	<div class="wrapper">
		<div class="hidden-phone menu" id="menu">
			<div class="profile">
				<span>欢迎您：</span> <a><%=name%></a>
			</div>

			<ul class="menu-lists">

				<li class="menu-list menu-any "><a href="keylist"
					class="menu-title"><i></i><span>关键词设置</span> </a>
				</li>
				<li class="menu-list menu-rep active"><a href="#"
					class="menu-title"><i></i><span>自添加爬虫列表</span> </a>
				</li>

			</ul>
		</div>
		<div id="content" class="content">
			<ul class="breadcrumb">
				<li>您在这里：</li>
				<li class="color-red">设置/自添加爬虫列表</li>
			</ul>
			<br> <br> <br>
			<table align="center" cellspacing="10">
				<%
					if (urls == null || urls.size() == 0) {
				%>
				<tr>
					<td>尚未添加网站</td>

				</tr>
				<%
					} else {
				%>

				<%
					for (WebSite url : urls) {
				%>
				<tr>
					<td><%=url.getWebsite()%></td>
					<td>
						<form action="delurl" method="POST">
							<input value=删除 class="btn btn-danger" type="submit"> <input
								type='hidden' name='uid' value="<%=url.getId()%>">
						</form></td>
				</tr>
				<%
					}
					}
				%>
			</table>


			<br> <br> <br> <br>

			<div align="center">
				<h4>添加Url</h4>
				<table cellspacing="10">
					<form action="addurl" method="POST">
						<tr>
							<td><input id="label" name="website" type="text">
							</td>
							<td><input value=添加 class="btn btn-info" type="submit">
							</td>
						</tr>

					</form>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
