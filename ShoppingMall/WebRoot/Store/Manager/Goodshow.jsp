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

<title>商品详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/homepage.css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".alter").hide();
		$(".smassage").show();
		$(".result").hide();
		$(".result .btn").css({
			"marginLeft" : "10px"
		})
	})

	function show() {
		$(".alter").hie();
		$(".smassage").show();
		$(".left-content a:eq(1)").css("color", "#004CDB");
		$(".left-content a:eq(1)").css("textDecoration", "none");
		$(".left-content a:eq(0)").css("color", "#003399");
		$(".left-content a:eq(0)").css("textDecoration", "underline");
	}

	function alter() {
		$(".alter").show();
		$(".smassage").hide();
		$(".left-content a:eq(1)").css("color", "#004CDB");
		$(".left-content a:eq(1)").css("textDecoration", "none");
		$(".left-content a:eq(0)").css("color", "#003399");
		$(".left-content a:eq(0)").css("textDecoration", "underline");
	}
	
	
function GetQueryString(id)
{
     var reg = new RegExp("(^|&)"+ id +"=([^&]*)(&|$)");
     var r = location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

	function storemassage(flag) {
		var goodid=GetQueryString("id");
		
		
		$.ajax({
			type:"get",
			dataType:"json",
			url:"GoodCtrl",
			data:{
				method : "load",
				goodid:goodid
			},
			success : function(data) {
				if (flag == 1) { //显示
					$("input[name='goodname']").val(data.goodname);
					$("input[name='gprice']").val(data.gprice);
					$("input[name='gtotalNum']").val(data.gtotalNum);
					$("input[name='gremainNum']").val(data.gremainNum);
					$("input[name='introduction']").val(data.introduction);
					$("input[name='gtype']").val(data.gtype);
					$("input[name='gstate']").val(data.gstate);
				} else { //修改	           
					$.ajax({
						type:"get",
						dataType : "json",
						url : "GoodCtrl",
						data : {
							method : "alter",
							id : data.goodid,
							gname : $("#al_goodname").val(),
							introduction : $("#al_introduction").val(),
							gpic : data.gpic,
							gprice : $("#al_gprice").val(),
							gremainnum : $("#al_gremainNum").val(),
							gtotalnum : $("#al_gtotalNum").val(),
							gtype : $("#al_gtype").val(),
							gstate :$('input[name="goodstate"]:checked').val()
						},
						success : function(data) {
							if (data.flag == true) {
								window.alert("修改成功");
								storemassage(1);
							}
						}
					})
				}
			}
		})
	}
</script>

<style>
div.smassage {
	text-align: center;
	margin: 0px auto;
}
</style>

</head>

<body onload="storemassage(1)">
	<div class="bg">
		<jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>

		<div class="smassage" style="center">
			<from> <label>商品名： </label>
			<input readonly="readonly" name="goodname">
			<span></span>
			<br>
			<label>商品价格： </label>
			<input readonly="readonly" name="gprice">
			<span></span>
			<br>
			<label>进货： </label>
			<input readonly="readonly" name="gtotalNum">
			<span></span>
			<br>
			<label>库存： </label>
			<input readonly="readonly" name="gremainNum">
			<span></span>
			<br>
			<label>介绍：</label>
			<input readonly="readonly" name="introduction">
			<span></span>
			<br>
			<label>类型： </label>
			<input readonly="readonly" name="gtype">
			<span></span>
			<br>
			<label>状态： </label>
			<input readonly="readonly" name="gstate">
			<span></span>
			<br>
			<input type="button" value="修改商品信息" onclick="alter()">
			<br>
			</from>
		</div>

		<div class="alter" style="center">
			<from> <label>商品名： </label>
			<input id="al_goodname" name="goodname">
			<span></span>
			<br>
			<label>商品价格： </label>
			<input id="al_gprice" name="gprice">
			<span></span>
			<br>
			<label>进货： </label>
			<input id="al_gtotalNum" name="gtotalNum">
			<span></span>
			<br>
			<label>库存： </label>
			<input id="al_gremainNum" name="gremainNum">
			<span></span>
			<br>
			<label>介绍： </label>
			<input id="al_introduction" name="introduction">
			<span></span>
			<br>
			<label>类型： </label>
			<input id="al_gtype" name="gtype">
			<span></span>
			<br>
			<label>状态： </label><br>
			<input type="radio"  name="goodstate"  value="0" checked="checked">上架<br>
			<input type="radio"  name="goodstate"  value="2" >下架<br>
			<input type="radio"  name="goodstate"  value="1" >被举报<br>
			<span></span>
			<br>
			<input class="btn" type="button" value="修改" onclick="storemassage(2)">
			<br>
			</from>
		</div>
		<a href="./Store/Manager/GoodManager.jsp"><input class="btn"
			type="button" value="返回商品管理页面">
		</a>
	</div>
</body>
</html>
