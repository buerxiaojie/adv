package com.zjht.adv.dao;

import java.util.List;

import com.zjht.adv.entity.User;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.page.Pagination;

public interface UserDao {

	/**
	 * 获取列表
	 * 
	 */
	public List<User> getList();

	/**
	 * 获取列表
	 * 
	 */
	public List<User> getList(User bean);

	/**
	 * 获取分页
	 * 
	 */
	public Pagination getPage(User bean, int pageNo, int pageSize);

	/**
	 * 通过ID查找
	 * 
	 */
	public User findById(Long id);

	/**
	 * 保存
	 * 
	 */
	public User save(User bean);

	/**
	 * 更新
	 * 
	 */
	public User updateByUpdater(Updater<User> bean);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 通过code查找用户
	 * 
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * 通过用户名查找
	 * 
	 * @param loginNanem
	 * @return
	 */
	public User findByUserName(String loginNanem);

	/**
	 * 通过用户名和密码查找
	 * 
	 * @param loginNanme
	 * @param userPwd
	 * @return
	 */
	public User findByUserNameAndPassword(String loginNanme,
			String userPwd);

	/**
	 * 通过用户名查询用户信息列表
	 * 
	 * @param name
	 * @return
	 */
	public List<User> findByUName(String name);
	
	
	/**
     * 通过上级推荐码查询
     * @param superCode
     * @return
     */
    public User findBySuperCode(String superCode);
	
    /**
     * 根据我的推荐号查找自己推荐用户列表
     * @param myCode
     * @return
     */
    public List<Long> fingByMyCode(String myCode);
    
	/**
	 * 获取所有用户推广代码
	 * @return
	 */
	public List<String> getAllMyCodeList();    
	/**
     * 设置上级管理员为空
     * 
     */
    public Integer updateParentUserEmpty(User bean);
}
