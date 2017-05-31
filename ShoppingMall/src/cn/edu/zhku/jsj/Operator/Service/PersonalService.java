package cn.edu.zhku.jsj.Operator.Service;

import java.util.HashMap;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.User;

public class PersonalService {
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
	 * 该方法保存新用户
	 * 成功返回true，失败返回false
	 */
	public boolean save(User user)
	{
		UserDao dao=new UserDao();
		boolean flag=dao.save(user);
		return flag;
		
	}
	public boolean update(User user)
	{
		UserDao dao=new UserDao();
		boolean flag=dao.update(user);
		return flag;
	}
}
