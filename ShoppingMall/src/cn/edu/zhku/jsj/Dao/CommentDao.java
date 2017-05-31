package cn.edu.zhku.jsj.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Comment;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;

public class CommentDao {
	public boolean save(Comment comment,int user_id)
	{
		boolean flag=false;
		String sql="insert into comment(good_id,user_id,point) value(?,?,?)";
		Object []params={comment.getGood().getId(),comment.getPoint(),user_id};
		
		BaseUtil<Comment> util=new BaseUtil<Comment>();
		flag=util.update(sql, params);
		return flag;
	}
	public boolean update(Comment comment)
	{
		boolean flag=false;
		String sql="update comment set point=? where id=?";
		Object []params={comment.getPoint(),comment.getId()};
		BaseUtil<Comment> util=new BaseUtil<Comment>();
		flag=util.update(sql, params);
		return flag;
	}
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from comment where id=?";
		Object []params={id};
		BaseUtil<Comment> util=new BaseUtil<Comment>();
		flag=util.update(sql, params);
		return flag;
	}
	public Comment load(Map<String,Object> map,boolean flag)
	{
		Comment comment=new Comment();
		String sql="select * from  comment where 1=1";
		Object []params=map.values().toArray();
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		
		BaseUtil<Comment> util=new BaseUtil<Comment>();
		comment=(Comment)util.QueryOne(Comment.class, sql, params);
		if(flag)
		{
			String sql1="select * from user where id=(select * from comment where id=?)";
			Object []p={comment.getId()};
			BaseUtil<User> userutil=new BaseUtil<User>();
			comment.setUser((User)userutil.QueryOne(User.class, sql1, p));
		}
		return comment;
	}
	public List<Comment> list(Map<String,Object>map,Pager pager)
	{
		List<Comment> comments=new ArrayList<Comment>();
		String sql="select * from comment where 1=1";
		Object params=map.values().toArray();
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		sql=sql+" limit ?,?";
		BaseUtil<Comment> util=new BaseUtil<Comment>();
		comments=util.QueryList(Comment.class, sql, params,pager.getCurrent(),pager.getEachRecord());
		return comments;
	}
	public int countComment(Map<String,Object>map)
	{
		int result=0;
		String sql="select count(*) from comment where 1=1";
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
