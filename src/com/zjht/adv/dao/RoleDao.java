package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.Role;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface RoleDao {

    /**
     * 获取列表
     * 
     * @return
     */
    public List<Role> getList(Long id);

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
    public Role findById(Long id);

    /**
     * 通过名字查找
     * 
     * @param id
     * @return
     */
    public Role findByName(String roleName);

    /**
     * 保存
     * 
     * @param bean
     * @return
     */
    public Role save(Role bean);

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
    public Role updateByUpdater(Updater<Role> updater);
}
