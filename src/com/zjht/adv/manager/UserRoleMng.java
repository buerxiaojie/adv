package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface UserRoleMng {

    /**
     * 获取列表
     * 
     */
    public List<UserRole> getList(Long id);

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
     * 通过用户查找角色列表
     */
    public List<UserRole> findByUser(User user);

}
