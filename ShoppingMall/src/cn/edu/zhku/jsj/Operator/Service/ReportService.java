package cn.edu.zhku.jsj.Operator.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.GoodDao;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Pager;

public class ReportService {
	public boolean updateGoodState(int id,int gstate)
	{
		boolean flag=false;
		GoodDao dao=new GoodDao();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",id);
		Good good=new Good();
		good=dao.load(map,false);
		good.setGstate(gstate);
		flag=dao.update(good);
		return flag;
	}
	public List<Good> getReportGood(int gstate,Pager pager)
	{
		List<Good> list=new ArrayList<Good>();
		GoodDao dao=new GoodDao();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("gstate",gstate);
		list=dao.list(map, pager, "id","asc");
		return list;
	}
	public int countReport(int gstate)
	{
		int result=0;
		GoodDao dao=new GoodDao();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("gstate",gstate);
		result=dao.countGood(map);
		return result;
	}
}
