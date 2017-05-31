<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HomePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/Operator/ShopHomePage.css">
	
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".hide-content").hide();
			$(".first-content").mouseover(function(){
				$(".content .right-content").hide();
				$(".content .pic2").hide();
				$(".left-content ul li.first-content").css({"border":"2px solid #FF3276","border-right":"none"});
				$(".hide-content.first-content").css({"border":"2px solid #FF3276"});
				$(".left-content ul li:gt(1) ul").css({"borderRight":"2px solid #FF3276"});
				$(".hide-content").show();
			})
			$(".first-content").mouseout(function(){
				$(".content .right-content").show();
				$(".content .pic2").show();
				$(".left-content ul li.first-content").css({"border":"none"});
					$(".left-content ul li:gt(1) ul").css({"borderRight":"2px solid #F5F5F5"});
					$(".hide-content").hide();
				
			})
		})
		//请求前补充href
		function addInfo(event)
		{
			var val="&type="+event.target.innerHTML;
			var num=event.target.getAttribute("href").indexOf('&');
			if(num!=-1)
				var href=event.target.getAttribute("href").substring(0,num);
			else
				var href=event.target.getAttribute("href")
			event.target.setAttribute("href",href+val);
			
		}
	</script>

  </head>
  
  <body>
    <jsp:include page="${path }/Shopping/public/Header.jsp">
    	<jsp:param value="1" name="index"/>
    </jsp:include>
    <div class="content">
    	<div class="left-content">
	    	<ul>
				<li class="first-content">
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">女人</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">透气款女鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女外搭</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">chanel手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女T恤短袖</a></li>
					</ul>
	
				</li>   
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">男人</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">七匹狼夹克</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">耳环</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">红豆内裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">冰丝内裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">嘻哈帽男</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">男帽子潮</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">数码</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">LED电视</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">TCL电视</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">CPU</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">主板CPU套装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">苹果手机</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">母婴</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">宝宝衣服</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">古装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女童套装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女童装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">外贸童装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">洋娃娃</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">家居</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">钥匙扣</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">置物架</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">鞋柜</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">简欧沙发</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">玻璃茶具</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">花盆架</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">美食</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">马卡龙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">乌梅</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">嘉兴粽子</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">零食组合</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">红烧肉</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">随便果</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">美妆</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">长发假发</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">原宿</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">化妆水</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">美甲工具</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">洗脸皂</a></li>
						<li><a href="OperatorShopCtrl?method=searchType">韩束套装</a></li>
					</ul>
				</li>
				<li>
					<a href="OperatorShopCtrl?method=searchType" class="theme" onmousemove="addInfo(event)">箱包</a>
					<ul class="outline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">车钥匙包</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">锁匙包</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">超薄卡包</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">卡包式</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女包</a></li>
						<li><a href="OperatorShopCtrl?method=searchType">零钱包</a></li>
					</ul>
				</li>
	    	</ul>
	    </div>
	    <div class="hide-content first-content">
	    	<ul>
				<li class="first-content">
					<h3>裙子</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">套装裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">OL套装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">真丝裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">真丝旗袍</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">长裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">晚礼服</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">蕾丝旗袍</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">连衣裙夏</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">空姐服</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">少女旗袍</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">a字裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">睡裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">高级套装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">旗袍裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">背带裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">长款旗袍</a></li>
					</ul>
				</li>
				<li class="first-content">
					<h3>上衣</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">T恤裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">胖MM连衣裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">薄外套</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">大码雪纺</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">防嗮服</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">棉麻</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">防嗮开衫</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">棒球服</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">针织短袖</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女装外套</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">镂空罩衫</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">蕾丝上衣</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">长袖T恤女</a></li>
					</ul>
				</li>
				<li>
					<h3>裤子</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">连体裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">九分裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">破洞裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">背带裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">七分裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">裤裙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">套装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">小脚裤</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">裙装</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女装外套</a></li>
					</ul>
					
				</li>
				<li>
					<h3>鞋子</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">休闲鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">坡跟</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">凉鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">女鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">拖鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">学生鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">粗跟凉鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">帆布鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">松糕凉鞋</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">小白鞋</a></li>
					</ul>
				</li>
				<li>
					<h3>内衣</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">运动背心</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">堡狮龙</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">夏天睡衣</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">可爱睡衣</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">睡裙</a></li>
					</ul>
				</li>
				<li>
					<h3>配饰</h3>
					<ul class="inline">
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">陶瓷手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">天王表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">复古手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">电子表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">钨钢手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">石英表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">ck手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">dw手表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">腕表</a></li>
						<li><a href="OperatorShopCtrl?method=searchType" onmousemove="addInfo(event)">时尚眼镜</a></li>
					</ul>
				</li>
			</ul>
	    </div>
	    	<img alt="" src="images/pic3.png" class="pic1">
	    	
    		<img alt="" src="images/pic1.jpg" class="pic2">
    		<img alt="" src="images/pic4.png" class="pic3">
    		<img alt="" src="images/pic2.jpg" class="pic2">
    		<img alt="" src="images/pic4.png" class="pic3">

    		
    </div>
	
  </body>
</html>
