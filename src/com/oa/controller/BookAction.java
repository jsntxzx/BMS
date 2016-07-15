package com.oa.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oa.entity.Book;
import com.oa.entity.Record;
import com.oa.service.BookService;
import com.oa.service.RecordService;
import com.oa.util.Paging;


@Controller
@RequestMapping("book") 
public class BookAction {
	
	@Autowired
	private BookService bs ;
	@Autowired
	private RecordService rs ;
	
	private int pageSize = 10 ;
	
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,
			ModelMap modelMap,
			@RequestParam(value = "key",required = false) String key,
			@RequestParam(value = "value",required = false) String value,
			@RequestParam(value = "curPage", defaultValue = "0", required = false) Integer curPage) throws UnsupportedEncodingException{
		if(value != null && request.getMethod() == "GET"){
			value = new String(value.getBytes("ISO8859-1"),"UTF-8") ;
		}
		request.getSession().setAttribute("key", key);
		request.getSession().setAttribute("value", value);
		request.getSession().setAttribute("curPage", curPage);
		Map<String,Object> map = new HashMap<String,Object>() ;
		if(key != null && key.length() > 0 ){
			map.put(key, value);
		}
		Map<String,Object> orderMap = new HashMap<String,Object>() ;
		Paging paging = bs.getAll(Book.class, curPage, pageSize, map, orderMap);
		modelMap.put("paging", paging);
		modelMap.put("key", key);
		modelMap.put("value", value);	
		return "page/book/list";
	}
	
	@RequestMapping(value="save")
	public String save(HttpServletRequest request,
			ModelMap modelMap,
			RedirectAttributes attr,
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "sn") String sn,
			@RequestParam(value = "totalcopy") Integer totalcopy) {
		
		if(title == null || title.length() < 2 || title.length() > 100 
				|| sn== null || sn.length() < 5 || sn.length() > 100 ||
				totalcopy ==null || totalcopy < 1 || totalcopy > 1000)
		{
			modelMap.put("addf", 1 );			
			return "page/book/add";
		}
		
		Book n = null ;
		
		if(id == null)
		{
			n = new Book() ;
			n.setLeftcopy(totalcopy);
		}
		else
		{
			n = (Book) bs.getObjectById(Book.class, id);
			if(n.getTotalcopy() > totalcopy)
			{
				modelMap.put("dhl", n);
				modelMap.put("addf", 2);
				return "page/book/add";
			}
		}
		n.setTitle(title);
		n.setSn(sn);	
		n.setTotalcopy(totalcopy);		
		bs.saveOrUpdate(n);
		attr.addAttribute("key", request.getSession().getAttribute("key"));
		attr.addAttribute("value", request.getSession().getAttribute("value"));
		attr.addAttribute("curPage", request.getSession().getAttribute("curPage"));
		return "redirect:/book/list.html";
	}
	
	@RequestMapping(value = "update/{id}")
	public String update(HttpServletRequest request, ModelMap modelMap,
			@PathVariable(value = "id") Integer id) {
		Book n = (Book) bs.getObjectById(Book.class, id);		
		modelMap.put("dhl", n);
		return "page/book/add";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(HttpServletRequest request, ModelMap modelMap,
			@PathVariable(value = "id") Integer id) {
		Book n = (Book) bs.getObjectById(Book.class, id);
		bs.delete(n);
		return "redirect:/book/list.html";
	}
	
	@RequestMapping(value="add")
	public String add(HttpServletRequest request,
			ModelMap modelMap){
		return "page/book/add";
	}
	
	@RequestMapping(value = "listborrow/{id}")
	public String listborrow(HttpServletRequest request, ModelMap modelMap,
			@PathVariable(value = "id") Integer id) {
		Book n = (Book) bs.getObjectById(Book.class, id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bid", n);
		map.put("status", 0);
		List<?> l = rs.getAll(Record.class, map) ;
		modelMap.put("list", l);
		modelMap.put("basic", n);
		return "page/book/borrowlist";
	}

}
