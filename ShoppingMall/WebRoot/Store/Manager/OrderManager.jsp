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

<title>订单管理</title>

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
	
       function storemassage(flag) {
		    var goodid=${param.goodid};
		    $.ajax({
			type:"get",
			dataType:"json",
			url:"StoreOrderCtrl",
			data:{
					method:"query",
					goodid:goodid
			 },
			success:function(data) {
				if(flag==1){  //显示
				    $("input[name='gname']").val(data.goodname);
					$("input[name='gnum']").val(data.num);
					$("input[name='uname']").val(data.username);
					$("input[name='rec']").val(data.receiver);
					$("input[name='rectel']").val(data.receivertel);
					$("input[name='loc']").val(data.location);
					$("input[name='cred']").val(data.order_createdate);
					$("input[name='state']").val(data.order_state);
				}else{     //修改	           
			           $.ajax({
			           type:"get",
			           dataType:"json",
			           url:"StoreOrderCtrl",
			           data:{
					         method:"alter",
					         order_id:data.order_id,
					         order_state:$('input[name="ostate"]:checked').val(),
					         order_addressid:data.order_addressid,
					         order_userid: data.order_userid,
					         order_createdate:data.order_createdate
			           },			           
			           success:function(data){
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
    div.smassage{text-align:center;margin:0px auto;}
</style>

</head>

<body onload="storemassage(1)">
	<div class="bg">
		<jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>
					
	   <div class="smassage" alter="center">
	   <from>
		<label>商品名：     </label><input  readonly="readonly"  name="gname"><span></span><br>
		<label>商品数量： </label><input  readonly="readonly"  name="gnum"><span></span><br>
		<label>买家：         </label><input  readonly="readonly"  name="uname"><span></span><br>		
		<label>收货人：        </label><input  readonly="readonly"  name="rec"><span></span><br>
		<label>收货人联系电话：</label><input readonly="readonly" name="rectel"><span></span><br>
		<label>收货人地址：</label><input  readonly="readonly"  name="loc"><span></span><br>
		<label>创建时间：  </label><input  readonly="readonly"  name="cred"><span></span><br>	
		<label>订单状态： </label><input  readonly="readonly"  name="state"><span></span><br>	
        <input  type="button" value="修改订单状态" onclick="alter()"><br>
        </from>
       </div>    
       
        <div> 
		<from class="alter">	
		<label>商品名：   </label><input   readonly="readonly"    name="gname"><span></span><br>
		<label>商品数量：</label><input   readonly="readonly"    name="gnum"><span></span><br>
		<label>买家：       </label><input    readonly="readonly"    name="uname"><span></span><br>
		<label>收货人：    </label><input    readonly="readonly"   name="rec"><span></span><br>
		<label>收货人联系电话：</label><input readonly="readonly"  name="rectel"><span></span><br>
		<label>收货人地址：</label><input    readonly="readonly"  name="loc"><span></span><br>
		<label>创建时间：   </label><input   readonly="readonly"     name="cred"><span></span><br>	
		<label>订单状态： </label><br>
			<input type="radio"  name="ostate"  value="0" checked="checked">未付款<br>
			<input type="radio"  name="ostate"  value="1" >未发货<br>
			<input type="radio"  name="ostate"  value="2" >已发货<br>
			<input type="radio"  name="ostate"  value="3" >已收货<br>
		<input class="btn" type="button" value="修改" onclick="storemassage(2)"><br>
		</from>	
		</div>

		<a href="./Store/Manager/Order.jsp"><input class="btn" type="button" value="返回订单管理页面" ></a>			
	</div>
</body>
</html>
