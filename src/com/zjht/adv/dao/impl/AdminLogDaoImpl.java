package com.zjht.adv.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.AdminLogDao;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class AdminLogDaoImpl extends HibernateBaseDao<AdminLog, Long> implements AdminLogDao {

	@Override
	public Pagination getPage(String userName, Integer type, String details, Date startTime, Date endTime, int pageNo, int pageSize) {
		Finder f = Finder.create("from AdminLog bean where 1=1 ");
		if (StringUtils.isNotBlank(userName)) {
			f.append(" and bean.user.userName like :userName");
			f.setParam("userName", "%" + userName + "%");
		}
		if (StringUtils.isNotBlank(details)) {
			f.append(" and bean.details like :details");
			f.setParam("details", "%" + details + "%");
		}
		if (type != null) {
			f.append(" and bean.type = :type").setParam("type", type);
		}
		if (startTime != null) {
			f.append(" and bean.time >= :startTime").setParam("startTime", startTime);
		}
		if (endTime != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			cal.add(Calendar.DATE, 1);
			f.append(" and bean.time < :endTime").setParam("endTime", cal.getTime());
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public AdminLog findById(Long id) {
		AdminLog entity = get(id);
		return entity;
	}

	@Override
	public AdminLog save(AdminLog bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<AdminLog> getEntityClass() {
		return AdminLog.class;
	}
}