package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.AreaDao;
import com.zjht.adv.entity.Area;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.AreaMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class AreaMngImpl implements AreaMng {
	@Override
	@Transactional(readOnly = true)
	public List<Area> getList(Long cityId) {
		List<Area> list = dao.getList(cityId);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(Long cityId, int pageNo, int pageSize) {
		return dao.getPage(cityId, pageNo, pageSize);
	}

	@Override
	public Area findById(Long id) {
		Area entity = dao.findById(id);
		return entity;
	}

	@Override
	public Area save(Area bean) {
		Area entity = dao.save(bean);
		return entity;
	}

	@Override
	public Area update(Area bean) {
		Updater<Area> updater = new Updater<Area>(bean);
		Area entity = dao.updateByUpdater(updater);
		return entity;
	}

	@Autowired
	private AreaDao dao;
}