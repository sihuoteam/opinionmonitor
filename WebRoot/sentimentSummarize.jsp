<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/sentimentSummarize/YiiTagCloud.css">
    <script type="text/javascript" src="./js/sentimentSummarize/jquery.js"></script>
    <script type="text/javascript" src="./js/sentimentSummarize/highcharts.src.js"></script>
    <script type="text/javascript" src="./js/sentimentSummarize/highcharts-more.src.js"></script>
    <script type="text/javascript" src="./js/sentimentSummarize/exporting.src.js"></script>
    <title>sentimentSummarize</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Bootstrap --> 
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- Theme -->
    <link rel="stylesheet" href="./css/style-red.css">
    <link rel="stylesheet" href="./css/style-red-my.css">

</head>
<body>
<div class="navbar">
    <a class="appbrand"><img src="logo.jpg" alt="" style="position: relative;top:0;left: 25px;"></a>
    <button class="menu-toggle" type="button"></button>
    <div class="topnav pull-left ln">
        <div class="btn-group" id="J-toggle-site">
            <a class="btn dropdown-toggle btn-small">
                name
                <span class="caret" style="display: none;"></span>
            </a>            <ul class="dropdown-menu">
        </ul>
        </div>
    </div>
    <ul class="topnav pull-right inline">
        <li><a href="" class="top-opt" data-toggle="tooltip" data-placement="bottom"><i></i> 设置</a></li>
        <li><a href="" class="top-logout" data-toggle="tooltip" data-placement="bottom"><i></i> 退出</a></li>
    </ul>

</div>

<div class="wrapper">
<div class="hidden-phone menu" id="menu" style="display: block;">
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
        <li class="menu-list menu-general active">
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


        <li class="menu-list menu-any ">
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
<div id="content" class="content" style="margin-left: 230px;">
    <!--<div id="content">-->

    <ul class="breadcrumb">
        <li>您在这里：</li>
        <li class="color-red">舆情概况</li>
    </ul>
    <div class="innerLR">

        <div class="row-fluid">
            <div class="span12">
                <ul class="inline tab-small">
                    <li class="first ">
                        <a href="">今日</a>        </li>
                    <li>
                        <a href="">昨日</a>        </li>
                    <li>
                        <a href="">前日</a>        </li>
                </ul>
            </div>
        </div>


        <div class="row-fluid">
            <div class="span3">
                <div class="widget">
                    <dl class="trend-box clearfix">
                        <dt>
                        <p>
                            <img src="">
                        </p>
                        <!--<p class="color-green">
                        +200%</p>-->
                        </dt>
                        <dd>
                            <h5><a href="">6</a></h5>
                            <p>舆情总量</p>
                        </dd>
                    </dl>
                </div>
            </div>
            <div class="span3 mglf20">
                <div class="widget">
                    <dl class="trend-box clearfix">
                        <dt>
                        <p>
                            <img src="">
                        </p>
                        <!--<p class="color-green">
                        +-</p>-->
                        </dt>
                        <dd>
                            <h5><a href="">positive:3</a></h5>
                            <p>正面</p>
                        </dd>
                    </dl>
                </div>
            </div>
            <div class="span3 mglf20">
                <div class="widget">
                    <dl class="trend-box clearfix">
                        <dt>
                        <p>
                            <img src="">
                        </p>
                        <!--<p class="color-green">
                        +50%</p>-->
                        </dt>
                        <dd>
                            <h5><a href="">3</a></h5>
                            <p>中立</p>
                        </dd>
                    </dl>
                </div>
            </div>
            <div class="span3 mglf20">
                <div class="widget">
                    <dl class="trend-box clearfix">
                        <dt>
                        <p>
                            <img src="">
                        </p>
                        <!--<p class="color-green">
                        +-</p>-->
                        </dt>
                        <dd>
                            <h5><a href="">0</a></h5>
                            <p>负面</p>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>




        <div class="row-fluid">
            <div class="span6">
                <div class="widget">
                    <div class="widget-hd"><h4>情感指数</h4></div>
                    <div class="widget-bd h290">
                        <div id="container" class="h220" data-highcharts-chart="0">
                            <div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 530px; height: 220px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="530" height="220"><desc>Created with Highcharts 3.0.4</desc><defs><clippath id="highcharts-1"><rect fill="none" x="1" y="0" width="509" height="195"></rect></clippath><lineargradient x1="0" y1="0" x2="0" y2="1" id="highcharts-2"><stop offset="0" stop-color="#FFF" stop-opacity="1"></stop><stop offset="1" stop-color="#DDD" stop-opacity="1"></stop></lineargradient><lineargradient x1="0" y1="0" x2="0" y2="1" id="highcharts-7"><stop offset="0" stop-color="#FFF" stop-opacity="1"></stop><stop offset="1" stop-color="#ACF" stop-opacity="1"></stop></lineargradient><lineargradient x1="0" y1="0" x2="0" y2="1" id="highcharts-8"><stop offset="0" stop-color="#9BD" stop-opacity="1"></stop><stop offset="1" stop-color="#CDF" stop-opacity="1"></stop></lineargradient></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="530" height="220"></rect><path fill="url(#highcharts-2)" d="M 265 47.5 A 157.5 157.5 0 1 1 264.8425000262499 47.500078749993435 M 265 205 A 0 0 0 1 0 265 205 " stroke="silver" stroke-width="1"></path><path fill="#4c88a2" d="M 115 204.99999999999997 A 150 150 0 0 1 158.82796985552176 99.04010185452233 L 165.90610519848696 106.10409506422084 A 140 140 0 0 0 125 204.99999999999997 Z"></path><path fill="#DDDF0D" d="M 158.93398282201787 98.93398282201787 A 150 150 0 0 1 370.95989814547767 98.82796985552173 L 363.89590493577913 105.90610519848694 A 140 140 0 0 0 166.00505063388334 106.00505063388334 Z"></path><path fill="#d95455" d="M 371.06601717798213 98.93398282201788 A 150 150 0 0 1 414.9999250000062 204.85000002499999 L 404.99993000000586 204.86000002333333 A 140 140 0 0 0 363.99494936611666 106.00505063388336 Z"></path><g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(496,10)"><title>Chart context menu</title><rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1"></text></g><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 115 204.99999999999997 L 125 204.99999999999997" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 115.07401594514025 200.28838613828077 L 125.06908154879758 200.60249372906205" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 115.29599073575926 195.58142207060303 L 125.27625802004198 196.20932726589615" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 115.665705309538 190.8837530022229 L 125.62132495556878 191.82483613540805" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 116.18279480282831 186.2000149653544 L 126.10394181597309 187.45334730099742" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 116.84674891072933 181.53483024396542 L 126.72363231668072 183.09917489436774" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 117.6569123906967 176.89280281214138 L 127.47978489798359 178.76661595799862" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 118.61248570918787 172.27851379051873 L 128.37165332857535 174.45994620448414" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 119.71252583070532 167.6965169252719 L 129.3983574419916 170.18341579692043" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 120.95594714845848 163.15133409411575 L 130.55888400522792 165.94124515450804" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 122.34152255572693 158.647450843758 L 131.85208771867846 161.73762078750747" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 123.86788465686612 154.18931196320642 L 133.2766923464084 157.57669116565933" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 125.53352711676223 149.78131709729846 L 134.83129197564475 153.46256262414522" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 127.33680614740277 145.42781640478302 L 136.5143524042426 149.39929531113083" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 129.275942130097 141.13310626523923 L 138.3242126547572 145.39089918088996" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 131.34902137174475 136.9014250390681 L 140.25908661362845 141.44133003646354" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 133.5539979934204 132.73694888474284 L 142.31706479385903 137.55448562575998" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 135.8886959494084 128.6437876374444 L 144.49611621944786 133.7342017949481" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 138.35081117469767 124.62598075315061 L 146.7940904297178 129.9842487029406" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 140.93791385881568 120.68749332218047 L 149.2087196015613 126.30832710070177" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 143.6474508437578 116.83221215612913 L 151.7376207875073 122.71006467905386" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 146.47674814364638 113.06394195205364 L 154.37829826740327 119.1930124885834" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 149.42301358363153 109.38640153769663 L 157.12814601138945 115.76064143518352" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 152.48333955543103 105.80322020145228 L 159.9844502517356 112.41633885468879" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 155.6547058867882 102.31793411069677 L 162.94439216100233 109.16340516998366" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 158.93398282201778 98.93398282201797 L 166.00505063388326 106.00505063388343" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 162.31793411069663 95.65470588678834 L 169.16340516998355 102.94439216100244" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 165.80322020145215 92.48333955543114 L 172.41633885468866 99.98445025173574" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 169.38640153769649 89.42301358363166 L 175.7606414351834 97.12814601138955" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 173.06394195205343 86.4767481436465 L 179.1930124885832 94.37829826740341" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 176.83221215612897 83.64745084375791 L 182.71006467905372 91.73762078750738" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 180.68749332218033 80.93791385881578 L 186.30832710070166 89.20871960156138" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 184.6259807531505 78.35081117469775 L 189.98424870294042 86.79409042971791" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 188.64378763744423 75.88869594940849 L 193.73420179494798 84.49611621944793" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 192.73694888474262 73.55399799342052 L 197.55448562575978 82.31706479385915" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 196.90142503906793 71.34902137174484 L 201.4413300364634 80.25908661362853" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 201.1331062652391 69.27594213009709 L 205.39089918088982 78.32421265475728" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 205.42781640478287 67.33680614740285 L 209.3992953111307 76.51435240424266" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 209.78131709729826 65.53352711676229 L 213.46256262414505 74.8312919756448" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 214.18931196320628 63.86788465686618 L 217.57669116565918 73.27669234640842" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 218.64745084375787 62.34152255572698 L 221.73762078750735 71.85208771867852" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 223.15133409411555 60.95594714845856 L 225.94124515450787 70.55888400522798" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 227.69651692527177 59.712525830705346 L 230.18341579692031 69.39835744199166" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 232.2785137905186 58.612485709187894 L 234.459946204484 68.37165332857538" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 236.89280281214124 57.656912390696704 L 238.76661595799848 67.47978489798359" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 241.53483024396536 56.84674891072936 L 243.09917489436765 66.72363231668072" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 246.2000149653543 56.18279480282834 L 247.45334730099736 66.10394181597312" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 250.88375300222285 55.665705309538 L 251.824836135408 65.62132495556881" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 255.58142207060297 55.29599073575926 L 256.2093272658961 65.27625802004198" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 260.2883861382807 55.074015945140275 L 260.60249372906196 65.06908154879758" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 265 55 L 265 65" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 269.7116138617192 55.074015945140246 L 269.3975062709379 65.06908154879758" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 274.418577929397 55.29599073575926 L 273.79067273410385 65.27625802004198" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 279.1162469977771 55.665705309538 L 278.175163864592 65.62132495556881" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 283.7999850346456 56.18279480282831 L 282.54665269900255 66.10394181597309" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 288.4651697560346 56.84674891072933 L 286.9008251056323 66.72363231668072" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 293.1071971878587 57.656912390696704 L 291.2333840420014 67.47978489798359" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 297.7214862094814 58.612485709187894 L 295.540053795516 68.37165332857535" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 302.3034830747282 59.71252583070532 L 299.81658420307963 69.39835744199164" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 306.84866590588433 60.955947148458534 L 304.05875484549205 70.55888400522795" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 311.3525491562421 62.341522555726954 L 308.26237921249265 71.85208771867849" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 315.81068803679364 63.86788465686615 L 312.42330883434073 73.27669234640842" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 320.2186829027017 65.53352711676229 L 316.5374373758549 74.8312919756448" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 324.57218359521704 67.33680614740283 L 320.60070468886926 76.51435240424263" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 328.8668937347609 69.27594213009706 L 324.60910081911015 78.32421265475726" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 333.09857496093196 71.34902137174481 L 328.5586699635365 80.25908661362848" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 337.26305111525727 73.55399799342044 L 332.44551437424013 82.31706479385909" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 341.35621236255565 75.88869594940843 L 336.26579820505196 84.49611621944787" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 345.37401924684946 78.35081117469774 L 340.0157512970595 86.79409042971788" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 349.31250667781956 80.9379138588157 L 343.6916728992983 89.20871960156133" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 353.167787843871 83.64745084375788 L 347.28993532094626 91.73762078750735" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 356.93605804794646 86.47674814364643 L 350.8069875114167 94.37829826740334" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 360.6135984623034 89.42301358363157 L 354.2393585648165 97.12814601138948" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 364.19677979854777 92.48333955543106 L 357.5836611453112 99.98445025173565" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 367.6820658893033 95.6547058867883 L 360.83659483001645 102.94439216100241" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 371.06601717798213 98.93398282201788 L 363.99494936611666 106.00505063388336" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 374.3452941132117 102.31793411069667 L 367.0556078389976 109.16340516998356" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 377.51666044456897 105.80322020145222 L 370.01554974826433 112.41633885468875" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 380.5769864163684 109.38640153769659 L 372.8718539886105 115.76064143518349" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 383.52325185635357 113.06394195205354 L 375.62170173259665 119.1930124885833" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 386.3525491562421 116.83221215612903 L 378.26237921249265 122.71006467905376" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 389.0620861411843 120.68749332218044 L 380.7912803984387 126.30832710070175" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 391.6491888253023 124.62598075315059 L 383.2059095702822 129.98424870294053" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 394.11130405059157 128.64378763744435 L 385.50388378055214 133.73420179494806" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 396.44600200657953 132.73694888474273 L 387.6829352061409 137.55448562575987" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 398.6509786282552 136.901425039068 L 389.7409133863715 141.44133003646346" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 400.72405786990294 141.13310626523915 L 391.6757873452427 145.39089918088987" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 402.6631938525972 145.427816404783 L 393.4856475957574 149.3992953111308" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 404.46647288323777 149.78131709729837 L 395.1687080243552 153.46256262414514" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 406.1321153431338 154.1893119632064 L 396.72330765359163 157.5766911656593" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 407.658477444273 158.6474508437579 L 398.1479122813215 161.73762078750735" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 409.04405285154144 163.15133409411564 L 399.4411159947721 165.94124515450795" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 410.2874741692947 167.69651692527174 L 400.60164255800834 170.1834157969203" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 411.3875142908121 172.27851379051867 L 401.62834667142465 174.45994620448408" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 412.3430876093033 176.89280281214127 L 402.52021510201644 178.7666159579985" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 413.15325108927067 181.53483024396536 L 403.2763676833193 183.09917489436768" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 413.8172051971717 186.20001496535428 L 403.89605818402686 187.4533473009973" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 414.334294690462 190.8837530022228 L 404.3786750444312 191.82483613540793" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 414.7040092642407 195.58142207060286 L 404.723741979958 196.20932726589598" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 414.9259840548598 200.2883861382807 L 404.9309184512024 200.602493729062" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 415 204.9999999999998 L 405 204.9999999999998" stroke="#666" stroke-width="1" opacity="1"></path><path fill="none" d="M 116.84674891072936 181.53483024396536 L 126.72363231668072 183.09917489436765" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 122.34152255572698 158.64745084375787 L 131.85208771867852 161.73762078750735" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 131.34902137174484 136.901425039068 L 140.2590866136285 141.44133003646346" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 143.6474508437579 116.83221215612902 L 151.73762078750735 122.71006467905374" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 158.93398282201787 98.93398282201787 L 166.00505063388334 106.00505063388334" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 176.83221215612906 83.64745084375788 L 182.71006467905377 91.73762078750735" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 196.901425039068 71.34902137174481 L 201.44133003646346 80.2590866136285" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 218.6474508437579 62.341522555726954 L 221.73762078750738 71.85208771867849" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 241.5348302439654 56.84674891072933 L 243.09917489436768 66.72363231668072" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 265 55 L 265 65" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 288.46516975603464 56.84674891072933 L 286.90082510563235 66.72363231668072" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 311.3525491562421 62.34152255572698 L 308.26237921249265 71.85208771867852" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 333.098574960932 71.34902137174484 L 328.55866996353654 80.25908661362851" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 353.167787843871 83.64745084375788 L 347.28993532094626 91.73762078750735" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 371.06601717798213 98.93398282201788 L 363.99494936611666 106.00505063388336" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 386.3525491562421 116.83221215612903 L 378.26237921249265 122.71006467905376" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 398.6509786282552 136.901425039068 L 389.7409133863715 141.44133003646346" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 407.658477444273 158.6474508437579 L 398.1479122813215 161.73762078750735" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 413.15325108927067 181.53483024396536 L 403.2763676833193 183.09917489436768" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 415 205 L 405 205" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 115 204.99999999999997 L 125 204.99999999999997" stroke="#666" stroke-width="2" opacity="1"></path><path fill="none" d="M 115 204.99999999999997 A 150 150 0 1 1 414.9999250000062 204.85000002499999 M 265 205 A 0 0 0 1 0 265 205 " stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-data-labels" visibility="visible" zIndex="2" transform="translate(10,10) scale(1 1)"><g zIndex="1" style="cursor:default;" transform="translate(241,210)" visibility="hidden"><rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="29" height="22" stroke="silver" stroke-width="1"></rect><text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;font-weight:bold;color:#666;line-height:14px;fill:#666;" zIndex="1"><tspan x="3">2.5</tspan></text></g></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)" style="" clip-path="url(#highcharts-1)"><path fill="black" d="M -12 -1.5 L 84 -1.5 120 -0.5 120 0.5 84 1.5 -12 1.5 z" transform="translate(255,195) rotate(-45 0 0)"></path><circle cx="0" cy="0" r="5" fill="black" transform="translate(255,195)"></circle></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)"></g></g><g class="highcharts-legend" zIndex="7"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="7" height="7" stroke="#909090" stroke-width="1" visibility="hidden"></rect><g zIndex="1"><g></g></g></g><g class="highcharts-axis-labels" zIndex="7"><text x="146.11793546310582" y="166.37287570313157" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-72 146.11793546310582 166.37287570313157)" opacity="1"><tspan x="146.11793546310582">-4</tspan></text><text x="191.52684346344085" y="103.87287570313157" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-36 191.52684346344085 103.87287570313157)" opacity="1"><tspan x="191.52684346344085">-2</tspan></text><text x="265" y="80" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0)" opacity="1"><tspan x="265">0</tspan></text><text x="338.47315653655915" y="103.87287570313157" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(36 338.47315653655915 103.87287570313157)" opacity="1"><tspan x="338.47315653655915">2</tspan></text><text x="383.8820645368942" y="166.37287570313157" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(72 383.8820645368942 166.37287570313157)" opacity="1"><tspan x="383.8820645368942">4</tspan></text><text x="140" y="204.99999999999997" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-90 140 204.99999999999997)" opacity="1"><tspan x="140">-5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-81 0 -9999)"><tspan x="0">-4.5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-62.99999999999999 0 -9999)"><tspan x="0">-3.5</tspan></text><text x="163.87287570313157" y="131.52684346344085" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-54 163.87287570313157 131.52684346344085)" opacity="1"><tspan x="163.87287570313157">-3</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-45 0 -9999)"><tspan x="0">-2.5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-27 0 -9999)"><tspan x="0">-1.5</tspan></text><text x="226.37287570313157" y="86.1179354631058" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-18 226.37287570313157 86.1179354631058)" opacity="1"><tspan x="226.37287570313157">-1</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(-9 0 -9999)"><tspan x="0">-0.5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(9 0 -9999)"><tspan x="0">0.5</tspan></text><text x="303.62712429686843" y="86.11793546310581" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(18 303.62712429686843 86.11793546310581)" opacity="1"><tspan x="303.62712429686843">1</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(27 0 -9999)"><tspan x="0">1.5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(45 0 -9999)"><tspan x="0">2.5</tspan></text><text x="366.12712429686843" y="131.52684346344085" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(54 366.12712429686843 131.52684346344085)" opacity="1"><tspan x="366.12712429686843">3</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(62.99999999999999 0 -9999)"><tspan x="0">3.5</tspan></text><text x="0" y="-9999" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(81 0 -9999)"><tspan x="0">4.5</tspan></text><text x="390" y="205" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" transform="translate(0,0) rotate(90 390 205)" opacity="1"><tspan x="390">5</tspan></text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(0,0)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="rgb(255,255,255)" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85"></rect><text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1"></text></g></svg><div class="highcharts-contextmenu" style="position: absolute; z-index: 1000; padding: 24px; display: none; right: -14px; top: 8px;"><div style="-webkit-box-shadow: rgb(136, 136, 136) 3px 3px 10px; box-shadow: rgb(136, 136, 136) 3px 3px 10px; border: 1px solid rgb(160, 160, 160); background-color: rgb(255, 255, 255); padding: 5px 0px; background-position: initial initial; background-repeat: initial initial;"><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">打印图片</div><hr><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为png</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为jpeg</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为pdf</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为svg</div></div></div></div></div>
                        <div id="today-index">情感指数：<span class="color-red">2.50</span></div>
                        <div id="yw0"></div>          </div>
                </div>
            </div>

            <div class="span6 mglf20">
                <div class="widget">
                    <div class="widget-hd"><h4>情感分布</h4></div>
                    <div class="widget-bd h290">
                        <div id="pie" class="h290" data-highcharts-chart="1">
                            <div class="highcharts-container" id="highcharts-3" style="position: relative; overflow: hidden; width: 530px; height: 290px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="530" height="290"><desc>Created with Highcharts 3.0.4</desc><defs><clippath id="highcharts-4"><rect fill="none" x="0" y="0" width="510" height="228"></rect></clippath><lineargradient x1="0" y1="0" x2="0" y2="1" id="highcharts-9"><stop offset="0" stop-color="#FFF" stop-opacity="1"></stop><stop offset="1" stop-color="#ACF" stop-opacity="1"></stop></lineargradient><lineargradient x1="0" y1="0" x2="0" y2="1" id="highcharts-10"><stop offset="0" stop-color="#9BD" stop-opacity="1"></stop><stop offset="1" stop-color="#CDF" stop-opacity="1"></stop></lineargradient></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="530" height="290"></rect><g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(496,10)"><title>Chart context menu</title><rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1"></text></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)" style="cursor:pointer;"><path fill="#d95455" d="M 254.9788179868157 10.000002157104262 A 104 104 0 1 1 255.08281797791628 217.9999670249108 L 255 114 A 0 0 0 1 0 255 114 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#979080" d="M 254.97881798681573 217.99999784289574 A 104 104 0 0 1 254.85554608644054 10.00010032184234 L 255 114 A 0 0 0 0 0 255 114 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path><path fill="#4c88a2" d="M 254.95954604101237 10.000007867898361 A 104 104 0 0 1 254.85554608644054 10.00010032184234 L 255 114 A 0 0 0 0 0 255 114 Z" stroke="#FFFFFF" stroke-width="1" stroke-linejoin="round" transform="translate(0,0)"></path></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(10,10) scale(1 1)"></g></g><g class="highcharts-legend" zIndex="7" transform="translate(182,248)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="166" height="26" stroke="#909090" stroke-width="1" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">正面</tspan></text><rect rx="2" ry="2" fill="#d95455" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(61,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">中立</tspan></text><rect rx="2" ry="2" fill="#979080" x="0" y="4" width="16" height="12" zIndex="3"></rect></g><g class="highcharts-legend-item" zIndex="1" transform="translate(114,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">负面</tspan></text><rect rx="2" ry="2" fill="#4c88a2" x="0" y="4" width="16" height="12" zIndex="3"></rect></g></g></g></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(34,15)" opacity="0"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="81" height="48" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="81" height="48" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="81" height="48" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></rect><rect rx="0" ry="0" fill="rgb(255,255,255)" x="0.5" y="0.5" width="81" height="48" fill-opacity="0.85" stroke="#979080" stroke-width="1" anchorX="93.17691436938946" anchorY="20.0047607421875"></rect><text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1"><tspan style="font-size: 10px" x="8">中立</tspan><tspan style="fill:" x="8" dy="16">舆情数量</tspan><tspan dx="0">: </tspan><tspan style="font-weight:bold" dx="0">3</tspan></text></g></svg><div class="highcharts-contextmenu" style="position: absolute; z-index: 1000; padding: 24px; display: none; right: -14px; top: 8px;"><div style="-webkit-box-shadow: rgb(136, 136, 136) 3px 3px 10px; box-shadow: rgb(136, 136, 136) 3px 3px 10px; border: 1px solid rgb(160, 160, 160); background-color: rgb(255, 255, 255); padding: 5px 0px; background-position: initial initial; background-repeat: initial initial;"><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">打印图片</div><hr><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为png</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为jpeg</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为pdf</div><div style="cursor: pointer; padding: 0px 10px; background-image: none; color: rgb(48, 48, 48); font-size: 11px; background-position: initial initial; background-repeat: initial initial;">保存为svg</div></div></div></div></div>
                        <div id="yw1"></div>        </div>
                </div>
            </div>
        </div>











        <div class="row-fluid">
            <div class="span6">
                <div class="widget">
                    <div class="widget-hd"><h4>话题分布</h4></div>
                    <div class="widget-bd h290">
                        <div id="topic" class="h290" data-highcharts-chart="2">
                            <div class="highcharts-container" id="highcharts-5" style="position: relative; overflow: hidden; width: 530px; height: 290px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="530" height="290"><desc>Created with Highcharts 3.0.4</desc><defs><clippath id="highcharts-6"><rect fill="none" x="0" y="0" width="494" height="211"></rect></clippath></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="530" height="290"></rect><g class="highcharts-button" style="cursor:default;" title="Chart context menu" stroke-linecap="round" transform="translate(496,10)"><title>Chart context menu</title><rect rx="2" ry="2" fill="white" x="0.5" y="0.5" width="24" height="22" stroke="none" stroke-width="1"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" y="13" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:black;fill:black;" zIndex="1"></text></g><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path fill="none" d="M 26 169.5 L 520 169.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 26 116.5 L 520 116.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 26 63.5 L 520 63.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 26 10.5 L 520 10.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 26 221.5 L 520 221.5" stroke="#C0C0C0" stroke-width="1" zIndex="1" opacity="1"></path></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 519.5 222 L 519.5 227" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 272.5 222 L 272.5 227" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 26.5 222 L 26.5 227" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 26 221.5 L 520 221.5" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-axis" zIndex="2"></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(26,10) scale(1 1)" style="cursor:pointer;" clip-path="url(#highcharts-6)"><rect fill="#d95455" x="64.5" y="211.5" width="118" height="0" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect><rect fill="#d95455" x="311.5" y="52.5" width="118" height="159" stroke="#FFFFFF" stroke-width="1" rx="0" ry="0"></rect></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(26,10) scale(1 1)"></g></g><g class="highcharts-legend" zIndex="7" transform="translate(223,248)"><rect rx="0" ry="0" fill="none" x="0.5" y="0.5" width="84" height="26" stroke="#909090" stroke-width="1" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;cursor:pointer;color:#274b6d;fill:#274b6d;" text-anchor="start" zIndex="2"><tspan x="21">舆情数量</tspan></text><rect rx="2" ry="2" fill="#d95455" x="0" y="4" width="16" height="12" zIndex="3"></rect></g></g></g></g><g class="highcharts-axis-labels" zIndex="7"><text x="149.5" y="236" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:227px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="149.5">仕馨月子</tspan></text><text x="396.5" y="236" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:227px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="middle" opacity="1"><tspan x="396.5">广州月子中心</tspan></text></g><g class="highcharts-axis-labels" zIndex="7"><text x="18" y="226" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="18">0</tspan></text><text x="18" y="173" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="18">2</tspan></text><text x="18" y="120" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="18">4</tspan></text><text x="18" y="67" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="18">6</tspan></text><text x="18" y="14" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:155px;color:#666;cursor:default;line-height:14px;fill:#666;" text-anchor="end" opacity="1"><tspan x="18">8</tspan></text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" visibility="hidden" transform="translate(0,0)"><rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></rect><rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></rect><rect rx="3" ry="3" fill="none" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></rect><rect rx="3" ry="3" fill="rgb(255,255,255)" x="0.5" y="0.5" width="16" height="16" fill-opacity="0.85"></rect><text x="8" y="21" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:12px;color:#333333;fill:#333333;" zIndex="1"></text></g></svg></div></div>
                        <div id="yw2"></div>        </div>
                </div>
            </div>


            <div class="span6 mglf20">
                <div class="widget">
                    <div class="widget-hd"><h4>负面舆情预警</h4></div>
                    <div class="widget-bd h290">
                        <ul class="prefix-stopwatch">

                            暂无数据          </ul>
                    </div>
                </div>
            </div>
        </div>







        <div class="row-fluid">
            <div class="span6">
                <div class="widget">
                    <div class="widget-hd"><h4>重要舆情</h4></div>
                    <div class="widget-bd h290">
                        <ul class="prefix-stopwatch">
                            <li><a target="_blank" href="http://weibo.com/2711987451/Bewpd5a5m">[微博]@广州月嫂公司_吉宝乐 您好！来自【微问...</a><span>新浪微博</span><span><a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=sina_3734611631230412">转载(1)</a></span></li>
                            <li><a target="_blank" href="http://www.jiaodong.net/news/system/more/2010300/0035/2010300_00003560.shtml">[新闻]社会新闻</a><span>胶东在线</span><span><a target="_blank" href="http://yq.adt100.com/moniter/dedup?dedup_id=30836303">转载(1)</a></span></li>
                    </div>
                </div>
            </div>


            <div class="span6 mglf20">
                <div class="widget">
                    <div class="widget-hd"><h4>热词发现</h4></div>
                    <div class="widget-bd h290">

                        <div id="yw3" class="YiiTagCloud">
                            &nbsp;
                            <a style="font-size: 12pt;color: #517F7E" target="_blank" class=" YiiTagCloudWord" href="http://yq.adt100.com/moniter/index?include=%E5%AF%8C%E4%BA%BA&adv=1">富人</a>
                            &nbsp;  &nbsp;
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
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
</div>
</div>

<div id="pop" style="display:none;">
    <div id="popHead"><h2>&nbsp;</h2><a id="popClose" title="关闭">关闭</a> </div>
    <div id="popContent">
        <dl>
            <dt id="popIntro"></dt>
            <dd id="popMore"><a href="http://yq.adt100.com/moniter/imp?merge=1"></a></dd>
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

<script type="text/javascript" src="./js/sentimentSummarize/pop.js"></script>
<script type="text/javascript" src="./js/sentimentSummarize/jquery.simInput.js"></script>
<script type="text/javascript" src="./js/sentimentSummarize/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="./js/sentimentSummarize/common.js"></script>
<script type="text/javascript" src="./js/sentimentSummarize/my.js"></script>

<script type="text/javascript">

    $(function(){
        $('[data-toggle="checkbox"]').simInput();
        $('[data-toggle="radio"]').simInput();
    });

</script>

<!--[if lt IE 8]>

<script type="text/javascript" src="/apps/yuqing/theme/red/js/ie-fix.js" ></script>

<![endif]-->

<script type="text/javascript">
    /*<![CDATA[*/
    jQuery(window).on('load',function() {
        setTimeout(function(){var chart = new Highcharts.Chart({'chart':{'renderTo':'container','type':'gauge','plotBackgroundColor':null,'plotBackgroundImage':null,'plotBorderWidth':0,'plotShadow':false},'exporting':{'enabled':true},'legend':{'borderRadius':0},'tooltip':{'borderRadius':0},'title':{'text':''},'pane':{'startAngle':-90,'endAngle':90,'size':[30],'center':['50%','100%']},'yAxis':{'min':-5,'max':5,'minorTickInterval':'auto','minorTickWidth':1,'minorTickLength':10,'minorTickPosition':'inside','minorTickColor':'#666','tickPixelInterval':30,'tickWidth':2,'tickPosition':'inside','tickLength':10,'tickColor':'#666','labels':{'step':2,'rotation':'auto'},'title':{'text':''},'plotBands':[{'from':-5,'to':-2.5,'color':'#4c88a2'},{'from':-2.5,'to':2.5,'color':'#DDDF0D'},{'from':2.5,'to':5,'color':'#d95455'}]},'series':[{'name':'情感指数','data':[2.5],'tooltip':{'valueSuffix':''}}]});}, 100);
        setTimeout(function(){var chart = new Highcharts.Chart({'chart':{'renderTo':'pie'},'exporting':{'enabled':true},'legend':{'borderRadius':0},'tooltip':{'borderRadius':0},'colors':['#d95455','#979080','#4c88a2','#83823f','#f0c189','#40131a'],'title':{'text':''},'plotOptions':{'pie':{'allowPointSelect':true,'cursor':'pointer','dataLabels':{'enabled':false},'showInLegend':true},'series':{'cursor':'pointer','events':{'click':function(e) {
            location.href = e.point.url;
        }}}},'series':[{'type':'pie','name':'舆情数量','data':[{'name':'正面','y':3,'url':'/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=positive'},{'name':'中立','y':3,'url':'/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=neutral'},{'name':'负面','y':0,'url':'/moniter/index?start_date=2014-07-21&end_date=2014-07-21&sentiment=negative'}]}]});}, 100);
        setTimeout(function(){var chart = new Highcharts.Chart({'chart':{'renderTo':'topic'},'exporting':{'enabled':true},'legend':{'borderRadius':0},'colors':['#d95455','#979080','#4c88a2','#83823f','#f0c189','#40131a'],'title':{'text':''},'yAxis':{'title':{'text':''}},'xAxis':{'categories':['仕馨月子','广州月子中心']},'plotOptions':{'series':{'cursor':'pointer','events':{'click':function(e) {
            location.href = e.point.url;
        }}}},'series':[{'name':'舆情数量','type':'column','data':[{'y':0,'url':'/moniter/index?start_date=2014-07-21&end_date=2014-07-21&topic_id=996'},{'y':6,'url':'/moniter/index?start_date=2014-07-21&end_date=2014-07-21&topic_id=999'}]}]});}, 100);
    });
    /*]]>*/
</script>

</body>
</html>