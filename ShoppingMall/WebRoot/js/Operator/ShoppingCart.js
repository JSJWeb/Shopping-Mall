	$(function(){
			//全选
			$(".checkAll").click(function(){
				if($(this).prop("checked"))
					$("input[type='checkbox']").prop("checked",true);
				else
					$("input[type='checkbox']").prop("checked",false);
			})
			//点击删除商品
			$(".del").click(function(){
				var flag=confirm("确认是否删除");
				var a=$(this).parent().parent();
				var b=$(this).parent().parent().prev();
				if(flag)
				{
					$.ajax({
						type:"get",
						dataType:"json",
						url:"OperatorCartCtrl",
						data:{
							method:"del",
							index:$("input[name='index']").val()
						},
						success:function(data)
						{
							if(data.flag)
							{
								a.remove();
								b.remove();
							}
						}
					})
				}
			})
			//数量变化
			
			$("input[type='checkbox']").click(function(){
				var count=$("input:checked.choose").length;
				$(".num").html(count);
			})
			//价格变化
			$("input[type='checkbox']").click(function(){
				var totalprice=0.00;
				$("input:checked.choose").each(function(){
					totalprice=parseFloat((totalprice-0)+($(this).parent().siblings(".price").html()-0)).toFixed(2);
				})
				if(totalprice==0)
					$(".totalprice").html("0.00");
				else
					$(".totalprice").html(totalprice);
			})
			//按钮颜色变化
			$("input[type='checkbox']").click(function(){
				if($("input:checked.choose").length>=1)
				{
					$(".goodTable input.btn").css({"backgroundColor":"#F22D00","cursor":"pointer"});
				}
				else
				{
					$(".goodTable input.btn").css({"backgroundColor":"#B0B0B0","cursor":"not-allowed"});
				}
			})
		})
