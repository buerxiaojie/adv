package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.ProvinceDao;
import com.zjht.adv.entity.Province;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class ProvinceDaoImpl extends HibernateBaseDao<Province, Long> implements ProvinceDao {
	/**
	 * @see getList
	 */
	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Province> getList() {
		Finder f = Finder.create("from Province bean where 1=1");
		f.append(" order by bean.id asc");
		return find(f);
	}

	@Override
	public Pagination getPage(int pageNo, int pageSize) {
		Finder f = Finder.create("from Province bean where 1=1 ");
		f.append(" order by bean.id asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Province findById(Long id) {
		Province entity = get(id);
		return entity;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public Province findByName(String name) {
		Finder f = Finder.create("from Province bean where 1=1");
		if (name != null) {
			f.append(" and bean.name=:name");
			f.setParam("name", name);
		}else {
			return null;
		}
		List<Province> list = find(f);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Province save(Province bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<Province> getEntityClass() {
		return Province.class;
	}
}