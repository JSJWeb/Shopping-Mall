<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
 %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>My JSP 'CustomerManager.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/Operator/public.css">
	<style type="text/css">
		.header .header-top
		{
			
			height:38px;
			background-color:#F5F5F5;
			 color:#6C6C6C;
		}
		.header .header-top .left-ul
		{
			float:left;
			margin-left:10%;
		}
		.header .header-top .left-ul li
		{
			float:left;

		}
		.header-top .left-ul li a
		{
			line-height:38px;
			display:inline-block;
			padding:0 14px;
			text-decoration:none;
			cursor:pointer;
		}
		.header-top .left-ul li a:hover
		{
			color:#FE5E2D;
		}
		.header .header-top .right-ul
		{
			float:right;
			margin-right:10%;
		}
		.header .header-top .right-ul li
		{
			float:left;
			
		}
		.header-top .right-ul li a
		{
			line-height:38px;
			display:inline-block;
			padding:0 14px;
			text-decoration:none;
			cursor:pointer;
		}
		.header-top .right-ul li a:hover
		{
			color:#FE5E2D;
		}
		
		.header .header-search
		{
			clear:both;
			text-align:center;
			margin-top:32px;
		}
		.header .header-search input
		{
			width:504px;
			height:42px;
			border:2px solid #FF6705;
			padding-left:14px;
			padding-top:-1px;
		}
		.header .header-search .btn
		{
			width:124px;
			background-color:#FF6705;
			color:#fff;
			font-size:18px;
			cursor:pointer;
			
		}
	</style>

		
	</script>
  </head>
  
  <body>
  	
  	<div class="header">
  		<div class="header-top">
  			<ul class="left-ul">
  				
	  			<c:choose>
	  				<c:when test="${sessionScope.user!=null }">
	  					<li><a>${sessionScope.user.username },欢迎进入</a></li>
	  					<li><a href="LogoutCtrl?method=logout">退出登录</a></li>
	  				</c:when>
	  				<c:otherwise>
	  					<li><a href="#">注册</a></li>
	  					<li><a href="${path }index.jsp">请登录</a></li>
	  				</c:otherwise>
	  			</c:choose>
	  		</ul>
	  		<ul class="right-ul">
	  			<li><a href="${path }Shopping/Manager/HomePage.jsp">首页</a></li>
	  			<li><a href="#">我的淘宝</a></li>
	  			<li><a href="${path }Shopping/Manager/ShoppingCart.jsp">购物车</a></li>
	  			<li><a href="${path }Store/public/StoreHeader.jsp">卖家中心</a></li>
	  			<li><a href="${path }Operator/Manager/HomePage.jsp">运营商平台</a></li>
	  		</ul>
  		</div>
  		<div class="header-search">
	  		<form action="OperatorShopCtrl" method="get">
	  			<input type="hidden" name="method" value="searchInfo">
	  			<input type="hidden" name="currentpage" value="1">
	  			<input type="text" name="keyword"><input class="btn" type="submit" value="搜索">
	  		</form>
  		</div>
  	</div>
  </body>
</html>
