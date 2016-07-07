package com.oa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.EntityDao;
import com.oa.entity.Employee;
import com.oa.entity.Record;


@Service
public class RecordService extends BaseService{
	@Autowired
	private EntityDao entityDao;
	
	public int getBorrowCountByEmployee(Employee e){
		int ret = 0 ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("eid", e);
		map.put("status", 0);
		List l = entityDao.createCriteria(Record.class, map, null);
		if(l != null && l.size() > 0)
		{
			ret = l.size() ;
		}
		return ret ;
	}

}
