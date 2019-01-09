package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.Role;
import com.zjht.adv.page.Pagination;

public interface RoleMng {

    /**
     * 获取列表
     * 
     */
    public List<Role> getList(Long id);

    /**
     * 获取分页
     * 
     */
    public Pagination getPage(Long id, int pageNo, int pageSize);

    /**
     * 通过ID查找
     * 
     */
    public Role findById(Long id);

    /**
     * 保存
     * 
     */
    public Role save(Role bean);

    /**
     * 更新
     * 
     */
    public Role update(Role bean);

    /**
     * 删除
     * 
     */
    public void delete(Long id);
    
    /**
     * 多选ID删除
     * 
     */
    public Role[] deleteIds(Long[] ids);


    /**
     * 通过用户名查找
     * 
     * @param roleName
     * @return
     */
    public Role findByRoleName(String roleName);

}