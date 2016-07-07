package com.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.entity.Book;
import com.oa.entity.Record;


@Service
public class DuoBiaoService extends BaseService{
	@Autowired
	private BookService bs ;
	@Autowired
	private RecordService rs ;
	
	public boolean saveBookAndRecord(Book b , Record r)
	{
		try{
			bs.saveOrUpdate(b);
			rs.saveOrUpdate(r);
			return true ;
		}
		catch(Exception e) {
			return false;
		}
		
	}
	

}
