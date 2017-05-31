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

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Operator.Service.ReportService;
import net.sf.json.JSONObject;
@SuppressWarnings("serial")
@WebServlet(name="OperatorReportCtrl",urlPatterns="/Operator/OperatorReportCtrl")
public class OperatorReportCtrl extends HttpServlet {
	
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
	public void reportGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url=request.getParameter("url");
		url=url.substring(url.indexOf("id="));
		System.out.println(url);
		int id=Integer.parseInt(url);
		ReportService service=new ReportService();
		//改变商品状态为被举报状态1
		boolean flag=service.updateGoodState(id,1);
		JSONObject obj=new JSONObject();
		obj.put("flag",flag);
		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
	public void handleReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		Pager pager=new Pager();
		ReportService service=new ReportService();
		pager.setTotalRecord(service.countReport(1));
		System.out.println("totalRecord:"+pager.getTotalRecord());
		pager.setCurrentPage(currentPage);
		pager.setEachRecord(10);
		List<Good> list=new ArrayList<Good>();
		list=service.getReportGood(1, pager);
		request.setAttribute("goods",list);
		request.getRequestDispatcher("/Operator/Manager/Report.jsp").forward(request,response);
	}
	public void handleGood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println("id:"+id);
		ReportService service=new ReportService();
		boolean flag=service.updateGoodState(id,2);
		JSONObject obj=new JSONObject();
		obj.put("flag",flag);
		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
}
