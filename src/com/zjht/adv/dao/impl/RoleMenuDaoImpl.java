package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.RoleMenuDao;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class RoleMenuDaoImpl extends HibernateBaseDao<RoleMenu, Long> implements RoleMenuDao {

    @Override
    @SuppressWarnings({ "unchecked" })
    public List<RoleMenu> getList(Long id) {
        Finder f = Finder.create("from RoleMenu bean where 1=1");
        if (id != null) {
            f.append(" and bean.id=:id");
            f.setParam("id", id);
        }
        f.append(" order by bean.id asc");
        return find(f);

    }

    @Override
    public Pagination getPage(Long id, int pageNo, int pageSize) {
        Finder f = Finder.create("from RoleMenu bean where 1=1 ");
        if (id != null) {
            f.append(" and bean.id=:id");
            f.setParam("id", id);
        }
        f.append(" order by bean.id asc");
        return find(f, pageNo, pageSize);
    }

    @Override
    public RoleMenu findById(Long id) {
        RoleMenu entity = get(id);
        return entity;
    }

    @Override
    public RoleMenu save(RoleMenu bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public void delete(Long id) {
        RoleMenu c=get(id);
        if (c!=null) {
            getSession().delete(c);
        }
    }

    @Override
    protected Class<RoleMenu> getEntityClass() {
        return RoleMenu.class;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<RoleMenu> findByRoleId(Long roleId) {
        Finder f = Finder.create("from RoleMenu bean where 1=1");
        if (roleId != null) {
            f.append(" and bean.role.id=:roleId");
            f.setParam("roleId", roleId);
        }
        f.append(" order by bean.id asc");
        return find(f);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> findByRoles(List<Role> roles) {
		Finder f = Finder.create("select bean.menu from RoleMenu bean where bean.menu.delStatus=:delStatus").setParam("delStatus", SysMenu.DEL_UNDONE);
        if (roles != null&&roles.size()>0) {
            f.append(" and bean.role in (:roles)");
            f.setParamList("roles", roles.toArray());
        }
        f.append(" order by bean.menu.sort desc");
        return find(f);
	}

}