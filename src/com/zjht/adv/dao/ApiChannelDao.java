package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface ApiChannelDao {

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
	 * @param updater
	 * @return
	 */
	public ApiChannel updateByUpdater(Updater<ApiChannel> updater);
	
	/**
	 * 彻底删除记录
	 * @param id
	 * @return
	 */
	public void delete(Long id);
}
