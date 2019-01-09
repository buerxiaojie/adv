package com.zjht.adv.entity.base;

import java.util.Date;
import java.util.Set;

import com.zjht.adv.entity.Address;
import com.zjht.adv.entity.Information;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class BaseUser implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = -2195555485509637879L;

    private Long              id;

    private String            code;                                     // 用户编号

    private String            userName;                                 // 用户名

    private String            userPwd;                                  // 密码

    private Integer           status;                                   // 状态:0-失效; 1-正常; 2-锁定;3-待审核

    private String            email;                                    // 邮箱

    private String            mobile;                                   // 手机

    private Integer           userType;                                 // 用户类型:1.普通用户 2.油站用户 3.车队用户 4 管理员

    private Date              createTime;                               // 创建时间
    
    private String           myCode;                                   // 我的推荐码
    
    private String           superCode;                                // 上级推荐码
    
    private User			 parentUser;							   // 上级管理员

    private Set<Address>      addresses;

    private Set<Information>  information;

    private Set<UserRole>     userRoles;


    // Constructors

    /** default constructor */
    public BaseUser() {}

    // Property accessors

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Information> getInformation() {
        return information;
    }

    public void setInformation(Set<Information> information) {
        this.information = information;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getMyCode() {
        return myCode;
    }

    public void setMyCode(String myCode) {
        this.myCode = myCode;
    }

    public String getSuperCode() {
        return superCode;
    }

    public void setSuperCode(String superCode) {
        this.superCode = superCode;
    }

	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}



}