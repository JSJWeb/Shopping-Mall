package cn.edu.zhku.jsj.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String username;
	private String nickname;
	private String password;
	private String tel;
	private String sex;
	private int type;
	private Date birth;
	private Date register;
	private List<Address>addresses=new ArrayList<Address>();
	public int getId() {
		return id;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getRegister() {
		return register;
	}
	public void setRegister(Date register) {
		this.register = register;
	}
	public User(String username, String nickname, String password, String tel, String sex, int type, Date birth,
			Date register) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.tel = tel;
		this.sex = sex;
		this.type = type;
		this.birth = birth;
		this.register = register;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", tel=" + tel + ", sex=" + sex + ", type=" + type + ", birth=" + birth + ", register=" + register
				+ ", addresses=" + addresses + "]";
	}
	
	
}
