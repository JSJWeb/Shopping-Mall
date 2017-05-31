package cn.edu.zhku.jsj.Model;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	private int id;
	private String point;//评论
	private Good good;
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment(String point, Good good,User user) {
		super();
		this.point = point;
		this.good = good;
		this.user = user;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", point=" + point + ", good=" + good + ", users=" + user + "]";
	}
	
	
}
