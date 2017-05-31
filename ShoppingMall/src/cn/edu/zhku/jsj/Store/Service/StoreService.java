package cn.edu.zhku.jsj.Store.Service;


import java.util.HashMap;
import java.util.Map;
import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.User;

public class StoreService {
	/*
	 * 该方法验证某个条件是否满足
	 * 满足返回true，不满足返回false
	 */
	public boolean verify(String key,String value)
	{
		boolean flag=true;
		UserDao dao=new UserDao();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put(key,value);
		User user=dao.load(params, false);
		if(null==user||user.equals(""))
			flag=false;
		return flag;
	}		
	/*
	 * 该方法验证某个条件是否满足
	 * 返回User
	 */
	public User show(String key,String value)
	{
		UserDao dao=new UserDao();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put(key,value);
		User user=dao.load(params, false);
		return user;
	}		
	/*
	 * 该方法提供修改用户方式
	 * 输入：User
	 * 输出：boolean,true为修改成功，false为修改失败
	 */
	public boolean update(User user)
	{   
		UserDao dao=new UserDao();
		boolean flag=dao.update(user);
		return flag;
	}
	/*
	 * 该方法保存新用户
	 * 成功返回true，失败返回false
	 */
	public boolean save(User user)
	{
		UserDao dao=new UserDao();
		boolean flag=dao.save(user);
		return flag;
	}	
}

