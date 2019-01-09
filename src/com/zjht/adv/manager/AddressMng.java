package com.zjht.adv.manager;

import java.util.List;


import com.zjht.adv.entity.Address;
import com.zjht.adv.page.Pagination;


public interface AddressMng {
    
    
    /**
     * 获取列表
     * 
     */
    public List<Address> getList();

    /**
     * 获取分页
     * 
     */
    public Pagination getPage(int pageNo, int pageSize);

    /**
     * 通过ID查找
     * 
     */
    public Address findById(Long id);

    /**
     * 保存
     * 
     */
    public Address save(Address bean);

    /**
     * 更新
     * 
     */
    public Address update(Address bean);

    /**
     * 删除
     * 
     * @param id
     */
    public Address deleteById(Long id);

    /**
     * 通过用户id查找
     * 
     * @param userId
     * @return
     */
    public List<Address> findByUserId(Long userId);

    /**
     * 删除
     * 
     * @param ids
     *            id数组
     * @return
     */
    public Address[] deleteByIds(Long[] ids);

}
