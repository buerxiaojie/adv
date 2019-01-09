package com.zjht.adv.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.ApiChannelServiceDao;
import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class ApiChannelServiceDaoImpl extends HibernateBaseDao<ApiChannelService, Long> implements ApiChannelServiceDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ApiChannelService> getList(ApiChannelService bean) {
		Finder f = Finder.create("from ApiChannelService bean where 1=1");
		if (bean!=null) {
			if (bean.getApiChannel()!=null) {
				if (bean.getApiChannel().getId()!=null) {
					f.append(" and bean.apiChannel.id=:acid").setParam("acid", bean.getApiChannel().getId());
				}
			}
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
			if (StringUtils.isNotBlank(bean.getServiceName())) {
				f.append(" and bean.serviceName=:serName").setParam("serName", bean.getServiceName());
			}
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@Override
	public Pagination getPage(ApiChannelService bean, int pageNo, int pageSize) {
		Finder f = Finder.create("from ApiChannelService bean where 1=1");
		if (bean!=null) {
			if (bean.getApiChannel()!=null) {
				if (bean.getApiChannel().getId()!=null) {
					f.append(" and bean.apiChannel.id=:acid").setParam("acid", bean.getApiChannel().getId());
				}
			}
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
			if (StringUtils.isNotBlank(bean.getServiceName())) {
				f.append(" and bean.serviceName=:serName").setParam("serName", bean.getServiceName());
			}
		}
		f.append(" order by bean.id desc");
		return find(f,pageNo,pageSize);
	}

	@Override
	public ApiChannelService findById(Long id) {
		return get(id);
	}

	@Override
	public Long save(ApiChannelService bean) {
		return (Long) getSession().save(bean);
	}

	@Override
	public void delete(Long id) {
		ApiChannelService acs=get(id);
		if (acs!=null) {
			getSession().delete(acs);
		}
	}

	@Override
	protected Class<ApiChannelService> getEntityClass() {
		return ApiChannelService.class;
	}

}
