<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'StoreManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/Operator/public.css">
	<link rel="stylesheet" type="text/css" href="css/Operator/store.css">
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script> 
	<script type="text/javascript" src="js/Operator/store.js"></script>
  </head>
  
  <body>
  <jsp:include page="/Shopping/public/Header.jsp">
  	<jsp:param value="1" name="index"/>
  </jsp:include>
  <jsp:include page="/Operator/public/publicHeader.jsp">
	 <jsp:param value="2" name="index"/>
  </jsp:include>
  <div class="storeManager">
	  	<form>
	  		<span>排序方式：</span>
	  		<select name="order" onchange='emptyRecord()'>
	  			<option value="id" selected="selected">编号</option>
	  			<option value="username">姓名</option>
	  		</select>
	  		<select name="choose" onchange='empty()'>
	  			<option value="asc" selected="selected">升序</option>
	  			<option value="desc">降序</option>
	  		</select>
	  		<input type="button" class="btn" value="显示所有商家" onclick="emptyRecord(),listInfo()">
	  		<div class="searchUser">
	  			<input type="text" name="username" onfocus="emptyRecord()"/>
	  			<input type="button" class="btn" value="搜索商家" onclick="listInfo()"/>
	  		</div>
	  		
	  		
	  		<input type="hidden" name="currentPage" value="1">
	  		<input type="hidden" name="eachRecord" value="10">
	
	  		
	  	</form>
  		<table id="UserTable" border="0" cellspacing="0"  cellpadding="0"></table>

		<ul class="Pager"></ul>
	   	<ul>
	   		<li>
	   			<input type="hidden" name="totalRecord" value="0">
	   			共<span class="totalPage">0</span>页
	   			<span>到第<input name="randomPage" type="text" size="2">页</span>
	   		</li>
	   		<li><input type="button" class="btn" value="确定" onclick="change(),listInfo()"></li>
	   	</ul>


  </div>	
  </body>
</html>
