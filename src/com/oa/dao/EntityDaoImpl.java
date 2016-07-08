package com.oa.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.util.Paging;

public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao {

	/**
	 * hql
	 */
	@SuppressWarnings("unchecked")
	public List<Object> createQuery(final String queryString) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createQuery(queryString);
						List<Object> rows = query.list();

						if (session != null && session.isConnected()) {
							session.close();
						}
						return rows;
					}
				});
	}

	public Paging createQueryFy(final Class ca, final Map<String, Object> map,
			final Map<String, Object> pxmap, final Integer curPage,
			final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Criteria criteria = session.createCriteria(ca);
						Criteria criteriaTot = session.createCriteria(ca);
						if (map != null) {
							Set set = map.keySet();
							for (Iterator it = set.iterator(); it.hasNext();) {
								String str = (String) it.next();
								if (map.get(str) != null
										&& !map.get(str).equals("")) {
									criteria.add(Restrictions.eq(str, map
											.get(str)));
									criteriaTot.add(Restrictions.eq(str, map
											.get(str)));
								}
							}
						}
						if (pxmap != null) {
							Set set = pxmap.keySet();
							for (Iterator it = set.iterator(); it.hasNext();) {
								String str = (String) it.next();
								if (pxmap.get(str).equals("asc")) {
									criteria.addOrder(Order.asc(str));
								} else {
									criteria.addOrder(Order.desc(str));
								}
							}
						}

						Paging paging = new Paging();
						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);
						paging.setList(criteria.list());
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}

	/**
	 * Criteria的查询
	 * 
	 * @param cs
	 *            查询类
	 * @param map
	 *            查询条件 (key：字段名,value：字段值)
	 * @param orderMap
	 *            排序条件 (key：字段名,value：字段值)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List createCriteria(final Class cs, final Map<String, Object> map,
			final Map<String, Object> orderMap) {
		return (List) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Criteria criteria = session.createCriteria(cs);

						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (s.indexOf(">") != -1) {
									criteria.add(Restrictions.gt(s.replace(">",
											""), map.get(s)));

								} else if (s.indexOf("<") != -1) {
									criteria.add(Restrictions.lt(s.replace("<",
											""), map.get(s)));
								} else {
									criteria
											.add(Restrictions.eq(s, map.get(s)));
								}
							}
						}

						if (orderMap != null) {
							Set<String> key = orderMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (orderMap.get(s).equals("desc")) {
									criteria.addOrder(Order.desc(s));
								} else {
									criteria.addOrder(Order.asc(s));
								}
							}
						}

						if (session != null && session.isConnected()) {
							session.close();
						}
						return criteria.list();
					}
				});
	}

	/**
	 * 带有分页的查询
	 * 
	 * @param cs
	 *            查询类
	 * @param map
	 *            查询条件 key：字段名 value：字段值
	 * @param curPage
	 *            当前页码
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Paging createQueryPaging(final Class cs,
			final Map<String, Object> map, final Map<String, Object> orderMap,
			final Integer curPage, final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Criteria criteria = session.createCriteria(cs);
						Criteria criteriaTot = session.createCriteria(cs);
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);
						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (s.indexOf(">") != -1) {
									criteria.add(Restrictions.gt(s.replace(">",
											""), map.get(s)));
									criteriaTot.add(Restrictions.gt(s.replace(
											">", ""), map.get(s)));
								} else if (s.indexOf("<") != -1) {
									criteria.add(Restrictions.lt(s.replace("<",
											""), map.get(s)));
									criteriaTot.add(Restrictions.lt(s.replace(
											"<", ""), map.get(s)));
								} else if (s.indexOf("%") != -1) {
									criteria.add(Restrictions.like(s.replace(
											"%", ""), "%" + map.get(s) + "%"));
									criteria.add(Restrictions.or(Restrictions
											.like(s.replace("%", ""), "%"
													+ map.get(s) + "%"),
											Restrictions.like(s
													.replace("%", ""), "%"
													+ map.get(s) + "%")));
									criteriaTot.add(Restrictions.like(s
											.replace("%", ""), "%" + map.get(s)
											+ "%"));
								} else {
									criteria
											.add(Restrictions.eq(s, map.get(s)));
									criteriaTot.add(Restrictions.eq(s, map
											.get(s)));
								}
							}
						}
						if (orderMap != null) {
							Set<String> key = orderMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (s.equals("rand")) {
									criteria
											.add(Restrictions
													.sqlRestriction("1=1 order by rand()"));
								} else {
									if (orderMap.get(s).equals("desc")) {
										criteria.addOrder(Order.desc(s));
									} else {
										criteria.addOrder(Order.asc(s));
									}
								}
							}
						}
						paging.setList(criteria.list());

						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}

	public Paging createQueryPaging(final String queryString,
			final String countQueryString, final String type,
			final Integer curPage, final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Integer cp = 0;
						if (curPage != null) {
							cp = curPage;
						}
						int start = cp * pageSize;
						if (type != null && type.equals("HQL")) {
							// 查询列表
							Query qObj = session.createQuery(queryString);
							qObj.setFirstResult(start);
							qObj.setMaxResults(pageSize);
							List list = qObj.list();
							paging.setList(list);

							// 查询数量
							Query countQueryObj = session
									.createQuery(countQueryString);
							// BigInteger totalRows =
							// (BigInteger)countQueryObj.uniqueResult();
							// Long totalRows =((Long)
							// countQueryObj.uniqueResult()).longValue();
							Integer totalRows = Integer.parseInt(countQueryObj
									.uniqueResult().toString());
							paging.setTotalPage(totalRows.intValue());

							paging.setTotalRows(totalRows.intValue());
							paging.setPageRows(list.size());
						} else if (type != null && type.equals("SQL")) {
							// 查询列表
							SQLQuery qObj = session.createSQLQuery(queryString);
							qObj.setFirstResult(start);
							qObj.setMaxResults(pageSize);
							List list = qObj.list();
							paging.setList(list);

							// 查询数量
							SQLQuery countQueryObj = session
									.createSQLQuery(countQueryString);
							// BigInteger totalRows =((BigInteger)
							// countQueryObj.uniqueResult());
							// Long totalRows =((Long)
							// countQueryObj.uniqueResult()).longValue();
							Integer totalRows = Integer.parseInt(countQueryObj
									.uniqueResult().toString());
							paging.setTotalPage(totalRows.intValue());

							paging.setTotalRows(totalRows.intValue());
							paging.setPageRows(list.size());
						} else {
							paging.setTotalPage(0);
						}
						paging.setCurPage(cp);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}

	public Paging createQueryPaging2(final String queryString,
			final String countQueryString, final String type,
			final Integer curPage, final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Integer cp = 0;
						if (curPage != null) {
							cp = curPage;
						}
						int start = cp * pageSize;
						if (type != null && type.equals("HQL")) {
							// 查询列表
							Query qObj = session.createQuery(queryString);
							qObj.setFirstResult(start);
							qObj.setMaxResults(pageSize);
							List list = qObj.list();
							paging.setList(list);

							// 查询数量
							Query countQueryObj = session
									.createQuery(countQueryString);
							// BigInteger totalRows =
							// (BigInteger)countQueryObj.uniqueResult();
							// Long totalRows =((Long)
							// countQueryObj.uniqueResult()).longValue();
							Integer totalRows = Integer.parseInt(countQueryObj
									.uniqueResult().toString());
							paging.setTotalPage(totalRows.intValue());

							paging.setTotalRows(totalRows.intValue());
							paging.setPageRows(list.size());
						} else if (type != null && type.equals("SQL")) {
							// 查询列表
							SQLQuery qObj = session.createSQLQuery(queryString);
							qObj.setFirstResult(start);
							qObj.setMaxResults(pageSize);
							qObj.addScalar("finish_time",  StandardBasicTypes.STRING);
							qObj.addScalar("people_name",  StandardBasicTypes.STRING);
							List list = qObj.list();
							paging.setList(list);

							// 查询数量
							SQLQuery countQueryObj = session
									.createSQLQuery(countQueryString);
							// BigInteger totalRows =((BigInteger)
							// countQueryObj.uniqueResult());
							// Long totalRows =((Long)
							// countQueryObj.uniqueResult()).longValue();
							Integer totalRows = Integer.parseInt(countQueryObj
									.uniqueResult().toString());
							paging.setTotalPage(totalRows.intValue());
							paging.setTotalRows(totalRows.intValue());
							paging.setPageRows(list.size());
						} else {
							paging.setTotalPage(0);
						}
						paging.setCurPage(cp);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> createSqlQuery(final String queryString) {
		return (List<Object[]>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public List<Object[]> doInHibernate(
							org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createSQLQuery(queryString);
						List<Object[]> rows = query.list();
						if (session != null && session.isConnected()) {
							session.close();
						}
						return rows;
					}
				});
	}

	public Object saveOrUpdate(final Object model) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.saveOrUpdate(model);
				if (session != null && session.isConnected()) {
					session.close();
				}
				return null;
			}
		});
	}

	public void delete(final Object model) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.delete(model);
				if (session != null && session.isConnected()) {
					session.close();
				}
				return null;
			}
		});
	}

	public Object getObjectById(final Class cs, final Integer id) {
		return (Object) getHibernateTemplate().get(cs, id);
	}

	public boolean saveOrUpdateBySQL(String qStr, String type) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		if (type != null && type.equals("SQL")) {
			session.createSQLQuery(qStr).executeUpdate();
			if (session != null && session.isConnected()) {
				session.close();
			}
			return true;
		} else if (type != null && type.equals("HQL")) {
			session.createQuery(qStr).executeUpdate();
			if (session != null && session.isConnected()) {
				session.close();
			}
			return true;
		}
		return false;
	}

	/**
	 * sql
	 */
	@SuppressWarnings("unchecked")
	public List<Object> createsqlQuery(final String queryString) {
		return (List<Object>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Query query = session.createSQLQuery(queryString);
						List<Object> rows = query.list();
						if (session != null && session.isConnected()) {
							session.close();
						}
						return rows;
					}
				});
	}

	public Object save(final Object model) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.save(model);
				if (session != null && session.isConnected()) {
					session.close();
				}
				return null;
			}
		});
	}

	public void deleteSql(final String deleteSql) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.createSQLQuery(deleteSql).executeUpdate();
				if (session != null && session.isConnected()) {
					session.close();
				}
				return null;
			}
		});
	}

	/**
	 * sqlQuery
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> createSqlQuery(final String queryString,
			final Map<String, Type> addMap) {
		return (List<Object[]>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public List<Object[]> doInHibernate(
							org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						SQLQuery query = session.createSQLQuery(queryString);
						if (addMap != null) {
							Set<String> key = addMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								query.addScalar(s, addMap.get(s));
							}
						}
						List<Object[]> rows = query.list();
						if (session != null && session.isConnected()) {
							session.close();
						}
						return rows;
					}
				});
	}

	public Object saveOrUpdateForSp(final Object model) {
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				session.clear();
				session.saveOrUpdate(model);
				session.merge(model);
				if (session != null && session.isConnected()) {
					session.close();
				}
				return null;
			}
		});
	}

	public List<Map<String, Object>> queryBySQL(final String sql) {
		return queryBySQL(sql, null);
	}

	public List<Map<String, Object>> queryBySQL(final String sql,
			final Object[] params) {
		return queryBySQL(sql, params, null, -1, -1);
	}

	public List<Map<String, Object>> queryBySQL(final String sql,
			final Object[] params, final Map<String, Type> scalars) {
		return queryBySQL(sql, params, scalars, -1, -1);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryBySQL(final String sql,
			final Object[] params, final Map<String, Type> scalars,
			final Integer firstResult, final Integer maxResults) {
		if (StringUtils.isBlank(sql)) {
			return new ArrayList<Map<String, Object>>();
		} else {
			return getHibernateTemplate().execute(
					new HibernateCallback<List<Map<String, Object>>>() {
						public List<Map<String, Object>> doInHibernate(
								org.hibernate.Session session)
								throws org.hibernate.HibernateException {
							SQLQuery query = session.createSQLQuery(sql);
							if (params != null) {
								for (int i = 0; i < params.length; i++) {
									query.setParameter(i, params[i]);
								}
							}
							if (scalars != null) {
								Iterator<String> iterator = scalars.keySet()
										.iterator();
								while (iterator.hasNext()) {
									String s = iterator.next();
									query.addScalar(s, scalars.get(s));
								}
							}
							query
									.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
							if (firstResult == null) {
								query.setFirstResult(0);
							} else if (firstResult != -1) {
								query.setFirstResult(firstResult);
							}
							query.setMaxResults(maxResults);

							return query.list();
						}
					});
		}
	}

	public Paging createSQLQueryPaging(String sqlList, String sqlCount,
			Integer curPage, Integer pageSize) {
		return createSQLQueryPaging(sqlList, sqlCount, null, curPage, pageSize);
	}

	public Paging createSQLQueryPaging(String sqlList, String sqlCount,
			Object[] params, Integer curPage, Integer pageSize) {
		Paging paging = new Paging();

		if (curPage == null) {
			curPage = Integer.valueOf(0);
		}

		int firstResult = curPage * pageSize;
		paging
				.setList(queryBySQL(sqlList, params, null, firstResult,
						pageSize));

		int totalRows = findCountBySQL(sqlCount, params);
		// 三个都是总行数
		paging.setTotalRows(totalRows);
		paging.setPageRows(totalRows);
		paging.setTotalPage(totalRows);

		paging.setCurPage(curPage);
		paging.setPageSize(pageSize);
		return paging;
	}

	public int findCountBySQL(final String sql) {
		return findCountBySQL(sql, null);
	}

	public int findCountBySQL(final String sql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				Number num = (Number) query.uniqueResult();
				if (num == null) {
					return 0;
				} else {
					return num.intValue();
				}
			}
		});
	}

	public int findCountByHQL(final String hql) {
		return findCountByHQL(hql, null);
	}

	public int findCountByHQL(final String hql, final Object[] params) {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(org.hibernate.Session session)
					throws org.hibernate.HibernateException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				Number num = (Number) query.uniqueResult();
				if (num == null) {
					return 0;
				} else {
					return num.intValue();
				}
			}
		});
	}

	public int updateBySQL(String sql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		int rows = session.createSQLQuery(sql).executeUpdate();
		if (session != null && session.isConnected()) {
			session.close();
		}
		return rows;
	}

	/**
	 * 带有分页的查询
	 * 
	 * @param cs
	 *            查询类
	 * @param map
	 *            查询条件 key：字段名 value：字段值
	 * @param curPage
	 *            当前页码
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Paging createQueryPagingLike(final Class cs,
			final Map<String, Object> map, final Map<String, Object> orderMap,
			final Integer curPage, final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Criteria criteria = session.createCriteria(cs);
						Criteria criteriaTot = session.createCriteria(cs);
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);

						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								criteria.add(Restrictions.or(Restrictions.like(
										s.replace("%", ""), "%" + map.get(s)
												+ "%"), Restrictions.like(s
										.replace("%", ""), "%" + map.get(s)
										+ "%")));
								criteriaTot.add(Restrictions.or(Restrictions
										.like(s.replace("%", ""), "%"
												+ map.get(s) + "%"),
										Restrictions.like(s.replace("%", ""),
												"%" + map.get(s) + "%")));
							}
						}
						if (orderMap != null) {
							Set<String> key = orderMap.keySet();
							for (Iterator it = key.iterator(); it.hasNext();) {
								String s = (String) it.next();
								if (orderMap.get(s).equals("desc")) {
									criteria.addOrder(Order.desc(s));
								} else {
									criteria.addOrder(Order.asc(s));
								}
							}
						}

						paging.setList(criteria.list());
						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}

	public Paging createQueryPaging3(final Class cs, final List<String> sl, final String name,
			final Integer curPage,final Integer pageSize) {
		return (Paging) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Paging paging = new Paging();
						Criteria criteria = session.createCriteria(cs);
						Criteria criteriaTot = session.createCriteria(cs);
						criteria.setFirstResult(curPage * pageSize);
						criteria.setMaxResults(pageSize);

						 Disjunction disjunction =  Restrictions.disjunction();
						 for(String s : sl){
							 disjunction.add(Restrictions.like(s, "%" + name + "%"));
						 }
						 
						 criteria.add(disjunction);
						 criteriaTot.add(disjunction);

						paging.setList(criteria.list());
						Long totalRows = ((Long) criteriaTot.setProjection(
								Projections.rowCount()).uniqueResult())
								.longValue();
						paging.setTotalPage(totalRows.intValue());
						paging.setCurPage(curPage);
						paging.setPageSize(pageSize);

						if (session != null && session.isConnected()) {
							session.close();
						}
						return paging;
					}
				});
	}
}
