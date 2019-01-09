package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface RoleMenuDao {

    /**
     * 获取列表
     * 
     * @return
     */
    public List<RoleMenu> getList(Long id);

    /**
     * 获取分页
     * 
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getPage(Long id, int pageNo, int pageSize);

    /**
     * 通过id查找
     * 
     * @param id
     * @return
     */
    public RoleMenu findById(Long id);
    
    /**
     * 通过roleId查找
     * 
     * @param id
     * @return
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
     * @param bean
     * @return
     */
    public RoleMenu save(RoleMenu bean);

    /**
     * 删除
     * 
     * @param id
     */
    public void delete(Long id);

    /**
     * 更新
     * 
     * @param updater
     * @return
     */
    public RoleMenu updateByUpdater(Updater<RoleMenu> updater);
}
