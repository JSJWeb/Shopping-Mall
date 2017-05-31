package cn.edu.zhku.jsj.Operator.Service;

import java.util.Map;

import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.User;

public class LoginService {
	public User get(Map<String,Object> params)
	{
		User user=new User();
		UserDao dao=new UserDao();
		user=dao.load(params,false);
		return user;
	}
}
