package com.zjht.adv.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.SysMenuDao;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.SysMenuMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class SysMenuMngImpl implements SysMenuMng {

	@Autowired
	private SysMenuDao menuDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<SysMenu> getList() {
		return menuDao.getList();
	}

	@Override
	@Transactional(readOnly=true)
	public Pagination getPage(SysMenu menu, int pageNo, int pageSize) {
		return menuDao.getPage(menu, pageNo, pageSize);
	}

	@Override
	@Transactional(readOnly=true)
	public SysMenu findById(Integer id) {
		return menuDao.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SysMenu> findByUID(Long userid) {
		return menuDao.findByUID(userid);
	}

	@Override
	public SysMenu save(SysMenu bean) {
		bean.setParentIds(bean.getParent().getParentIds()+bean.getParent().getId()+",");
		return menuDao.save(bean);
	}

	@Override
	public SysMenu updateByUpdater(SysMenu bean) {
		Updater<SysMenu> updater=new Updater<SysMenu>(bean);
		return menuDao.updateByUpdater(updater);
	}

	@Override
	public SysMenu updateByUpdater(SysMenu bean, String oldParentIds) {
		bean.setParentIds(bean.getParent().getParentIds()+bean.getParent().getId()+",");
		Updater<SysMenu> updater=new Updater<SysMenu>(bean);
		SysMenu curMenu= menuDao.updateByUpdater(updater);
		List<SysMenu> list=menuDao.findByPids(","+bean.getId()+",");
		for (SysMenu menu : list) {
			menu.setParentIds(menu.getParentIds().replace(oldParentIds, bean.getParentIds()));
			Updater<SysMenu> u=new Updater<SysMenu>(menu);
			menuDao.updateByUpdater(u);
		}
		return curMenu;
	}
	
	@Override
	public SysMenu[] updateSort(Integer[] ids, Integer[] sorts) {
		SysMenu[] menus=new SysMenu[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			menus[i] = findById(ids[i]);
			menus[i].setSort(sorts[i]);
		}
		return menus;
	}

	@Override
	public void deleteByIds(List<Integer> listIds) {
		for (Integer id : listIds) {
			SysMenu menu=this.findById(id);
			menu.setDelStatus(SysMenu.DEL_DONE);
			menuDao.delChildById(menu.getParentIds()+menu.getId()+",");
		}
	}
	
}