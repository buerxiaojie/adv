package com.zjht.adv.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zjht.adv.dao.AddressDao;
import com.zjht.adv.entity.Address;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

@Repository
public class AddressDaoImpl extends HibernateBaseDao<Address, Long> implements AddressDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Address> getList() {
        Finder f = Finder.create("from Address bean where 1=1");
        f.append(" order by bean.id desc");
        return find(f);
    }

    @Override
    public Pagination getPage(int pageNo, int pageSize) {
        Finder f = Finder.create("from Address bean where 1=1");
        f.append(" order by bean.id desc");
        return find(f, pageNo, pageSize);
    }

    @Override
    public Address findById(Long id) {
        Address entity = get(id);
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Address> findByUserId(Long userId) {
        Finder f = Finder.create("from Address bean where 1=1");
        if (userId != null) {
            f.append(" and bean.user.id=:userId");
            f.setParam("userId", userId);
        }
        return find(f);
    }

    @Override
    public Address save(Address bean) {
        getSession().save(bean);
        return bean;
    }

    @Override
    public Address deleteById(Long id) {
        Address entity = super.get(id);
        if (entity != null) {
            getSession().delete(entity);
        }
        return entity;
    }

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

}
