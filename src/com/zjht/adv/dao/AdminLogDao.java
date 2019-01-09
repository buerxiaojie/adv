package com.zjht.adv.dao;

import java.util.Date;

import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface AdminLogDao {

	/**
	 * 获取分页
	 * 
	 * @param userName
	 *            用户名字
	 * @param logType
	 *            日志类型
	 * @param logDetails
	 *            日志内容
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(String userName, Integer type, String details, Date startTime, Date endTime, int pageNo, int pageSize);

	/**
	 * 通过id查找
	 * 
	 * @param id
	 * @return
	 */
	public AdminLog findById(Long id);

	/**
	 * 保存
	 * 
	 * @param bean
	 * @return
	 */
	public AdminLog save(AdminLog bean);

	/**
	 * 更新
	 * 
	 * @param updater
	 * @return
	 */
	public AdminLog updateByUpdater(Updater<AdminLog> updater);
}
