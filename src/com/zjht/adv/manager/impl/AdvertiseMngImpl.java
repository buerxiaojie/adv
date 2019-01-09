package com.zjht.adv.manager.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.AdvertiseDao;
import com.zjht.adv.entity.Advertise;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.AdvertiseMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class AdvertiseMngImpl implements AdvertiseMng {

	@Autowired
	private AdvertiseDao advertiseDao;
	@Transactional(readOnly=true)
	@Override
	public List<Advertise> getList(Advertise bean){
		return advertiseDao.getList(bean);
	}
	@Transactional(readOnly=true)
	@Override
	public Pagination getPage(Advertise bean, int pageNo, int pageSize) {
		return advertiseDao.getPage(bean, pageNo, pageSize);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Advertise findById(Long id) {
		return advertiseDao.findById(id);
	}

	@Override
	public Long save(Advertise bean) {
		return advertiseDao.save(bean);
	}

	@Override
	public Advertise updateByUpdater(Advertise bean) {
		Updater<Advertise> updater=new Updater<Advertise>(bean);
		return advertiseDao.updateByUpdater(updater);
	}
	
	@Override
	public boolean deleteById(Long id) {
		if (advertiseDao.deleteById(id)>0) {
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
		advertiseDao.delete(id);
	}

}
