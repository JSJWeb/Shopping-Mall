package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Operator.Service.CartService;
import net.sf.json.JSONObject;
@WebServlet(name="OperatorCartCtrl",urlPatterns="/OperatorCartCtrl")
public class OperatorCartCtrl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Method []methods=this.getClass().getMethods();
		String method=request.getParameter("method");
		try {
			for(Method m:methods)
			{
				if(m.getName().equalsIgnoreCase(method))
					m.invoke(this,request,response);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}
	public void addGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println("id:"+id);
		CartService service=new CartService();
		Good good=new Good();
		good=service.get(id);
		
		OrderItem item=new OrderItem(num,good);
		
		HttpSession session=request.getSession();
		List<OrderItem> items=new ArrayList<OrderItem>();
		if(null!=session.getAttribute("items"))
			items=(List<OrderItem>)session.getAttribute("items");
		items.add(item);
		for(OrderItem i:items)
		{
			System.out.println(i.getGood().getIntroduction());
		}
		session.setAttribute("items",items);
		response.sendRedirect(request.getContextPath()+"/Shopping/Manager/ShoppingCart.jsp");
	}
	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index=Integer.parseInt(request.getParameter("index"));
		HttpSession session=request.getSession();
		List<OrderItem> items=(List<OrderItem>) session.getAttribute("items");
		items.remove(index);
		session.setAttribute("items",items);
		JSONObject obj=new JSONObject();
		obj.put("flag",true);
		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
}
