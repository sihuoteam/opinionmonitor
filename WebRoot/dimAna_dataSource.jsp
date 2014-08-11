<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>dimAna_dataSource</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="./css/dataExport/jquery-ui.css">
	<script type="text/javascript" src="./js/dataExport/jquery.js"></script>
	
	<!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet">
 
    <!-- Theme -->
    <link rel="stylesheet" href="./css/style-red.css">
    
    <link rel="stylesheet" href="./css/style-red-my.css">
    
    <link rel="stylesheet" type="text/css" href="./css/dimensionAnalysis/dataSource/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="./css/dimensionAnalysis/dataSource/styles.css" />
<link rel="stylesheet" type="text/css" href="./css/dimensionAnalysis/dataSource/pager.css" />
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery.yii.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery.ba-bbq.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/highcharts.src.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/exporting.src.js"></script>
  </head>
  
  <body>

<div class="navbar">
    <a class="appbrand" href=""><img src="" alt="" style="position: relative;top:0;left: 25px;"></a>
    <button class="menu-toggle" type="button"></button>
    
    <!-- <div class="topnav pull-left ln">
        <div class="btn-group" id="J-toggle-site">
                          <a class="btn dropdown-toggle btn-small" href="">
                 name
                  <span class="caret" style="display: none;"></span>
              </a>            <ul class="dropdown-menu">
                        </ul>
        </div>
    </div> -->
    
    <ul class="topnav pull-right inline">
        <li><a href="" class="top-opt" data-toggle="tooltip" data-placement="bottom"><i></i> 关键词设置</a></li>
        <li><a href="" class="top-logout" data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a></li>
    </ul>
 
</div>
<div class="loading">载入中...</div>
<div class="wrapper">
    <div class="hidden-phone menu" id="menu">
        <div class="profile">
            <span>欢迎您：</span>
            <a href="">name</a>
        </div>
        <div id="side-search" class="search-box">
    <form action="" method="get">
						<div class="side-search-downlist">
							<p class="current" id="search-type">
								全部
							</p>
							<ul id="search-type-list" style="display: none;">
								<li>
									<a href="javascript:;" data-type="">全部</a>
								</li>
								<li>
									<a href="javascript:;" data-type="news">新闻</a>
								</li>
								<li>
									<a href="javascript:;" data-type="bbs">论坛</a>
								</li>
								<li>
									<a href="javascript:;" data-type="blog">博客</a>
								</li>
								<li>
									<a href="javascript:;" data-type="epub">电子报</a>
								</li>
								<li>
									<a href="javascript:;" data-type="weibo">微博</a>
								</li>
							</ul>
						</div>
						<div class="side-search-input">
							<input class="side-search" type="text" name="include" id="">
							<button class="side-search-btn">
								<i></i>
							</button>
							<input id="search-type-flag" type="hidden" name="source"
								value="0">
							<input id="search-type-flag" type="hidden" name="adv" value="1">
						</div>
					</form>
		</div>
            <ul class="menu-lists">
					<li class="menu-list menu-general">
						<a href="" class="menu-title"><i></i><span>舆情概况</span>
						</a>
					</li>
					<li id="more-sub-menu" class="menu-list menu-lat active">
						<a href="" class="menu-title"><i></i><span>维度分析</span>
						</a>
						<ul class="sub-menu-list" id="sub-menu-list">
							<li>
								<a href="">舆情走势</a>
							</li>
							<li class=sub-active>
								<a href="">数据来源</a>
							</li>
							<li>
								<a href="">媒体来源</a>
							</li>
							<li>
								<a href="">情感走势</a>
							</li>
						</ul>
					</li>


					<li class="menu-list menu-any ">
						<a href="" class="menu-title"><i></i><span>舆情监控</span>
						</a>


					</li>
					<li class="menu-list menu-rep ">
						<a href="" class="menu-title"><i></i><span>数据报告</span>
						</a>
					</li>
					<li class="menu-list menu-com ">
						<a href="" class="menu-title"><i></i><span>对比分析</span>
						</a>
					</li>
				</ul>
			</div>
    <div id="content" class="content">
        <!--<div id="content">-->
    <ul class="breadcrumb">
  <li>您在这里：</li>
  <li class="color-red">维度分析/数据来源</li>
</ul>
<div class="innerLR">
<form action="http://yq.adt100.com/source/index" method="get"><input type="hidden" value="3" name="period" id="period"><input type="hidden" value="" name="author" id="author"><div class="row-fluid pb10">
  <div class="clearfix time-opt">   
    <ul class="inline tab-small">
      <li class="first ">
        <a href="http://yq.adt100.com/source/index?period=1&source=&sentiment=&topic_id=&merge=1&author=">今日</a>      </li>
      <li>
        <a href="http://yq.adt100.com/source/index?period=2&source=&sentiment=&topic_id=&merge=1&author=">昨日</a>      </li>
      <li class="current">
        <a href="http://yq.adt100.com/source/index?period=3&source=&sentiment=&topic_id=&merge=1&author=">7天</a>            
      </li>
      <li>
        <a href="http://yq.adt100.com/source/index?period=4&source=&sentiment=&topic_id=&merge=1&author=">30天</a> 
      </li>
      <li></li>
      <li>自定义：
      
      <input class="input-small hasDatepicker" readonly="readonly" onchange="document.getElementById(&quot;period&quot;).value=&quot;&quot;; this.form.submit();" id="start_date" type="text" value="2014-08-05" name="start_date">      <a class="i-cal" href="javascript:;" id="start_date_link"></a>
      <script>
        $(function(){
          $("#start_date_link").click(function(){
             $("#start_date").datepicker("show");
          });
        });
      </script>

      <input class="input-small hasDatepicker" readonly="readonly" onchange="document.getElementById(&quot;period&quot;).value=&quot;&quot;; this.form.submit();" id="end_date" type="text" value="2014-08-11" name="end_date">      <a class="i-cal" href="javascript:;" id="end_date_link"></a>
      <script>
        $(function(){
          $("#end_date_link").click(function(){
             $("#end_date").datepicker("show");
          });
        });
      </script>
      </li>
      <li>
        <label>
        <div class="ez-checkbox ez-checked"><input data-toggle="checkbox" class="ez-hide" checked="checked" id="yt0" type="checkbox" value="1" name="merge">
        </div>合并相似文章
        </label>
      </li>
    </ul>
  </div>
</div>
<div class="row-fluid clearfix">
  <div class="control-group form-inline">
      
      <label class="control-label" for="">情感：</label>    <select class="mr20" name="sentiment" id="sentiment">
<option value="" selected="selected">全部</option>
<option value="positive">正面</option>
<option value="negative">负面</option>
<option value="neutral">中立</option>
</select>    
      <label class="control-label" for="">话题：</label>    <select class="mr20" name="topic_id" id="topic_id">
<option value="" selected="selected">全部</option>
<option value="913">观致3</option>
<option value="914">观致五门版</option>
<option value="982">性价比口碑</option>
</select>  
  </div>
</div>
</form>

 <div class="row-fluid">
      <div class="span12">
          <div class="widget">
              <div class="widget-hd"><span class="fr"><a href="http://yq.adt100.com/source/export?0=&period=3&source=&sentiment=&topic_id=&merge=1&start_date=&end_date=" class="i-export">导出</a></span>数据来源
              </div> 
              <div class="widget-bd">
                  
<table width="100%">
  <tbody><tr>
    <td width="50%" class="h400">
    <div id="yw0" data-highcharts-chart="0"><div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 482px; height: 350px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="482" height="350"><desc>Created with Highcharts 3.0.4</desc><defs><clippath id="highcharts-1"><rect fill="none" x="0" y="0" width="462" height="290"></rect></clippath></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="482" height="350"></rect><g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(448,10)"><title>Chart context menu</title><rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1"></text></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)" style="cursor:pointer;"><path fill="#d95455" d="M 230.97250411750116 10.000002800087373 A 135 135 0 1 1 186.69407253266837 17.477512605581865 L 231 145 A 0 0 0 1 0 231 145 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#979080" d="M 186.82161715177097 17.433270446737154 A 135 135 0 0 1 224.4700366554086 10.158019968860458 L 231 145 A 0 0 0 0 0 231 145 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#4c88a2" d="M 224.60488187794752 10.1515574275885 A 135 135 0 0 1 230.81248770836035 10.00013022546861 L 231 145 A 0 0 0 0 0 231 145 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#83823f" d="M 230.94748764939106 10.0000102131375 A 135 135 0 0 1 230.81248770836035 10.00013022546861 L 231 145 A 0 0 0 0 0 231 145 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#f0c189" d="M 230.94748764939106 10.0000102131375 A 135 135 0 0 1 230.81248770836035 10.00013022546861 L 231 145 A 0 0 0 0 0 231 145 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)"></g></g><g class="highcharts-legend" zIndex="7" transform="translate(99,310)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="284" height="24" stroke="#909090" stroke-width="1" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">微博</tspan></text><rect rx="2" ry="2" fill="#d95455" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(61,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">新闻</tspan></text><rect rx="2" ry="2" fill="#979080" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(114,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">论坛</tspan></text><rect rx="2" ry="2" fill="#4c88a2" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(167,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">博客</tspan></text><rect rx="2" ry="2" fill="#83823f" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(220,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">电子报</tspan></text><rect rx="2" ry="2" fill="#f0c189" x="0" y="4" width="16" height="12" zIndex="3"></rect></g></g></g></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(0,0)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="rgb(255,255,255)" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85"></rect><text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1"></text></g></svg></div></div>    </td>
    <td width="50%" class="h400">
<div id="yw1" data-highcharts-chart="1"><div class="highcharts-container" id="highcharts-2" style="position: relative; overflow: hidden; width: 481px; height: 350px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="481" height="350"><desc>Created with Highcharts 3.0.4</desc><defs><clippath id="highcharts-3"><rect fill="none" x="0" y="0" width="277" height="419"></rect></clippath></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="481" height="350"></rect><g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(447,10)"><title>Chart context menu</title><rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1"></text></g><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path fill="none" d="M 110.5 10 L 110.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 170.5 10 L 170.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 230.5 10 L 230.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 290.5 10 L 290.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 350.5 10 L 350.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 410.5 10 L 410.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 470.5 10 L 470.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 51.5 10 L 51.5 287" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 51 121.5 L 46 121.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 51 176.5 L 46 176.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 51 232.5 L 46 232.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 51 286.5 L 46 286.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 51 65.5 L 46 65.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 51 10.5 L 46 10.5" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 51.5 10 L 51.5 287" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-axis" zIndex="2"></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(471,287) rotate(90) scale(-1,1) scale(1 1)" style="" width="420" height="277" clip-path="url(#highcharts-3)"><rect fill="#d95455" x="236.5" y="44.5" width="26" height="375" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect><rect fill="#d95455" x="180.5" y="401.5" width="26" height="18" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect><rect fill="#d95455" x="125.5" y="416.5" width="26" height="3" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect><rect fill="#d95455" x="69.5" y="419.5" width="26" height="0" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect><rect fill="#d95455" x="14.5" y="419.5" width="26" height="0" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(471,287) rotate(90) scale(-1,1) scale(1 1)" width="420" height="277"></g></g><g class="highcharts-legend" zIndex="7" transform="translate(198,310)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="84" height="24" stroke="#909090" stroke-width="1" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">舆情数量</tspan></text><rect rx="2" ry="2" fill="#d95455" x="0" y="4" width="16" height="12" zIndex="3"></rect></g></g></g></g><g class="highcharts-axis-labels" zIndex="7"><text x="43" y="43.215624999999974" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:139px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="43">微博</tspan></text><text x="43" y="98.61562499999998" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:139px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="43">新闻</tspan></text><text x="43" y="154.015625" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:139px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="43">论坛</tspan></text><text x="43" y="209.41562500000003" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:139px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="43">博客</tspan></text><text x="43" y="264.815625" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:139px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="43">电子报</tspan></text></g><g class="highcharts-axis-labels" zIndex="7"><text x="51" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="51">0</tspan></text><text x="111" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="111">100</tspan></text><text x="171" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="171">200</tspan></text><text x="231" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="231">300</tspan></text><text x="291" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="291">400</tspan></text><text x="351" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="351">500</tspan></text><text x="411" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="411">600</tspan></text><text x="471" y="301" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="471">700</tspan></text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(0,0)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="rgb(255,255,255)" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85"></rect><text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1"></text></g></svg></div></div>    </td>
  </tr>
 </tbody></table> 
    </div>
    </div>
    </div>
  
  
</div>
</div>
</div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" src="./js/dimensionAnalysis/dataSource/bds_s_v2.js"></script>


     
<script>
Highcharts.setOptions({
	lang: {
    printChart:'打印图片',
		downloadJPEG: '保存为jpeg',
    downloadPNG: '保存为png',
    downloadSVG: '保存为svg',
    downloadPDF: '保存为pdf'
	}
});
</script>
<!--</div>--><!-- content -->


<div id="pop" style="display:none;">
  <div id="popHead"><h2>&nbsp;</h2><a id="popClose" title="关闭">关闭</a> </div>
  <div id="popContent">
    <dl>
      <dt id="popIntro"></dt>
      <dd id="popMore"><a href="http://yq.adt100.com/moniter/imp?merge=1"></a></dd>
    </dl>
  </div>
</div>

<script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="./js/jquery.min.js"><\/script>')</script>

<script>
  $(function(){
    $.getJSON("/moniter/pop?merge=1", function(data){
      if(data['num'] > 0) {
        var pop=new Pop(data['data'], data["num"]);
      }
    });
  //    var pop=new Pop(
  //    "请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介.请输入你的内容简介，这里是内容简介", "3");
  });
</script>


<!--
<script type="text/javascript" src="/apps/yuqing/theme/scripts/jquery-1.8.2.min.js"></script>
-->

<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/pop.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery.simInput.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/common.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/my.js"></script>

<script type="text/javascript">
  
  $(function(){
    $('[data-toggle="checkbox"]').simInput();
    $('[data-toggle="radio"]').simInput();
  });

</script>

<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]--> 

<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="./js/dimensionAnalysis/dataSource/jquery.yiilistview.js"></script>

</body>
</html>
