package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.LoginService;
import net.sf.json.JSONObject;


@WebServlet(name="LoginCtrl",urlPatterns="/LoginCtrl")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		verify(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8");
		String password=new String(request.getParameter("password").getBytes("ISO-8859-1"),"utf-8");
		
		//参数设置
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("username",username);
		params.put("password",password);
		LoginService service=new LoginService();
		User user=new User();
		user=service.get(params);
		JSONObject obj=new JSONObject();
		if(null==user||user.equals(""))
		{
			
			obj.put("flag","flase");
			
		}
		else
		{
			obj.put("flag","true");
			HttpSession session=request.getSession();
			session.setAttribute("user",user);
		}
		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
}
