package org.pandaria.cafe.dao;

import java.util.List;
import org.pandaria.cafe.domain.Contact;

public interface ContactDao {

public abstract Contact queryById(long id);
	
	public abstract List<Contact> retrieveAll();
	
	public abstract void add(Contact contact);
	
	public abstract void update(Contact contact);
	
	public abstract boolean delete(Contact contact);
	
	public abstract String getSysDate();
}
