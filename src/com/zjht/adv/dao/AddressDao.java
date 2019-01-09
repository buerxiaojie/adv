package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Address;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface AddressDao {
	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<Address> getList();


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
	public Address findById(Long id);

	/**
	 * 通过用户id查找
	 * 
	 * @param userId
	 * @return
	 */
	public List<Address> findByUserId(Long userId);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Address save(Address bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public Address updateByUpdater(Updater<Address> updater);

	/**
	 * 通过id删除
	 * 
	 * @param id
	 *            需要删除的id
	 * @return
	 */
	public Address deleteById(Long id);
}
