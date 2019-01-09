package com.zjht.adv.dao.impl;

import java.util.List;

import com.zjht.adv.dao.UserRoleDao;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

public class UserRoleDaoImpl extends HibernateBaseDao<UserRole, Long> implements UserRoleDao {

    @Override
    @SuppressWarnings({ "unchecked" })
    public List<UserRole> getList(Long id) {
        Finder f = Finder.create("from UserRole bean where 1=1");
        f.append(" and bean.user.id=:id");
        f.setParam("id", id);
        return find(f);
    }

    @Override
    public Pagination getPage(UserRole bean, int pageNo, int pageSize) {
        Finder f = Finder.create("from User bean where 1=1 ");
        if (bean != null) {
            if (bean.getUser() != null) {
                f.append(" and bean.user = :user").setParam("user", bean.getUser());
            }
            if (bean.getRole() != null) {
                f.append(" and bean.role = :role").setParam("role", bean.getRole());
            }
        }
        f.append(" order by bean.id desc");
        return find(f, pageNo, pageSize);
    }

    public UserRole findById(Long id) {
        UserRole entity = get(id);
        return entity;
    }

    @Override
    public UserRole save(UserRole bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public void delete(Long id) {
        UserRole ur = get(id);
        getSession().delete(ur);
    }

    @Override
    protected Class<UserRole> getEntityClass() {
        return UserRole.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> findByUser(User user) {
        Finder f = Finder.create("from UserRole bean where 1=1");
        if (user != null) {
            f.append(" and bean.user=:user");
            f.setParam("user", user);
        }
        return find(f);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserRole> findByRoleId(Long roleId) {
        Finder f = Finder.create("from UserRole bean where 1=1");
        if (roleId != null) {
            f.append(" and bean.role.id=:roleId");
            f.setParam("roleId", roleId);
        }
        return find(f);
    }

}
