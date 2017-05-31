<%@ page language="java" import="java.util.*,cn.edu.zhku.jsj.Model.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CustomerManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/Operator/public.css">
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script> 
	<script type="text/javascript">
		function back()
		{
			window.history.go(-1);
		}
  	</script>
  	<style type="text/css">
  		
		.singleUser
		{
			width:1000px;
			margin:20px auto;
			color:#333333;

		}
		.singleUser form
		{
			padding-left:200px;
			margin-bottom:50px;
		}
		.singleUser span
		{
			display:inline-block;
			width:100px;
			text-align:right;
			padding-right:20px;
		}
		.singleUser input
		{
			width:200px;
			height:40px;
			display:inline-block;
			margin-top:10px;
			padding-left:10px;
		}
		.singleUser .radio
		{
			width:10px;
			height:10px;
		}
		.singleUser .btn
		{
			width:100px;
			background-color:#FF6705;
			border:none;
			cursor:pointer;
			color:#fff;
			font-size:20px;
		}
		.singleUser .areaTip
		{
			display:inline-block;
			margin:10px 0;

		}
		.singleUser .address
		{
			width:500px;
			border:1px solid #BFBFBF;
			margin-bottom:10px;
			padding:10px;
		}
		.singleUser .address span
		{
			text-align:left;
		}
		.singleUser .address div
		{
			border-top:1px solid #E6E6E6;
			margin-top:6px;
			padding-top:6px;
		}
		.singleUser .address .concretAddress 
		{
			display:inline-block;
			width:250px;
			
		}
  	</style>
  </head>
  
  <body>
  	 <jsp:include page="/Shopping/public/Header.jsp">
  		<jsp:param value="1" name="index"/>
  	 </jsp:include>
  	 <jsp:include page="/Operator/public/publicHeader.jsp">
  		<jsp:param value="2" name="index"/>
  	 </jsp:include>
 	<div class="singleUser">
 		<form>
 			<input type="button" class="btn" value="返回" onclick="back()"><br>
 			<input type="hidden" name="id" value="${requestScope.user.id}">
	 		<span>用户名</span><input type="text" name="username" value="${requestScope.user.username }"><br>
	 		<span>昵称</span><input type="text" name="nickname" value="${requestScope.user.nickname }"><br>
	 		<span>电话</span><input type="text" name="tel" value="${requestScope.user.tel }"><br>
	 		<span>性别</span>
	 		<c:choose>
	 			<c:when test="${fn:contains(requestScope.user.sex,'男')}">
	 				<input type="radio" name="sex" checked="checked" class="radio">男
	 				<input type="radio" name="sex" class="radio">女<br>
	 			</c:when>
	 			<c:otherwise>
	 				<input type="radio" name="sex" class="radio">男
	 				<input type="radio" name="sex" checked="checked" class="radio">女<br>
	 			</c:otherwise>
	 		</c:choose>
	 		<span>生日</span><input type="text" name="birth" value="${requestScope.user.birth }"><br>
	 		<span>注册日期</span><input type="text" name="register" value="${requestScope.user.register }" readonly><br>
	 		<span class="areaTip">用户地址信息</span>
	 		<c:forEach items="${requestScope.user.addresses }" var="a">
	 			<div class="address">
	 				<span>收货人</span><span>${a.receiver}</span>
	 				<span>收货人电话</span><span>${a.receivertel}</span>
	 				<div><span>收货地址</span><span  class="concretAddress">${a.location}</span></div>
	 			</div>
	 		</c:forEach>
 		</form>
 		
 	</div>
  </body>
</html>
