package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Province;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;


public interface ProvinceDao {
	/**
	 * 获取列表
	 * @return
	 */
	public List<Province> getList();
	
	/**
	 * 获取分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * @param id
	 * @return
	 */
	public Province findById(Long id);
	
	/**
	 * 通过名字查找
	 * @param id
	 * @return
	 */
	public Province findByName(String name);

	/**
	 * 保存
	 * @param bean
	 * @return
	 */
	public Province save(Province bean);

	/**
	 * 更新
	 * @param updater
	 * @return
	 */
	public Province updateByUpdater(Updater<Province> updater);
}
