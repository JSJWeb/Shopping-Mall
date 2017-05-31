package cn.edu.zhku.jsj.Operator.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.GoodDao;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;

public class ShopService {
	public List<Good> search(Map<String,Object> map,Pager pager)
	{
		List<Good> goods=new ArrayList<Good>();
		GoodDao dao=new GoodDao();
		goods=dao.fuzzyload(map, pager,"id","asc");
		return goods;
	}
	public int countGood(Map<String,Object> map)
	{
		int result=0;
		GoodDao dao=new GoodDao();
		result=dao.countGood(map);
		return result;
	}
}
