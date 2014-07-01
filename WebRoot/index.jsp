
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 5/29/14
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        myChart.set
        myChart.draw();
    </script>
  </body>
  <script type="text/javascript">
      var myDate= new Array([10,20],[15,10],[20,30],[25,10],[30,5]);
      var myChart = new JSChart('chartcontainer',"line");
      myChart.setDateArray(myDate);
      myChart
      myChart.draw();
  </script>
</html>
