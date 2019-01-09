package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.RoleDao;
import com.zjht.adv.dao.RoleMenuDao;
import com.zjht.adv.dao.UserRoleDao;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.RoleMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class RoleMngImpl implements RoleMng {

    @Autowired
    private RoleDao dao;

    @Autowired
    private UserRoleDao urDao;

    @Autowired
    private RoleMenuDao rmdao;

    @Override
    public List<Role> getList(Long id) {
        return dao.getList(id);
    }

    @Override
    public Pagination getPage(Long id, int pageNo, int pageSize) {
        return dao.getPage(id, pageNo, pageSize);
    }

    @Override
    public Role findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Role save(Role bean) {
        Role Role = dao.save(bean);
        return Role;
    }

    @Override
    public Role update(Role bean) {
        Updater<Role> updater = new Updater<Role>(bean);
        return dao.updateByUpdater(updater);
    }

    @Override
    public void delete(Long id) {
    	List<UserRole> listur=urDao.findByRoleId(id);
    	for (UserRole userRole : listur) {
			urDao.delete(userRole.getId());
		}
    	List<RoleMenu> list=rmdao.findByRoleId(id);
    	for (RoleMenu rm : list) {
    		rmdao.delete(rm.getId());
		}
        dao.delete(id);
    }
    
    @Override
    public Role[] deleteIds(Long[] ids) {
    	Role[] beans = new Role[ids.length];
        for (int i = 0, len = ids.length; i < len; i++) {
            delete(Long.parseLong(ids[i] + ""));
        }
        return beans;
    }

    @Override
    public Role findByRoleName(String roleName) {
        return dao.findByName(roleName);
    }

}
