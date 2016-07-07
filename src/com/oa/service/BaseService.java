package com.oa.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.EntityDao;
import com.oa.util.Paging;

@Service
public class BaseService {
	@Autowired
	private EntityDao entityDao;

	public Object saveOrUpdate(final Object model) {
		return entityDao.saveOrUpdate(model);
	}

	public Object getObjectById(final Class cs, final Integer id) {
		return entityDao.getObjectById(cs, id);
	}

	public Paging getAll(Class cs, Integer curPage, Integer pageSize,
			Map<String, Object> map) {
		Map<String, Object> orderMap = new LinkedHashMap<String, Object>();
		orderMap.put("id", "asc");
		Paging paging = entityDao.createQueryPaging(cs, map, orderMap, curPage,
				pageSize);
		return paging;
	}

	public Paging getAll(Class cs, Integer curPage, Integer pageSize,
			Map<String, Object> map, Map<String, Object> orderMap) {
		Paging paging = entityDao.createQueryPaging(cs, map, orderMap, curPage,
				pageSize);
		return paging;
	}

	public List getAll(Class cs, Map<String, Object> map) {

		Map<String, Object> orderMap = new LinkedHashMap<String, Object>();
		orderMap.put("id", "asc");
		return entityDao.createCriteria(cs, map, orderMap);
	}
	
	public List getAll(Class cs, Map<String, Object> map, Map<String, Object> orderMap) {
		return entityDao.createCriteria(cs, map, orderMap);
	}

	public List getAll(Class cs) {

		Map<String, Object> orderMap = new LinkedHashMap<String, Object>();
		orderMap.put("id", "asc");
		return entityDao.createCriteria(cs, null, orderMap);
	}
	
	public List getAllByOrder(Class cs, String key , String order) {

		Map<String, Object> orderMap = new LinkedHashMap<String, Object>();
		orderMap.put(key, order);
		return entityDao.createCriteria(cs, null, orderMap);
	}
	

	public void delete(Object model) {
		entityDao.delete(model);
	}
}
