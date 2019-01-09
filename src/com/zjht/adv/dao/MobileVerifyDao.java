package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.MobileVerify;

public interface MobileVerifyDao {
	/**
	 * 保存发送
	 * 
	 * @param bean
	 * @return
	 */
	public MobileVerify save(MobileVerify bean);

	/**
	 * 更新
	 * 
	 * @param bean
	 * @return
	 */
	public MobileVerify update(MobileVerify bean);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public MobileVerify findById(Long id);
	
	/**
	 * 通过ip地址查找该ip地址最新的记录
	 * @param ip
	 * @return
	 */
	public MobileVerify findByIp(String ip);

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public MobileVerify findByMobile(String mobile);

	/**
	 * 查找过期验证码
	 * 
	 * @param time
	 * @return
	 */
	public List<MobileVerify> findExpiredMobile(Long time);

	/**
	 * 
	 * @param id
	 */
	public void deleteById(Long id);
}
