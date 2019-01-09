package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.ProvinceDao;
import com.zjht.adv.entity.Province;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.ProvinceMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class ProvinceMngImpl implements ProvinceMng {
	@Override
	@Transactional(readOnly = true)
	public List<Province> getList() {
		List<Province> list = dao.getList();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		return dao.getPage(pageNo, pageSize);
	}

	@Override
	public Province findById(Long id) {
		Province entity = dao.findById(id);
		return entity;
	}

	@Override
	public Province save(Province bean) {
		Province entity = dao.save(bean);
		return entity;
	}

	@Override
	public Province update(Province bean) {
		Updater<Province> updater = new Updater<Province>(bean);
		Province entity = dao.updateByUpdater(updater);
		return entity;
	}

	@Autowired
	private ProvinceDao dao;
}