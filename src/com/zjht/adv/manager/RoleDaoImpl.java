package com.zjht.adv.manager;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.RoleDao;
import com.zjht.adv.entity.Role;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class RoleDaoImpl extends HibernateBaseDao<Role, Long> implements RoleDao {

    @Override
    @SuppressWarnings({ "unchecked" })
    public List<Role> getList(Long id) {
        Finder f = Finder.create("from Role bean where 1=1");
        if (id != null) {
            f.append(" and bean.id=:id");
            f.setParam("id", id);
        }
        f.append(" order by bean.id asc");
        return find(f);

    }

    @Override
    public Pagination getPage(Long id, int pageNo, int pageSize) {
        Finder f = Finder.create("from Role bean where 1=1 ");
        if (id != null) {
            f.append(" and bean.id=:id");
            f.setParam("id", id);
        }
        f.append(" order by bean.id asc");
        return find(f, pageNo, pageSize);
    }

    @Override
    public Role findById(Long id) {
        Role entity = get(id);
        return entity;
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public Role findByName(String roleName) {
        Finder f = Finder.create("from Role bean where 1=1");
        if (roleName != null) {
            f.append(" and bean.roleName=:roleName");
            f.setParam("roleName", roleName);
        } else {
            return null;
        }
        List<Role> list = find(f);
        if (list != null && list.size() > 0) { return list.get(0); }
        return null;
    }

    @Override
    public Role save(Role bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public void delete(Long id) {
        Role c=get(id);
        if (c!=null) {
            getSession().delete(c);
        }
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

}