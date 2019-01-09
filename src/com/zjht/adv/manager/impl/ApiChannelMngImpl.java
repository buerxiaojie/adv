package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.ApiChannelDao;
import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.ApiChannelMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class ApiChannelMngImpl implements ApiChannelMng {

	@Autowired
	private ApiChannelDao apiChannelDao;
	
	@Override
	public List<ApiChannel> getList(ApiChannel bean) {
		return apiChannelDao.getList(bean);
	}

	@Override
	public Pagination getPage(ApiChannel bean, int pageNo, int pageSize) {
		return apiChannelDao.getPage(bean, pageNo, pageSize);
	}

	@Override
	public ApiChannel findById(Long id) {
		return apiChannelDao.findById(id);
	}

	@Override
	public Long save(ApiChannel bean) {
		return apiChannelDao.save(bean);
	}

	@Override
	public ApiChannel updateByUpdater(ApiChannel bean) {
		return apiChannelDao.updateByUpdater(new Updater<ApiChannel>(bean));
	}

	@Override
	public void delete(Long id) {
		apiChannelDao.delete(id);
	}

}
