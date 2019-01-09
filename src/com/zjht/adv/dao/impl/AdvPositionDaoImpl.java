package com.zjht.adv.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.AdvPositionDao;
import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.util.DateTimeUtils;

@Repository
public class AdvPositionDaoImpl extends HibernateBaseDao<AdvPosition, Long>
		implements AdvPositionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<AdvPosition> getList(AdvPosition bean) {
		Finder f = Finder.create("from AdvPosition bean where bean.status!=:dstatus").setParam("dstatus", AdvPosition.STATUS_DISABLE);
		if (bean!=null) {
			if (StringUtils.isNotBlank(bean.getName())) {
				f.append(" and bean.name like '%").append(bean.getName()).append("%'");
			}
			if (bean.getCreateTime()!=null) {
				f.append(" and date_format(bean.createTime,'%Y-%m-%d') = :ctime").setParam("ctime", DateTimeUtils.formatDateStr(bean.getCreateTime(), "yyyy-MM-dd"));
			}
			if (bean.getUpdateTime()!=null) {
				f.append(" and date_format(bean.updateTime,'%Y-%m-%d') = :utime").setParam("utime", DateTimeUtils.formatDateStr(bean.getUpdateTime(), "yyyy-MM-dd"));
			}
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
		}
		f.append(" order by bean.id asc");
		return find(f);
	}
	
	@Override
	public Pagination getPage(AdvPosition bean, int pageNo,
			int pageSize) {
		Finder f = Finder.create("from AdvPosition bean where 1=1");
		if (bean!=null) {
			if (StringUtils.isNotBlank(bean.getName())) {
				f.append(" and bean.name like '%").append(bean.getName()).append("%'");
			}
			if (bean.getCreateTime()!=null) {
				f.append(" and date_format(bean.createTime,'%Y-%m-%d') = :ctime").setParam("ctime", DateTimeUtils.formatDateStr(bean.getCreateTime(), "yyyy-MM-dd"));
			}
			if (bean.getUpdateTime()!=null) {
				f.append(" and date_format(bean.updateTime,'%Y-%m-%d') = :utime").setParam("utime", DateTimeUtils.formatDateStr(bean.getUpdateTime(), "yyyy-MM-dd"));
			}
			
			if (bean.getStatus()!=null) {
				f.append(" and bean.status=:status").setParam("status", bean.getStatus());
			}
		}
		f.append(" order by bean.id desc");
		return find(f,pageNo,pageSize);
	}

	@Override
	public AdvPosition findById(Long id) {
		return get(id);
	}

	@Override
	public Long save(AdvPosition bean) {
		return (Long) getSession().save(bean);
	}

	@Override
	public int deleteById(Long id) {
		Query q=getSession().createQuery("update AdvPosition bean set bean.status=:status where bean.id=:id");
		q.setParameter("status", AdvPosition.STATUS_DISABLE);
		q.setParameter("id", id);
		return q.executeUpdate();
	}

	@Override
	public int delByIds(List<Long> ids) {
		Query q=getSession().createQuery("update AdvPosition bean set bean.status=:status where bean.id in (:ids)");
		q.setParameter("status", AdvPosition.STATUS_DISABLE);
		q.setParameterList("ids", ids);
		return q.executeUpdate();
	}

	@Override
	protected Class<AdvPosition> getEntityClass() {
		return AdvPosition.class;
	}

	@Override
	public void delete(Long id) {
		AdvPosition adv=get(id);
		if (adv!=null) {
			getSession().delete(adv);
		}
	}

}
