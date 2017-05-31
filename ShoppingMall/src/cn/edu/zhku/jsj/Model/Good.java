package cn.edu.zhku.jsj.Model;

public class Good {
	private int id;
	private String gname;
	private double gprice;
	private int gtotalNum;
	private int gremainNum;
	private String gpic;
	private String introduction;
	private String gtype;//类型
	private int gstate;//状态：0上架，1下架
	private User user;//店铺
	
	public Good(String name, double price, int totalNum, int remainNum, String pic, String introduction, String type,
			int state) {
		super();
		this.gname = name;
		this.gprice = price;
		this.gtotalNum = totalNum;
		this.gremainNum = remainNum;
		this.gpic = pic;
		this.introduction = introduction;
		this.gtype = type;
		this.gstate = state;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public int getGtotalNum() {
		return gtotalNum;
	}

	public void setGtotalNum(int gtotalNum) {
		this.gtotalNum = gtotalNum;
	}

	public int getGremainNum() {
		return gremainNum;
	}

	public void setGremainNum(int gremainNum) {
		this.gremainNum = gremainNum;
	}

	public String getGpic() {
		return gpic;
	}

	public void setGpic(String gpic) {
		this.gpic = gpic;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public int getGstate() {
		return gstate;
	}

	public void setGstate(int gstate) {
		this.gstate = gstate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Good() {
		super();
	}
	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + gname + ", price=" + gprice + ", totalNum=" + gtotalNum + ", remainNum="
				+ gremainNum + ", pic=" + gpic + ", introduction=" + introduction + ", type=" + gtype + ", state=" + gstate
				+ "]";
	}
	
	
}
