package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.Advertise;
import com.zjht.adv.page.Pagination;

public interface AdvertiseMng {
	
	/**
	 * 根据条件筛选返回对应数据
	 * @param bean
	 * @return
	 */
	public List<Advertise> getList(Advertise bean);
	/**
	 * 根据条件筛选分页返回数据(前台)
	 * @param groupon
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Advertise bean, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public Advertise findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Long save(Advertise bean);

	/**
	 * 更新
	 * 
	 * @param bean
	 * @return
	 */
	public Advertise updateByUpdater(Advertise bean);

	/**
	 * 彻底删除记录
	 * @param id
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * 通过id删除(修改状态)
	 * 
	 * @param id
	 *            需要删除的id
	 * @return 
	 */
	public boolean deleteById(Long id);
	
	/**
	 * 根据id集合批量删除（修改状态）
	 * @param ids id集合
	 * @return 
	 */
	public boolean delByIds(List<Long> ids); 
}
