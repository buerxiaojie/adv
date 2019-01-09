package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.AddressDao;
import com.zjht.adv.entity.Address;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.AddressMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class AddressMngImpl implements AddressMng {
    
    @Autowired
    private AddressDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Address> getList() {
        List<Address> list = dao.getList();
        return list;
    }
    

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize) {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public Address findById(Long id) {
        Address entity = dao.findById(id);
        return entity;
    }

    @Override
    public Address save(Address bean) {
        Address entity = dao.save(bean);
        return entity;
    }

    @Override
    public Address update(Address bean) {
        Updater<Address> updater = new Updater<Address>(bean);
        Address entity = dao.updateByUpdater(updater);
        return entity;
    }

    @Override
    public Address deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        List<Address> list = dao.findByUserId(userId);
        return list;
    }

    @Override
    public Address[] deleteByIds(Long[] ids) {
        Address[] beans = new Address[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(Long.parseLong(ids[i] + ""));
        }
        return beans;
    }

}
