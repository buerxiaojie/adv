package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zjht.adv.dao.UserRoleDao;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.UserRoleMng;
import com.zjht.adv.page.Pagination;

public class UserRoleMngImpl implements UserRoleMng {

    @Autowired
    private UserRoleDao dao;


    @Override
    public List<UserRole> getList(Long id) {
        return dao.getList(id);
    }

    @Override
    public Pagination getPage(UserRole bean, int pageNo, int pageSize) {
        return dao.getPage(bean, pageNo, pageSize);
    }

    @Override
    public UserRole findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public UserRole save(UserRole bean) {
        return dao.save(bean);
    }

    @Override
    public UserRole updateByUpdater(Updater<UserRole> bean) {
        return dao.updateByUpdater(bean);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<UserRole> findByUser(User user) {
        return dao.findByUser(user);
    }

}
