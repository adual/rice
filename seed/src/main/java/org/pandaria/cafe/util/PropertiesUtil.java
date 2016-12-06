package org.pandaria.cafe.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.pandaria.cafe.common.Constant;

public class PropertiesUtil {

	private static Properties properties;
	
	private static void init() throws Exception {
		try {
			InputStream ins = PropertiesUtil.class.getClassLoader().getResourceAsStream(Constant.JDBC_CONFIG);
			properties = new Properties();
			properties.load(ins);
		} catch (IOException e) {
			Exception ne = new Exception(String.format("Failed to init configure in %s", PropertiesUtil.class.getName()),e);
			throw ne;
		}
	}
	
	public static Properties getProperties() throws Exception{
		if (properties == null) {
			init();
		}
		return properties;
	}
	
	public static Properties load(final Properties properties, String propertyName) throws Exception{
		try {
			InputStream ins = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertyName);
			properties.load(ins);
			return properties;
		} catch (IOException e) {
			Exception ne = new Exception(String.format("Failed to init configure in %s", PropertiesUtil.class.getName()),e);
			throw ne;
		}
	}
}
