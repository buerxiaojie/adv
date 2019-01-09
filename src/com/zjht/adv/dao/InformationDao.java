package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Information;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface InformationDao {
	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<Information> getList();


	/**
	 * 获取分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public Information findById(Long id);

	/**
	 * 通过用户id查找
	 * 
	 * @param userId
	 * @return
	 */
	public Information findByUserId(Long userId);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Information save(Information bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public Information updateByUpdater(Updater<Information> updater);

	/**
	 * 通过id删除
	 * 
	 * @param id
	 *            需要删除的id
	 * @return
	 */
	public Information deleteById(Long id);
}
