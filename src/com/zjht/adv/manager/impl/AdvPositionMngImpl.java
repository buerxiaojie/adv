package com.zjht.adv.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.AdvPositionDao;
import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.AdvPositionMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class AdvPositionMngImpl implements AdvPositionMng {

	@Autowired
	private AdvPositionDao advPositionDao;

	@Transactional(readOnly=true)
	@Override
	public List<AdvPosition> getList(AdvPosition bean) {
		return advPositionDao.getList(bean);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Pagination getPage(AdvPosition bean, int pageNo, int pageSize) {
		return advPositionDao.getPage(bean, pageNo, pageSize);
	}
	
	@Transactional(readOnly=true)
	@Override
	public AdvPosition findById(Long id) {
		return advPositionDao.findById(id);
	}

	@Override
	public Long save(AdvPosition bean) {
		return advPositionDao.save(bean);
	}

	@Override
	public AdvPosition updateByUpdater(AdvPosition bean) {
		Updater<AdvPosition> updater=new Updater<AdvPosition>(bean);
		return advPositionDao.updateByUpdater(updater);
	}
	
	@Override
	public boolean deleteById(Long id) {
		if (advPositionDao.deleteById(id)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delByIds(List<Long> ids) {
		boolean flag=true;
		for (Long id : ids) {
			delete(id);
		}
		return flag;
	}

	@Override
	public void delete(Long id) {
		advPositionDao.delete(id);
	}

}
