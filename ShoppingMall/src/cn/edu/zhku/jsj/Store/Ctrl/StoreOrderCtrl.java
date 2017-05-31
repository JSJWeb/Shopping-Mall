package cn.edu.zhku.jsj.Store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.PersonalService;
import cn.edu.zhku.jsj.Store.Service.GoodService;
import cn.edu.zhku.jsj.Store.Service.StoreOrderService;
import cn.edu.zhku.jsj.Store.Service.StoreService;
import cn.edu.zhku.jsj.Util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name = "StoreOrderCtrl", urlPatterns = "/StoreOrderCtrl")
public class StoreOrderCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoreOrderCtrl() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Method[] methods = this.getClass().getMethods();
		String method = request.getParameter("method");
		try {
			for (Method m : methods) {
				if (method.equalsIgnoreCase(m.getName())) {
					System.out.println("M:" + m.getName());
					m.invoke(this, request, response);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	/* 该方法根据userid查询订单详情列表
	 * 输入：userid
	 * 输出：List<OrderItem>
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String user_id = request.getParameter("userid");
		StoreOrderService service = new StoreOrderService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		List<OrderItem> otderitem = new ArrayList<OrderItem>();
		otderitem = service.selete(params);
		Good good=new Good();
		for (OrderItem g : otderitem) {
//			int good_id = g.getId();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("good_id", good_id);
//			List<Good> good = new ArrayList<Good>();
//			good = service.load(map);
			
		    good.setId(2);
		    good.setGname("鞋子2");
			g.setGood(good);
			System.out.println(g.toString());
		}
		request.setAttribute("otderitem", otderitem);	
			request.getRequestDispatcher("Store/Manager/Order.jsp").forward(request, response);
	}

	/*根据goodid查找订单
     * 输入：goodid
     * 输出：order相关信息
	 */
	public void query(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String good_id = request.getParameter("goodid");
		
		//根据good_id查询order_item
		StoreOrderService service = new StoreOrderService();
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("good_id",good_id); 
		OrderItem Item = service.queryitem(params);
		System.out.println(Item.toString());		
		
		//根据order_item查询order(包括用户（买家)和地址)
		Map<String,Object>map=new HashMap<String,Object>();
		String id = Integer.toString(Item.getId());
        boolean flag1=true; //用户（买家）
        boolean flag2=true; //地址
        boolean flag3=false;
		map.put("id",id); 
		Order order = service.queryorder(map,flag1,flag2,flag3);		
		System.out.println(order.toString());		
	  
		//根据good_id查询good
        boolean flag=false; //不用查询店铺
        GoodService gservice = new GoodService();
    	Map<String,Object>par = new HashMap<String,Object>();
    	par.put("id",good_id); 
		Good good = gservice.load(par,flag);		
	    System.out.println(good.toString());	
	    //格式转换
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	    String Cdate=sdf.format(order.getCreateDate());  
	    int ostate = order.getOstate();
	    System.out.println("ostate"+ostate);
	    String ost =null;
	    if(ostate==0)
	    	ost="未付款";
	    else if(ostate==1)
	    	ost="未发货";
	    else if(ostate==2)
	    	ost="已发货";
	    else if(ostate==3)
	    	ost="已收货";
	    
	    //前台输出
	    JSONObject obj = new JSONObject();
	    obj.put("goodname",good.getGname());
		obj.put("num",Item.getNum());
		obj.put("username",order.getUser().getUsername());
		obj.put("receiver", order.getAddress().getReceiver());
		obj.put("receivertel", order.getAddress().getReceivertel());
		obj.put("location", order.getAddress().getLocation());
		obj.put("order_state",ost);
		obj.put("order_createdate",Cdate);
		//前台需要使用的数据
		obj.put("order_id",order.getId());
		obj.put("order_userid",order.getUser().getId());
		obj.put("order_addressid",order.getAddress().getId());
 
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}


	/*修改订单信息(只能修改order_state)
     * 输入：order相关信息
     * 输出：boolean,成功返回true，失败返回false
	 */
	public void alter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ParseException {
		//获取数据
		
		String order_id = request.getParameter("order_id");
		String order_state = request.getParameter("order_state");
		String order_addressid = request.getParameter("order_addressid");
		String order_userid = request.getParameter("order_userid");
		String order_createdate = request.getParameter("order_createdate");
		
		System.out.println("order_state"+order_state);
		
		//类型转换
		int id = Integer.parseInt(order_id);
		int state = Integer.parseInt(order_state);
		int userid = Integer.parseInt(order_userid);
		int addressid = Integer.parseInt(order_addressid);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date createdate=sdf.parse(order_createdate);   
		java.sql.Date sqlC= new java.sql.Date(createdate.getTime());
		//order打包
		User user = new User(); 
		Address address = new Address();
		user.setId(userid);
		address.setId(addressid);
		Order order = new Order(id,sqlC,state);
		order.setUser(user);
		order.setAddress(address);
		System.out.println(order.toString());
		
		StoreOrderService service = new StoreOrderService();
		boolean flag = service.update(order);
		result(response, flag);
	}

	public void result(HttpServletResponse response, Object result)
			throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		obj.put("flag", result);
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}

}
