package com.zjht.adv.manager;

import java.util.List;


import com.zjht.adv.entity.Information;
import com.zjht.adv.page.Pagination;


public interface InformationMng {
    
    
    /**
     * 获取列表
     * 
     */
    public List<Information> getList();

    /**
     * 获取分页
     * 
     */
    public Pagination getPage(int pageNo, int pageSize);

    /**
     * 通过ID查找
     * 
     */
    public Information findById(Long id);

    /**
     * 保存
     * 
     */
    public Information save(Information bean);

    /**
     * 更新
     * 
     */
    public Information update(Information bean);

    /**
     * 删除
     * 
     * @param id
     */
    public Information deleteById(Long id);

    /**
     * 通过用户id查找
     * 
     * @param userId
     * @return
     */
    public Information findByUserId(Long userId);

    /**
     * 删除
     * 
     * @param ids
     *            id数组
     * @return
     */
    public Information[] deleteByIds(Long[] ids);

}
