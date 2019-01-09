package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.Province;
import com.zjht.adv.page.Pagination;

public interface ProvinceMng{
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
	 * 保存
	 * @param bean
	 * @return
	 */
	public Province save(Province bean);

	/**
	 * 更新
	 * @param province
	 * @return
	 */
	public Province update(Province province);
}
