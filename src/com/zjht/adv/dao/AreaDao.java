package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Area;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface AreaDao {
	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<Area> getList(Long cityId);

	/**
	 * 获取分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Long cityId, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public Area findById(Long id);

	/**
	 * 通过名字和省id查找
	 * 
	 * @param id
	 * @return
	 */
	public Area findByName(String name, Long cityId);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Area save(Area bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public Area updateByUpdater(Updater<Area> updater);
}
