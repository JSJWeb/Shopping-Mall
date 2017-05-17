package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;
import cn.edu.zhku.jsj.Model.Address;

public class UserDao {
	/*
	 * 该方法根据查询条件查询具体用户
	 * 成功返回User,失败返回null
	 */

	public User load(String key,Object value)
	{
		User user=new User();
		BaseUtil<User> dao=new BaseUtil<User>();
		String sql="select * from user where " +key+"=?";
		System.out.println("load:"+sql);
		Object params[]={value};
		user=(User) dao.QueryOne(User.class, sql, params);
		String sql1="select * from address where user_id=?";
		BaseUtil<Address> dao1=new BaseUtil<Address>();
		List<Address> addresses=dao1.QueryList(Address.class,sql1,user.getId());
		user.setAddresses(addresses);
		return user;
	}
	/*
	 * 该方法根据查询条件，排序，排序方式分页查询用户
	 * 成功返回list,失败返回null
	 * 参数Pager 需提供当前页数和每页记录数
	 */

	public List<User> list(Map<String,Object> params,Pager pager,String order,String choose)
	{
		if(pager.getTotalRecord()==0)
		{
			
			pager.setTotalRecord(countUser());
			System.out.println("查询数据库："+pager.getTotalRecord());
		}
		
		List<User> list=new ArrayList<User>();
		BaseUtil<User> dao=new BaseUtil<User>();
		String sql="select * from user  where 1=1";
		for(String key:params.keySet())
			sql=sql+" and "+key+"=?";
		sql=sql+" order by ? ? limit ?,?";
		Object []params1=params.values().toArray();
		System.out.println("list:"+sql);
		list=dao.QueryList(User.class, sql,params1[0],order,choose,pager.getCurrent(),pager.getEachRecord());
		return list;
	}
	/*
	 * 该方法保存一个新用户
	 * 保存成功返回true,失败返回false
	 */

	public boolean save(User user)
	{
		boolean flag=false;
		BaseUtil<User> dao=new BaseUtil<User>();
		String sql="insert into user(username,nickname,password,tel,sex,type,birth,register) value(?,?,?,?,?,?,?,?)";
		//TODO:参数数组赋值问题
		Object params[]={user.getUsername(),user.getNickname(),user.getPassword(),user.getTel(),user.getSex(),
				user.getType(),user.getBirth(),user.getRegister()};
		flag=dao.update(sql, params);
		return flag;
		
	}
	/*
	 * 该方法修改用户信息
	 * 修改成功返回true，失败返回false
	 */

	public boolean update(User user)
	{
		boolean flag=false;
		BaseUtil<User> dao=new BaseUtil<User>();
		String sql="update user set username=?,nickname=?,password=?,tel=?,"
				+ "sex=?,type=?,birth=?,register=? where id=?";
		Object params[]={user.getUsername(),user.getNickname(),user.getPassword(),user.getTel(),user.getSex(),
				user.getType(),user.getBirth(),user.getRegister(),user.getId()};
		flag=dao.update(sql, params);
		return flag;
	}
	/*
	 * 该方法删除一个用户
	 * 删除成功返回true，失败返回false
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		BaseUtil<User> dao=new BaseUtil<User>();
		String sql="delete from user where id=?";
		Object params[]={id};
		flag=dao.update(sql, params);
		return flag;
	}
	/*
	 * 该方法计算表总记录
	 */
	public int countUser()
	{
		int count =0;
		BaseUtil dao=new BaseUtil();
		count =dao.count(User.class);
		return count;
	}
	public static void main(String[] args) {
		
		
		UserDao dao=new UserDao();
		User user=dao.load("id",1);
		System.out.println(user.toString());
		for(Address a:user.getAddresses())
		{
			System.out.println(a.toString());
		}

//		User user=new User("���","likui","123","1234-4567788","��",0,
//		new java.sql.Date(19960606),new java.sql.Date(new java.util.Date().getTime()));
//		user.setId(3);
//		boolean flag=dao.update(user);
//		System.out.println(flag);
//		
//		User u=new User("hello","world","345","123457677","f",1,new java.sql.Date(0),new java.sql.Date(new java.util.Date().getTime()));
//		boolean flag=dao.save(u);
//		System.out.println(flag);
//		Map<String,Object>params=new HashMap();
//		params.put("username","宋江");
//		String order="nickname";
//		String choose="desc";
//		Pager pager=new Pager();
//		pager.setCurrentPage(1);
//		pager.setEachRecord(2);
//		List<User> list=dao.list(params,pager,order, choose);
//		for(User u:list)
//		{
//			System.out.println(u.toString());
//		}
//		User u=dao.load(1);
//		System.out.println(u.toString());
	}
}
