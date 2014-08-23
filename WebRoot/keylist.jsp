<%@ page language="java" import="java.util.*,com.hhhy.db.beans.KeyWord, com.hhhy.db.DBUtils" pageEncoding="GB18030"%>
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
    String email = (String) session.getAttribute("name");
    //List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
    List<KeyWord> keywords = (List<KeyWord>) request.getAttribute("keywords");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Keys.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap.css">
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap-theme.css">
	
	<link rel="stylesheet" href="./css/style-red.css">
	
		<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">	

</style>
  </head>
  
  <body>
  <iframe frameborder="0" style="display: none;"></iframe>
  
<div class="navbar">
    <a class="appbrand"></a>
    <button class="menu-toggle" type="button"></button>

    <ul class="topnav pull-right inline">
			<li><a href="loginWeb.jsp" class="top-logout" data-toggle="tooltip"
				data-placement="bottom"><i></i> <%=email %>退出</a></li>
			
	</ul>

</div>
  <br><br><br>
    <table align="center" cellspacing="10">
    <% if(keywords==null || keywords.size()==0){ %>
			<tr>
			<td>还没有添加关键词</td>
			
			</tr>
		<% } else{ %>
		
		<% for(KeyWord keyword:keywords){ %>
    	<tr>
  			<td>
  			<a href="./summarize?kid=<%=keyword.getId() %>" >
  			<%=keyword.getKeyword() %>
  			</a>
  			</td>
  			<td>
				<form action="deletekeyword" method="POST">    
                    	<input value=删除 class="btn btn-danger" type="submit">    
                    	<input type='hidden' name='kid' value="<%=keyword.getId() %>">
				</form>
			</td>
		</tr>		
		<% }} %>
    </table>
    
    
    <br><br><br><br>
    
    <div align="center">
    	<h4>添加关键词</h2>
    	<table cellspacing="10">
			<form action="addkeyword" method="POST"> 
				<tr>	
					<td><input id="label" name="keyword" type="text"></td>
					<td><input value=添加 class="btn btn-success" type="submit"></td>
				</tr>
				
       		</form>
      	</table>
    </div>
  </body>
</html>
