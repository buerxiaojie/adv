package com.zjht.adv.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.MobileVerifyDao;
import com.zjht.adv.entity.MobileVerify;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;

@Transactional
public class MobileVerifyDaoImpl extends HibernateBaseDao<MobileVerify, Long> implements MobileVerifyDao {

	@Override
	public MobileVerify save(MobileVerify bean) {
		this.getSession().save(bean);
		return bean;
	}

	@Override
	public MobileVerify update(MobileVerify bean) {
		this.getSession().update(bean);
		return bean;
	}

	@Override
	public MobileVerify findById(Long id) {
		return this.get(id);
	}

	@Override
	public MobileVerify findByMobile(String mobile) {
		Finder f = Finder.create("from MobileVerify bean where bean.mobile=:mobile order by bean.id desc");
		f.setParam("mobile", mobile);
		@SuppressWarnings("unchecked")
		List<MobileVerify> list=find(f);
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		MobileVerify bean = this.get(id);
		if (bean != null) {
			this.getSession().delete(bean);
		}
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<MobileVerify> findExpiredMobile(Long time) {
		Finder f = Finder.create("from MobileVerify bean where bean.sendTime<:sendTime");
		f.setParam("sendTime", new Timestamp(System.currentTimeMillis() - time));
		return find(f);
	}

	@Override
	public MobileVerify findByIp(String ip) {
		Finder f = Finder.create("from MobileVerify bean where bean.ipAddr=:ip order by bean.sendTime desc");
		f.setParam("ip", ip);
		return (find(f) == null || find(f).size() < 1) ? null : ((MobileVerify) find(f).get(0));
	}

	@Override
	protected Class<MobileVerify> getEntityClass() {
		// TODO Auto-generated method stub
		return MobileVerify.class;
	}

}
