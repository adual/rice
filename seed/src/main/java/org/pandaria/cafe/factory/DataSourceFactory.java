package org.pandaria.cafe.factory;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.util.StringUtils;

public class DataSourceFactory {

	private final String DEFAULT_DATABASE_DRIVER_CLASS_NAME = "oracle.jdbc.OracleDriver";
	private final int DEFAULT_INITIAL_CONN_POOL_SIZE = 1;
	private final int DEFAULT_MAX_CONN_POOL_SIZE = 10;
	private final int DEFAULT_MAX_IDLE_TIME = 3000;
	private int acquireIncrement;
	
	private Map<String, DataSource> dsRegister = new HashMap<String, DataSource>();
	
	private String dbPropFile;
	private String commonPropFile;
	private Properties dbProperties;
	private Properties commonProperties;
	
	private void initialize() throws Exception{
		System.out.println(String.format("Initialize Property File %s", dbPropFile));
		this.dbProperties = loadConfigProps(dbPropFile);
		System.out.println(String.format("Initialize Property File %s", commonPropFile));
		this.commonProperties = loadConfigProps(commonPropFile);
		
	}
	
	private Properties loadConfigProps(String propFile) throws Exception{
		Properties prop = new Properties();
		if (!StringUtils.isEmpty(propFile)) {
			InputStream ins = null;
			try{
				ins = DataSourceFactory.class.getClassLoader().getResourceAsStream(propFile);
				prop.load(ins);
			}catch(Exception e){
				throw e;
			}finally{
				if (ins != null) {
					ins.close();
				}
			}
		}else{
			System.out.println("The input propFile is Empty.");
			return null;
		}
		return prop;
	}
	
	public DataSource getDataSource(String dsId) throws Exception{
		synchronized (dsRegister) {
			if (!dsRegister.containsKey(dsId)) {
				DataSource ds;
				try{
					ds = buildDataSource(dsId);
					if (ds != null) {
						dsRegister.put(dsId, ds);
					}
				}catch(Exception e){
					throw e;
				}
			}
			return dsRegister.get(dsId);
		}
	}
	
	private DataSource buildDataSource(String branch) throws Exception{
		com.jolbox.bonecp.BoneCPDataSource bds = null;
		
		try{
			String dbUrl = dbProperties.getProperty(branch + ".dbUrl");
			if (null == dbUrl) {
				throw new SQLException(String.format("Invalid Branch Code [%s], can't find it in [s%].", branch, dbPropFile));
			}
			String dbUserId = dbProperties.getProperty(branch + "dbUserId");
			String dbPassword = dbProperties.getProperty(branch + "dbPassword");
			
			//init connection pool
			bds = new com.jolbox.bonecp.BoneCPDataSource();
			bds.setJdbcUrl(dbUrl);
			bds.setUsername(dbUserId);
			bds.setPassword(dbPassword);
			bds.setDriverClass(DEFAULT_DATABASE_DRIVER_CLASS_NAME);
			
			//set pool properties
			bds.setDefaultAutoCommit(Boolean.parseBoolean(commonProperties.getProperty("autoCommit","true")));
			bds.setIdleConnectionTestPeriodInSeconds(DEFAULT_MAX_IDLE_TIME);
			bds.setMinConnectionsPerPartition(DEFAULT_INITIAL_CONN_POOL_SIZE);
			bds.setMaxConnectionsPerPartition(DEFAULT_MAX_CONN_POOL_SIZE);
			bds.setPartitionCount(3);
			bds.setAcquireIncrement(acquireIncrement);
			bds.setConnectionTimeout(DEFAULT_MAX_IDLE_TIME, null);
			
		}catch (Exception e){
			System.out.println();
			throw e;
		}
		return bds;
	}

	public String getDbPropFile() {
		return dbPropFile;
	}

	public void setDbPropFile(String dbPropFile) {
		this.dbPropFile = dbPropFile;
	}

	public String getCommonPropFile() {
		return commonPropFile;
	}

	public void setCommonPropFile(String commonPropFile) {
		this.commonPropFile = commonPropFile;
	}

	public Properties getCommonProperties() {
		return commonProperties;
	}

	public void setCommonProperties(Properties commonProperties) {
		this.commonProperties = commonProperties;
	}
	
}
