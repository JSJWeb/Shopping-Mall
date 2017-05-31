package cn.edu.zhku.jsj.Store.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import cn.edu.zhku.jsj.Dao.OrderDao;
import cn.edu.zhku.jsj.Dao.OrderItemDao;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.OrderItem;

public class StoreOrderService {
	/*
	 * 该方法提供条件查询订单详细方式
	 * 输入：需要带名称的数据params
	 * 输出：OrderItem
	 */
	public OrderItem queryitem(Map<String,Object>params)
	{
		OrderItem item=new OrderItem();
		OrderItemDao dao=new OrderItemDao();
		item=dao.load(params);
		return item;
	}
	/*
	 * 该方法提供条件查询订单详细方式
	 * 输入：需要带名称的数据params
	 * 输出：List<OrderItem>
	 */
	public List<OrderItem> selete(Map<String,Object>params)
	{
		List<OrderItem> item=new ArrayList<OrderItem>();
		OrderItemDao dao=new OrderItemDao();
		item=dao.selete(params);
		return item;
	}
	
	/*
	 * 该方法提供条件查询订单方式
	 * 输入：需要带名称的数据map,flag1(地址),flag2（买家）,flag3(订单详细)
	 * 输出：Order
	 */
	public Order queryorder(Map<String,Object> map,boolean flag1,boolean flag2,boolean flag3)
	{
		Order order=new Order();
		OrderDao dao=new OrderDao();
		order=dao.load(map, flag1, flag2, flag3);
		return order;
	}	
	/*
	 * 该方法提供修改订单方式
	 * 输入：Order
	 * 输出：boolean,true为修改成功，false为修改失败
	 */
	public boolean update(Order order)
	{
		System.out.println(order.toString());
		OrderDao dao=new OrderDao();
		boolean flag=dao.update(order);
		return flag;
	}
}
