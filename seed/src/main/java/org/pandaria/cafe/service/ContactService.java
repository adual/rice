package org.pandaria.cafe.service;

import java.util.List;
import org.pandaria.cafe.domain.Contact;
import org.springframework.dao.DataAccessException;

public interface ContactService {

	public abstract Contact queryById(long id) throws DataAccessException, Exception ;
	
	public abstract List<Contact> retrieveAll() throws DataAccessException, Exception ;
	
	public abstract void add(Contact contact) throws DataAccessException, Exception ;
	
	public abstract void update(Contact contact) throws DataAccessException, Exception ;
	
	public abstract boolean delete(Contact contact) throws DataAccessException, Exception ;
	
	public abstract String getSysDate() throws DataAccessException, Exception ;
	
}
