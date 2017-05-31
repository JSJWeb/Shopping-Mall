<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加商品首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/homepage.css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("input[name='goodname']").focus(function() {
			$("span:eq(0)").html("");
			$("span:eq(1)").html("");
		})

		$("input[name='gprice']").focus(function() {
			if ($("input[name='goodname']").val() == "")
				$("span:eq(0)").html("商品名不能为空!");
			else {
			  $.ajax({
					type:"get",
					url :"GoodCtrl",
					dataType:"json",
					data: {
					method:"check",
					goodname:$("input[name='goodname']").val()
              },
              success : function(data) {
                  if (data.flag == true)
                     $("span:eq(0)").html("商品已存在!");
                   }
                })
               }
           })                
           
           $("input[name='gtotalNum']").focus(function() {
              if ($("input[name='gprice']").val() == "")
				   $("span:eq(1)").html("商品价格不能为空!");
               $("span:eq(2)").html("");
           })
           
           $("input[name='introduction']").focus(function() {
              if ($("input[name='gtotalNum']").val() == "")
				   $("span:eq(2)").html("进货不能为空!");
              $("span:eq(3)").html("");
            })
            
            $("input[name='gtype']").focus(function() {
              if ($("input[name='introduction']").val() == "")
				   $("span:eq(3)").html("介绍不能为空!");
              $("span:eq(4)").html("");
            })
            
            $("input[name='gstate']").focus(function() {
              if ($("input[name='gtype']").val() == "")
				   $("span:eq(4)").html("类型不能为空!");
              $("span:eq(5)").html("");
            })
 })
   

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

	
</script>

<style>
div.add {
	text-align: center;
	margin: 0px auto;
}
</style>

</head>

<body>
	<div class="bg">
		<jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>
     <div class="right-content">
     <div class="add">
			<form name="login" action="GoodCtrl"  enctype="multipart/form-data" method="post"> 
	  	    <input type="hidden" name="user_id" value="1">
			<label>商品名： </label> <input name="goodname"><br>
			<span></span><br>
			<label>商品价格： </label> <input name="gprice"><br>
			 <span></span><br>
			<label>进货： </label> <input name="gtotalNum"><br>
			<span></span><br>
			<label>介绍： </label> <input name="introduction"><br>
			<span></span><br>
			<label>类型： </label> <input name="gtype"><br>
			<span></span><br>
			<label>状态： </label> <input name="gstate" value="上架"><br>
			<span></span><br>
			<label>图片： </label> <input type="file" name="gpic"> (仅上传".jpg"格式的图片)<br>
			<span></span><br>
			<input class="btn" type="submit" value="添加" >
			<input type="reset" value="重置">
			</form>
			<br>
			</from>
			</div>
		</div>
		<a href="./Store/Manager/GoodManager.jsp"><input class="btn"
			type="button" value="商品管理页面"> </a>
	</div>
</body>
</html>
