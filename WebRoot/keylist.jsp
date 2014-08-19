<%@ page language="java" import="java.util.*,com.hhhy.db.beans.KeyWord, com.hhhy.db.DBUtils"
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
    List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
    //List<KeyWord> keywords = (List<KeyWord>) request.getAttribute("keywords");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>keylist</title>
		<link rel="stylesheet" type="text/css"
			href="./css/sentimentSummarize/YiiTagCloud.css">
		<link rel="stylesheet" type="text/css" href="./css/login.css">

		<!-- Bootstrap -->
		<link href="./css/bootstrap.min.css" rel="stylesheet">
		<link href="./css/bootstrap-responsive.min.css" rel="stylesheet">
		<!-- Theme -->
		<link rel="stylesheet" href="./css/style-red.css">
		<link rel="stylesheet" href="./css/style-red-my.css">

		<style type="text/css">
div {
	width: 280px;
	align: center;
	top: 200px;
	margin-left: auto;
	margin-right: auto;
}

ul.pop {
	position: absolute;
	top: 150px
}

li.pop {
	margin: 0px auto width : 50px;
	height: 50px;
}
</style>

	</head>
	<body>
	<br><br><br>
		<!--  <div class="pop_abs" id="center">
<div class="hidden-phone menu" style="display: block;">   
-->
		<div align="center">
		<% if(keywords==null || keywords.size()==0){ %>
			<div>还没有添加关键词</div>
		<% } else{ %>
			<ul class="menu-lists ">
				<% for(KeyWord keyword:keywords){ %>

				<li class="menu-list menu-general active ">
					<a href="./summarize?kid=<%=keyword.getId() %>" class="menu-title">
					<i></i>
					<span>
					<%=keyword.getKeyword() %>
					</span>
					</a>
					<form action="deletekeyword" method="POST">    
                    	<input value=删除 type="submit">    
                    	<input type='hidden' name='kid' value="<%=keyword.getId() %>">
					</form>
				</li>				
				<% } %>				
			</ul>
			<% } %>
			
			
			<br><br><br><br>
			<h2>添加关键词</h2>
				<form action="addkeyword" method="POST"> 
					<dl class=" " id="label_field">
						
						<dd><input id="label" name="keyword" type="text"></dd>
     					
     
					</dl>
					<input value="添加" type="submit">
         		</form>
		</div>
	</body>
</html>
