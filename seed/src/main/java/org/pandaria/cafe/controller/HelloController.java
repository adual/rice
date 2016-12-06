package org.pandaria.cafe.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/hello")
public class HelloController {
	
	@RequestMapping(path="/{name}")
	@ResponseBody
	public String sayHello(@PathVariable() String name){
		return String.format("Hi, %s, it's %s now.", name,new Date().toGMTString());
	}

}
