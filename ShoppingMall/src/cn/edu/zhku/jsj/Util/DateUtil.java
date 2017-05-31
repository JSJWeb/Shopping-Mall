package cn.edu.zhku.jsj.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {
	public static java.sql.Date getTime(String date)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date date2 = null;
		try {
			java.util.Date date1=format.parse(date);
			date2=new java.sql.Date(date1.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}
	public static void main(String[] args) {
//		System.out.println(DateUtil.getTime("2002-6-23"));
	}
}
