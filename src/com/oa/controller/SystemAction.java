package com.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("welcome") 
public class SystemAction {
	
	
	@RequestMapping
	public String load(
			HttpServletRequest request,
			ModelMap modelMap){
		return "main" ;
	}

}
