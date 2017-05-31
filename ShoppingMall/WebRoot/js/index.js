	$(function(){
			$("input[name='username']").focus(function(){
				$(".placeholder:eq(0)").html("");
				$(".placeholder:eq(1)").html("");
			});
			$("input[name='password']").focus(function(){
        		if($("input[name='username']").val()=="")
         			$(".placeholder:eq(1)").html("用户名不能为空!").css("color","#f00");
         		else
         			$(".placeholder:eq(1)").html("");
       		});
       		$("input[name='password']").blur(function(){
       			if($("input[name='username']").val()!="")
       			{
       				if($("input[name='password']").val()=="")
       					$(".placeholder:eq(2)").html("密码不能为空!").css("color","#f00");
       				else
       					$(".placeholder:eq(2)").html("");
       			}
       			
       		})
       			
		})
		function login()
		{
			$.ajax({
				type:"get",
				url:"LoginCtrl",
				dataType:"json",
				data:{
					method:"verify",
					username:$("input[name='username']").val(),
					password:$("input[name='password']").val()
				},
				success:function(data){
					if(data.flag=="true")
						location.href="Shopping/Manager/HomePage.jsp";
					else
						$(".placeholder:eq(0)").html("用户名或密码错误!").css("color","#f00");
				}
				
			})
		}