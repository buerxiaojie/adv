package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface SysMenuDao {
	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<SysMenu> getList();

	/**
	 * 获取分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(SysMenu menu,int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public SysMenu findById(Integer id);

	/**
	 * 通过userid查找
	 * 
	 * @param id
	 * @return
	 */
	public List<SysMenu> findByUID(Long userid);
	
	/**
	 * 
	 * @param pids
	 * @return
	 */
	public List<SysMenu> findByPids(String pids);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public SysMenu save(SysMenu bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public SysMenu updateByUpdater(Updater<SysMenu> updater);
	
	/**
	 * 删除
	 * @param cid
	 */
	public int delChildById(String cid);
}
