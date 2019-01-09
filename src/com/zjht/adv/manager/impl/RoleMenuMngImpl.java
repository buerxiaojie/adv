package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.RoleMenuDao;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.RoleMenuMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class RoleMenuMngImpl implements RoleMenuMng {

    @Autowired
    private RoleMenuDao dao;


    @Override
    public List<RoleMenu> getList(Long id) {
        return dao.getList(id);
    }

    @Override
    public Pagination getPage(Long id, int pageNo, int pageSize) {
        return dao.getPage(id, pageNo, pageSize);
    }

    @Override
    public RoleMenu findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public RoleMenu save(RoleMenu bean) {
        RoleMenu RoleMenu = dao.save(bean);
        return RoleMenu;
    }

    @Override
    public RoleMenu update(RoleMenu bean) {
        Updater<RoleMenu> updater = new Updater<RoleMenu>(bean);
        return dao.updateByUpdater(updater);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public List<RoleMenu> findByRoleId(Long roleId) {
        return dao.findByRoleId(roleId);
    }

	@Override
	public List<SysMenu> findByRoles(List<Role> roles) {
		return dao.findByRoles(roles);
	}

}
