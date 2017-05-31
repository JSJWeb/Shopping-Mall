package cn.edu.zhku.jsj.Util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties jdbcProp;
	public static Properties getProp(){
		if(null==jdbcProp)
		{
			jdbcProp=new Properties();
		}
		try {
			jdbcProp.load(PropertiesUtil.class.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jdbcProp;
	}

}
