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

	<style type="text/css">
		.operator-header{
			
			width:1000px;
			margin:0 auto;
			margin-top:50px;
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
		.operator-header ul{
			float:left;
			width:1000px;
			background-color:#f96;
		}
		.operator-header ul li{
			float:left;
			padding-left:52px;
			
		}
		.operator-header ul li a{
			display:inline-block;
			text-decoration:none;
			color:#fff;
			padding:15px 10px;
		}
		.operator-header ul li a:hover{
			background-color:#FF6633;
			
		}
		
		
	</style>
  </head>
  
  <body>
  	
  	<div class="operator-header">
  		<ul>
  			<li><a>>运营商平台</a></li>
  			<li><a href="Operator/Manager/HomePage.jsp" id="homepage">个人中心</a></li>
	    	<li><a href="Operator/Manager/CustomerManager.jsp" id="customer">顾客管理</a></li>
	    	<li><a href="Operator/Manager/StoreManager.jsp" id="store">店铺管理</a></li>
	    	<li><a href="Operator/OperatorReportCtrl?method=handleReport&currentPage=1" id="report">举报处理</a></li>
    	</ul>
  	</div>
    
  </body>
</html>
