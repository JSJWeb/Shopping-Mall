package cn.edu.zhku.jsj.Operator.Service;

import java.util.HashMap;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.GoodDao;
import cn.edu.zhku.jsj.Model.Good;

public class CartService {
	public Good get(int id)
	{
		GoodDao dao=new GoodDao();
		Good good=new Good();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("id",id);
		good=dao.load(map,true);
		return good;
	}
	
}
