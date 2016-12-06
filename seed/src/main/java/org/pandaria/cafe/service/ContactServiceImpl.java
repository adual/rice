package org.pandaria.cafe.service;

import java.util.List;
import java.util.Map;
import org.pandaria.cafe.domain.Contact;
import org.pandaria.cafe.factory.JdbcTemplateFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class ContactServiceImpl implements ContactService{

	private JdbcTemplateFactory jdbcTemplateFactory;
	
	
	public Contact queryById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> retrieveAll() throws DataAccessException, Exception {
		String sql = "SELECT * FROM CONTACT";
		RowMapper<Contact> rowMapper = new BeanPropertyRowMapper<Contact>();
		((BeanPropertyRowMapper<Contact>)rowMapper).setMappedClass(org.pandaria.cafe.domain.Contact.class);
		List<Contact> list = jdbcTemplateFactory.buildJdbcTemplate().query(sql, rowMapper);
		return list;
	}

	public void add(Contact contact) throws DataAccessException, Exception {
		String sql = "insert into contact (first_name, last_name) values (?, ?);";
		jdbcTemplateFactory.buildJdbcTemplate().update(sql,new Object[]{contact.getFirstName(),contact.getLastName(),contact.getBirthDate()});
	}

	public void update(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	public boolean delete(Contact contact) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getSysDate() throws DataAccessException, Exception{
		String sql = "SELECT sysdate() as SYSDATE";
		Map<String, Object> res = jdbcTemplateFactory.buildJdbcTemplate().queryForMap(sql);
		return String.valueOf(res.get("SYSDATE"));
	}

	public JdbcTemplateFactory getJdbcTemplateFactory() {
		return jdbcTemplateFactory;
	}

	public void setJdbcTemplateFactory(JdbcTemplateFactory jdbcTemplateFactory) {
		this.jdbcTemplateFactory = jdbcTemplateFactory;
	}

}
