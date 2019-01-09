package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.CityDao;
import com.zjht.adv.entity.City;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.CityMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class CityMngImpl implements CityMng {
	@Override
	@Transactional(readOnly = true)
	public List<City> getList(Long provinceId) {
		List<City> list = dao.getList(provinceId);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(Long provinceId, int pageNo, int pageSize) {
		return dao.getPage(provinceId, pageNo, pageSize);
	}

	@Override
	public City findById(Long id) {
		City entity = dao.findById(id);
		return entity;
	}

	@Override
	public City save(City bean) {
		City entity = dao.save(bean);
		return entity;
	}

	@Override
	public City update(City bean) {
		Updater<City> updater = new Updater<City>(bean);
		City entity = dao.updateByUpdater(updater);
		return entity;
	}

	@Autowired
	private CityDao dao;
}