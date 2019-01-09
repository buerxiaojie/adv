package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.InformationDao;
import com.zjht.adv.entity.Information;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class InformationDaoImpl extends HibernateBaseDao<Information, Long> implements InformationDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Information> getList() {
        Finder f = Finder.create("from Information bean where 1=1");
        f.append(" order by bean.id desc");
        return find(f);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize) {
        Finder f = Finder.create("from Information bean where 1=1");
        f.append(" order by bean.id desc");
        return find(f, pageNo, pageSize);
    }

    @Override
    public Information findById(Long id) {
        Information entity = get(id);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Information findByUserId(Long userId) {
        Finder f = Finder.create("from Information bean where 1=1");
        if (userId != null) {
            f.append(" and bean.user.id=:userId");
            f.setParam("userId", userId);
        }
        List<Information> list = find(f);
        if (list != null && list.size() > 0) { return list.get(0); }
        return null;
    }

    @Override
    public Information save(Information bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Information deleteById(Long id) {
        Information entity = super.get(id);
        if (entity != null) {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Information> getEntityClass() {
        return Information.class;
    }

}
