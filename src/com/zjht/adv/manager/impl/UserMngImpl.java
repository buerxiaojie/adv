package com.zjht.adv.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.UserDao;
import com.zjht.adv.dao.UserRoleDao;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.hibernate.Updater;
import com.zjht.adv.manager.UserMng;
import com.zjht.adv.page.Pagination;

@Service
@Transactional
public class UserMngImpl implements UserMng {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserRoleDao urDao;

	@Override
	public List<User> getList() {
		return dao.getList();
	}
	
	@Override
	public List<User> getList(User user) {
		return dao.getList(user);
	}

	@Override
	public Pagination getPage(User bean, int pageNo, int pageSize) {
		return dao.getPage(bean, pageNo, pageSize);
	}

	@Override
	public User findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public User save(User bean) {
		User user = dao.save(bean);
		return user;
	}
	
	@Override
	public User save(User bean,Long[] rids) {
		User user = dao.save(bean);
		if (rids!=null&&rids.length>0) {
			for (Long rid : rids) {
				UserRole ur=new UserRole();
				ur.setRole(new Role(rid));
				ur.setUser(bean);
				urDao.save(ur);
			}
		}
		return user;
	}

	@Override
	public User update(User bean) {
		Updater<User> updater = new Updater<User>(bean);
		return dao.updateByUpdater(updater);
	}

	@Override
	public User update(User bean,Long[] rids) {
		User user=this.findById(bean.getId());
		user.setEmail(bean.getEmail());
		user.setMobile(bean.getMobile());
		if (bean.getStatus()!=null) {
			user.setStatus(bean.getStatus());
		}
		if (bean.getParentUser()!=null) {
			user.setParentUser(bean.getParentUser());
		}
		if (rids!=null&&rids.length>0) {
			Set<UserRole> set=user.getUserRoles();
			for (Long rid : rids) {
				if (set!=null&&set.size()>0) {
					boolean noexist=true;
					for (UserRole userRole : set) {
						if (userRole.getRole().getId().intValue()==rid.intValue()) {
							noexist=false;
						}
					}
					if (noexist) {
						UserRole ur=new UserRole();
						ur.setRole(new Role(rid));
						ur.setUser(user);
						urDao.save(ur);
					}
				}else{
					UserRole ur=new UserRole();
					ur.setRole(new Role(rid));
					ur.setUser(user);
					urDao.save(ur);
					set.add(ur);
				}
			}
			if (set!=null&&set.size()>0) {
				List<UserRole> listRemove=null;
				for (UserRole userRole : set) {
					boolean noexist=true;
					for (Long rid : rids) {
						if (userRole.getRole().getId().intValue()==rid.intValue()) {
							noexist=false;
							break;
						}
					}
					if (noexist) {
						if (listRemove==null) {
							listRemove=new ArrayList<UserRole>();
						}
						listRemove.add(userRole);
					}
				}
				if (listRemove!=null&&listRemove.size()>0) {
					for (UserRole userRole : listRemove) {
						set.remove(userRole);
					}
				}
			}
			
//			if (set!=null&&set.size()>0) {
//				for (UserRole userRole : set) {
//					boolean noexist=true;
//					for (Long rid : rids) {
//						if (userRole.getRole().getId().intValue()==rid.intValue()) {
//							noexist=false;
//							break;
//						}
//					}
//					if (noexist) {
//						urDao.delete(userRole.getId());
//					}
//				}
//			}
		}
		return user;
	}
	
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public User findByCode(String code) {
		return dao.findByCode(code);
	}

	@Override
	public User findByUserName(String loginNanem) {
		return dao.findByUserName(loginNanem);
	}

	@Override
	public User findByUserNameAndPassword(String loginNanme, String userPwd) {
		return dao.findByUserNameAndPassword(loginNanme, userPwd);
	}

	@Override
	public List<User> findByUName(String name) {
		return dao.findByUName(name);
	}

	@Override
	public String updateUser(String username, String oldPassword, String password) {
		// ChangePasswordSecurityResponse response =
		// executorUtilsImpl.changePassword(username,oldPassword,password);
		// //System.out.println(response.isSuccess()+"message is :"+response.getMessage());
		// if(!response.isSuccess()){
		// return response.getMessage();
		// }
		return "Y";
	}

	@Override
	public User changePwd(User bean) {
		User base=dao.findById(bean.getId());
		base.setUserPwd(bean.getUserPwd());
		return base;
	}
	
	
	
	@Override
    public User findBySuperCode(String superCode) {
        return dao.findBySuperCode(superCode);
    }

	@Override
	public List<String> getAllMyCodeList() {
		return dao.getAllMyCodeList();
	}

	@Override
	public Integer updateParentUserEmpty(User bean) {
		return dao.updateParentUserEmpty(bean);
	}
	
	
}
