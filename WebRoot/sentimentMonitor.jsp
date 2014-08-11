<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Long userid = (Long)session.getAttribute("userid");
if(userid==null){
	response.sendRedirect("./loginWeb.jsp");
	return;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/sentimentMonitor/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="./css/sentimentMonitor/jquery-ui-timepicker-addon.css">
    <link rel="stylesheet" type="text/css" href="./css/sentimentMonitor/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/sentimentMonitor/pager.css">
    <script type="text/javascript" src="./js/sentimentMonitor/jquery.js"></script>
    <script type="text/javascript" src="./js/sentimentMonitor/jquery.yii.js"></script>
    <script type="text/javascript" src="./js/sentimentMonitor/jquery.ba-bbq.js"></script>
    <title>sentimentMonitor</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Bootstrap -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Theme -->
    <link rel="stylesheet" href="./css/style-red.css">

    <!--[if lt IE 7]>
    <link rel="stylesheet" href="/apps/yuqing/theme/red/css/ie6-my.css" />
    <![endif]-->

    <!--[if lt IE 8]>
    <link rel="stylesheet" href="/apps/yuqing/theme/red/css/ie6-fix.css" />
    <![endif]-->

    <link rel="stylesheet" href="http://yq.adt100.com/apps/yuqing/theme/red/css/style-red-my.css">


    <link href="./css/sentimentMonitor/bdsstyle.css" rel="stylesheet" type="text/css"><script src="./js/sentimentMonitorles/logger.js"></script>
</head>

<body>
<iframe frameborder="0" style="display: none;"></iframe><div id="bdshare_s" style="display: block;"><iframe id="bdsIfr" style="position:absolute;display:none;z-index:9999;" frameborder="0"></iframe><div id="bdshare_l" style="display: none;"><div id="bdshare_l_c"><h6>分享到</h6><ul><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_mshare mshare">一键分享</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_qzone qqkj">QQ空间</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tsina xlwb">新浪微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdysc bdysc">百度云收藏</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_renren rrw">人人网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tqq txwb">腾讯微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdxc bdxc">百度相册</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_kaixin001 kxw">开心网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tqf txpy">腾讯朋友</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tieba bdtb">百度贴吧</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_douban db">豆瓣网</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_tsohu shwb">搜狐微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_bdhome bdhome">百度新首页</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_sqq sqq">QQ好友</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_thx thx">和讯微博</a></li><li><a href="http://yq.adt100.com/moniter/index?merge=1#" class="bds_more">更多...</a></li></ul><p><a href="http://yq.adt100.com/moniter/index?merge=1#" class="goWebsite">百度分享</a></p></div></div></div>
<div class="navbar">
    <a class="appbrand"><img src="logo" alt="" style="position: relative;top:0;left: 25px;"></a>
    <button class="menu-toggle" type="button"></button>
    
    <!-- <div class="topnav pull-left ln">
        <div class="btn-group" id="J-toggle-site">
            <a class="btn dropdown-toggle btn-small">
                name
                <span class="caret" style="display: none;"></span>
            </a>
            <ul class="dropdown-menu"></ul>
        </div>
    </div> -->
    
    <ul class="topnav pull-right inline">
        <li><a href="" class="top-opt" data-toggle="tooltip" data-placement="bottom"><i></i> 关键词设置</a></li>
        <li><a href="" class="top-logout" data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a></li>
    </ul>

</div>

<div class="wrapper">
<div class="hidden-phone menu" id="menu">
    <div class="profile">
        <span>欢迎您：</span>
        <a>name</a>
    </div>
    <div id="side-search" class="search-box">
        <form action="" method="get">
            <div class="side-search-downlist">
                <p class="current" id="search-type">全部</p>
                <ul id="search-type-list" style="display: none;">
                    <li><a href="javascript:;" data-type="">全部</a></li>
                    <li><a href="javascript:;" data-type="news">新闻</a></li>
                    <li><a href="javascript:;" data-type="bbs">论坛</a></li>
                    <li><a href="javascript:;" data-type="blog">博客</a></li>
                    <li><a href="javascript:;" data-type="epub">电子报</a></li>
                    <li><a href="javascript:;" data-type="weibo">微博</a></li>
                </ul>
            </div>
            <div class="side-search-input">
                <input class="side-search" type="text" name="include" id="">
                <button class="side-search-btn"><i></i></button>
                <input id="search-type-flag" type="hidden" name="source" value="0">
                <input id="search-type-flag" type="hidden" name="adv" value="1">
            </div>
        </form>
    </div>
    <ul class="menu-lists">
        <li class="menu-list menu-general ">
            <a href="" class="menu-title"><i></i><span>舆情概况</span></a>
        </li>
        <li id="more-sub-menu" class="menu-list menu-lat ">
            <a href="" class="menu-title"><i></i><span>维度分析</span></a>
            <ul class="sub-menu-list" id="sub-menu-list">
                <li><a href="">舆情走势</a></li>
                <li><a href="">数据来源</a></li>
                <li><a href="">媒体来源</a></li>
                <li><a href="">地域分布</a></li>
                <li><a href="">情感走势</a></li>
                <li><a href="">话题分布</a></li>
                <li><a href="">TOP 排行</a></li>
            </ul>
        </li>


        <li class="menu-list menu-any active">
            <a href="" class="menu-title"><i></i><span>舆情监控</span></a>


        </li>
        <li class="menu-list menu-rep ">
            <a href="" class="menu-title"><i></i><span>数据报告</span></a>
        </li>
        <li class="menu-list menu-com ">
            <a href="" class="menu-title"><i></i><span>对比分析</span></a>
        </li>
    </ul>
</div>
<div id="content" class="content">
<!--<div id="content">-->
<ul class="breadcrumb">
    <li>您在这里：</li>
    <li class="color-red">全部舆情</li>
</ul>
<div class="innerLR">

<form action="h" method="get"><input type="hidden" value="3" name="period" id="period"><input type="hidden" value="" name="author" id="author"><input type="hidden" value="" name="adv" id="adv"><input type="hidden" value="" name="imp" id="imp"><div class="row-fluid">
    <div class="search-filter-box">
        <div class="normal-search-box clearfix">
            <div class="clearfix time-opt span10">
                <div class="control-group form-inline">
                    <ul class="inline tab-small">
                        <li class="first ">
                            <a class="moniter-period" href="">今日</a>            </li>
                        <li>
                            <a class="moniter-period" href="">昨日</a>            </li>
                        <li class="current">
                            <a class="moniter-period" href="">7天</a>
                        </li>
                        <li>
                            <a class="moniter-period" href="">30天</a>
                        </li>
                        <li></li>
                        <li style="padding-left:0px;">自定义：
                            <input class="input-small hasDatepicker" style="width:120px;margin-right:5px;" id="start_date" name="start_date" type="text" value="2014-07-15 00:00"> - <input class="input-small hasDatepicker" style="width:120px;margin-left:5px;" id="end_date" name="end_date" type="text" value="2014-07-21 23:59">
                        </li>
                        <li>
                            <div class="ez-checkbox ez-checked"><input data-toggle="checkbox" class="ez-hide" checked="checked" id="yt0" type="checkbox" value="1" name="merge"></div>合并相似文章
                        </li>
                    </ul>
                </div>
                <div class="control-group form-inline">

                    <label class="control-label" for="">来源：</label>          <select class="mr20" name="source" id="source">
                    <option value="" selected="selected">全部</option>
                    <option value="news">新闻</option>
                    <option value="bbs">论坛</option>
                    <option value="blog">博客</option>
                    <option value="epub">电子报</option>
                    <option value="weibo">微博</option>
                </select>

                    <label class="control-label" for="">情感：</label>          <select class="mr20" name="sentiment" id="sentiment">
                    <option value="" selected="selected">全部</option>
                    <option value="positive">正面</option>
                    <option value="negative">负面</option>
                    <option value="neutral">中立</option>
                </select>
                    <label class="control-label" for="">话题：</label>          <select class="mr20" name="topic_id" id="topic_id">
                    <option value="" selected="selected">全部</option>
                    <option value="996">name</option>
                    
                </select>
                </div>
            </div>
            <div class="span2">
                <p>
                    <a href="javascript:;" id="show-advance-search">高级搜索</a>
                </p>
                <p>

                    <a href="javascript:void(0);" onclick="jQuery.ajax({
              &#39;url&#39;:&#39;/moniter/history&#39;,
              &#39;cache&#39;:false,
              &#39;success&#39;:function(html){
                  $(&#39;#history_result&#39;).html(html);
                  $(&#39;#history_dialog&#39;).dialog(&#39;open&#39;);
                  return false;
                }
            });">
                        历史筛选器
                    </a>

                </p>
            </div>
        </div>
        <!-- normal -->

        <div class="advance-search-box J-advance-search-box">
            <div class="control-group form-horizontal">
                <label class="control-label">搜索结果：</label>
                <div class="controls">
                    <div class="control-group form-inline">
                        <p class="mb10">
                            <label class="control-label">包含以下<strong>全部</strong>关键词：</label>
                            <input type="text" value="" name="include" id="include">            </p>
                        <p class="mb10">
                            <label class="control-label">包含以下<strong>任一</strong>关键词：</label>
                            <input type="text" value="" name="any" id="any">            </p>
                        <p class="mb10">
                            <label class="control-label"><strong>不包含</strong>以下关键词：</label>
                            <input type="text" value="" name="exclude" id="exclude">            </p>
                    </div>
                </div>
                <label class="control-label">关键词位置：</label>
                <div class="controls s-radio">
                    <span id="position"><label><div class="ez-radio ez-selected"><input data-toggle="checkbox" value="0" id="position_0" checked="checked" type="radio" name="position" class="ez-hide"></div> 不限位置</label><label><div class="ez-radio"><input data-toggle="checkbox" value="1" id="position_1" type="radio" name="position" class="ez-hide"></div> 仅限标题中</label><label><div class="ez-radio"><input data-toggle="checkbox" value="2" id="position_2" type="radio" name="position" class="ez-hide"></div> 仅限URL中</label></span>        </div>
                <div class="mb10">
                    <label class="control-label">限制站点：</label>
                    <div class="controls">
                        <input type="text" value="" name="host" id="host">          </div>
                </div>
                <div class="mb10">
                    <label class="control-label">限制作者：</label>
                    <div class="controls">
                        <input type="text" value="" name="author" id="author">          </div>
                </div>
                <div class="mb10">
                    <label class="control-label">结果条数：</label>
                    <div class="controls">
                        <select name="perpage" id="perpage">
                            <option value="10">每页10条</option>
                            <option value="30">每页30条</option>
                            <option value="50">每页50条</option>
                            <option value="100">每页100条</option>
                        </select>        </div>
                </div>
                <div class="mb10 search-advance-btn">
                    <label class="control-label">&nbsp;</label>
                    <div class="controls">
                        <input class="btn-red" type="submit" name="yt1" value="高级搜索">          </div>
                </div>
            </div>
        </div>
        <div class="toggle-advance-search"><a href="javascript:;" id="hide-advance-search">隐藏高级搜索</a></div>
        <!-- adv -->


    </div>

</div>
</form>

<div class="row-fluid">
<div class="span12">
<div class="widget-tab">
<div class="widget-tab-hd">
    <ul class="clearfix tab-hd" id="moniter-sort-move">

        <li class="current">

            <a class="moniter-period" href="">全部</a>
        </li>

        <li>

            <a class="moniter-period" href="">正面</a>
        </li>

        <li>

            <a class="moniter-period" href="">中立</a>
        </li>

        <li>

            <a class="moniter-period" href="">负面</a>
        </li>


        <li class="moniter-sort-li"><a class="i-seq desc" href=""><span>发布时间</span></a></li>
        <li class="moniter-sort-li"><a class="i-seq" href=""><span>转载数</span></a></li></ul>
</div>
<div class="widget-tab-bd">

<div class="row-fluid" id="info_list">
<table id="moniter-sort"><tbody><tr>
    <th class="tac" nowrap=""><a class="i-seq desc" href=""><span>发布时间</span></a></th>
    <th class="tac" nowrap=""><a class="i-seq" href=""><span>转载数</span></a></th></tr></tbody></table>
<ul class="m-news-filter">
    <li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-red">正</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://bbs.zhongsou.com/3/m_41/2761.html">【新闻】&gt; 人间冷暖</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9553259">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                ... 最少交535.464元
                昨日，记者从郑州市社会保险局获悉，郑州市城镇职工及个体劳动者基本医疗保险缴费标准调整，除退休职工无需缴费外，在岗参保职工一年最少要交535.464元。按照规定，城镇职工基本医疗保险费由用人 [详细] 2014.07 21 10:26 美宝之家提醒防欺诈 赴美<b>月子</b><b>中心</b>要慎选
                关于近期赴美生子欺 诈事件频曝光，主要原因是由于很多的家庭对选择赴美生子的认识不够，对中介的机构了解不多，导致了入宿民宿，进而引起了欺 诈行为的在此出现，在美宝之家看来，赴美生子要 [详细] 2014.07  ... <span style="color:#FAFAF9;">9553259</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30883757"><span class="mr20">转载（1）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">中搜论坛</span>
                <span>2014/07/21 13:26:38</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-gray">中</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://bbs.zhongsou.com/3/20140721/14974416.html">【新闻】美宝之家是骗子?传闻可信度让人怀疑</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9549009">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                ... 随着需求的不断增长，行业间的竞争也慢慢朝着恶性化的趋势发展。很多小公司为了能招揽更多客户，不断的用一些非正当手段赢取自身的利益，通过众多的网络宣传平台恶意中伤一些较大规模正规的同行竞争者。
                美宝之家在中国和美国都是有注册过的正式机构，不仅在美国拥有多家大型<b>月子</b><b>中心</b>，在中国依然拥有超百人的专业服务团队。然而，正是这样一个行业的领头者却成为同行小公司群力攻击的对象。
                现在网络上很多人都在抨击美宝之家的种种罪行，而这些无非都是一些住宿条件不好、伙食不好这类千篇一律的问题，而当你真正问到一些实质性的问题时他们又 ... <span style="color:#FAFAF9;">9549009</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30875692"><span class="mr20">转载（1）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">中搜论坛</span>
                <span>2014/07/21 11:30:00</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-red">正</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://www.qianhuaweb.com/content/2014-07/21/content_5012547.htm">【新闻】编辑： 博涵</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9556413">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                ... 修复、心理疏导等24小时贴心照护服务。

                (石家庄<b>月子</b><b>中心</b>会所的护理人员为贵宾提供健康护理)
                兰 ... <span style="color:#FAFAF9;">9556413</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30889717"><span class="mr20">转载（1）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">千华网</span>
                <span>2014/07/21 09:59:00</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-gray">中</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="weibo-intro-bd">
                <a target="_blank" href="http://weibo.com/u/1876369791">曾子萱萱：</a>                抵达美丽加州洛杉矶。感谢模范好爸爸的支持                <a target="_blank" href="http://ww2.sinaimg.cn/large/6fd7217fjw1eik5wags5pj20u215ob2a.jpg"><img src="./舆情监控_files/29523.png"></a>                <a href="http://weibo.com/1876369791/BeyQZigY4" target="_blank">[查看原文]</a>
                <span style="color:#FAFAF9;">9537377</span>
            </div>
            <div class="weibo-plus clearfix">
                  <span class="fr">
                    <span class="mr20">转发（0）</span>
                    <span class="mr20">评论（3）</span>
                  </span>

                <span class="mr20"><a href="http://weibo.com/1876369791/BeyQZigY4" target="_blank">2014/07/21 09:38:17</a></span>
                <!--<span class="mr20">来自<a href="http://app.weibo.com/t/feed/3G5oUM" rel="nofollow">iPhone 5s</a></span>-->
                <span class="mr20">新浪微博</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>




        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-red">正</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://www.jiaodong.net/news/system/more/2010300/0035/2010300_00003560.shtml">【新闻】社会新闻</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9527682">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                ... 的哥(图) 新商圈大悦城加剧城市拥堵
                交警将进行路网改造 涉嫌洗黑钱？烟台一女子听信骗子被忽悠7800元 烟台机场2家VIP贵宾厅要关闭
                企业证实已接通知 <b>月子</b><b>中心</b>28天收费6.6万
                预约爆满排到明年2月份 伪造行驶证怕处罚跑路
                一年后才来交警队处理 夫妻头胎孩子健康有缺陷
                不受"单独两孩"政策限制 莱阳新 ... <span style="color:#FAFAF9;">9527682</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30836303"><span class="mr20">转载（1）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">胶东在线</span>
                <span>2014/07/21 04:04:01</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-gray">中</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="weibo-intro-bd">
                <a target="_blank" href="http://weibo.com/u/2711987451">微问助手：</a>                @<b>广州</b><b>月</b><b>嫂</b>公司_吉宝乐 您好！来自【微问】的网友向您请教育儿方面的问题：“一周四个<b>月</b>宝宝不吃饭，爱闹脾气怎么...”『分享知识和经验来帮助TA，请点击 http://t.cn/RP2WWRo 』回答还能攒积分换礼品，iPad在向你招手！不愿收到求助信息请点击http://t.cn/zTPze3U                <a target="_blank" href="http://ww4.sinaimg.cn/large/a1a5a4fbjw1eijvbyr1ubj20c80g0q49.jpg"><img src="./舆情监控_files/29523.png"></a>                <a href="http://weibo.com/2711987451/Bewpd5a5m" target="_blank">[查看原文]</a>
                <span style="color:#FAFAF9;">9527302</span>
            </div>
            <div class="weibo-plus clearfix">
                  <span class="fr">
                    <span class="mr20">转发（0）</span>
                    <span class="mr20">评论（0）</span>
                  </span>

                <span class="mr20"><a href="http://weibo.com/2711987451/Bewpd5a5m" target="_blank">2014/07/21 03:24:21</a></span>
                <!--<span class="mr20">来自<a href="http://app.weibo.com/t/feed/3ktQgw" rel="nofollow">微问</a></span>-->
                <span class="mr20">新浪微博</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>




        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-gray">中</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="weibo-intro-bd">
                <a target="_blank" href="http://weibo.com/u/5072991458">乐乐月嫂：</a>                发表了博文 《儿童最易发生烫伤的N个家庭细节》 - 　　夏季儿童意外烫伤高发 省消防医院收治的儿童占到总体病人的35% 　　每逢夏季来临，除了儿童溺水以外，烫伤等事故也呈高发态势。 　　昨天，<b>广州</b><b>月</b><b>嫂</b> http://t.cn/RPAVxBi                                <a href="http://weibo.com/5072991458/BeiQIFBlJ" target="_blank">[查看原文]</a>
                <span style="color:#FAFAF9;">9472818</span>
            </div>
            <div class="weibo-plus clearfix">
                  <span class="fr">
                    <span class="mr20">转发（0）</span>
                    <span class="mr20">评论（0）</span>
                  </span>

                <span class="mr20"><a href="http://weibo.com/5072991458/BeiQIFBlJ" target="_blank">2014/07/19 16:53:40</a></span>
                <!--<span class="mr20">来自<a href="http://app.weibo.com/t/feed/2Bvk1e" rel="nofollow">新浪博客</a></span>-->
                <span class="mr20">新浪微博</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>




        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-red">正</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://www.chinadaily.com.cn/hqcj/xfly/2014-07-19/content_12039259.html">【新闻】京华良子"奶奶、姥姥班"开课啦</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9450705">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                [提要] <b>月</b><b>嫂</b>市场劲爆点，京华良子真正实现把<b>月子</b><b>中心</b>搬回家，让爱在家里无限蔓延，京华良子把 ... <span style="color:#FAFAF9;">9450705</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30745609"><span class="mr20">转载（2）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">中国日报网</span>
                <span>2014/07/19 03:43:00</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-red">正</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="news-intro-hd">
                <strong><a target="_blank" href="http://news.xinmin.cn/rollnews/2014/07/19/24834545.html">【新闻】京华良子家庭服务有限公司招聘</a></strong>
                <span><a target="_blank" href="http://yq.adt100.com/moniter/snapshot/9450244">网页快照</a></span>
            </div>
            <div class="news-intro-bd">
                ... 2.性别不限；3.一年以上销售岗位工作经验或有 <b>月</b><b>嫂</b> 工作经验者优先；4.有较强的沟通表达能力。
                工资标准 ：面谈
                如有意向，请把简历投送至：zhaopin@ihomecare
                北京京华良子家庭服务有限公司诚邀加盟，和我们一起"把<b>月子</b><b>中心</b>搬回家"！加盟热线：4000515252
                新民网茶馆 由新民网出品
                微信号：newteahouse
                无节操、有道理
                最麻辣，最有趣的时事脱口秀！
                你今天脑补了吗？
                新民网事 由新民网出品
                微信号：xinminwangshi
                突发事、新鲜事、有趣事
                ... <span style="color:#FAFAF9;">9450244</span>
            </div>

            <div class="news-plus clearfix link-gray">
                  <span class="fr">
                    <a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30745580"><span class="mr20">转载（1）</span></a>                    <!--
                    <span class="mr20">点击（0）</span>
                    <span class="mr20">评论（0）</span>
                    -->
                  </span>

                <span class="mr20">佚名</span>
                <span class="mr20">新民网</span>
                <span>2014/07/19 03:38:00</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>


        </div>
    </div>


</li>
<li class="m-news-filter-item clearfix">
    <div class="m-i-flag">
        <span class="bg-sapn-gray">中</span>    </div>

    <div class="m-news-main">
        <div class="m-news-intro ">
            <div class="weibo-intro-bd">
                <a target="_blank" href="http://weibo.com/u/5072991458">乐乐月嫂：</a>                发表了博文 《怀孕前三个<b>月</b>有哪些食物不能够食用》 - 　　女性怀孕以后，前三个<b>月</b>不能够吃那些食物？<b>广州</b><b>月</b><b>嫂</b>公司之孕期知识提醒大家，孕妇在怀孕期间最好不要吃油条，动物的肝脏，过敏性食物。对于饮食的误区孕 http://t.cn/RPwtfTW                                <a href="http://weibo.com/5072991458/Be9tFd9L7" target="_blank">[查看原文]</a>
                <span style="color:#FAFAF9;">9428749</span>
            </div>
            <div class="weibo-plus clearfix">
                  <span class="fr">
                    <span class="mr20">转发（0）</span>
                    <span class="mr20">评论（0）</span>
                  </span>

                <span class="mr20"><a href="http://weibo.com/5072991458/Be9tFd9L7" target="_blank">2014/07/18 17:02:09</a></span>
                <!--<span class="mr20">来自<a href="http://app.weibo.com/t/feed/2Bvk1e" rel="nofollow">新浪博客</a></span>-->
                <span class="mr20">新浪微博</span>
            </div>
            <div class="news-tag">
                话题：
                <span class="tag-block">广州月子中心</span>              </div>




        </div>
    </div>


</li>
</ul><br><div class="pager"><div id="yw0" class="page-count">  <strong class="mr20" id="total-search-count">67条搜索结果</strong>


    <span class="current">1</span>
    <a href="">2</a>
    <a href="">3</a>
    <a href="">4</a>
    <a href="">5</a>
    <a href="">6</a>
    <a href="">7</a>
    <a href="">下一页</a>
    <a href="">尾页</a></div></div><div class="keys" style="display:none" title="/moniter/index?merge=1"><span>9553259</span><span>9549009</span><span>9556413</span><span>9537377</span><span>9527682</span><span>9527302</span><span>9472818</span><span>9450705</span><span>9450244</span><span>9428749</span></div>
</div>
<script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=0" src="./js/sentimentMonitor/bds_s_v2.js"></script>

<script type="text/javascript">
    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script>
</div>
</div>
</div>
</div>


</div>
<script>
    $(function(){
        $("#moniter-sort-move").find('li[class=moniter-sort-li]').each(function(){$(this).remove();});
        $("#moniter-sort").find('th').each(function(){
            $("#moniter-sort-move").append('<li class=moniter-sort-li>'+ $(this).html() + '</li>');
        });
    });

</script>

<!--</div>--><!-- content -->
</div>
</div>

<div id="pop" style="display:none;">
    <div id="popHead"><h2>&nbsp;</h2><a id="popClose" title="关闭">关闭</a> </div>
    <div id="popContent">
        <dl>
            <dt id="popIntro"></dt>
            <dd id="popMore"><a href=""></a></dd>
        </dl>
    </div>
</div>

<script type="text/javascript">window.jQuery || document.write('<script type="text/javascript" src="/apps/yuqing/theme/scripts/jquery-1.8.2.min.js"><\/script>')</script>

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

<script type="text/javascript" src="./js/sentimentMonitor/pop.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/jquery.simInput.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/common.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/my.js"></script>

<script type="text/javascript">

    $(function(){
        $('[data-toggle="checkbox"]').simInput();
        $('[data-toggle="radio"]').simInput();
    });

</script>

<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]-->

<script type="text/javascript" src="./js/sentimentMonitor/jquery-ui.min.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/jquery-ui-i18n.min.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="./js/sentimentMonitor/jquery.yiilistview.js"></script>
<script type="text/javascript">
    /*<![CDATA[*/
    jQuery(function($) {

        jQuery('#start_date').datetimepicker(jQuery.extend({showMonthAfterYear:false}, jQuery.datepicker.regional['en_us'], {'dateFormat':'yy-mm-dd','onClose':function(dateText, inst) {
            document.getElementById("period").value=""; this.form.submit();
        }}));
        jQuery('#end_date').datetimepicker(jQuery.extend({showMonthAfterYear:false}, jQuery.datepicker.regional['en_us'], {'dateFormat':'yy-mm-dd','onClose':function(dateText, inst) {
            document.getElementById("period").value=""; this.form.submit();
        }}));
        jQuery('body').on('click','#yt0',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#source',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#sentiment',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('body').on('change','#topic_id',function(){jQuery.yii.submitForm(this,'',{});return false;});
        jQuery('#info_list').yiiListView({'ajaxUpdate':[],'ajaxVar':'ajax','pagerClass':'pager','loadingClass':'list-view-loading','sorterClass':'sorter','enableHistory':false});
        jQuery('#mail_dialog').dialog({'title':'分享到邮件','autoOpen':false,'modal':true,'width':'500','height':'400','buttons':[{'text':'发送','click':function(){
            $.post(
                    $("#mail_form").attr("action"),
                    $("#mail_form").serialize(),
                    function(html){
                        $("#mail_dialog").dialog("close");
                        $("#hint_result").html(html);
                        $("#hint_dialog").dialog("open");
                    }
            );
        }},{'text':'取消','click':function(){$(this).dialog("close");}}]});
        jQuery('#share_dialog').dialog({'title':'分享到媒体','autoOpen':false,'modal':true,'width':'500','height':'400'});
        jQuery('#hint_dialog').dialog({'title':'操作结果','autoOpen':false,'modal':true,'width':'200','height':'200'});
        jQuery('#transmit_dialog').dialog({'title':'发送私信','autoOpen':false,'modal':true,'width':'500','height':'400','buttons':[{'text':'发送','click':function(){
            $.post(
                    $("#transmit_form").attr("action"),
                    $("#transmit_form").serialize(),
                    function(html){
                        $("#transmit_dialog").dialog("close");
                        $("#hint_result").html(html);
                        $("#hint_dialog").dialog("open");
                    }
            );
        }},{'text':'取消','click':function(){$(this).dialog("close");}}]});
        jQuery('#history_dialog').dialog({'title':'历史筛选器','autoOpen':false,'modal':true,'width':'600','height':'200'});
    });
    /*]]>*/
</script>


<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable ui-dialog-buttons" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-1"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-1" class="ui-dialog-title">分享到邮件</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="mail_dialog" class="ui-dialog-content ui-widget-content">
    <form id="mail_form" action="http://yq.adt100.com/info/mail" method="post">  <input type="hidden" name="info_id" id="info_id">
        <div class="winbox-share">
            <div class="share-content">
                <strong id="org_title"></strong>
                <div id="org_summary"></div>
            </div>
            <label>邮件地址：</label>
            <div class="">
                <input type="text" value="" name="mail_to" id="mail_to">
            </div>
            <label>邮件正文：</label>
            <div class="share-input">
                <textarea name="mail_content" id="mail_content"></textarea>
            </div>
        </div></form></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div><div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix"><div class="ui-dialog-buttonset"><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">发送</span></button><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">取消</span></button></div></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-2"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-2" class="ui-dialog-title">分享到媒体</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="share_dialog" class="ui-dialog-content ui-widget-content">
    <div class="winbox-share">
        <div class="share-content">
            <strong id="share_org_title"></strong>
            <div id="share_org_summary"></div>
        </div>
        <div>
            <!-- Baidu Button BEGIN -->
            <%--<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare">--%>
                <%--<a class="bds_qzone" title="分享到QQ空间" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_tsina" title="分享到新浪微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_tqq" title="分享到腾讯微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_renren" title="分享到人人网" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<a class="bds_t163" title="分享到网易微博" href="http://yq.adt100.com/moniter/index?merge=1#"></a>--%>
                <%--<span class="bds_more"></span>--%>
                <%--<a class="shareCount" href="http://yq.adt100.com/moniter/index?merge=1#" title="累计分享0次">0</a>--%>
            <%--</div>--%>

            <!-- Baidu Button END -->
        </div>
    </div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-3"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-3" class="ui-dialog-title">操作结果</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="hint_dialog" class="ui-dialog-content ui-widget-content">
    <div id="hint_result"></div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable ui-dialog-buttons" tabindex="-1" style="display: none; outline: 0px; z-index: 1000; position: absolute;" role="dialog" aria-labelledby="ui-id-4"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-4" class="ui-dialog-title">发送私信</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div style="" id="transmit_dialog" class="ui-dialog-content ui-widget-content">
    <%--<form id="transmit_form" action="http://yq.adt100.com/info/msg" method="post"><input type="hidden" name="info_id" id="minfo_id">--%>
        <%--<div class="winbox-share">--%>
            <%--<div class="share-content">--%>
                <%--<strong id="morg_title"></strong>--%>
                <%--<div id="morg_summary"></div>--%>
            <%--</div>--%>
            <%--<label>私信给：</label>--%>
            <%--<div class="">--%>
                <%--<select name="msg_to" id="msg_to">--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<label>私信正文：</label>--%>
            <%--<div class="share-input">--%>
                <%--<textarea name="msg_content" id="msg_content"></textarea>--%>
            <%--</div>--%>
        <%--</div></form></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div><div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix"><div class="ui-dialog-buttonset"><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">发送</span></button><button type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">取消</span></button></div></div></div><div class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable" tabindex="-1" role="dialog" aria-labelledby="ui-id-5" style="display: none; outline: 0px; z-index: 1000; position: absolute;"><div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-5" class="ui-dialog-title">历史筛选器</span><a href="http://yq.adt100.com/moniter/index?merge=1#" class="ui-dialog-titlebar-close ui-corner-all" role="button"><span class="ui-icon ui-icon-closethick">close</span></a></div><div id="history_dialog" class="ui-dialog-content ui-widget-content">--%>
    <%--<div id="history_result"></div></div><div class="ui-resizable-handle ui-resizable-n" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-e" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-s" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-w" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se ui-icon-grip-diagonal-se" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-sw" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-ne" style="z-index: 1000;"></div><div class="ui-resizable-handle ui-resizable-nw" style="z-index: 1000;"></div></div>--%>

</body>
</html>