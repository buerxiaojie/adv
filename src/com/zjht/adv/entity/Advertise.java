package com.zjht.adv.entity;

import java.util.Date;

import com.zjht.adv.entity.base.BaseAdvertise;

/**
 * Advertise entity. @author MyEclipse Persistence Tools
 */

public class Advertise extends BaseAdvertise {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1367136154168542591L;

	public Advertise() {
		super();
	}

	public Advertise(Integer advPosition, String advName, String advImg,
			String advLink, String advTip, String advTarget, Integer advClick,
			Integer advSort, Integer advStatus, Date advCreateTime,
			Date advUpdateTime) {
		super(advPosition, advName, advImg, advLink, advTip, advTarget,
				advClick, advSort, advStatus, advCreateTime, advUpdateTime);
	}

	/**
	 * 降序
	 */
	public static final String SORTBY_DESC = "desc";

	/**
	 * 升序
	 */
	public static final String SORTBY_ASC = "asc";
	/**
	 * 正常
	 */
	public static final int STATUS_NORMAL = 1;
	/**
	 * 停用
	 */
	public static final int STATUS_DISABLE = 0;
	/**
	 * 删除
	 */
	public static final int STATUS_DEL = 2;
	/**
	 * 网站首页栏位
	 */
	public static final int POSITION_INDEX_FLASH = 1;
	/**
	 * 网站首页中部栏位
	 */
	public static final int POSITION_BOARD_RIGHT = 2;
	/**
	 * 团购页面左栏位
	 */
	public static final int POISTION_GROUPON_INDEX = 3;
	/**
	 * 油站首页栏位
	 */
	public static final int POSITION_OIL_INDEX = 4;
	/**
     * 友情链接
     */
    public static final int Link_INDEX = 5;
    /**
     * 团购页面右栏位
     */
    public static final int POISTION_GROUPON_SILE=6;
    /**
     * 移动话费兑换
     */
    public static final int GZ_INTEGRAL_EXCHANGE=7;

	// 排序类型（desc，asc）
	private String sortType;
	// 位置标识编号
	private Long positionId;

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getPositionStr() {
		Integer advposition = getAdvPosition();
		int position=0;
		if (advposition!=null) {
			position=advposition.intValue();
		}
		String pStr = null;
		switch (position) {
		case POSITION_INDEX_FLASH:
			pStr = "网站首页顶部栏位";
			break;
		case POSITION_BOARD_RIGHT:
			pStr = "网站首页中部栏位";
			break;
		case POISTION_GROUPON_INDEX:
			pStr = "团购页面左栏位";
			break;
		case POSITION_OIL_INDEX:
			pStr = "油站首页栏位";
			break;
		case Link_INDEX:
            pStr = "友情链接";
            break;
		case POISTION_GROUPON_SILE:
            pStr = "团购页面右栏位";
            break;
		case GZ_INTEGRAL_EXCHANGE:
            pStr = "移动话费兑换";
            break;
		default:
			pStr = "未知栏位";
			break;
		}
		return pStr;
	}
	
	public String getStatusStr(){
		int status=getAdvStatus();
		String sStr=null;
		switch (status) {
		case STATUS_NORMAL:
			sStr="启用";
			break;
		case STATUS_DISABLE:
			sStr="<font color='red'>禁用</font>";
			break;
		case STATUS_DEL:
			sStr="删除";
			break;
		default:
			break;
		}
		return sStr;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
}