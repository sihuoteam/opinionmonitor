<%@ page language="java"
	import="java.util.*,com.hhhy.db.beans.KeyWord, com.hhhy.db.DBUtils"
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
	String email = (String) session.getAttribute("name");
	//List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
	String startTime = "123";
	String endTime = "123";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>历史时间设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="./css/key/boostrap.css">
<link rel="stylesheet" type="text/css"
	href="./css/key/boostrap-theme.css">

<link rel="stylesheet" href="./css/style-red.css">

<!--  Bootstrap css-->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Bootstrap-->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- jQuery bootstrap.min.js  -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- Bootstrap JavaScript -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">
</style>
</head>

<body>

	<div class="navbar">
		<a class="appbrand">
			<!-- <img src="logo.jpg" alt=""
			style="position: relative; top: 0; left: 25px;"> --> </a>
		<button class="menu-toggle" type="button"></button>
		<ul class="topnav pull-right inline">
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i> 返回关键词设置</a>
			</li>
		</ul>

	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<form action="history" method="get" align="center" cellspacing="10">
	<div class="row-fluid">
	    <div class="search-filter-box">
	        <!-- div class="normal-search-box clearfix"> -->
	            <div class="clearfix time-opt span10">
	                <div class="control-group form-inline">
	                    <ul class="inline tab-small">
	                        <li style="padding-left:0px;">设置历史舆情监控时间： 
								<input id="start_date" type="text" name="start_date" value="<%=startTime %>">
									<!-- <a class="i-cal" href="javascript:;" id="start_date_link"></a>  -->
								<script>
									$(function() {
										$("#start_date")
												.datepicker();
								});
								</script> 
								<input id="end_date" type="text"
									name="end_date" value="<%=endTime %>"> <!-- <a class="i-cal" href="javascript:;" id="end_date_link"> </a>  -->
								<script>
									$(function() {
										$("#end_date")
												.datepicker();
									});
								</script>
							</li>
	                    </ul>
	                </div> 
	            </div> 
	            <div class="export-submit">
				<input class="btn-red" type="submit" name="yt0" value="设置">
				</div>
	        <!-- </div> -->
	    </div>
	</div>
</form>




</body>
</html>
