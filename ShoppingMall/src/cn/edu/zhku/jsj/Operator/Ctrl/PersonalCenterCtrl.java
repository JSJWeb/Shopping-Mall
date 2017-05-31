package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.PersonalService;
import cn.edu.zhku.jsj.Util.DateUtil;
import net.sf.json.JSONObject;

@WebServlet(name="PersonalCenterCtrl",urlPatterns="/Operator/PersonalCenterCtrl")
public class PersonalCenterCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Method []methods=PersonalCenterCtrl.class.getMethods();
		String method=request.getParameter("method");
		try {
			for(Method m:methods)
			{
				if(method.equalsIgnoreCase(m.getName()))
				{
					m.invoke(this,request,response);
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//验证是否存在用户
	public void verifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		PersonalService service=new PersonalService();
		boolean flag=service.verify("username",username);
		result(response,flag);
	}
	//保存用户
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String nickname=request.getParameter("nickname");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex");
		String birth=request.getParameter("birth");
		int type=Integer.parseInt(request.getParameter("type"));
		System.out.println("sex:"+sex);
		java.sql.Date date=DateUtil.getTime(birth);
		User user=new User(username,nickname,password,tel,sex,type,date,new java.sql.Date(new java.util.Date().getTime()));
		System.out.println(user.toString());
		PersonalService service=new PersonalService();
		boolean flag=service.save(user);
		result(response,flag);
	}
	//检查密码是否正确
	public void verifyPsw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("password");
		PersonalService service=new PersonalService();
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		boolean flag=false;
		if(user.getPassword().equals(password))
			flag=true;
		result(response,flag);
	}
	//修改密码
	public void updatePsw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("password");
		PersonalService service=new PersonalService();
		User user=(User)request.getSession().getAttribute("user");
		boolean flag=service.update(user);
		result(response,flag);
		
	}
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String nickname=request.getParameter("nickname");
		String tel=request.getParameter("tel");
		String sex=request.getParameter("sex");
		String date=request.getParameter("birth");
		java.sql.Date birth=DateUtil.getTime(date);
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		user.setUsername(username);
		user.setBirth(birth);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setTel(tel);
		user.setSex(sex);
		PersonalService service=new PersonalService();
		boolean flag=service.update(user);
		result(response,flag);
	}
	
	public void result(HttpServletResponse response,Object result)throws ServletException, IOException 
	{
		JSONObject obj=new JSONObject();
		obj.put("flag",result);
		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}

}
