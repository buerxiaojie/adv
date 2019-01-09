package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.page.Pagination;

public interface ApiChannelMng {

	/**
	 * 获取渠道列表
	 * @param bean 查询过滤条件
	 * @return
	 */
	public List<ApiChannel> getList(ApiChannel bean);
	
	/**
	 * 根据条件筛选分页返回数据
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(ApiChannel bean, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public ApiChannel findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Long save(ApiChannel bean);

	/**
	 * 更新
	 * 
	 * @param bean
	 * @return
	 */
	public ApiChannel updateByUpdater(ApiChannel bean);
	
	/**
	 * 彻底删除记录
	 * @param id
	 * @return
	 */
	public void delete(Long id);
}
