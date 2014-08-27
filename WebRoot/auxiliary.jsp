<%@ page language="java" import="java.util.*" pageEncoding="UTF8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Long userid = (Long) session.getAttribute("userid");

if (userid == null) {
	response.sendRedirect("./loginWeb.jsp");
	return;
}

KeyWord kw = (KeyWord)session.getAttribute("keyword");
String keyword = kw.getKeyWord();
String aux = kw.getAuxiliary();
List<String> auxis = new ArrayList();
auxis.addAll(aux.split(";"));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>附属关键词列表</title>
    
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
	
		<!--  Bootstrap css-->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

		<!-- Bootstrap-->
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

	<!-- jQuery bootstrap.min.js  -->
	<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

	<!-- Bootstrap JavaScript -->
	<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	
	<style type="text/css"></style>
  </head>
  
  <body>   
	
	<div class="navbar">
		<a class="appbrand"><!-- <img src="logo.jpg" alt=""
			style="position: relative; top: 0; left: 25px;"> --> </a>
		<button class="menu-toggle" type="button"></button>
		<ul class="topnav pull-right inline">
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i> 返回关键词设置</a></li>
		</ul>

	</div>
	<div class="wrapper">
		
		
		<div class="innerLR">	
		<br><br><br>
		    <table align="center" cellspacing="10">
		    <% if(auxis==null || auxis.size()==0){ %>
					<tr>
					<td>尚未添加<%=keyword %>的附属关键词</td>
					
					</tr>
				<% } else{ %>
				
				<% for(String auxi:auxis){ %>
		    	<tr>
		  			<td>
		  			
		  			<%=auxi %>
		  			
		  			</td>
		  			<td>
		  			<!-- 
		  				<a href="./summarize?kid=<%= %>" >
		  				<input value=查看 class="btn btn-info" type="submit">
		  				</a>
		  				 -->
		  				 
		  			</td>
		  			<td>
						<form action="delaux" method="POST">    
		                    	<input value=删除 class="btn btn-danger" type="submit">    
		                    	<input type='hidden' name='auxiliary' value="<%=auxi%>">
						</form>
					</td>
				</tr>		
				<% }} %>
		    </table>
		    
		    
		    <br><br><br><br>
		    
		    <div align="center">
		    	<h4>添加<%=keyword %>的附属关键词</h4>
		    	<table cellspacing="10">
					<form action="addaux" method="POST"> 
						<tr>	
							<td><input id="label" name="auxiliary" type="text"></td>
							<td><input value=添加 class="btn btn-info" type="submit"></td>
						</tr>
						
		       		</form>
		      	</table>
		    </div>
		</div>	
	</div>
  </body>
</html>
