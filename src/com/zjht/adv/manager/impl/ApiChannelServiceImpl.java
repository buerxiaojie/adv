package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.ApiChannelServiceDao;
import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.ApiChannelServiceMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class ApiChannelServiceImpl implements ApiChannelServiceMng {

	@Autowired
	private ApiChannelServiceDao apiChannelServiceDao;

	@Override
	public List<ApiChannelService> getList(ApiChannelService bean) {
		return apiChannelServiceDao.getList(bean);
	}

	@Override
	public Pagination getPage(ApiChannelService bean, int pageNo, int pageSize) {
		return apiChannelServiceDao.getPage(bean, pageNo, pageSize);
	}

	@Override
	public ApiChannelService findById(Long id) {
		return apiChannelServiceDao.findById(id);
	}

	@Override
	public Long save(ApiChannelService bean) {
		return apiChannelServiceDao.save(bean);
	}

	@Override
	public ApiChannelService updateByUpdater(ApiChannelService bean) {
		return apiChannelServiceDao.updateByUpdater(new Updater<ApiChannelService>(bean));
	}

	@Override
	public void delete(Long id) {
		apiChannelServiceDao.delete(id);
	}

}
