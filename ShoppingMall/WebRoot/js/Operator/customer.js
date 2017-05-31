
	   function listInfo(){  
             $.ajax({  
               type:"post",//请求方式  
               url:"Operator/CustomerManagerCtrl",//发送请求地址  
               timeout:30000,//超时时间：30秒  
               dataType:"json",//设置返回数据的格式  
               data:{
               		method:"list",
               		username:$("input[name='username']").val(),
               		order:$("select[name='order']").val(),
               		choose:$("select[name='choose']").val(),
               		currentPage:$("input[name='currentPage']").val(),
               		eachRecord:$("input[name='eachRecord']").val(),
               		totalRecord:$("input[name='totalRecord']").val()
               },
               //请求成功后的回调函数 data为json格式  
               success:function(data){  
               	     var table = $("#UserTable"); 
               	     var ul=$(".Pager");
	            	 table.empty();  
	            	 ul.empty();
	            	 table.append('<tr><th>编号</th><th>姓名</th><th>性别</th><th>类型</th><th>操作</th></tr>');  

              		 var objs=eval(data);
	                 var obj=objs.users;
	                 if(obj.length==1)
                	 {
	                	 var msg=obj[0].msg;
	                	 table.append('<tr><td colspan="5">'+msg+'</td></tr>');
                	 }
	                 else
	                {
	                	 var totalRecord=$("input[name='totalRecord']");
		                 var totalPage=$(".totalPage");
		                 
		                 ul.append('<li><a class="prevPage"><上一页</a></li>');
		                 $("ul:last").show();
		           		 for(var i=0;i< obj.length;i++)
		           		 {
		           		 	if(i==0)
		           		 	{
		           		 		totalRecord.val(obj[i].totalRecord);		
		                 		
		           		 	}
		           		 	else if(i==1)
		           		 	{
		           		 		totalPage.html(obj[i].totalPage);
		           		 	}
		           		 	
		           		 	else
		           		 	{
			           		 	 var id = obj[i].id;
			                	 var username = obj[i].username;
			                	 var sex = obj[i].sex;
			                	 var type=grant(obj[i].type);
			                	 table.append('<tr><td>'+id+'</td><td>'+username+'</td><td>'+sex+'</td><td>'+type+'</td>'
			               			+'<td><a href="Operator/CustomerManagerCtrl?method=get&id='+id+'"'+'>查看</a>'
			               			+'<a class="del">删除</a></td></tr>');	
			               		 
		           		 	}
		           		 
		            	}  
		            	for(var j=0;j<totalPage.html();j++)
	             		{
	             			var temp=j+1;
	             			ul.append('<li><a class="c_page">'+temp+'</a></li>');
	             		}
						ul.append('<li><a class="nextPage">下一页></a></li>');
						$("a").css("cursor","pointer");
						//点击请求某一页数据
						$(".c_page").mousedown(function(){
					       		$("input[name='currentPage']").val($(this).html());
					       		return listInfo();
	    				});
						//点击请求下一页数据
	    				$(".nextPage").mousedown(function(){
	    					var currentPage=$("input[name='currentPage']");
	    					
	    					if(parseInt(currentPage.val())==parseInt(totalPage.html()))
	    					{
	    						alert("已经是最后一页了");
	    						return;
	    					}
	    					else
	    					{
	    						currentPage.val(parseInt(currentPage.val())+1);
	    						return listInfo();
	    					}
	    				});
	    				//点击请求上一页数据
	    				$(".prevPage").mousedown(function(){
	    					var currentPage=$("input[name='currentPage']");
	    					
	    					if(parseInt(currentPage.val())==1)
	    					{
	    						alert("已经是第一页了");
	    						return;
	    					}
	    					else
	    					{
	    						currentPage.val(parseInt(currentPage.val())-1);
	    						return listInfo();
	    					}
	    				});
	    				//请求删除某一列数据
	    				$(".del").mousedown(function(){
							var flag=confirm("是否确认删除");
							var attr=$(this).parent().parent();
							var id=$(this).parent().parent().children(":first").html();
							if(flag)
							{
								$.ajax({
									type:"get",
									url:"Operator/CustomerManagerCtrl?method=delete&id="+id,
									dataType:"json",
									success:function(data)
									{
										var flag=data.flag;
										if(flag) $(attr).remove();
										else alert("删除失败");
										return true;
									} 
								})
							}
							else return false;								
						}) 
	                }
	                 table.css({"width":"60%","textAlign":"center","color":"#FFBBFF","margin":"20px auto"});
	                 $("th").css({"backgroundColor":"#003399","fontSize":"20px","color":"#fff","padding":"10px 15px"});
	                 $("tr:odd").css("backgroundColor","#FF6633");
	                 $("tr:even").css("backgroundColor","#FF9966");
	                 $("td").css({"fontSize":"20px","padding":"10px 15px"});
	                 $("td a").css({"color":"#FFBBFF","textDecoration":"underline","paddingRight":"10%","fontSize":"20px"});
	                 $("td a").hover(function(){
	                	 $(this).css({"textDecoration":"none"});
	                 },function(){
	                	 $(this).css({"textDecoration":"underline"});
	                 }
	                );
	                 ul.css({"marginLeft":"10%"});
	                 $(".Pager li").css({"float":"left"});
	                 $(".Pager li a").css({"color":"#7C7C7C","border":"1px solid #CCCCCC","borderRight":"none","padding":"10px 15px","fontSize":"16px"});
	                 $(".Pager li a:last").css({"borderRight":"1px solid #999999"});
	                 $(".Pager li a").hover(function(){
	                	 $(this).css({"color":"#FF6700","borderColor":"#FF6700","borderRight":"1px solid #FF6700"});
	                 },function(){
	                	 $(this).css({"color":"#7C7C7C","borderColor":"#CCCCCC","borderRight":"none"});
	                	 $(".Pager li a:last").css({"borderRight":"1px solid #999999"});
	                 });
	                 var currentPage=$("input[name='currentPage']").val();
	                 //告知当前页面
	                 $(".Pager li a:eq('+currentPage+')").css("color","#f00");
	                
	                 
               },  
             
           });  
          } 
	   $(function(){
		   $("ul:last").hide();
		   $("ul:last li").css({"float":"left","color":"#7C7C7C","marginLeft":"20px"});
		   $("ul:last li input[type='text']").css({"border":"1px solid #CCCCCC","color":"#FF6700","paddingLeft":"4px"});
	   })
        function grant(type)
        {
        	if(type==0) return '顾客'
        	else if(type==1) return '商家'
        	else return '运营商'
        }
         //清空还原当前页数和总记录默认值以及搜索框的值
        function emptyRecord()
        {
        	$("input[name='currentPage']").val(1);
            $("input[name='totalRecord']").val(0);
            $("input[name='username']").val("");
        }
        //随机跳转页数赋值给当前页数
        function change()
        {
        	$("input[name='currentPage']").val($("input[name='randomPage']").val());
        }
        