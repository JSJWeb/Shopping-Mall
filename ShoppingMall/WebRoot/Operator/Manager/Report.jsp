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
    
    <title>My JSP 'Report.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/Operator/report.css">
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
	$(function(){
		  var table=$(".reportPager .reportTable");
		  table.css({"textAlign":"center","color":"#FFBBFF","margin":"10px auto"});
		  $(".reportPager .reportTable caption").css({"color":"#616D79","padding":"10px","fontSize":"20px"});
          $("tr:odd").css("backgroundColor","#FF6633");
          $("tr:even").css("backgroundColor","#FF9966");
          $("tr:eq(0)").css({"backgroundColor":"#003399","fontSize":"20px","color":"#fff","padding":"10px 15px"});
          $("td").css({"fontSize":"14px","padding":"5px"});
          $("td a").css({"color":"#FFBBFF","textDecoration":"underline","fontSize":"14px"});
          $("td a").hover(function(){
         	 $(this).css({"textDecoration":"none"});
          },function(){
         	 $(this).css({"textDecoration":"underline"});
          }
         )
         $("span").css({"width":"100px","padding":"10px 450px","color":"#f00","diplay":"inline-block","margin":"0 auto"});
          
         $(".soldout").click(function(){
			var a=$(this).parent().parent();
			$.ajax({
				type:'post',
				dataType:'json',
				url:'Operator/OperatorReportCtrl',
				data:{
					method:'handleGood',
					id:a.children(":first").val()
				},
				success:function(data)
				{
					if(data.flag)
					{
						a.children(":eq(5)").html("已下架");
						a.children(":eq(7)").html("已处理")
						$("span").html("商品已下架！");
					}
						
				}
			})
		})
	})
	

	</script>

  </head>
  
  <body>
     <jsp:include page="/Shopping/public/Header.jsp">
  		<jsp:param value="1" name="index"/>
 	 </jsp:include>
  	 <jsp:include page="/Operator/public/publicHeader.jsp">
  		<jsp:param value="2" name="index"/>
  	 </jsp:include>
  	 <div class="reportPager">
  	 		<span></span>
	  	 <table border="0" cellspacing="0"  cellpadding="0" class="reportTable">
	  	 	<caption>举报商品列表</caption>
	  	 	<tr>
	  	 		<td>编号</td>
	  	 		<td>名称</td>
	  	 		<td>类型</td>
	  	 		<td>介绍</td>
	  	 		<td>状态</td>
	  	 		<td>相关链接</td>
	  	 		<td>操作</td>
	  	 	</tr>
	  	 	<c:forEach items="${requestScope.goods }" var="g" varStatus="s">
			<tr class="goodInfo">
				<input type="hidden" value="${g.id }" name="id"/>
				<td>${s.index+1 }</td>
				<td>${g.gname }</td>
				<td>${g.gtype }</td>
				<td>${g.introduction }</td>

				<c:choose>
					<c:when test="${g.gstate==1 }">
						<td>被举报</td>
						<td><a href="#">商品链接</a></td>
						<td><a class="soldout">下架商品</a></td>
					</c:when>
					<c:otherwise>
						<td>已下架</td>
						<td><a href="#">商品链接</a></td>
						<td><a>已处理</a></td>
					</c:otherwise>
				</c:choose>
				
			</tr>	 		
	  	 	</c:forEach>
	  	 </table>
	  	 
  	 </div>
  </body>
</html>
