package com.oa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oa.entity.Book;
import com.oa.entity.Employee;
import com.oa.entity.Record;
import com.oa.service.BookService;
import com.oa.service.DuoBiaoService;
import com.oa.service.EmployeeService;
import com.oa.service.RecordService;
import com.oa.util.Paging;


@Controller
@RequestMapping("manage") 
public class ManageAction {
	
	@Autowired
	private  EmployeeService es ;
	
	@Autowired
	private BookService bs ;
	
	@Autowired
	private RecordService rs ;
	
	@Autowired
	private DuoBiaoService db ;
	
	private int pageSize = 10 ;
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request, ModelMap modelMap) {
		List el = es.getAll(Employee.class) ;
		List bl = bs.getAll(Book.class) ;
		modelMap.put("el", el);
		modelMap.put("bl", bl);
		return "page/manage/add";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "borrowcheck")
	public String borrowcheck(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "eid") Integer eid,
			@RequestParam(value = "bid") Integer bid) {
		String msg = "" ;
		if(eid == null || bid == null){
			msg = "<font color=red>请选择员工和所借书籍</font>" ;			
		}
		else{
			Employee e = (Employee) es.getObjectById(Employee.class, eid);
			Book b = (Book) bs.getObjectById(Book.class, bid);
			int count = rs.getBorrowCountByEmployee(e);
			if(count > 5){
				msg = "<font color=red>该员工借书数目超过5本</font>" ;
			}
			else if(b.getLeftcopy() == 0){
				msg = "<font color=red>所借书籍已经全部借出</font>" ;
			}
			else{
				Record r = new Record() ;
				r.setAddtime(new Date());
				r.setBid(b);
				r.setEid(e);
				r.setStatus(0);
			
				int left = b.getLeftcopy() - 1 ;
				b.setLeftcopy(left);
				
				if(db.saveBookAndRecord(b, r)){
					msg =  "<font color=blue>借书成功</font>" ;
				}
				else{
					msg =  "<font color=red>借书失败</font>" ;
				}
			}
			
		}
		List el = es.getAll(Employee.class) ;
		List bl = bs.getAll(Book.class) ;
		modelMap.put("addf", msg);
		modelMap.put("el", el);
		modelMap.put("bl", bl);
		return "page/manage/add";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "bid", required = false) Integer bid,
			@RequestParam(value = "eid", required = false) Integer eid,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) {

		Map<String,Object> map = new HashMap<String,Object>() ;
		if(bid != null){
			Book b = (Book) bs.getObjectById(Book.class, bid);
			map.put("bid", b);
		}
		if(eid != null){
			Employee e = (Employee) es.getObjectById(Employee.class, eid);
			map.put("eid", e);
		}		
		Map<String,Object> orderMap = new HashMap<String,Object>() ;
		orderMap.put("id", "desc");
		Paging paging = rs.getAll(Record.class, curPage, pageSize, map, orderMap);
		List el = es.getAll(Employee.class) ;
		List bl = bs.getAll(Book.class) ;
		modelMap.put("el", el);
		modelMap.put("bl", bl);
		modelMap.put("paging", paging);
		modelMap.put("bid", bid);
		modelMap.put("eid", eid);
		return "page/manage/list";
	}
	
	
	@RequestMapping(value = "return/{id}")
	public String returnbook(HttpServletRequest request, ModelMap modelMap,
			@PathVariable(value = "id") Integer id) {
		Record r = (Record) rs.getObjectById(Record.class, id) ;		
		r.setStatus(1);
		rs.saveOrUpdate(r);
		return "redirect:/manage/list.html";
	}
	
	
	
	
	

}
