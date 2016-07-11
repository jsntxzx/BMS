package com.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.entity.Book;
import com.oa.entity.Employee;
import com.oa.entity.Record;


@Service
public class DuoBiaoService extends BaseService{
	@Autowired
	private EmployeeService es ;
	@Autowired
	private BookService bs ;
	@Autowired
	private RecordService rs ;
	
	public boolean saveBookAndEmployeeAndRecord(Book b , Employee e, Record r)
	{
		try{
			bs.saveOrUpdate(b);
			es.saveOrUpdate(e);
			rs.saveOrUpdate(r);
			return true ;
		}
		catch(Exception ex) {
			return false;
		}
		
	}
	

}
