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
    <title>数据统计</title>
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
	width:60%;
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
		
	<form action="GoodCtrl" method="get">
	  	<input type="hidden" name="method" value="query">
	  	<input type="hidden" name="userid" value="${sessionScope.user.id}">
	  	<input type="hidden" name="totalRecord" value="0">
        <input class="btn" type="submit" value="数据统计" >
	 </form>

		
    <c:forEach items="${requestScope.good}" var="good">	
   <table id="customers" >
	<tr>
			<th>商品</th>
			<th>进货/个</th>
			<th>库存/个</th>
			<th>售出/个</th>
			<th>单价/元</th>
			<th>利润/元</th>
	</tr>	
	<tr>	
    <td>
        <img align="top"height="200px" width="200px"src="<c:url value='/goodpic/${good.gpic}'/>"/><br>
        <c:out value="${good.gname}"></c:out>
    </td>  
    <td>
       <c:out value="${good.gtotalNum}"></c:out> 
    </td>
    <td>   
       <c:out value="${good.gremainNum}"></c:out>
    </td>
    <td>
      <c:out value="${good.gtotalNum-good.gremainNum}"></c:out>
    </td>
    <td>   
       <c:out value="${good.gprice}"></c:out>
    </td>
    <td>  
       <c:out value="${(good.gtotalNum-good.gremainNum)*good.gprice}"></c:out>
	</tr>
	</table>	             
</c:forEach>








  </body>
</html>
