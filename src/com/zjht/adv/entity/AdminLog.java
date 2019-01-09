package com.zjht.adv.entity;

import com.zjht.adv.entity.base.BaseAdminLog;

/**
 * AdminLog entity. @author MyEclipse Persistence Tools
 */

public class AdminLog extends BaseAdminLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1023945862850448096L;

	/**
	 * 成功
	 */
	public static final int RESULT_SUCC = 1;
	/**
	 * 失败
	 */
	public static final int RESULT_FAIL = 0;
	/**
	 * 登录
	 */
	public static final int TYPE_LOGIN = 1;;
	/**
	 * 保存
	 */
	public static final int TYPE_SAVE = 2;;
	/**
	 * 删除
	 */
	public static final int TYPE_DEL = 3;;
	/**
	 * 修改
	 */
	public static final int TYPE_UPDATE = 4;
	/**
	 * 其他
	 */
	public static final int TYPE_OTHER = 99;

	public String getTypeStr() {
		Integer type = getType();
		if (type == null) {
			return "未知";
		}
		switch (type) {
		case TYPE_LOGIN:
			return "登录";
		case TYPE_SAVE:
			return "保存";
		case TYPE_DEL:
			return "删除";
		case TYPE_UPDATE:
			return "修改";
		case TYPE_OTHER:
			return "其他";
		default:
			return "未知";
		}
	}

	public String getResultStr() {
		Integer result = getResult();
		if (result == null) {
			return "未知";
		}
		switch (result) {
		case RESULT_SUCC:
			return "成功";
		case RESULT_FAIL:
			return "失败";
		default:
			return "未知";
		}
	}
}