package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Util.BaseUtil;

public class OrderItemDao {
	/*
	 * 该方法用于保存一个订单明细
	 * 成功返回true，失败返回false
	 * 输入：OrderItem对象和隶属的订单号
	 * 注：OrderItem对象里的user和good不能为null
	 */
	public boolean save(OrderItem item,int order_id)
	{
		boolean flag=false;
		String sql="insert into order_item(good_id,num,order_id)value(?,?,?)";
		Object []params={item.getGood().getId(),item.getNum(),order_id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于删除某个订单明细
	 * 成功返回true，失败返回false
	 * 输入：订单明细id
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from order_item where id=?";
		Object []params={id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);	
		return flag;
	}
	/*
	 * 该方法用于修改某个订单明细
	 * 成功返回true，失败返回false
	 * 输入：订单明细id和订单明细购买数量
	 * 
	 */
	//TODO:OrderItem update方法只允许更新购买数量
	public boolean update(int id,int num)
	{
		boolean flag=false;
		String sql="update order_item set num=? where id=?";
		Object []params={num,id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);
		return flag;
	}
	
	public OrderItem load(Map<String,Object> map)
	{
		OrderItem item=new OrderItem();
		String sql="select * from order_item where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		item=(OrderItem) util.QueryOne(OrderItem.class,sql,params);
		return item;
	}
	
	public List<OrderItem> selete(Map<String,Object> map)
	{
		List<OrderItem> item=new ArrayList<OrderItem>();
		String sql="select * from order_item where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		item=(List<OrderItem>) util.QueryList(OrderItem.class,sql,params);
		return item;
	}
}
