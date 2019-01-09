package com.zjht.adv.entity;

import com.zjht.adv.entity.base.BaseRoleMenu;

/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RoleMenu extends BaseRoleMenu {

	public RoleMenu() {
		super();
	}

	public RoleMenu(Long id, Role role, SysMenu menu) {
		super(id, role, menu);
	}

	public RoleMenu(Long id) {
		super(id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3585914398095235704L;

	
}