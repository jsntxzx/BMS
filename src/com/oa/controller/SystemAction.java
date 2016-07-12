package com.oa.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.oa.entity.Book;
import com.oa.entity.Employee;
import com.oa.entity.Record;
import com.oa.service.BookService;
import com.oa.service.EmployeeService;
import com.oa.service.RecordService;


@Controller
@RequestMapping
public class SystemAction {
	
	@Autowired
	private  EmployeeService es ;
	
	@Autowired
	private BookService bs ;
	
	@Autowired
	private RecordService rs ;
	
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
		Integer booknum = bs.getCount(Book.class,null);
		Integer employeenum = es.getCount(Employee.class, null);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", 0);
		Integer outbooknum = rs.getCount(Record.class,map);
		Integer totalborrownum = rs.getCount(Record.class,null);
		modelMap.put("num1",booknum) ;
		modelMap.put("num2",employeenum) ;
		modelMap.put("num3",outbooknum) ;
		modelMap.put("num4",totalborrownum) ;
		return "main" ;
	}
}
