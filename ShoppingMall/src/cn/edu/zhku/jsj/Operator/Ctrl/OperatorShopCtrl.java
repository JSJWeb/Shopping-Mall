package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Operator.Service.ShopService;
@WebServlet(name="OperatorShopCtrl",urlPatterns="/OperatorShopCtrl")
public class OperatorShopCtrl extends HttpServlet {

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
	public void searchType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=new String(request.getParameter("type").getBytes("ISO-8859-1"),"utf-8");
		
		System.out.println(type);
		Pager pager=new Pager();
		ShopService service=new ShopService();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("gtype",type);
		map.put("gstate",0);
		pager.setTotalRecord(service.countGood(map));
		pager.setEachRecord(10);
		pager.setCurrentPage(1);
		List<Good>goods=service.search(map, pager);
		for(Good g:goods)
		{
			System.out.println(g.toString());
		}
		request.setAttribute("list",goods);
		request.getRequestDispatcher("Shopping/Manager/test.jsp").forward(request,response);
	}
	public void searchInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String introduction=new String(request.getParameter("keyword").getBytes("ISO-8859-1"),"utf-8");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("introduction",introduction);
		map.put("gstate",0);
		ShopService service=new ShopService();
		List<Good> list=new ArrayList<Good>();
		Pager pager=new Pager();
		
		pager.setTotalRecord(service.countGood(map));
		pager.setCurrentPage(1);
		pager.setEachRecord(10);
		list=service.search(map, pager);
		for(Good g:list)
		{
			System.out.println(g.toString());
		}
		request.setAttribute("list",list);
		request.getRequestDispatcher("Shopping/Manager/test.jsp").forward(request,response);
	}
	
}
