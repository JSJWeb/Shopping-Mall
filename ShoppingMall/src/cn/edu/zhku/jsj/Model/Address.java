package cn.edu.zhku.jsj.Model;

public class Address {
	private int id;

	private String receivertel;
	private String receiver;
	private String location;
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
	
}