package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;
//TODO:数据库商品级联删除问题
public class GoodDao {
	/*
	 * 该方法用于保存一个商品
	 * 成功返回true，失败返回false
	 * 输入：good对象和店铺ID
	 */
	public boolean save(Good good,int user_id)
	{
		boolean flag=false;
		String sql="insert into good(introduction,gname,gpic,gprice,"
				+ "gremainnum,gstate,gtotalnum,gtype,user_id) value(?,?,?,?,?,?,?,?,?)";
		Object []params={good.getIntroduction(),good.getGname(),good.getGpic(),
				good.getGprice(),good.getGremainNum(),good.getGstate(),good.getGtotalNum(),
				good.getGtype(),user_id};
		BaseUtil<Good> util =new BaseUtil<Good>();
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于删除某个具体的商品
	 * 成功返回true，失败返回false
	 * 输入：商品id
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from good where id=?";
		Object[]params={id};
		BaseUtil<Good> util=new BaseUtil<Good>();
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于修改某个商品的具体信息
	 * 成功返回true，失败返回false
	 * 输入：good对象
	 */
	public boolean update(Good good)
	{
		boolean flag=false;
		String sql="update good set introduction=?,gname=?,gpic=?,gprice=?,"
				+ "gremainnum=?,gstate=?,gtotalnum=?,gtype=? where id=?";
		Object []params={good.getIntroduction(),good.getGname(),good.getGpic(),
				good.getGprice(),good.getGremainNum(),good.getGstate(),good.getGtotalNum(),
				good.getGtype(),good.getId()};
		BaseUtil<Good> util =new BaseUtil<Good>();
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于加载某个商品
	 * 成功返回true，失败返回false
	 * 输入：条件集合和布尔值（是否查询相应的店铺）
	 * 
	 */
	public Good load(Map<String,Object> map,boolean flag)
	{
		Good good=new Good();
		String sql="select * from good where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<Good> util=new BaseUtil<Good>();
		good=(Good) util.QueryOne(Good.class, sql, params);
		if(flag)
		{
			String sql1="select * from user where id=(select id from address where id=?)";
			BaseUtil<User> userUtil=new BaseUtil<User>();
			Object []p={good.getId()};
			good.setUser((User)userUtil.QueryOne(User.class, sql1, p));
		}
		return good;
	}
	/*
	 * 该方法用于分页查询商品
	 * 输入：条件集合、分页方式pager、排序条件order和排序方式choose
	 * 成功返回true，失败返回false
	 */
	public List<Good> list(Map<String,Object> map,Pager pager,String order,String choose)
	{
		List<Good> goods=new ArrayList<Good>();
		String sql="select * from good where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else 
			sql=sql+" order by ? asc limit ?,?";
		Object params=map.values().toArray();
		BaseUtil<Good> util=new BaseUtil<Good>();
		
		goods=util.QueryList(Good.class, sql, params,order,pager.getCurrent(),pager.getEachRecord());
		return goods;
	}
	/*
	 * 该方法用于分页模糊查询商品
	 * 输入：条件集合、分页方式pager、排序条件order和排序方式choose
	 * 成功返回true，失败返回false
	 */
	public List<Good> fuzzyload(Map<String,Object> map,Pager pager,String order,String choose)
	{
		List<Good> goods=new ArrayList<Good>();
		String sql="select * from good where 1=1";
		for(String key:map.keySet())
		{
			if(map.get(key) instanceof String)
			{
				sql=sql+" and "+key+" like ?";
				map.put(key,"%"+map.get(key)+"%");
			}
			else
				sql=sql+" and "+key+"=?";
		}
		
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else 
			sql=sql+" order by ? asc limit ?,?";
		System.out.println("Good fuzzySql:"+sql);
		Object params=map.values().toArray();
		BaseUtil<Good>util=new BaseUtil<Good>();
		goods=util.QueryList(Good.class, sql, params,order,pager.getCurrent(),pager.getEachRecord());
		for(Good g:goods)
		{
			System.out.println(g.toString());
		}
		return goods;
	}
	/*
	 * 该方法用于计算商品的数量
	 * 输入：条件集合
	 * 成功返回int值
	 */
	public int countGood(Map<String,Object> map)
	{
		int count =0;
		BaseUtil<Good> util=new BaseUtil<Good>();
		String sql="select count(*) from good where 1=1";
		
		if(null!=map)
		{
			Object value=new Object();
			for(String key:map.keySet())
			{
				//为模糊查询拼接sql语句
				if(map.get(key) instanceof String)
				{
					//模糊查询
					sql=sql+" and "+key+" like ?";
					map.put(key,"%"+map.get(key)+"%");
				}
				else
					sql=sql+" and "+key+"=?";
			}
			Object param=map.values().toArray();
			count =util.count(Good.class,sql,param);
		}
		else
			count =util.count(Good.class,sql);
		return count;
	}
	public static void main(String[] args) {
////
//		GoodDao dao=new GoodDao();
////
////		
//		List<Good> list=new ArrayList<Good>();
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("gtype","女人");
//		Pager pager=new Pager();
//		pager.setTotalRecord(dao.countGood(map));
//		pager.setEachRecord(4);
//		pager.setCurrentPage(1);
//		String order="id";
//		String choose="desc";
//		list=dao.fuzzyload(map, pager, order, choose);
//		for(Good g:list)
//			System.out.println(g.toString());
		
//		System.out.println("-----------------");
//		
//		good.setName("时尚单鞋");
//		good.setId(3);
//		System.out.println(dao.update(good));
//		System.out.println("____________");
//		
//		Map<String,Object>map=new HashMap<String,Object>();
//		map.put("id",3);
//		Good g=dao.load(map,true);
//		System.out.println(g.toString());
//		if(null!=g.getUser())
//			System.out.println(g.getUser().toString());
//		System.out.println(dao.delete(3));
	}
}
