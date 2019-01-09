package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zjht.adv.dao.InformationDao;
import com.zjht.adv.entity.Information;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.InformationMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class InformationMngImpl implements InformationMng {
    
    @Autowired
    private InformationDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Information> getList() {
        List<Information> list = dao.getList();
        return list;
    }
    

    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(int pageNo, int pageSize) {
        return dao.getPage(pageNo, pageSize);
    }

    @Override
    public Information findById(Long id) {
        Information entity = dao.findById(id);
        return entity;
    }

    @Override
    public Information save(Information bean) {
        Information entity = dao.save(bean);
        return entity;
    }

    @Override
    public Information update(Information bean) {
        Updater<Information> updater = new Updater<Information>(bean);
        Information entity = dao.updateByUpdater(updater);
        return entity;
    }

    @Override
    public Information deleteById(Long id) {
        return dao.deleteById(id);
    }

    @Override
    public Information findByUserId(Long userId) {
        Information entity = dao.findByUserId(userId);
        return entity;
    }

    @Override
    public Information[] deleteByIds(Long[] ids) {
        Information[] beans = new Information[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            beans[i] = deleteById(Long.parseLong(ids[i] + ""));
        }
        return beans;
    }

}
