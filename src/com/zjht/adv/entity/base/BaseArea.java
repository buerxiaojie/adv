package com.zjht.adv.entity.base;

import com.zjht.adv.entity.City;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */

public class BaseArea implements java.io.Serializable {

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = 513504326764266622L;
    private Long id;
	private String name;
	private Integer code;

	private City city;

	// Constructors

	/** default constructor */
	public BaseArea() {
	}
	public BaseArea(Long id) {
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}