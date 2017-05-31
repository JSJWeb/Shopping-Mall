package cn.edu.zhku.jsj.Store.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Operator.Service.ShopService;
import cn.edu.zhku.jsj.Store.Service.GoodService;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

@WebServlet(name = "GoodCtrl", urlPatterns = "/GoodCtrl")
@SuppressWarnings("serial")
public class GoodCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoodCtrl() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Method[] methods = this.getClass().getMethods();
		String method = request.getParameter("method");
		System.out.println(1);
		try {
			for (Method m : methods) {
				if (m.getName().equalsIgnoreCase(method))
					m.invoke(this, request, response);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}
	
    /*添加商品
     * 输入：user_id、goodname、introduction、gprice、gtotalNum、gtype、gstate、gpic
     * 输出：返回商品管理页面
	*/
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SmartUpload mySmartUpload=new SmartUpload(); 
		int count=0;    //上传文件数量
		boolean flag = false;		
		
		try{
		mySmartUpload.initialize(this.getServletConfig(),request,response);
		//限制每个文件的最大长度
		mySmartUpload.setMaxFileSize(50*1024*1024);
		//设定允许上传的文件格式jpg
		mySmartUpload.setAllowedFilesList("jpg");
		mySmartUpload.upload();
		//获得上传的文件
		File myfile=mySmartUpload.getFiles().getFile(0);		
		//获得上传文件的名字
		String filename = myfile.getFileName();
		session.setAttribute("Filename", filename);		
		//保存文件的目录
		count=mySmartUpload.save("/goodpic");
		String url = request.getContextPath()+"/WebRoot/goodpic";	
		//获得文件描述的信息
		Request re=mySmartUpload.getRequest();
		String goodpic= filename;  //保存文件名(包括后缀)到数据库
		
		//获取数据		
		String userid = re.getParameter("user_id");
		String goodintroduction = re.getParameter("introduction");
		String goodname = re.getParameter("goodname");
		String goodprice = re.getParameter("gprice");
		String goodtotalnum = re.getParameter("gtotalNum");
		String goodtype = re.getParameter("gtype");
		String goodstate = re.getParameter("gstate");
		
//		System.out.println("userid" + re.getParameter("user_id"));
//		System.out.println("goodintroduction" + re.getParameter("introduction"));
//		System.out.println("goodname" + re.getParameter("goodname"));
//		System.out.println("goodprice" + re.getParameter("gprice"));
//		System.out.println("goodtotalnum" + re.getParameter("gtotalNum"));
//		System.out.println("goodtype" + re.getParameter("gtype"));
//		System.out.println("goodstate" + re.getParameter("gstate"));

		// 类型转换
		int user_id = Integer.parseInt(userid);
		double gprice = Double.valueOf(goodprice).doubleValue();
		int gtotalnum = Integer.parseInt(goodtotalnum);
		int gremainnum = gtotalnum;
		int gstate = 2;
		if(goodstate.equals("上架"))
		    gstate = 1;
		
		// good打包
		Good good = new Good(goodname, gprice, gtotalnum, gremainnum, goodpic,goodintroduction, goodtype, gstate);

		System.out.println(good.toString());

		try{
		   GoodService service = new GoodService();
		   flag = service.save(good,user_id);
		}catch(Exception e){
			System.out.println("文件上传出错1");
		e.printStackTrace();
	    } 
	}catch(Exception e){
		System.out.println("文件上传出错2");
	 e.printStackTrace();
	}finally{
		if(flag==true)
		  System.out.println("成功");		
		request.getRequestDispatcher("Store/Manager/GoodManager.jsp").forward(request, response);
	}	
}

	/*该方法根据state或introduction分页查询商品列表（搜索页面的显示）
	 * 输入：gtype、introduction、order、choose、currentPage、eachRecord、totalRecord
	 * 输出：introduction、gpic
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Pager pager = new Pager();
		GoodService service = new GoodService();
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取参数
		String gtype = request.getParameter("gtype");
		String introduction = request.getParameter("introduction");
		String order = request.getParameter("order");
		String choose = request.getParameter("choose");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int eachRecord = Integer.parseInt(request.getParameter("eachRecord"));
		int totalRecord = Integer.parseInt(request.getParameter("totalRecord"));

		if (totalRecord != 0)
			// totalPage不为0则存进pager
			pager.setTotalRecord(totalRecord);
		else
			pager.setTotalRecord(service.countGood(params));

		// 参数设置
		pager.setCurrentPage(currentPage);
		pager.setEachRecord(eachRecord);

		List<Good> list = new ArrayList<Good>();
		if (null == gtype || gtype.equals("")) {
			if (null == introduction || introduction.equals("")) { //默认根据类型查找
				params.put("gtype", gtype);
				list = service.query(params, pager, order, choose);
			} else if (null != introduction || introduction.length() != 0) { //根据介绍查找
				params.put("introduction", introduction);
				list = service.Fuzzyquery(params, pager, order, choose);
			}
		} else {  //根据介绍查找
			params.put("gtype", gtype);
			list = service.query(params, pager, order, choose);
		}
		process(request, response, list, pager);
	}

	/* 该方法根据userid查询商品列表
	 * 输入：userid、flag(flag=1,返回订单管理页面，否则返回数据统计页面)
	 * 输出：good
	 */
	public void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String user_id = request.getParameter("userid");
		int totalRecord = Integer.parseInt(request.getParameter("totalRecord"));
		
		System.out.println("userid"+user_id);
		System.out.println("totalRecord"+totalRecord);
		
		Pager pager = new Pager();
		GoodService service = new GoodService();
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取参数
		
		String order = "id";
		String choose = "asc";
		int currentPage = 1;
		int eachRecord = 100;

		if (totalRecord != 0)
			// totalPage不为0则存进pager
			pager.setTotalRecord(totalRecord);
		else
			pager.setTotalRecord(service.countGood(params));
		
		// 参数设置
		pager.setCurrentPage(currentPage);
		pager.setEachRecord(eachRecord);
		params.put("user_id", user_id);
		
		List<Good> good = new ArrayList<Good>();
		good = service.query(params, pager, order, choose);
		for (Good g : good) {
			System.out.println(g.toString());
		}
		request.setAttribute("good", good);
			request.getRequestDispatcher("Store/Manager/DataStatistics.jsp").forward(request, response);
		
	}

	/* 分页查询
	 * 该方法根据userid查询商品列表
	 * 输入：userid、currentpage(获取当前页面)
	 * 输出：List<Good>
	 */
	public void searchInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String user_id = new String(request.getParameter("userid").getBytes("ISO-8859-1"), "utf-8");
		String currentpage = request.getParameter("currentpage");
		
		System.out.println("user_id"+user_id);
		System.out.println("currentpage"+currentpage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		GoodService service = new GoodService();
		List<Good> list = new ArrayList<Good>();
		Pager pager = new Pager();
		pager.setEachRecord(10);
		pager.setTotalRecord(service.countGood(map));

		if (currentpage == null || Integer.parseInt(currentpage) <= 1) {
			pager.setCurrentPage(1);
		} else {
			pager.setCurrentPage(Integer.parseInt(currentpage));
		}

		list = service.search(map, pager);
		for (Good g : list) {
			System.out.println(g.toString());
		}
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("Store/Manager/GoodManager.jsp").forward(request, response);
	}

	
	/* 该方法根据goodid查询商品列表
	 * 输入：goodid
	 * 输出：good的信息
	 */
	public void load(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String id = new String(request.getParameter("goodid").getBytes("ISO-8859-1"), "utf-8");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		GoodService service = new GoodService();
		Good good = new Good();
		boolean flag = false;

		good = service.load(map, flag);
		System.out.println(good.toString());
		String gstate=null;
		if(good.getGstate()==0)
		  gstate="上架";
		else if(good.getGstate()==1)
		  gstate="被举报";
		else if(good.getGstate()==2)
		  gstate="下架";
		
		JSONObject obj = new JSONObject();
		// 前台输出
		obj.put("goodid", good.getId());
		obj.put("goodname", good.getGname());
		obj.put("gprice", good.getGprice());
		obj.put("gtotalNum", good.getGtotalNum());
		obj.put("gremainNum", good.getGremainNum());
		obj.put("gpic", good.getGpic());
		obj.put("introduction", good.getIntroduction());
		obj.put("gtype", good.getGtype());
		if(gstate!=null)
		  obj.put("gstate", gstate);

		PrintWriter out = response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}

	   /* 该方法根据goodname查询某个商品是否存在,
	    * 输入：goodname
	    * 输出：boolean,存在返回true,不存在返回false
	   */
		public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			String gname = request.getParameter("goodname");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gname", gname);

			GoodService service = new GoodService();
			Good good = new Good();
			boolean flag1 = false;  //不查询店铺
			boolean flag = true;
			good = service.load(map,flag1);
			if(null==good||good.equals(""))
				flag=false;
			System.out.println("flag" + flag);
			JSONObject obj = new JSONObject();
			// 前台输出
			obj.put("flag", flag);
			PrintWriter out = response.getWriter();
			out.println(obj);
			out.flush();
			out.close();
		}

   /* 该方法修改商品信息
	* 输入：good相关信息
	* 输出：boolean,存在返回true,不存在返回false
    */
	public void alter(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ParseException {
		// 获取数据
		String goodintroduction = new String(request.getParameter("introduction").getBytes("ISO-8859-1"), "utf-8");
		String goodname = new String(request.getParameter("gname").getBytes("ISO-8859-1"), "utf-8");
		String goodtype = new String(request.getParameter("gtype").getBytes("ISO-8859-1"), "utf-8");
		String goodid = request.getParameter("id");
		String goodpic = request.getParameter("gpic");
		String goodprice = request.getParameter("gprice");
		String goodremainnum = request.getParameter("gremainnum");
		String goodtotalnum = request.getParameter("gtotalnum");
		String goodstate = request.getParameter("gstate");

		// 类型转换
		int id = Integer.parseInt(goodid);
		double gprice = Double.valueOf(goodprice).doubleValue();
		int gtotalnum = Integer.parseInt(goodtotalnum);
		int gremainnum = Integer.parseInt(goodremainnum);
		int gstate = Integer.parseInt(goodstate);
		
		// good打包
		Good good = new Good(goodname, gprice, gtotalnum, gremainnum, goodpic,goodintroduction, goodtype, gstate);
		good.setId(id);

		System.out.println(good.toString());

		GoodService service = new GoodService();
		boolean flag = service.update(good);
		result(response, flag);
	}

	 /* 该方法删除商品
		* 输入：goodid
		* 输出：返回商品管理页面
	    */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

		// 获取数据
		String goodid = request.getParameter("goodid");
		System.out.println("goodid:"+goodid);
		// 类型转换
		int id = Integer.parseInt(goodid);

		GoodService service = new GoodService();
		boolean flag = service.delete(id);
		if (flag)
			System.out.print("删除成功");
		request.getRequestDispatcher("Store/Manager/GoodManager.jsp").forward(request, response);
	}

	public void process(HttpServletRequest request,HttpServletResponse response, List<Good> list, Pager pager)throws ServletException, IOException {
		JSONObject resultJson = new JSONObject();// 创建最后结果的json
		JSONArray jsonArray = new JSONArray();// json数组
		JSONObject o1 = new JSONObject();
		if (pager.getTotalPage() == 0) {
			o1.put("msg", "搜索不到此商品");
			jsonArray.add(o1);
		} else {
			// 总记录数
			o1.put("totalRecord", pager.getTotalRecord());
			JSONObject o2 = new JSONObject();
			o2.put("totalPage", pager.getTotalPage());
			jsonArray.add(o1);
			jsonArray.add(o2);
			for (Good u : list) {
				JSONObject obj = new JSONObject();
				obj.put("gpic", u.getGpic());
				obj.put("introduction", u.getIntroduction());
				jsonArray.add(obj);
			}
		}

		resultJson.put("users", jsonArray);
		PrintWriter out = response.getWriter();
		out.println(resultJson);
		out.flush();
		out.close();
	}

	public void result(HttpServletResponse response, Object result)throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		obj.put("flag", result);
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.flush();
		out.close();
	}
}
