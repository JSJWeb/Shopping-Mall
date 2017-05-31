<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'HomePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/Operator/public.css">
	<link rel="stylesheet" type="text/css" href="css/Operator/homepage.css">
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/Operator/HomePage.js"></script>
	
  </head>
  
  <body>
  <jsp:include page="/Shopping/public/Header.jsp">
  	<jsp:param value="1" name="index"/>
  </jsp:include>
  <jsp:include page="/Operator/public/publicHeader.jsp">
    	<jsp:param value="2" name="index"/>
    </jsp:include>
  <div class="bg">
  	
    <div class="left-content">
    	<ul>
    		<li><a onclick="addUser()">新增用户</a></li>
    		<li><a onclick="updatePsw()">修改密码</a></li>
    		<li><a onclick="updateSingle()">个人资料</a></li>
    	</ul>
    </div>
    <div class="right-content">
    	<div class="add">
    		<label>用户名：</label><input type="text" name="username"><span></span><br>
    		<label>密码：</label><input type="password" name="password"><span></span><br>
    		<label>确认密码：</label><input type="password" name="secondPsw"><span></span><br>
    		<label>昵称：</label><input type="text" name="nickname"><span></span><br>
    		<label>联系电话：</label><input type="text" name="tel"><span></span><br>
    		<label>性别：</label>
    			<input type="radio" name="sex" value="男" class="sex"> 男
    			<input type="radio" name="sex" value="女" class="sex"> 女<br>
    		<label>出生年月：</label>
	    		<input type="text" size="2" name="year" class="date first"> -
	    		<input type="text" size="2" name="month" class="date"> -
	    		<input type="text" size="2" name="day" class="date"><span></span><br>
    		<input type="hidden" name="type" value="2">
    		<input class="btn" type="button" value="注册" onclick="register()">
    		
    		
    	</div>
    	<div class="updatePsw">
    		<!-- session中的user比较 -->

    		<label>原密码：</label><input type="password" name="oldPsw"><span></span><br>
    		<label>原密码确认：</label><input type="password" name="secondOldPsw"><span></span><br>
    		<label>新密码：</label><input type="password" name="newPsw"><span></span><br>
    		<label>新密码确认：</label><input type="password" name="secondNewPsw"><span></span><br>
    		<input class="btn" type="button" value="确认修改" onclick="update()">
    	</div>
    	<div class="singleUser">
    		<label>用户名：</label><input type="text" name="username" value="${sessionScope.user.username }"><span></span><br>
    		<label>昵称：</label><input type="text" name="nickname" value="${sessionScope.user.nickname }"><span></span><br>
    		<label>联系电话：</label><input type="text" name="tel" value="${sessionScope.user.tel}"><span></span><br>
    		<label>性别：</label>
    		<c:choose>
    			<c:when test="${fn:contains(sessionScope.user.sex,'男')}">
	 				<input type="radio" name="sex" value="男" class="sex" checked="checked"> 男
    				<input type="radio" name="sex" value="女" class="sex"> 女<br>
	 			</c:when>
	 			<c:otherwise>
	 				<input type="radio" name="sex" value="男" class="sex" > 男
    				<input type="radio" name="sex" value="女" class="sex" checked="checked"> 女<br>
	 			</c:otherwise>
    		</c:choose>
	 			
    		<label>出生年月：</label><input type="text" name="birth"value="${sessionScope.user.birth }"><span></span><br>
    		<input type="button" class="btn" value="提交修改"  onclick="updateUser()">
    	</div>
    	<div class="result">
    		<span>注册成功</span><br>
    		<input class="btn" type="button">
    	</div>
    	
    </div>
  </div>
    
  </body>
</html>
