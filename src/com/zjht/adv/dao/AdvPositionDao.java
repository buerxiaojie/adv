package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface AdvPositionDao {
	
	/**
	 * 获取所有可用广告位置
	 * @param bean 查询过滤条件
	 * @return
	 */
	public List<AdvPosition> getList(AdvPosition bean);
	
	/**
	 * 根据条件筛选分页返回数据
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(AdvPosition bean, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public AdvPosition findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Long save(AdvPosition bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public AdvPosition updateByUpdater(Updater<AdvPosition> updater);
	
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
	 * @return 返回受影响行数
	 */
	public int deleteById(Long id);
	
	/**
	 * 根据id集合批量删除（修改状态）
	 * @param ids id集合
	 * @return 返回受影响行数
	 */
	public int delByIds(List<Long> ids); 
}
