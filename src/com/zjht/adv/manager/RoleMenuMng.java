package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.page.Pagination;

public interface RoleMenuMng {

    /**
     * 获取列表
     * 
     */
    public List<RoleMenu> getList(Long id);

    /**
     * 获取分页
     * 
     */
    public Pagination getPage(Long id, int pageNo, int pageSize);

    /**
     * 通过ID查找
     * 
     */
    public RoleMenu findById(Long id);
    
    /**
     * 通过roleId查找
     * 
     */
    public List<RoleMenu> findByRoleId(Long roleId);
    
    /**
     * 通过roles查找
     * 
     * @param id
     * @return
     */
    public List<SysMenu> findByRoles(List<Role> roles);

    /**
     * 保存
     * 
     */
    public RoleMenu save(RoleMenu bean);

    /**
     * 更新
     * 
     */
    public RoleMenu update(RoleMenu bean);

    /**
     * 删除
     * 
     * @param id
     */
    public void delete(Long id);

}