package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.page.Pagination;

public interface ApiChannelServiceMng {

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
	 * @param bean
	 * @return
	 */
	public ApiChannelService updateByUpdater(ApiChannelService bean);
	
	/**
	 * 彻底删除记录
	 * @param id
	 * @return
	 */
	public void delete(Long id);
}
