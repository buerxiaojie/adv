package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.CityDao;
import com.zjht.adv.entity.City;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class CityDaoImpl extends HibernateBaseDao<City, Long> implements CityDao {

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<City> getList(Long provinceId) {
		Finder f = Finder.create("from City bean where 1=1");
		if (provinceId != null) {
			f.append(" and bean.province.id=:provinceId");
			f.setParam("provinceId", provinceId);
		}
		f.append(" order by bean.id asc");
		return find(f);

	}

	@Override
	public Pagination getPage(Long provinceId, int pageNo, int pageSize) {
		Finder f = Finder.create("from City bean where 1=1 ");
		if (provinceId != null) {
			f.append(" and bean.province.id=:provinceId");
			f.setParam("provinceId", provinceId);
		}
		f.append(" order by bean.id asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public City findById(Long id) {
		City entity = get(id);
		return entity;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public City findByName(String name, Long provinceId) {
		Finder f = Finder.create("from City bean where 1=1");
		if (name != null) {
			f.append(" and bean.name=:name");
			f.setParam("name", name);
		} else {
			return null;
		}
		if (provinceId != null) {
			f.append(" and bean.province.id=:provinceId");
			f.setParam("provinceId", provinceId);
		} else {
			return null;
		}
		List<City> list = find(f);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public City save(City bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<City> getEntityClass() {
		return City.class;
	}
}