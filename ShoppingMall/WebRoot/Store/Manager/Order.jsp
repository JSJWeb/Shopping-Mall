<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

<style>
#customers
{
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	width:100%;
	border-collapse:collapse;
}
#customers td, #customers th 
{
	font-size:1em;
	border:1px solid #98bf21;
	padding:3px 7px 2px 7px;
}
#customers th 
{
	font-size:1.1em;
	text-align:left;
	padding-top:5px;
	padding-bottom:4px;
	background-color:#A7C942;
	color:#ffffff;
}
#customers tr.alt td 
{
	color:#000000;
	background-color:#EAF2D3;
}
</style>
  </head>
  <body>
    <jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>
		
	<form action="StoreOrderCtrl" method="get">
	  	<input type="hidden" name="method" value="show">
	  	<input type="hidden" name="userid" value="${param.user_id}">
        <input class="btn" type="submit" value="查询订单">
	 </form>
		

  <c:forEach items="${requestScope.otderitem}" var="otderitem">	
   <table id="customers" class="smassage" onchange="saled()">
	<tr>
			<th>订单号</th>
			<th>订单详情</th>
	</tr>	
	<tr>	
    <td>
        <c:out value="${otderitem.good.gname}"></c:out>
    </td>  
    <td>
       <lable><a href="./Store/Manager/OrderManager.jsp?goodid=${otderitem.good.id}">订单详情</lable></a>
    </td>
	</table>	             
   </c:forEach>
  </body>
</html>
