package com.zjht.adv.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.AdvertiseDao;
import com.zjht.adv.entity.Advertise;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.util.DateTimeUtils;

@Repository
public class AdvertiseDaoImpl extends HibernateBaseDao<Advertise, Long>
		implements AdvertiseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Advertise> getList(Advertise bean){
		Finder f = Finder.create("from Advertise bean where 1=1");
		if (bean!=null) {
			if (StringUtils.isNotBlank(bean.getAdvName())) {
				f.append(" and bean.advName like '%").append(bean.getAdvName()).append("%'");
			}
			if (bean.getAdvCreateTime()!=null) {
				f.append(" and date_format(bean.advCreateTime,'%Y-%m-%d') = :ctime").setParam("ctime", DateTimeUtils.formatDateStr(bean.getAdvCreateTime(), "yyyy-MM-dd"));
			}
			if (bean.getAdvUpdateTime()!=null) {
				f.append(" and date_format(bean.advUpdateTime,'%Y-%m-%d') = :utime").setParam("utime", DateTimeUtils.formatDateStr(bean.getAdvUpdateTime(), "yyyy-MM-dd"));
			}
//			if (bean.getAdvPosition()!=null) {
//				f.append(" and bean.advPosition=:advPosition").setParam("advPosition", bean.getAdvPosition());
//			}
			if (bean.getPosition()!=null&&bean.getPosition().getId()!=null) {
				f.append(" and bean.position.id=:apid").setParam("apid", bean.getPosition().getId());
			}
			if (bean.getAdvStatus()!=null) {
				f.append(" and bean.advStatus=:status").setParam("status", bean.getAdvStatus());
			}
			if (StringUtils.isNotBlank(bean.getSortType())) {
				f.append(" order by bean.advSort ").append(bean.getSortType());
			}
		}
		if (StringUtils.isNotBlank(bean.getSortType())) {
			f.append(",bean.id desc");
		}else{
			f.append(" order by bean.id desc");
		}
		return find(f);
	}
	
	@Override
	public Pagination getPage(Advertise bean, int pageNo,
			int pageSize) {
		Finder f = Finder.create("from Advertise bean where 1=1");
		if (bean!=null) {
			if (StringUtils.isNotBlank(bean.getAdvName())) {
				f.append(" and bean.advName like '%").append(bean.getAdvName()).append("%'");
			}
			if (bean.getAdvCreateTime()!=null) {
				f.append(" and date_format(bean.advCreateTime,'%Y-%m-%d') = :ctime").setParam("ctime", DateTimeUtils.formatDateStr(bean.getAdvCreateTime(), "yyyy-MM-dd"));
			}
			if (bean.getAdvUpdateTime()!=null) {
				f.append(" and date_format(bean.advUpdateTime,'%Y-%m-%d') = :utime").setParam("utime", DateTimeUtils.formatDateStr(bean.getAdvUpdateTime(), "yyyy-MM-dd"));
			}
//			if (bean.getAdvPosition()!=null) {
//				f.append(" and bean.advPosition=:advPosition").setParam("advPosition", bean.getAdvPosition());
//			}
			if (bean.getPosition()!=null&&bean.getPosition().getId()!=null) {
				f.append(" and bean.position.id=:apid").setParam("apid", bean.getPosition().getId());
			}
			if (bean.getAdvStatus()!=null) {
				f.append(" and bean.advStatus=:status").setParam("status", bean.getAdvStatus());
			}
			if (StringUtils.isNotBlank(bean.getSortType())) {
				f.append(" order by bean.advSort ").append(bean.getSortType());
			}
		}
		if (StringUtils.isNotBlank(bean.getSortType())) {
			f.append(",bean.id desc");
		}else{
			f.append(" order by bean.id desc");
		}
		return find(f,pageNo,pageSize);
	}

	@Override
	public Advertise findById(Long id) {
		return get(id);
	}

	@Override
	public Long save(Advertise bean) {
		return (Long) getSession().save(bean);
	}

	@Override
	public int deleteById(Long id) {
		Query q=getSession().createQuery("update Advertise bean set bean.advStatus=:status where bean.id=:id");
		q.setParameter("status", Advertise.STATUS_DISABLE);
		q.setParameter("id", id);
		return q.executeUpdate();
	}

	@Override
	public int delByIds(List<Long> ids) {
		Query q=getSession().createQuery("update Advertise bean set bean.advStatus=:status where bean.id in (:ids)");
		q.setParameter("status", Advertise.STATUS_DISABLE);
		q.setParameterList("ids", ids);
		return q.executeUpdate();
	}

	@Override
	protected Class<Advertise> getEntityClass() {
		return Advertise.class;
	}

	@Override
	public void delete(Long id) {
		Advertise adv=get(id);
		if (adv!=null) {
			getSession().delete(adv);
		}
	}

}
