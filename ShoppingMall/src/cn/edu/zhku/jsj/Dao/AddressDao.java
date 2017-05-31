package cn.edu.zhku.jsj.Dao;


import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;

public class AddressDao {
	/*
	 * 该方法用于保存具体用户的地址
	 * 保存成功返回true，失败返回false
	 * 输入：address对象和顾客id
	 * 
	 */
	public boolean save(Address address,int user_id)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address> ();
		String sql="insert into address(location,receiver,receivertel,user_id)"
				+ "value(?,?,?,?)";
		Object params[]={address.getLocation(),address.getReceiver(),address.getReceivertel(),user_id};
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于删除某个地址
	 * 成功返回true,失败返回false
	 * 输入：地址id
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address>();
		String sql="delete from address where id=?";
		Object[] params={id};
		flag=util.update(sql,params);
		return flag;
		
	}
	/*
	 * 该方法用于修改某个地址信息
	 * 成功返回true，失败返回false
	 * 输入：address对象
	 */
	public boolean update(Address address)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address> ();
		String sql="update address set location=?,receiver=?,receivertel=? where id=?";
		Object params[]={address.getLocation(),address.getReceiver(),address.getReceivertel(),address.getId()};
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于加载某个地址
	 * 成功返回address对象，失败返回null
	 * 输入：条件集合，布尔值flag(true则查询相应的顾客)
	 */
	public Address load(Map<String,Object> map,boolean flag)
	{
		Address address=new Address();
		String sql="select * from address where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<Address>util=new BaseUtil<Address>();
		address=(Address) util.QueryOne(Address.class, sql, params);
		if(flag)
		{
			String sql1="select * from user where id=(select id from address where id=?)";
			Object param[]={address.getId()};
			BaseUtil<User> util2=new BaseUtil<User>();
			
			address.setUser((User)util2.QueryOne(User.class, sql1, param));
		}
		return address;
	}
	public static void main(String[] args) {
//		Address address=new Address("花荣","0745-2435476","上海交通大学");
		AddressDao dao=new AddressDao();
//		System.out.println(dao.save(address,1));
//		
//		System.out.println("-----------------");
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("user_id",2);
//		Address a1=dao.load(map,true);
//		System.out.println(a1.toString());
//		System.out.println(a1.getUser().toString());
//		System.out.println("-----------------");
		
//		
//		address.setReceiver("解宝");
//		System.out.println(dao.update(address));
//		System.out.println("--------------------------");
//		
//		System.out.println(dao.delete(1));
		
		
		
		
	}

}
