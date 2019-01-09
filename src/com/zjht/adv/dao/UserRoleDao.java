package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface UserRoleDao {

    /**
     * 获取列表
     * 
     */
    public List<UserRole> getList(Long userId);

    /**
     * 获取分页
     * 
     */
    public Pagination getPage(UserRole bean, int pageNo, int pageSize);

    /**
     * 通过ID查找
     * 
     */
    public UserRole findById(Long id);

    /**
     * 保存
     * 
     */
    public UserRole save(UserRole bean);

    /**
     * 更新
     * 
     */
    public UserRole updateByUpdater(Updater<UserRole> bean);

    /**
     * 删除
     * 
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据用户查找角色
     */
    public List<UserRole> findByUser(User user);

    /**
     * 通过roleId获取权限列表
     */
    public List<UserRole> findByRoleId(Long roleId);

}
