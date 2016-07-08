package com.oa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class SystemAction {
	
	/**
	 * 进入系统主页
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("welcome") 
	public String welcome(
			HttpServletRequest request,
			ModelMap modelMap){
		return "main" ;
	}
}
