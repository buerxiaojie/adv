package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.Area;
import com.zjht.adv.page.Pagination;

public interface AreaMng {
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
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Area save(Area bean);

	/**
	 * 更新
	 * 
	 * @param area
	 * @return
	 */
	public Area update(Area area);
}
