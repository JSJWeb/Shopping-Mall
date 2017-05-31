package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;

public class OrderDao {
	//保存订单
	public boolean save(Order order)
	{
		boolean flag=false;
		String sql="insert into order(createdate,state,user_id,address_id)value(?,?,?,?)";
		Object []params={order.getCreateDate(),order.getOstate(),order.getUser().getId(),order.getAddress().getId()};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	//更新订单
	public boolean update(Order order)
	{
		System.out.println("orderdao");
		System.out.println(order.toString());
		boolean flag=false;
		String sql="update `order` set ostate=?,createdate=?,address_id=?,user_id=? where id=?";
		Object []params={order.getOstate(),order.getCreateDate(),order.getAddress().getId(),order.getUser().getId(),order.getId()};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	//删除订单
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from order where id=?";
		Object[]params={id};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	//查找订单,flag1:地址,flag2:找人，flag3:找订单详情
	public Order load(Map<String,Object> map,boolean flag1,boolean flag2,boolean flag3)
	{
		Order order=new Order();
		String sql="select * from `order` where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<Order> util=new BaseUtil<Order>();
		order=(Order) util.QueryOne(Order.class, sql, params);
		if(flag1)
		{
			String sql1="select * from address where id=(select id from `order` where id=?)";
			BaseUtil<Address> addressUtil=new BaseUtil<Address>();
			Object[]p={order.getId()};
			order.setAddress((Address)addressUtil.QueryOne(Address.class, sql1, p));
		}
		if(flag2)
		{
			String sql1="select * from user where id=(select id from `order` where id=?)";
			BaseUtil<User> addressUtil=new BaseUtil<User>();
			Object[]p={order.getId()};
			order.setUser((User)addressUtil.QueryOne(User.class, sql1, p));
		}
		if(flag3)
		{
			String sql3="select * from order_item where id=?";
			BaseUtil<OrderItem> itemUtil=new BaseUtil<OrderItem>();
			Object []param={order.getId()};
			order.setItems(itemUtil.QueryList(OrderItem.class, sql3, param));
		}
		return order;
	}
	public List<Order> list(Map<String,Object>map,Pager pager,String order,String choose)
	{
		List<Order>orders=new ArrayList<Order>();
		String sql="select * from `order` where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else sql=sql+" order by ? asc limit ?,?";
		Object params=map.values().toArray();
		BaseUtil<Order> util=new BaseUtil<Order>();
		orders=util.QueryList(Order.class, sql, params,order,pager.getCurrent(),pager.getEachRecord());
		return orders;
	}
	public int countOrder(Map<String,Object>map)
	{
		int result=0;
		String sql="select count(*) from order where 1=1";
		BaseUtil<Order> util=new BaseUtil<Order>();
		if(null!=map)
		{
			for(String key:map.keySet())
				sql=sql+" and "+key+"=?";
			Object params=map.values().toArray();
			result=util.count(Order.class,sql,params);
		}
		else
			util.count(Order.class,sql);
		return result;
	}
}
