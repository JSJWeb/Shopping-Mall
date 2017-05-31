package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;
import cn.edu.zhku.jsj.Util.DateUtil;
import cn.edu.zhku.jsj.Model.Address;

public class UserDao {
	/*
	 * 模糊查询
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<User>
	 */
	public List<User> fuzzyLoad(Map<String,Object> params,Pager pager,String order,String choose)
	{
		List<User> list=new ArrayList<User>();
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="select * from user where 1=1";
		for(String key:params.keySet())
		{
			if(!"type".equals(key))
			{
				params.put(key,"%"+params.get(key)+"%");
				sql=sql+" and "+key+" like ?";
			}
			else	
				sql=sql+" and "+key+"=?";
		}
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else sql=sql+" order by ? asc limit ?,?";

		Object []params1=params.values().toArray();
		Object param=params1;
		list=util.QueryList(User.class, sql,param,order,pager.getCurrent(),pager.getEachRecord());
		return list;
	}
	/*
	 * 该方法根据查询条件查询具体用户以及相应的地址
	 * 成功返回User,失败返回null
	 * 注：当flag为true时执行地址查询，false时不查询地址
	 */
	public User load(Map<String,Object> params,boolean flag)
	{
		User user=new User();
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="select * from user where 1=1";
		for(String key:params.keySet())
			sql=sql+" and "+key+"=?";
		Object []params1=params.values().toArray();
		
		user=(User) util.QueryOne(User.class, sql, params1);
		//flag为true时查询地址
		if(flag)
		{
			String sql1="select * from address where user_id=?";
			BaseUtil<Address> util1=new BaseUtil<Address>();
			List<Address> addresses=util1.QueryList(Address.class,sql1,user.getId());
			user.setAddresses(addresses);
		}
		return user;
	}
	/*
	 * 该方法根据查询条件，排序，排序方式分页查询用户
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<User>
	 */

	public List<User> list(Map<String,Object> params,Pager pager,String order,String choose)
	{

		List<User> list=new ArrayList<User>();
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="select * from user  where 1=1";
		for(String key:params.keySet())
			sql=sql+" and "+key+"=?";
		//PreparedStatement使String类只能作为值出现在等号后面
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else sql=sql+" order by ? asc limit ?,?";
		Object params1=params.values().toArray();
		list=util.QueryList(User.class, sql,params1,order,pager.getCurrent(),pager.getEachRecord());
		return list;
	}
	/*
	 * 该方法保存一个新用户
	 * 保存成功返回true,失败返回false
	 */

	public boolean save(User user)
	{
		boolean flag=false;
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="insert into user(username,nickname,password,tel,sex,type,birth,register) value(?,?,?,?,?,?,?,?)";
		//TODO:参数数组赋值问题
		Object params[]={user.getUsername(),user.getNickname(),user.getPassword(),user.getTel(),user.getSex(),
				user.getType(),user.getBirth(),user.getRegister()};
		flag=util.update(sql, params);
		return flag;
		
	}
	/*
	 * 该方法修改用户信息
	 * 修改成功返回true，失败返回false
	 */

	public boolean update(User user)
	{
		boolean flag=false;
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="update user set username=?,nickname=?,password=?,tel=?,"
				+ "sex=?,type=?,birth=?,register=? where id=?";
		Object params[]={user.getUsername(),user.getNickname(),user.getPassword(),user.getTel(),user.getSex(),
				user.getType(),user.getBirth(),user.getRegister(),user.getId()};
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法删除一个用户
	 * 删除成功返回true，失败返回false
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="delete from user where id=?";
		Object params[]={id};
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法计算表总记录
	 * 输入：type用户类型、map模糊查询时的条件
	 * 输出：总记录数int
	 */
	public int countUser(int type,Map<String,Object> map)
	{
		int count =0;
		BaseUtil<User> util=new BaseUtil<User>();
		String sql="select count(*) from user where type=?";
		//判断是否为模糊查询
		if(null!=map)
		{
			Object value=new Object();
			for(String key:map.keySet())
			{
				//为模糊查询拼接sql语句
				sql=sql+" and "+key+" like ?";
				value= "%"+map.get(key)+"%";
			}
			count =util.count(User.class,sql,type,value);
		}
		else
			count =util.count(User.class,sql,type);
		return count;
	}
	
	public static void main(String[] args) {
		UserDao dao=new UserDao();
		java.sql.Date date=DateUtil.getTime("2004-5-4");
		User user=new User("小智","yir","123456","12343546467","女",2,date,new java.sql.Date(new java.util.Date().getTime()));
		System.out.println(dao.save(user));
	}
}
