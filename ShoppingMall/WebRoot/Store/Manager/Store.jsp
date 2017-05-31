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

<title>店铺管理首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/Store.css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".add").hide();
		$(".updatePsw").hide();
		$(".smassage").hide();
		$(".alter").hide();
		$(".result").hide();
		$(".result .btn").css({
			"marginLeft" : "10px"
		})
	})
	function addUser() {
		$(".add").show();
		$(".left-content a:eq(0)").css("color", "#004CDB");
		$(".left-content a:eq(0)").css("textDecoration", "none");
		$(".left-content a:eq(1)").css("color", "#003399");
		$(".left-content a:eq(1)").css("textDecoration", "underline");
		$(".updatePsw").hide();
		$(".smassage").hide();
		$(".alter").hide();
	}
	function updatePsw() {
		$(".add").hide();
		$(".updatePsw").show();
		$(".smassage").hide();
		$(".alter").hide();
		$(".left-content a:eq(1)").css("color", "#004CDB");
		$(".left-content a:eq(1)").css("textDecoration", "none");
		$(".left-content a:eq(0)").css("color", "#003399");
		$(".left-content a:eq(0)").css("textDecoration", "underline");
	}
	
	function show() {
		$(".add").hide();
		$(".updatePsw").hide();
		$(".smassage").show();
		$(".alter").hide();
		$(".left-content a:eq(1)").css("color", "#004CDB");
		$(".left-content a:eq(1)").css("textDecoration", "none");
		$(".left-content a:eq(0)").css("color", "#003399");
		$(".left-content a:eq(0)").css("textDecoration", "underline");
	}
	
	function alter() {
		$(".add").hide();
		$(".updatePsw").hide();
		$(".smassage").hide();
		$(".alter").show();
		$(".left-content a:eq(1)").css("color", "#004CDB");
		$(".left-content a:eq(1)").css("textDecoration", "none");
		$(".left-content a:eq(0)").css("color", "#003399");
		$(".left-content a:eq(0)").css("textDecoration", "underline");
	}

	$(function() {
		$("input[name='username']").focus(function() {
			$("span:eq(0)").html("");
			$("span:eq(1)").html("");
		})
		
		$("input[name='password']").focus(function() {
			if ($("input[name='username']").val() == "")
				$("span:eq(0)").html("用户名不能为空!");
			else {
				$.ajax({
					type : "post",
					url : "StoreCenterCtrl",
					dataType : "json",
					data : {
					method : "verifyUser",
					username : $("input[name='username']").val()
					},
					success : function(data) {
						if (data.flag == true)
							$("span:eq(0)").html("用户名已存在!");
					}
				})
			}
		})
		
		$("input[name='password']").focus(function() {
			$("span:eq(1)").html("");
		})

		$("input[name='secondPsw']").focus(function() {
			 if ($("input[name='username']").val() != ""&& $("input[name='password']").val() == "")
					$("span:eq(1)").html("密码不能为空!");
			  $("span:eq(2)").html("");
		})

		$("input[name='nickname']").focus(function() {
			if ($("input[name='password']").val() != ""	&& $("input[name='username']").val() != "")
				if ($("input[name='secondPsw']").val() != $("input[name='password']").val())
						$("span:eq(2)").html("密码与确认密码不一致");
				$("span:eq(3)").html("");
		})

		$("input[name='nickname']").blur(function() {
			if ($(this).val() != "")
				if ($("input[name='nickname']").val().length < 2|| $("input[name='nickname']").val().length > 10)
						$("span:eq(3)").html("昵称只能在2到10之间");
		})
		//店铺名不能重名，检测								
			$("input[name='tel']").focus(function() {
			if ($("input[name='nickname']").val() == "")
				$("span:eq(3)").html("店铺名不能为空!");
			else {
				$.ajax({
					type : "post",
					url : "StoreCenterCtrl",
					dataType : "json",
					data : {
						method : "verifyStore",
						nickname : $("input[name='nickname']").val()
					},
					success : function(data) {
						if (data.flag == true)
							$("span:eq(3)").html("店铺名已存在!");
					}
				})
			}
		})
		
		$("input[name='tel']").focus(function() {
			$("span:eq(4)").html("");
		})
		
		$("input[name='tel']").blur(function() {
			if ($(this).val() != "")
				if ($("input[name='tel']").val().length < 8|| $("input[name='tel']").val().length > 11)
					$("span:eq(4)").html("联系电话只能在8到11之间");
				})

		//修改密码区
		$("input[name='oldPsw']").focus(function() {
		    $(".updatePsw span:eq(0)").html("");
	    })
	    
	    //检测密码是否正确
		$("input[name='oldPsw']").blur(function() {
		if ($("input[name='oldPsw']").val() == "")
			$(".updatePsw span:eq(0)").html("原密码不为空！");
	     })
	
		$("input[name='secondOldPsw']").focus(function() {
			$(".updatePsw span:eq(1)").html("");
		})
		
		$("input[name='secondOldPsw']").blur(function() {
			if ($(this).val() != $("input[name='oldPsw']").val())
				$(".updatePsw span:eq(1)").html("原密码与确认密码不一致！");
			else {
				$.ajax({
					type : "post",
					url : "StoreCenterCtrl",
					dataType : "json",
					data : {
						method : "verifyPsw",
						password : $("input[name='oldPsw']").val(),
						username:$("#globaluname").val()
					},
					success : function(data) {
						if (data.flag == false)
							$(".updatePsw span:eq(0)").html("密码错误!");
					}
				})
			}
		})
		
		$("input[name='newPsw']").focus(function() {
			$(".updatePsw span:eq(2)").html("");
		})
		$("input[name='newPsw']").blur(function() {
			if ($("input[name='newPsw']").val() == "")
				$(".updatePsw span:eq(2)").html("新密码不为空！");
			else if ((parseInt($("input[name='newPsw']").val())< 4)||(parseInt($("input[name='newPsw']").val())>12))
					$(".updatePsw span:eq(2)").html("密码长度必须在4到12之间");
				})
				
		$("input[name='secondNewPsw']").focus(function() {
			$(".updatePsw span:eq(3)").html("");
		})
		
		$("input[name='secondNewPsw']").blur(function() {
			if ($("input[name='newPsw']").val() != $(this).val())
				$(".updatePsw span:eq(3)").html("新密码与确认密码不一致！");
		})

		$("span").css({
			"paddingLeft" : "125px",
			"color" : "#FA8350"
		});
	})

	function register() {
		$("input[name='type']").val(1);
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
		if (username == "")
			$("span:eq(0)").html("用户名不能为空!");
		else if (password == "")
			$("span:eq(1)").html("密码不能为空!");
		else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "StoreCenterCtrl",
				data : {
					method : "save",
					username : username,
					password : password,
					nickname : $("input[name='nickname']").val(),
					tel : $("input[name='tel']").val(),
					type : $("input[name='type']").val()
				},
				success : function(data) {
					if (data.flag == true) {
						$(".add").hide();
						$(".result").show();
						$(".result span").html("注册成功!");
						$(".result .btn").val("返回继续注册");

						$(".result .btn").click(function() {
							$(".add").hide();
		                    $(".updatePsw").hide();
		                    $(".smassage").hide();
		                    $(".alter").hide();
						})
					}
				}
			})
		}
	}
	
	function update() {
		var password = $("input[name='newPsw']").val();
		var oldPsw = $("input[name='oldPsw']").val();
		if (oldPsw == "")
			$(".updatePsw span:eq(0)").html("原密码不能为空！");
		else if (password == "")
			$(".updatePsw span:eq(2)").html("新密码不能为空！");
		else {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "StoreCenterCtrl",
				data : {
					method : "updatePsw",
					password : password
				},
				success : function(data) {
					if (data.flag == true) {
						$(".updatePsw").hide();
						$(".result").show();
						$(".result span").html("密码修改成功!");
						$(".result .btn").val("返回");

						$(".result .btn").click(function() {
							$(".updatePsw").hide();
							$(".result").hide();
							$("input").val("");
							$("span").val("");
						})

					}
				}
			})
		}
}

       function storemassage(flag) {
		$.ajax({
			type : "get",
			dataType : "json",
			url : "StoreCenterCtrl",
			data : {
					method : "showStore",
					username : $("#globaluname").val()
			 },
			success:function(data) {
				if(flag==1){
				    $("input[name='uname']").val(data.username);
					$("input[name='unick']").val(data.nickname);
					$("input[name='utel']").val(data.tel);
					$("input[name='uregister']").val(data.register);
				}else{
				  $.ajax({
			           type:"get",
			           dataType:"json",
			           url:"StoreCenterCtrl",
			           data:{
					         method:"alter",
					         id:data.id,
					         username: $("#alter_uname").val(),
					         password:data.password,
					         nickname: $("#alter_unick").val(),
					         tel: $("#alter_utel").val(),
					         birth:data.birth,
					         register:data.regiter,
					         type:data.type
			           },
			           success:function(data) {
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

</head>

<body onload="storemassage(1)">
	<div class="bg">
		<jsp:include page="/Store/public/StoreHeader.jsp">
			<jsp:param value="1" name="index" />
		</jsp:include>
		<div class="left-content">
			<ul>
				<li><a onclick="addUser()">注册</a></li>
				<li><a onclick="updatePsw()">修改密码</a></li>
				<li><a onclick="show()">店铺信息</a></li>
			</ul>
		</div>
		<div class="right-content">
			<div class="add">
				<label>用户名：</label><input type="text" name="username"><span></span><br>
				<label>密码：</label><input type="password" name="password"><span></span><br>
				<label>确认密码：</label><input type="password" name="secondPsw"><span></span><br>
				<label>店铺名：</label><input type="text" name="nickname"><span></span><br>
				<label>联系电话：</label><input type="text" name="tel"><span></span><br>
				<input type="hidden" name="type" value="1"> 
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
			<div class="result">
				<span>注册成功</span><br> <input class="btn" type="button">
			</div>
		
		
		<div class="smassage">
		
		<label>用户名：</label><input  readonly="readonly"  name="uname"><span></span><br>
		<label>店铺名：</label><input  readonly="readonly"  name="unick"><span></span><br>
		<label>联系电话：</label><input readonly="readonly"  name="utel"><span></span><br>
		<label>注册时间：</label><input readonly="readonly"  name="uregister"><span></span><br>	
		<input  type="hidden"  id="globaluname" value="${sessionScope.user.username}">	
        <input  type="button" value="修改信息" onclick="alter()">
       </div>    
       
       
        
		<from class="alter">
		  <label>用户名：</label><input id="alter_uname" type="text" name="uname"><span></span><br>
		  <label>店铺名：</label><input id="alter_unick" type="text" name="unick"><span></span><br>
		  <label>联系电话：</label><input id="alter_utel" type="text" name="utel"><span></span><br>
		  <input class="btn" type="button" value="修改" onclick="storemassage(2)">
		</from>				
		</div>
	</div>
</body>
</html>
