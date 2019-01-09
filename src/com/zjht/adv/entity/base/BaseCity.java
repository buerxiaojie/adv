package com.zjht.adv.entity.base;

import java.util.Set;

import com.zjht.adv.entity.Area;
import com.zjht.adv.entity.Province;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class BaseCity implements java.io.Serializable {

	// Fields

	/**
     * 
     */
    private static final long serialVersionUID = -7548733785951736196L;
    private Long id;
	private String name;
	private Integer code;

	private Province province;
	private Set<Area> areas;

	// Constructors

	/** default constructor */
	public BaseCity() {
	}
	
	public BaseCity(Long id){
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}