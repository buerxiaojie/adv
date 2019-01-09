package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.City;
import com.zjht.adv.page.Pagination;

public interface CityMng {
	/**
	 * 获取列表
	 * 
	 * @return
	 */
	public List<City> getList(Long provinceId);

	/**
	 * 获取分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Long provinceId, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public City findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public City save(City bean);

	/**
	 * 更新
	 * 
	 * @param city
	 * @return
	 */
	public City update(City city);
}
