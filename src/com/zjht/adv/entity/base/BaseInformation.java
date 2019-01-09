package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.User;


public class BaseInformation implements java.io.Serializable {

    /**
     * 用户资料表
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;                     // 资料ID
    private String realName;             // 真实姓名
    private Integer age;                 // 年龄
    private String sex;                  // 性别
    private Integer year;                // 出生年
    private Integer month;               // 出生月
    private Integer day;                 // 出生日
    private String address;              // 详细地址
    private Date createTime;             // 创建时间
    private Date updateTime;             // 修改时间
    private String cardPicPath;          // 身份证件
    private User user;                   // 用户ID

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getRealName() {
        return realName;
    }

    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    
    public Integer getAge() {
        return age;
    }

    
    public void setAge(Integer age) {
        this.age = age;
    }

    
    public String getSex() {
        return sex;
    }

    
    public void setSex(String sex) {
        this.sex = sex;
    }

    
    public Integer getYear() {
        return year;
    }

    
    public void setYear(Integer year) {
        this.year = year;
    }

    
    public Integer getMonth() {
        return month;
    }

    
    public void setMonth(Integer month) {
        this.month = month;
    }

    
    public Integer getDay() {
        return day;
    }

    
    public void setDay(Integer day) {
        this.day = day;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public Date getCreateTime() {
        return createTime;
    }

    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    public Date getUpdateTime() {
        return updateTime;
    }

    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }


    public String getCardPicPath() {
        return cardPicPath;
    }


    public void setCardPicPath(String cardPicPath) {
        this.cardPicPath = cardPicPath;
    }




}
