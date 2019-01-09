package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.page.Pagination;

public interface AdvPositionMng {

	/**
	 * 获取所有可用广告位置
	 * @param bean 查询过滤条件
	 * @return
	 */
	public List<AdvPosition> getList(AdvPosition bean);
	
	/**
	 * 根据条件筛选分页返回数据(前台)
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
	 * @param bean
	 * @return
	 */
	public AdvPosition updateByUpdater(AdvPosition bean);

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
