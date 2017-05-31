<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>卖家中心</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

	<link rel="stylesheet" type="text/css" href="css/public.css">

	<style type="text/css">
		body{
			width:1000px;
			margin:0 auto;
		}
		h1{
			text-align:center;
			font-size:40px;
			color:#FF9966;
			margin:30px 0 20px 0;
			
		}
		h1:hover{
			color:#003399;
		}
		h2{
			text-align:center;
			color:#999999;
			margin-bottom:20px;
		}
		.header ul{
			float:left;
			width:1000px;
			background-color:#f96;
		}
		.header ul li{
			float:left;
			padding-left:52px;
			
		}
		.header ul li a{
			display:inline-block;
			text-decoration:none;
			color:#fff;
			padding:15px 10px;
		}
		.header ul li a:hover{
			background-color:#FF6633;
			
		}
		
		
	</style>
  </head>
  
  <body>
  	
  	<div class="header">
  		<h1>卖家中心</h1>
  		<c:choose>
  		<c:when test="${sessionScope.user.type!=1 }">
	  		<script>
                window.alert("你没有权限访问！");
            </script>
         </c:when>
	  	<c:if test="${sessionScope.user.type==1}">
  		<h2>${sessionScope.user.username },欢迎进入</h2>
  		
  		<ul>
  		    <li><a href="Shopping/Manager/HomePage.jsp?user_name=${sessionScope.user.username }" id="store">首页</a></li>
	    	<li><a href="Store/Manager/Store.jsp?user_name=${sessionScope.user.username }" id="store">店铺管理</a></li>
	    	<li><a href="Store/Manager/GoodManager.jsp?user_id=${sessionScope.user.id}" id="good">商品管理</a></li>
	    	<li><a href="Store/Manager/Order.jsp?user_id=${sessionScope.user.id}" id="order">订单管理</a></li>
	    	<li><a href="Store/Manager/DataStatistics.jsp?user_id=${sessionScope.user.id}" id="statistic">数据统计</a></li>
    	</ul>
    	 </c:if>
     </c:choose>
  	</div>
  </body>
</html>
