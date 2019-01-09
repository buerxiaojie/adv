package com.zjht.adv.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.ApiChannelDao;
import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class ApiChannelDaoImpl extends HibernateBaseDao<ApiChannel, Long> implements ApiChannelDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ApiChannel> getList(ApiChannel bean) {
		Finder f = Finder.create("from ApiChannel bean where 1=1");
		if (bean!=null) {
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
			if (StringUtils.isNotBlank(bean.getAppNo())) {
				f.append(" and bean.appNo=:appNo").setParam("appNo", bean.getAppNo());
			}
			if (StringUtils.isNotBlank(bean.getAppAccount())) {
				f.append(" and bean.appAccount=:appAccount").setParam("appAccount", bean.getAppAccount());
			}
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@Override
	public Pagination getPage(ApiChannel bean, int pageNo, int pageSize) {
		Finder f = Finder.create("from ApiChannel bean where 1=1");
		if (bean!=null) {
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
			if (StringUtils.isNotBlank(bean.getAppNo())) {
				f.append(" and bean.appNo=:appNo").setParam("appNo", bean.getAppNo());
			}
			if (StringUtils.isNotBlank(bean.getAppAccount())) {
				f.append(" and bean.appAccount=:appAccount").setParam("appAccount", bean.getAppAccount());
			}
		}
		f.append(" order by bean.id desc");
		return find(f,pageNo,pageSize);
	}

	@Override
	public ApiChannel findById(Long id) {
		return get(id);
	}

	@Override
	public Long save(ApiChannel bean) {
		return (Long) getSession().save(bean);
	}

	@Override
	public void delete(Long id) {
		ApiChannel ac=get(id);
		if (ac!=null) {
			getSession().delete(ac);
		}
	}

	@Override
	protected Class<ApiChannel> getEntityClass() {
		return ApiChannel.class;
	}

}
