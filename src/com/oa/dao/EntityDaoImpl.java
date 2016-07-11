package com.oa.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.oa.util.Paging;

@SuppressWarnings("deprecation")
public class EntityDaoImpl extends HibernateDaoSupport implements EntityDao {

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
	public List<?> createCriteria(final Class<?> cs, final Map<String, Object> map,
			final Map<String, Object> orderMap) {
		return (List<?>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(org.hibernate.Session session)
							throws org.hibernate.HibernateException {
						Criteria criteria = session.createCriteria(cs);

						if (map != null) {
							Set<String> key = map.keySet();
							for (Iterator<?> it = key.iterator(); it.hasNext();) {
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
							for (Iterator<?> it = key.iterator(); it.hasNext();) {
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
	@SuppressWarnings({ })
	public Paging createQueryPaging(final Class<?> cs,
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
							for (Iterator<?> it = key.iterator(); it.hasNext();) {
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
							for (Iterator<?> it = key.iterator(); it.hasNext();) {
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

	public Object getObjectById(final Class<?> cs, final Integer id) {
		return (Object) getHibernateTemplate().get(cs, id);
	}

}
