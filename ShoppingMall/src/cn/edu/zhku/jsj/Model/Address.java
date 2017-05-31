package cn.edu.zhku.jsj.Model;

public class Address {
	private int id;

	private String receivertel;
	private String receiver;
	private String location;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReceivertel() {
		return receivertel;
	}
	public void setReceivertel(String receivertel) {
		this.receivertel = receivertel;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", receivertel=" + receivertel + ", receiver=" + receiver + ", location="
				+ location + "]";
	}
	public Address( String receiver,String receivertel, String location) {
		super();
		this.receivertel = receivertel;
		this.receiver = receiver;
		this.location = location;
	}
	public Address() {
		super();
	}
	
}
