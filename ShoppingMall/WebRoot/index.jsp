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
    <title>My JSP 'CustomerManager.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

	<link rel="stylesheet" type="text/css" href="css/Operator/public.css">

	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<style type="text/css">
		body
		{
			background:url("images/login.jpg") no-repeat;
		}
		div
		{
			float:right;
			width:350px;
			height:300px;
			margin-top:140px;
			margin-right:30px;
		}
		input
		{
			width:300px;
			height:42px;
			font-size:20px;
			display:inline-block;
			margin-top:26px;
			margin-left:25px;
			padding-left:10px;
			background:#070510;
			color: #fff;  
			text-shadow:1px 1px 1px;   
			border-top: 1px solid #312E3D;   
    		border-left: 1px solid #312E3D;   
    		border-right: 1px solid #312E3D;   
    		border-bottom: 1px solid #56536A;  
    		border-radius: 4px; 
    		outline: none; 
		}
		.btn
		{
			background:#5AB3B9;
			color:#D3D3D3;
			
			cursor:pointer;
		}
		.btn:hover
		{
			background:#8BDDEB;
			color:#fff;
		}
		span
		{
			padding-left:25px;
		}
	</style>
  </head>
  
  <body>
    <div>
    	<span class="placeholder"></span><br>
    	<input type="text" name="username" placeholder="用户名"><br>
    	<span class="placeholder"></span><br>
    	<input type="password" name="password" placeholder="密码" ><br>
    	<span class="placeholder"></span><br>
    	<input class="btn" type="button" value="登录" onclick="login()"><br>
    </div>
  </body>
</html>
