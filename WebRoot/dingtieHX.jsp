<%@ page language="java"
	import="java.util.*,com.hhhy.db.beans.PostArt,com.hhhy.db.DBUtils,com.hhhy.web.service.webservice.hexun.HexunCrawler"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    //Long userid = (Long) session.getAttribute("userid");
    //if (userid == null) {
    //    response.sendRedirect("./loginWeb.jsp");
    //    return;
   // }
   // String email = (String) session.getAttribute("name");
    //List<KeyWord> keywords = DBUtils.getUserKeyWord(userid);
    String number = request.getParameter("sid");
    //DFCFCrawler dfcf = new DFCFCrawler();
    //dfcf.parserDFCF();HexunCrawler
    //HexunCrawler.
    List<PostArt> posts = HexunCrawler.parseHexun(number);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>和讯股吧顶贴</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--

	<link rel="stylesheet" type="text/css" href="./css/key/boostrap.css">
	<link rel="stylesheet" type="text/css" href="./css/key/boostrap-theme.css">
	
	<link rel="stylesheet" href="./css/style-red.css">	
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<style type="text/css">	

</style>

	-->
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
		<li><a href="dtAll.jsp">顶贴</a>
			<li><a href="keylist" class="top-opt" data-toggle="tooltip"
				data-placement="bottom"><i></i>设置</a>
			</li>
			<li><a href="loginWeb.jsp" class="top-logout"
				data-toggle="tooltip" data-placement="bottom"><i></i>退出</a></li>

		</ul>

	</div>
	<br>


			<!-- <div class="widget-hd">
				<h2>发帖顶贴</h2>
			</div> -->
			<div class="page-header" style="margin-left: 5%">
				<span class="glyphicon glyphicon-th-large"></span>发帖顶贴
			</div>
			<div class="widget-bd h290" style="margin-left: 10%">
				<!-- <div id="container" class="h220" data-highcharts-chart="0">
					<div class="highcharts-container" id="highcharts-poscount"
						 style="position: relative; overflow: hidden; width: 460px; height: 220px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: 'Lucida Grande', 'Lucida Sans Unicode', Verdana, Arial, Helvetica, sans-serif; font-size: 12px;">
-->
						<table cellpadding="10"  align="center">
							<tr>
								<td>题目：</td>
								<td><input id="title" name="title" type="text"></td>
							</tr>
							<tr>
								<td>发帖内容：</td>
								<td><textarea name="contentF" id="contentF" rows ="3" style="resize:none"></textarea></td>
							</tr>
							<!-- <tr><td> </td><td></td></tr>
							<tr>
								<td>顶贴内容：</td>
								<td><textarea name="contentD" id="contentD"
										placeholder="回车分隔" rows ="6" style="resize:none"></textarea></td>
							</tr> -->
							
							
							<tr>
								<td></td>
								<td></td>
								<td><a href=""> <input value=顶该贴 class="btn btn-info"
										type="submit"> </a></td>
							</tr>
						</table>

					</div>
				<!-- </div>
			</div> -->


	
			<div class="page-header" style="margin-left: 5%">
				<span class="glyphicon glyphicon-th-large"></span>首页顶贴
			</div>
			<div class="widget-bd h290">
				<!-- <div id="pie" class="h290" data-highcharts-chart="1">
					<div class="highcharts-container"
						id="highcharts-emotionDistributed"
						style="position: relative; width: 460px; height: 290px; text-align: left; line-height: normal; z-index: 0;">
 -->

						<table align="center" cellspacing="10">
							<%
								if(posts==null || posts.size()==0){
							%>
							<tr>
								<td>没有帖子显示</td>

							</tr>
							<%
								} else{ int i=0;
							%>
							<%
								for(PostArt post:posts){
							%>
							<tr>
								<td><a href="<%=post.getUrl()%>"><%=post.getTitle()%></a>
								</td>

								<td><input type="text" id=<%=i%> placeholder="在此输入顶贴内容">
								</td>
								<td>
									<button class="btn btn-info" onclick=dingtie("<%=post.getUrl()%>",<%=i%>)>
										点击这里</button></td>

							</tr>
							<%
								i++; }}
							%>
						</table>
						<script type="text/javascript">
    function dingtie(url, id){
    var cont = $("#"+id).val();
    	$.ajax({
    	type:"post",
    	url: "dingtiehexun",
    	data: {url:url, content:cont},
    	success: function(msg){alert(msg);$("#"+id).val()="";}
    	});
    }
    
    </script>
					</div>
				<!-- </div>
			</div> -->

</body>
</html>
