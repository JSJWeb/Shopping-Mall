package cn.edu.zhku.jsj.Model;
/*
 * 分页的设置
 */
public class Pager {
	private int totalPage;//总页数=总记录数/每页记录数
	private int totalRecord;//总记录数
	private int eachRecord;//每页记录数
	private int current;//当前记录=每页记录数*当前页数
	private int currentPage;//当前页数
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		totalPage=(int) Math.ceil((double)totalRecord/eachRecord);
		return totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getEachRecord() {
		return eachRecord;
	}
	public void setEachRecord(int eachRecord) {
		this.eachRecord = eachRecord;
	}
	public int getCurrent() {
		current=eachRecord*(currentPage-1);
		return current;
	}
	
	
}
