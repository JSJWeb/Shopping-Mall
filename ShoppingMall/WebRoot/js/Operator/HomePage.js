		$(function(){
			
			$(".bg .right-content .add").hide();
			$(".bg .right-content .updatePsw").hide();
			$(".bg .right-content .singleUser").hide();
			$(".bg .result").hide();
			$(".bg .result .btn").css({
				"marginLeft":"10px"
			})
		})
		function addUser()
		{
			$(".bg .add").show();
			$(".bg .right-content .updatePsw").hide();
			$(".bg .right-content .singleUser").hide();
			$(".left-content a:eq(0)").css("color","#004CDB");
			$(".left-content a:eq(0)").css("textDecoration","none");
			$(".left-content a:eq(1)").css("color","#003399");
			$(".left-content a:eq(1)").css("textDecoration","underline");
			$(".left-content a:eq(2)").css("color","#003399");
			$(".left-content a:eq(2)").css("textDecoration","underline");
			$(".updatePsw").hide();
		}
		function updatePsw()
		{
			$(".add").hide();
			$(".bg .right-content .singleUser").hide();
			$(".updatePsw").show();
			$(".left-content a:eq(1)").css("color","#004CDB");
			$(".left-content a:eq(1)").css("textDecoration","none");
			$(".left-content a:eq(0)").css("color","#003399");
			$(".left-content a:eq(0)").css("textDecoration","underline");
			$(".left-content a:eq(2)").css("color","#003399");
			$(".left-content a:eq(2)").css("textDecoration","underline");
			
		}
		function updateSingle()
		{
			$(".bg .right-content .add").hide();
			$(".bg .right-content .updatePsw").hide();
			$(".bg .right-content .singleUser").show();
			$(".left-content a:eq(2)").css("color","#004CDB");
			$(".left-content a:eq(2)").css("textDecoration","none");
			$(".left-content a:eq(0)").css("color","#003399");
			$(".left-content a:eq(0)").css("textDecoration","underline");
			$(".left-content a:eq(1)").css("color","#003399");
			$(".left-content a:eq(1)").css("textDecoration","underline");
		}
		
		
		$(function(){
			$("input[name='username']").focus(function(){
				$("span:eq(0)").html("");
				$("span:eq(1)").html("");
			})
			$("input[name='password']").focus(function(){
				if($("input[name='username']").val()=="")
					$("span:eq(0)").html("用户名不能为空!");
				else
				{
					$.ajax({
						type:"post",
						url:"PersonalCenterCtrl",
						dataType:"json",
						data:{
							method:"verifyUser",
							username:$("input[name='username']").val()
						},
						success:function(data)
						{
							if(data.flag==true)
								$("span:eq(0)").html("用户名已存在!");
						}
					
					})
				}
			})
			$("input[name='password']").focus(function(){
				$("span:eq(1)").html("");	
			})
			//$("input[name='password']").blur(function(){
			//	if($(this).val()==""&&$("input[name='username']").val()!="")
			//		$("span:eq(1)").html("密码不能为空!");
			//})
			$("input[name='secondPsw']").focus(function(){
				if($("input[name='username']").val()!=""&&$("input[name='password']").val()=="")
					$("span:eq(1)").html("密码不能为空!");
				$("span:eq(2)").html("");	
			})
			//$("input[name='secondPsw']").blur(function(){
			//	if($(this).val()!=$("input[name='password']").val())
			//		$("span:eq(2)").html("密码与确认密码不一致");
			//})
			$("input[name='nickname']").focus(function(){
				if($("input[name='password']").val()!=""&&$("input[name='username']").val()!="")
					if($("input[name='secondPsw']").val()!=$("input[name='password']").val())
						$("span:eq(2)").html("密码与确认密码不一致");
				$("span:eq(3)").html("");	
			})
			$("input[name='nickname']").blur(function(){
				if($(this).val()!="")
					if($("input[name='nickname']").val().length<2||$("input[name='nickname']").val().length>10)
						$("span:eq(3)").html("昵称只能在2到10之间");
			})
			$("input[name='tel']").focus(function(){						
				$("span:eq(4)").html("");	
			})
			$("input[name='tel']").blur(function(){
				if($(this).val()!="")
					if($("input[name='tel']").val().length<8||$("input[name='tel']").val().length>11)
						$("span:eq(4)").html("联系电话只能在8到11之间");
			})
			$("input[name='year'] input[name='month'] input[name='day']").focus(function(){
				$("span:eq(5)").html("");	
			})
			$("input[name='year'] input[name='month'] input[name='day']").blur(function(){
				if($("input[name='year']").val()!=""||$("input[name='year']").val()!=""||$("input[name='month']").val()!="")
					if(parseInt($("input[name='year']").val())>2017||parseInt($("input[name='month']")).val()<0||parseInt($("input[name='month']").val())>12||parseInt($("input[name='day']").val())>31)
						$("span:eq(5)").html("填写的时间不符合实际");
			})
	
			//修改密码区
			$("input[name='oldPsw']").focus(function(){
				$(".updatePsw span:eq(0)").html("");
			})
			$("input[name='oldPsw']").blur(function(){
				if($("input[name='oldPsw']").val()=="")
					$(".updatePsw span:eq(0)").html("原密码不为空！");
				else
				{
					$.ajax({
						type:"post",
						url:"PersonalCenterCtrl",
						dataType:"json",
						data:{
							method:"verifyPsw",
							password:$("input[name='oldPsw']").val()
						},
						success:function(data)
						{
							if(data.flag==true)
								$(".updatePsw span:eq(0)").html("密码正确!");
							else
								$(".updatePsw span:eq(0)").html("密码错误!");
						}
					})
				}
			})
			$("input[name='secondOldPsw']").focus(function(){
				$(".updatePsw span:eq(1)").html("");
			})
			$("input[name='secondOldPsw']").blur(function(){
				if($(this).val()!=$("input[name='oldPsw']").val())
					$(".updatePsw span:eq(1)").html("原密码与确认密码不一致！");
				
			})
			$("input[name='newPsw']").focus(function(){
				$(".updatePsw span:eq(2)").html("");
				
			})
			$("input[name='newPsw']").blur(function(){
				if($(this).val()=="") 
					$(".updatePsw span:eq(2)").html("新密码不为空！");
				else if(parseInt($(this).val())<4||parseInt($(this).val())>12)
					$(".updatePsw span:eq(2)").html("密码长度必须在4到12之间");
			})
			$("input[name='secondNewPsw']").focus(function(){
				$(".updatePsw span:eq(3)").html("");
			})
			$("input[name='secondNewPsw']").blur(function(){
				if($("input[name='newPsw']").val()!=$(this).val())
					$(".updatePsw span:eq(3)").html("新密码与确认密码不一致！");
			})
			
		
			$("span").css({"paddingLeft":"125px","color":"#FA8350"});
		})
		
		
		function register()
		{
			$(".add input[name='type']").val(2);
			var year=$(".add input[name='year']").val();
			var month=$(".add input[name='month']").val();
			var day=$(".add input[name='day']").val();
			var birth=year+'-'+month+'-'+day;
			var username=$(".add input[name='username']").val();
			var password=$(".add input[name='password']").val();

			if(username=="") $("span:eq(0)").html("用户名不能为空!");
			else if(password=="") $("span:eq(1)").html("密码不能为空!");
			else
			{
				$.ajax({
					type:"post",
					dataType:"json",
					url:"Operator/PersonalCenterCtrl",
					data:{
						method:"save",
						username:username,
						password:password,
						nickname:$(".add input[name='nickname']").val(),
						tel:$(".add input[name='tel']").val(),
						sex:$(".add input[name='sex']").val(),
						birth:birth,
						type:$(".add input[name='type']").val()
						
					},
					success:function(data)
					{
						if(data.flag==true)
						{
							$(".add").hide();
							$(".result").show();
							$(".result span").html("注册成功!");
							$(".result .btn").val("返回继续注册");
							
							$(".result .btn").click(function(){
								$(".bg .right-content .add").show();
								$(".result").hide();
								$(".bg .right-content .updatePsw").hide();
								$(".bg .right-content .singleUser").hide();
								$(".add input").val("");
								$(".add .btn").val("注册");
								$(".add input[name='type']").val(2);
							})
							
						}
					}
				})
			}
			
		}
		function update()
		{
			var password=$("input[name='newPsw']").val();
			var oldPsw=$("input[name='oldPsw']").val();
			if(oldPsw=="") $(".updatePsw span:eq(0)").html("原密码不能为空！");
			else if(password=="") $(".updatePsw span:eq(2)").html("新密码不能为空！");
			else
			{
				$.ajax({
					type:"post",
					dataType:"json",
					url:"Operator/PersonalCenterCtrl",
					data:{
						method:"updatePsw",
						password:password
					},
					success:function(data)
					{
						if(data.flag==true)
						{
							$(".updatePsw").hide();
							$(".result").show();
							$(".result span").html("修改成功!");
							$(".result .btn").val("点击返回修改页面");
							
							$(".result .btn").click(function(){
								$(".bg .right-content .add").hide();
								$(".result").hide();
								$(".bg .right-content .updatePsw").show();
								$(".bg .right-content .singleUser").hide();
								$(".updatePsw input").val("");
								$(".updatePsw .btn").val("确认修改");
								$("span").val("");
							})
							
						}
				}
				})
			}
			
		}
		function updateUser()
		{
			$.ajax({
				type:"post",
				dataType:"json",
				url:"Operator/PersonalCenterCtrl",
				data:{
					method:"updateUser",
					username:$(".singleUser input[name='username']").val(),
					password:$(".singleUser input[name='password']").val(),
					nickname:$(".singleUser input[name='nickname']").val(),
					tel:$(".singleUser input[name='tel']").val(),
					sex:$(".singleUser input[name='sex']").val(),
					birth:$(".singleUser input[name='birth']").val(),
					type:$(".singleUser input[name='type']").val()
					
				},
				success:function(data)
				{
					if(data.flag==true)
					{
						$(".bg .right-content .singleUser").hide();
						$(".result").show();
						$(".result span").html("修改成功!");
						$(".result .btn").val("返回继续修改");
						
						$(".result .btn").click(function(){
							$(".result").hide();
							$(".bg .right-content .singleUser").show();
						})
						
					}
				}
			})
		}

		