package com.zjht.adv.entity.base;

import java.util.Date;

import com.zjht.adv.entity.Area;
import com.zjht.adv.entity.City;
import com.zjht.adv.entity.Province;
import com.zjht.adv.entity.User;


public class BaseAddress implements java.io.Serializable {

    /**
     * 收货地址表
     */
    private static final long serialVersionUID = 1L;
    
    private Long id;                      // ID
    private String consigneeName;         // 收货人姓名
    private String mobile;                // 手机号
    private String address;               // 详细地址
    private String tel;                   // 座机号
    private String zip;                   // 邮政编码
    
    private User user;                    // 用户ID
    private Province province;            // 省ID
    private City city;                    // 市ID
    private Area area;                    // 区ID

    private Date createTime;              // 创建时间
    
    public BaseAddress(){
    }

    public BaseAddress(Long id) {
        this.id=id;
    }
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getConsigneeName() {
        return consigneeName;
    }

    
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    
    public String getMobile() {
        return mobile;
    }

    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getTel() {
        return tel;
    }

    
    public void setTel(String tel) {
        this.tel = tel;
    }

    
    public String getZip() {
        return zip;
    }

    
    public void setZip(String zip) {
        this.zip = zip;
    }

    
    public Province getProvince() {
        return province;
    }

    
    public void setProvince(Province province) {
        this.province = province;
    }

    
    public City getCity() {
        return city;
    }

    
    public void setCity(City city) {
        this.city = city;
    }

    
    public Area getArea() {
        return area;
    }

    
    public void setArea(Area area) {
        this.area = area;
    }

    
    
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
   

}
