package cn.edu.zhku.jsj.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;



public class DBUtil {
	private static Connection ct;
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	static{
		Properties prop=PropertiesUtil.getProp();
		driver=prop.getProperty("driver");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		url=prop.getProperty("url");
	}
	public static Connection getConnection()
	{
		try {
			Class.forName(driver);
			ct=DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ct;
	}
	public static void closeAll(Connection ct,PreparedStatement ps,ResultSet rs)
	{
		
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(ct!=null)ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
