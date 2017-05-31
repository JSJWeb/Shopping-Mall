package cn.edu.zhku.jsj.Operator.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;

public class UserService {
	/*
	 * 该方法提供查询所有用户
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<User>
	 * 注：Pager的当前记录数是通过计算得到的，在提供参数时需提供的Pager值为总记录数totalRecord和每页记录数eachRecord
	 *   
	 */
	public List<User> list(Map<String,Object> params,Pager pager,String order,String choose)
	{
		List<User> list=new ArrayList<User>();
		UserDao dao=new UserDao();
		list=dao.list(params, pager, order, choose);
		return list;
	}
	/*
	 * 该方法提供模糊查询用户方式
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<User>
	 */
	public List<User> load(Map<String,Object>params,Pager pager,String order,String choose)
	{
		List<User> list=new ArrayList<User>();
		UserDao dao=new UserDao();
		
		list=dao.fuzzyLoad(params,pager,order,choose);
		return list;
	}
	/*
	 * 该方法计算需查询用户的总记录
	 * 输入：用户类型type,查询条件params
	 * 输出：总记录数
	 * 注：如果查询方式不是模糊查询，查询条件params必须为null值，反之为模糊查询的条件
	 */
	public int countCustomerOrStore(int type,Map<String,Object> params)
	{
		Pager pager=new Pager();
		UserDao dao=new UserDao();
		pager.setTotalRecord(dao.countUser(type,params));
		return pager.getTotalRecord();
	}
	/*
	 * 该方法具体查询某个用户
	 * 输入：某个用户的id
	 * 输出：User类型
	 */
	public User get(int id)
	{
		User user=new User();
		UserDao dao=new UserDao();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("id",id);
		user=dao.load(map,true);
		return user;
	}
	/*
	 * 该方法删除某个具体用户
	 * 输入：某个用户的id值
	 * 输出：User类型
	 */
	public boolean delete(int id)
	{
		UserDao dao=new UserDao();
		boolean flag=dao.delete(id);
		return flag;
	}
}
