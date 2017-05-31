<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		function report()
		{
			$.ajax({
				type:'post',
				url:'OperatorReportCtrl',
				dataType:'json',
				data:{
					method:'reportGood',
					url:$("input[name='url']").val()
				},
				success:function(data)
				{
					if(data.flag)
					{
						alert("提交成功，等待处理");
					}
				}
			})
		}
	</script>
  </head>
  <form >
  	url:<input type="text" name="url">
  	<input type="button" value="提交" onclick="report()">
  </form>
  
  <body>
    
  </body>
</html>
