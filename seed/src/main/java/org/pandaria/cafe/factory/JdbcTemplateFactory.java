package org.pandaria.cafe.factory;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateFactory {

	private String branch = "common";
	private DataSourceFactory dataSourceFactory;
	private DataSource ds;
	
	public JdbcTemplate buildJdbcTemplate() throws Exception{
		return new JdbcTemplate(ds);
	}
	
	public JdbcTemplate createJdbcTemplate() throws Exception{
		return new JdbcTemplate(dataSourceFactory.getDataSource(branch));
	}
	
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}  

	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
