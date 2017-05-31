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
    
    <title>My JSP 'HomePage.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>


div.img
{
  margin: 2px;
  border: 1px solid #000000;
  height: auto;
  width: auto;
  float: left;
  text-align: center;
}    
div.img img
{
  display: inline;
  margin: 3px;
  border: 1px solid #ffffff;
}
div.img a:hover img {border: 1px solid #0000ff;}
div.desc
{
  text-align: center;
  font-weight: normal;
  width: 120px;
  margin: 2px;
}

div.Pager
{
  align:center;
  font-weight: normal;
  width: 120px;
  margin: 2px;
}
#container { 
min-height:100%; 
height: auto !important; 
height: 100%; /*IE6不识别min-height*/ 
position: relative; 
}  
#footer { 
position: absolute; 
bottom: 0; 
width: 100%; 
height: 60px;/*脚部的高度*/ 
background: #6cf; 
clear:both; 
} 

</style>


  </head>
  <body>
    <jsp:include page="${path }/Shopping/public/Header.jsp">
    	<jsp:param value="1" name="index"/>
    </jsp:include>
    <c:forEach items="${requestScope.list }" var="good">
    <div class="img">
     <img align="top"height="200px" width="200px"src="<c:url value='/goodpic/${good.gpic}.jpg'/>"/><br>
     <c:out value="${good.gprice }"></c:out><br>
     <div class="desc"><c:out value="${good.introduction}"></c:out><br></div>
    </div> 
   </c:forEach>
   
  <div id="container">
  <div id="footer">
   <form action="OperatorShopCtrl"  align="center">
   <input type="hidden" name="totalPage">共${requestScope.pager.totalPage}页  
   <input type="hidden" name ="currentPage">第${requestScope.pager.currentPage}页  
   <input type="hidden" name="method" value="searchInfo">
   <input type="hidden" name="keyword" value="${good.introduction}">
   <input type="text" name="currentpage" size="2">页
   <input class="btn" type="submit" value="go">
  </form>
  </div>
   </div>
  </body>
</html>
