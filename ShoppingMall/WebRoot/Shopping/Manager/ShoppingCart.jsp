<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShoppingCart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/Operator/ShoppingCart.css">

	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/Operator/ShoppingCart.js"></script>
	
  </head>
  
  <body>
    <jsp:include page="${path }/Shopping/public/Header.jsp">
    	<jsp:param value="1" name="index"/>
    </jsp:include>
    <div class="goodTable">
   		<ul class="tableHeader">
   			<li><input type="checkbox"  class="checkAll" checked="">全选</li>
   			<li class="info">商品信息</li>
   			<li>单价</li>
   			<li>数量</li>
   			<li>金额</li>
   			<li>操作</li>
   		</ul>
   		<c:forEach items="${sessionScope.items }" var="item" varStatus="s">
   		<ul class="tableUserContent">
   			<li class="info"><input type="checkbox">店铺：${item.good.user.nickname }</li>
   		</ul>
   		<ul class="tableGoodContent">
   			<input type="hidden" name="index" value="${s.index }">
   			<li><input type="checkbox" class="choose"></li>
   			<li class="info"><a href="#">${item.good.introduction }</a></li>
   			<li>${item.good.gprice }</li>
   			<li>${item.num }</li>
   			<li class="price"><fmt:formatNumber type="number" value="${item.num*item.good.gprice }" pattern="0.00" maxFractionDigits="2"/></li>
   			<li><a class="del">删除</a></li>
   		</ul>
   		</c:forEach>
   		<ul class="census">
    		<li><input type="checkbox" class="checkAll" checked="">全选</li>
   			<li class="info"></li>
   			<li>已选商品<span class="num">0</span>件</li>
   			<li>合计:<span class="totalprice">0.00</span></li>
   			<li><input type="button" value="确认订单" class="btn"></li>
    	</ul>
		
    </div>
  </body>
</html>
