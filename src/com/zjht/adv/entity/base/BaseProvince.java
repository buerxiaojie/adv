package com.zjht.adv.entity.base;

import java.util.Set;

import com.zjht.adv.entity.City;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */

public class BaseProvince implements java.io.Serializable {

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = 5352271474106711646L;
    private Long id;
	private String name;
	private Integer code;

	private Set<City> citys;

	// Constructors

	/** default constructor */
	public BaseProvince() {
	}

	public BaseProvince(Long id) {
		this.id=id;
	}

	// Property accessors

	public Integer getCode() {
		return this.code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<City> getCitys() {
		return citys;
	}

	public void setCitys(Set<City> citys) {
		this.citys = citys;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}