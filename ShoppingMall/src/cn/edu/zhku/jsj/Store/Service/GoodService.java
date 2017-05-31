package cn.edu.zhku.jsj.Store.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.edu.zhku.jsj.Dao.GoodDao;
import cn.edu.zhku.jsj.Dao.OrderDao;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.Pager;


public class GoodService {
	
	/* 该方法提供添加商品方式
	 * 输入：Good,user_id
	 * 输出：boolean,true为修改成功，false为修改失败
	 */
	public boolean save(Good good,int user_id)
	{
		GoodDao dao=new GoodDao();
		boolean flag=dao.save(good,user_id);
		return flag;
	}
	
	/*
	 * 该方法提供修改商品方式
	 * 输入：Good
	 * 输出：boolean,true为修改成功，false为修改失败
	 */
	public boolean update(Good good)
	{
		GoodDao dao=new GoodDao();
		boolean flag=dao.update(good);
		return flag;
	}
	
	/*
	 * 该方法删除某个具体商品
	 * 输入：某个商品的id值
	 * 输出：删除
	 */
	public boolean delete(int id)
	{
		GoodDao dao=new GoodDao();
		boolean flag=dao.delete(id);
		return flag;
	}	
	
	/*
	 * 该方法提供条件查询某个商品方式
	 * 输入：需要带名称的数据params,flag（是否查询相应的店铺）
	 * 输出：Good
	 */
	public Good load(Map<String,Object>params,boolean flag)
	{
		Good good=new Good();
		GoodDao dao=new GoodDao();
		good=dao.load(params,flag);
		return good;
	}
	
	/*
	 * 该方法提供条件查询用户方式
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<User>
	 */
	public List<Good> query(Map<String,Object>params,Pager pager,String order,String choose)
	{
		List<Good> list=new ArrayList<Good>();
		GoodDao dao=new GoodDao();
		list=dao.list(params,pager,order,choose);
		return list;
	}
	
	/*
	 * 该方法提供模糊查询用户方式
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)，排序方式order和升降序choose
	 * 输出：List<Good>
	 */
	public List<Good> Fuzzyquery(Map<String,Object>params,Pager pager,String order,String choose)
	{
		List<Good> list=new ArrayList<Good>();
		GoodDao dao=new GoodDao();
		
		list=dao.fuzzyload(params,pager,order,choose);
		return list;
	}
	/*
	 * 该方法提供查询商品方式
	 * 输入：需要带名称的数据params，分页数据pager(包括当前记录和每页记录数)
	 * 输出：List<Good>
	 */
	public List<Good> search(Map<String,Object> map,Pager pager)
	{
		List<Good> goods=new ArrayList<Good>();
		GoodDao dao=new GoodDao();
		goods=dao.fuzzyload(map, pager,"id","asc");
		return goods;
	}

	/*
	 * 该方法计算需查询商品的总记录
	 * 输入：商品类型type/商品介绍introduction,查询条件params
	 * 输出：总记录数
	 * 注：如果查询方式不是模糊查询，查询条件params必须为null值，反之为模糊查询的条件
	 */
	public int countGood(Map<String,Object> params)
	{
		Pager pager=new Pager();
		GoodDao dao=new GoodDao();
		pager.setTotalRecord(dao.countGood(params));
		return pager.getTotalRecord();
	}
}
