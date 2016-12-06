package org.pandaria.cafe.controller;

import java.util.List;

import org.pandaria.cafe.domain.Contact;
import org.pandaria.cafe.service.ContactService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactController {

	private ContactService contactService;
	@RequestMapping(value="/retrieveAll",method={RequestMethod.POST})
	public String retrieveContactList(ModelMap modelMap) throws DataAccessException, Exception{
		List<Contact> contactList = contactService.retrieveAll();
		modelMap.put("list", contactList);
		return "/contactList";
	}
	
}
