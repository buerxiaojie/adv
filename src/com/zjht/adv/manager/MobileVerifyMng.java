package com.zjht.adv.manager;

import java.util.Map;

import com.zjht.adv.entity.MobileVerify;

public interface MobileVerifyMng {
	/**
	 * 发送手机验证码
	 * 
	 * @param mobile
	 * @param ipAddr
	 * @return
	 */
	public MobileVerify sendVerifyCode(String mobile, String ipAddr);

	/**
	 * 验证手机验证码
	 * 
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean verifyCode(String mobile, String code);

	/**
	 * 清除手机验证码
	 * 
	 * @param mobile
	 */
	public void clearCode(String mobile);

	/**
	 * 通过手机号查找验证信息
	 * 
	 * @param mobile
	 * @return
	 */
	public MobileVerify findByMobile(String mobile);

	/**
	 * 清除过期验证码
	 */
	public void clearExpired();

	/**
	 * 删除手机验证码
	 * 
	 * @param mobileVerify
	 */
	public void delete(MobileVerify mobileVerify);

	/**
	 * 验证是否可发送
	 * 
	 * @param mobile
	 * @param ipAddr
	 * @return result:1、通过；2、不通过。msg：提示信息
	 */
	public Map<String, String> verifySend(String mobile, String ipAddr);
	
	/**
	 * 测试
	 */
	public void sendTest(String mobile, String content);
}
