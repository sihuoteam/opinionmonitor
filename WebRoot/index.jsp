<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta http-equiv="content-type" content="text/html;charset=utf-8">
      <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
      <script type="text/javascript" src="js/jscharts.js"></script>
    <title></title>
  </head>
  <body>
    <div id="chartcontainer">正在产生图标</div>
    <script type="text/javascript">
        var myDate= new Array([10,20],[15,10],[20,30],[25,10],[30,5]);
        var myChart = new JSChart('chartcontainer',"line");
        myChart.setDateArray(myDate);
        myChart.draw();
    </script>
    hahaf
  </body>
  <script type="text/javascript">
      var myDate= new Array([10,20],[15,10],[20,30],[25,10],[30,5]);
      var myChart = new JSChart('chartcontainer',"line");
      myChart.setDateArray(myDate);
      
      myChart.draw();
  </script>
</html>
