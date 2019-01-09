package com.zjht.adv.manager;

import java.util.List;

import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.page.Pagination;

public interface SysMenuMng {

    /**
     * 获取列表
     * 
     * @return
     */
    public List<SysMenu> getList();

    /**
     * 获取分页
     * 
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination getPage(SysMenu menu, int pageNo, int pageSize);

    /**
     * 通过id查找
     * 
     * @param id
     * @return
     */
    public SysMenu findById(Integer id);

    /**
     * 通过userid查找
     * 
     * @param id
     * @return
     */
    public List<SysMenu> findByUID(Long userid);

    /**
     * 保存
     * 
     * @param bean
     * @return
     */
    public SysMenu save(SysMenu bean);

    /**
     * 更新
     * 
     * @param updater
     * @return
     */
    public SysMenu updateByUpdater(SysMenu bean);

    /**
     * 更新
     * 
     * @param updater
     * @return
     */
    public SysMenu updateByUpdater(SysMenu bean, String oldParentIds);

    /**
     * 批量删除（修改状态）
     * @param listIds
     */
    public void deleteByIds(List<Integer> listIds);

    /**
     * 更新排序
     * @param ids
     * @param sorts
     * @return
     */
    public SysMenu[] updateSort(Integer[] ids, Integer[] sorts);
}
