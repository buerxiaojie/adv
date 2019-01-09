package com.zjht.adv.manager.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.dao.AdminLogDao;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.entity.User;
import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class AdminLogMngImpl implements AdminLogMng {
	@Override
	@Transactional(readOnly = true)
	public Pagination getPage(String userName, Integer type, String details, Date startTime, Date endTime, int pageNo, int pageSize) {
		return dao.getPage(userName, type, details, startTime, endTime, pageNo, pageSize);
	}

	@Override
	public AdminLog findById(Long id) {
		AdminLog entity = dao.findById(id);
		return entity;
	}

	@Override
	public AdminLog save(User user,String details, Integer type, Integer result) {
		AdminLog bean=new AdminLog();
		bean.setUser(user);
		bean.setDetails(details);
		bean.setType(type);
		bean.setResult(result);
		bean.setTime(new Timestamp(System.currentTimeMillis()));
		AdminLog entity = dao.save(bean);
		return entity;
	}

	@Override
	public AdminLog save(String details, Integer type, Integer result) {
		User user=AdminThread.get();
		AdminLog bean=new AdminLog();
		bean.setUser(user);
		bean.setDetails(details);
		bean.setType(type);
		bean.setResult(result);
		bean.setTime(new Timestamp(System.currentTimeMillis()));
		AdminLog entity = dao.save(bean);
		return entity;
	}

	@Autowired
	private AdminLogDao dao;
}