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
    
    <title>商品管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<!-- 	<link rel="stylesheet" type="text/css" href="styles.css"> -->

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

<script type="text/javascript">
   $(function() {
      if($("input[name='gstate']").val()=="0")
        $("input[name='gstate']").val("上架")
	  else if($("input[name='gstate']").val()=="1")
	    $("input[name='gstate']").val("被举报")
	  else if($("input[name='gstate']").val()=="1")
	    $("input[name='gstate']").val("下架")
	 $(".gstate").show();
	})
	
	
</script>


  </head>
  <body>
    <jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>
	
	<form action="GoodCtrl" method="get">
	    <input type="hidden" name="method" value="searchInfo">
	  	<input type="hidden" name="currentpage" value="1">
	  	<input type="hidden" name="userid" value="${sessionScope.user.id}">
	  	<input class="btn" type="submit" value="一键查看所有商品">
	</form>>
	<a href="./Store/Manager/GoodAdd.jsp?user_id=${sessionScope.user.id}">
		<input class="btn" type="button" value="添加商品">
	</a>
		

  <c:forEach items="${requestScope.list}" var="good">	
   <table id="customers" class="smassage" >
	<tr>
			<th>商品</th>
			<th>查看</th>
			<th>修改</th>
			<th>状态</th>
			<th>删除</th>
	</tr>	
	<tr>	
    <td>
        <img align="top"height="200px" width="200px"src="<c:url value='/goodpic/${good.gpic}' />"/><br>
        <c:out value="${good.gname}"></c:out>
    </td>  
    <td>
       <a href="./Store/Manager/Goodshow.jsp?id=${good.id}">查看</a>
    </td>
    <td>
       <a href="./Store/Manager/Goodshow.jsp?id=${good.id}">修改</a>
    </td>
    <div class="gstate">
     <td>
       <input name="gstate" id="gstate" type="hidden" value="${good.gstate}"></a>
    </td>
    <div>
    <td>
        <a href="./Store/Manager/GoodDelete.jsp?goodid=${good.id}">删除</a>
	  	</from>
    </td>
	</table>	             
   </c:forEach>

 <div id="container">
  <div id="footer">
   <form action="GoodCtrl"  align="center" method="get">
   <input type="hidden" name="method" value="searchInfo">
   <input type="hidden" name="totalPage">共${requestScope.pager.totalPage}页  
   <input type="hidden" name ="gopage">第${requestScope.pager.currentPage}页  
   <input type="hidden" name="keyword" value="${good.introduction}">
   <input type="hidden" name="userid" value="${sessionScope.user.id}">
   <input type="text" name="currentpage" size="2">页
   <input class="btn" type="submit" value="go">
  </form>
  </div>
   </div>
  </body>
</html>
