package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.AreaDao;
import com.zjht.adv.entity.Area;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class AreaDaoImpl extends HibernateBaseDao<Area, Long> implements AreaDao {

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<Area> getList(Long cityId) {
		Finder f = Finder.create("from Area bean where 1=1");
		if (cityId != null) {
			f.append(" and bean.city.id=:cityId");
			f.setParam("cityId", cityId);
		}
		f.append(" order by bean.id asc");
		return find(f);

	}

	@Override
	public Pagination getPage(Long cityId, int pageNo, int pageSize) {
		Finder f = Finder.create("from Area bean where 1=1 ");
		if (cityId != null) {
			f.append(" and bean.city.id=:cityId");
			f.setParam("cityId", cityId);
		}
		f.append(" order by bean.id asc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public Area findById(Long id) {
		Area entity = get(id);
		return entity;
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public Area findByName(String name, Long cityId) {
		Finder f = Finder.create("from Area bean where 1=1");
		if (name != null) {
			f.append(" and bean.name=:name");
			f.setParam("name", name);
		} else {
			return null;
		}
		if (cityId != null) {
			f.append(" and bean.city.id=:cityId");
			f.setParam("cityId", cityId);
		} else {
			return null;
		}
		List<Area> list = find(f);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Area save(Area bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<Area> getEntityClass() {
		return Area.class;
	}
}