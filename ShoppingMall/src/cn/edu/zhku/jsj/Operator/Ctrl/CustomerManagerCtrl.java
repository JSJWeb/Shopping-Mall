package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
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

import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@WebServlet(name="CustomerManagerCtrl",urlPatterns="/Operator/CustomerManagerCtrl")
@SuppressWarnings("serial")
public class CustomerManagerCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/*
	 * 该方法根据提交的条件查询用户列表
	 */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Pager pager=new Pager();
		UserService service=new UserService();
		Map<String,Object>params=new HashMap<String,Object>();
		//获取参数
		String username=request.getParameter("username");
		String order=request.getParameter("order");
		String choose=request.getParameter("choose");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		int eachRecord=Integer.parseInt(request.getParameter("eachRecord"));
		int totalRecord=Integer.parseInt(request.getParameter("totalRecord"));
		if(totalRecord!=0)
			//totalPage不为0则存进pager
			pager.setTotalRecord(totalRecord);
		//查询用户权限为0的顾客数量
		else if(null==username||username.equals(""))
				pager.setTotalRecord(service.countCustomerOrStore(0,null));
		else
		{
			params.put("username",username);
			pager.setTotalRecord(service.countCustomerOrStore(0,params));
		}

		//参数设置
		
		params.put("type",0);//用户权限0
		
		pager.setCurrentPage(currentPage);
		pager.setEachRecord(eachRecord);
		List<User> list=new ArrayList<User>();
		if(null==username||username.equals(""))
			list=service.list(params, pager, order, choose);
		else
		{
			System.out.println("username:"+username);
			params.put("username",username);
			list=service.load(params,pager,order,choose);
		}
		process(request,response,list,pager);
		
		
	}

	//查看具体用户信息，相应的地址也会被获取
	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		UserService service=new UserService();
		User user=service.get(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("Operator/Manager/SingleUser.jsp").forward(request,response);
	}
	//删除具体用户，相应的地址也会被删除
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		UserService service=new UserService();
		boolean flag=service.delete(id);
		JSONObject obj=new JSONObject();
		obj.put("flag",flag);
		PrintWriter out=response.getWriter();
		out.println(obj);
        out.flush();
        out.close();
	}
	public void process(HttpServletRequest request, HttpServletResponse response,List<User> list,Pager pager) throws ServletException, IOException{
		JSONObject resultJson = new JSONObject();// 创建最后结果的json
        JSONArray jsonArray = new JSONArray();// json数组
        JSONObject o1=new JSONObject();
        if(pager.getTotalPage()==0)
        {
        	o1.put("msg","搜索不到用户");
        	jsonArray.add(o1);
        }
        else
        {
        	//总记录数
            o1.put("totalRecord",pager.getTotalRecord());
            JSONObject o2=new JSONObject();
            o2.put("totalPage",pager.getTotalPage());
            jsonArray.add(o1);
            jsonArray.add(o2);
        	 for(User u:list)
             {
             	JSONObject obj=new JSONObject();
             	obj.put("username", u.getUsername());
             	obj.put("id", u.getId());
             	obj.put("sex",u.getSex());
             	obj.put("type",u.getType());
             	jsonArray.add(obj);
             }
        }
        
         resultJson.put("users",jsonArray);
       
		PrintWriter out=response.getWriter();
		out.println(resultJson);
        out.flush();
        out.close();
	}
}
