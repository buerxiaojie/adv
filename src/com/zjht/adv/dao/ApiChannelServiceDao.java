package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface ApiChannelServiceDao {

	/**
	 * 获取渠道服务权限列表
	 * @param bean 查询过滤条件
	 * @return
	 */
	public List<ApiChannelService> getList(ApiChannelService bean);
	
	/**
	 * 根据条件筛选分页返回数据
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(ApiChannelService bean, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public ApiChannelService findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public Long save(ApiChannelService bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public ApiChannelService updateByUpdater(Updater<ApiChannelService> updater);
	
	/**
	 * 彻底删除记录
	 * @param id
	 * @return
	 */
	public void delete(Long id);
}
