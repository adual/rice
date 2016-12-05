package com.vanceinfo.javaserial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@ResponseBody()
	@RequestMapping(value = "/aa")
	public String sayHello(){
		return String.format("<div><p>Hi, </p></div>", "adfasdfwe");
	}
}
