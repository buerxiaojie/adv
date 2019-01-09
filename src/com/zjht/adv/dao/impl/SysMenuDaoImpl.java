package com.zjht.adv.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.SysMenuDao;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class SysMenuDaoImpl extends HibernateBaseDao<SysMenu, Integer> implements SysMenuDao {

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<SysMenu> getList() {
		Finder f = Finder.create("from SysMenu bean where bean.delStatus=:delStatus order by bean.sort desc").setParam("delStatus", SysMenu.DEL_UNDONE);
		return find(f);

	}

	@Override
	public Pagination getPage(SysMenu menu,int pageNo, int pageSize) {
		Finder f = Finder.create("from SysMenu bean where 1=1 ");
		if (menu!=null) {
			if (menu.getDelStatus()!=null) {
				f.append(" and bean.delStatus!=:delStatus").setParam("delStatus", SysMenu.DEL_UNDONE);
			}
		}
		f.append(" order by bean.sort desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public SysMenu findById(Integer id) {
		SysMenu entity = get(id);
		return entity;
	}

	@Override
	public SysMenu save(SysMenu bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<SysMenu> getEntityClass() {
		return SysMenu.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> findByUID(Long userid) {
		Finder f = Finder.create("from SysMenu bean where bean.delStatus=:delStatus order by bean.sort desc").setParam("delStatus", SysMenu.DEL_UNDONE);
		return find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> findByPids(String pids) {
		Finder f = Finder.create("from SysMenu bean");
		f.append(" where bean.parentIds like '%").append(pids).append("%' order by bean.sort desc");
		return find(f);
	}

	@Override
	public int delChildById(String cid) {
		StringBuilder sbuilder=new StringBuilder("update SysMenu bean set bean.delStatus=:delStatus where bean.parentIds like '");
		sbuilder.append(cid).append("%'");
		Query q = getSession().createQuery(sbuilder.toString());
		q.setParameter("delStatus", SysMenu.DEL_DONE);
		return q.executeUpdate();
	}
}