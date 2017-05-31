package cn.edu.zhku.jsj.Store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.jmx.remote.util.Service;

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.PersonalService;
import cn.edu.zhku.jsj.Store.Service.StoreService;
import cn.edu.zhku.jsj.Util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(name="StoreCenterCtrl",urlPatterns="/StoreCenterCtrl")
public class StoreCenterCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Method []methods=this.getClass().getMethods();
		String method=request.getParameter("method");
		try {
			for(Method m:methods)
			{	
				if(method.equalsIgnoreCase(m.getName()))
				{System.out.println("M:"+m.getName());
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

	/*验证用户是否存在
     * 输入：username
     * 输出：boolean,存在为true,不存在为false
	 */
	public void verifyUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		StoreService service=new StoreService();
		boolean flag=service.verify("username",username);
		result(response,flag);
	}
	
	/*验证是否存在店铺
     * 输入：nickname
     * 输出：boolean,存在为true,不存在为false
	 */
	public void verifyStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickname=request.getParameter("nickname");
		StoreService service=new StoreService();
		boolean flag=service.verify("nickname",nickname);
		result(response,flag);
	}
	
	/*根据用户名查找店铺，返回店铺信息
     * 输入：username
     * 输出：user相关信息
	 */
	public void showStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
		System.out.println("username: "+ username);
		StoreService service=new StoreService();
		User user=service.show("username",username);
		System.out.println(user.toString());
		//格式转换
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String Register=sdf.format(user.getRegister());  
        String Birth=sdf.format(user.getBirth());  	
        //前台输出
        JSONObject obj=new JSONObject();
		obj.put("id",user.getId());
		obj.put("username",user.getUsername());
		obj.put("password",user.getPassword());
		obj.put("nickname",user.getNickname());
		obj.put("tel",user.getTel());
		obj.put("register",Register);
		obj.put("birth",Birth);
		obj.put("type",user.getType());
			
//		System.out.println("username" + user.getUsername());
//		System.out.println("nickname" + user.getNickname());
//		System.out.println("tel" + user.getTel());
//		System.out.println("register" + Register);

		PrintWriter out=response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
		
	/*保存用户
     * 输入：username相关信息
     *  输出：boolean,成功为true,失败为false
	 */
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
		String nickname = new String(request.getParameter("nickname").getBytes("ISO-8859-1"), "utf-8");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		//格式转化
		int type=Integer.parseInt(request.getParameter("type"));
		//打包user
		User user=new User(username,nickname,password,tel,null,type,new java.sql.Date(new java.util.Date().getTime()),new java.sql.Date(new java.util.Date().getTime()));
		System.out.println(user.toString());
		
		StoreService service=new StoreService();
		boolean flag=service.save(user);
		result(response,flag);
	}
	
	/*检查密码是否正确
     * 输入：username
     *  输出：boolean,成功为true,失败为false
	 */
	public void verifyPsw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password=request.getParameter("password");
		StoreService service= new StoreService();
		
		System.out.println("username"+username);
		System.out.println("password"+password);
		
		User user= new User();
		user = service.show("username",username);
		boolean flag=false;
		if(user.getPassword().equals(password))
			flag=true;
		result(response,flag);
	}
	
	/*修改密码
     * 输入：password
     * 输出：boolean,成功为true,失败为false
	 */
	public void updatePsw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password=request.getParameter("password");
		StoreService service=new StoreService();
		User user=(User)request.getSession().getAttribute("user");
		user.setPassword(password);
		boolean flag=service.update(user);
		result(response,flag);
	}
	
	/*修改店铺信息
     * 输入：user相关信息
     * 输出：boolean,成功为true,失败为false
	 */
	public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		//获取参数
		int id=Integer.parseInt(request.getParameter("id"));
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
		String nickname = new String(request.getParameter("nickname").getBytes("ISO-8859-1"), "utf-8");
		String password=request.getParameter("password");
		String tel=request.getParameter("tel");
		String birth=request.getParameter("birth"); 
		int type=Integer.parseInt(request.getParameter("type"));
		//格式转换
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date Birth=sdf.parse(birth);   
		java.sql.Date sqlB= new java.sql.Date(Birth.getTime());
		//打包user
		User user=new User(username,nickname,password,tel,null,type,sqlB,sqlB); //register=birth
		user.setId(id);
		System.out.println(user.toString());
		
		StoreService service=new StoreService();
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
