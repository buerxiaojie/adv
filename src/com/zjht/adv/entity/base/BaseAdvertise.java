package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.entity.User;

/**
 * Advertise entity. @author MyEclipse Persistence Tools
 */

public class BaseAdvertise implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
    private static final long serialVersionUID = -8544217641495155485L;

    private Long              id;

    private Integer           advPosition;                              //摆放位置（1：网站首页多图版位，2：今日看板右侧版位，3：油站页版位，4：团购页版位）

    private String            advName;                                  //名称

    private String            advImg;                                   //图片地址

    private String            advLink;                                  //链接地址

    private String            advTip;                                   //链接提示

    private String            advTarget;                                //链接目标(_self,_blank..)

    private Integer           advClick;                                 //点击次数

    private Integer           advSort;                                  //数值越大，排越前

    private Integer           advStatus;                                //状态：0表示不可用，1表示在用

    private Date              advCreateTime;                            //添加时间

    private Date              advUpdateTime;                            //修改时间

    private User              user;                                     //管理员标识编号

    private AdvPosition		  position;									//图片摆放位置

    // Constructors

    /** default constructor */
    public BaseAdvertise() {}

    /** full constructor */
    public BaseAdvertise(Integer advPosition, String advName, String advImg, String advLink, String advTip, String advTarget, Integer advClick, Integer advSort, Integer advStatus, Date advCreateTime,
            Date advUpdateTime) {
        this.advPosition = advPosition;
        this.advName = advName;
        this.advImg = advImg;
        this.advLink = advLink;
        this.advTip = advTip;
        this.advTarget = advTarget;
        this.advClick = advClick;
        this.advSort = advSort;
        this.advStatus = advStatus;
        this.advCreateTime = advCreateTime;
        this.advUpdateTime = advUpdateTime;
    }

    // Property accessors

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdvPosition() {
        return this.advPosition;
    }

    public void setAdvPosition(Integer advPosition) {
        this.advPosition = advPosition;
    }

    public String getAdvName() {
        return this.advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvImg() {
        return this.advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getAdvLink() {
        return this.advLink;
    }

    public void setAdvLink(String advLink) {
        this.advLink = advLink;
    }

    public String getAdvTip() {
        return this.advTip;
    }

    public void setAdvTip(String advTip) {
        this.advTip = advTip;
    }

    public String getAdvTarget() {
        return this.advTarget;
    }

    public void setAdvTarget(String advTarget) {
        this.advTarget = advTarget;
    }

    public Integer getAdvClick() {
        return this.advClick;
    }

    public void setAdvClick(Integer advClick) {
        this.advClick = advClick;
    }

    public Integer getAdvSort() {
        return this.advSort;
    }

    public void setAdvSort(Integer advSort) {
        this.advSort = advSort;
    }

    public Integer getAdvStatus() {
        return this.advStatus;
    }

    public void setAdvStatus(Integer advStatus) {
        this.advStatus = advStatus;
    }

    public Date getAdvCreateTime() {
        return this.advCreateTime;
    }

    public void setAdvCreateTime(Date advCreateTime) {
        this.advCreateTime = advCreateTime;
    }

    public Date getAdvUpdateTime() {
        return this.advUpdateTime;
    }

    public void setAdvUpdateTime(Date advUpdateTime) {
        this.advUpdateTime = advUpdateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public AdvPosition getPosition() {
		return position;
	}

	public void setPosition(AdvPosition position) {
		this.position = position;
	}

}