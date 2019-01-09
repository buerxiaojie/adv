package com.zjht.adv.dao.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.zjht.adv.dao.UserDao;
import com.zjht.adv.entity.User;
import com.zjht.adv.hibernate.Finder;
import com.zjht.adv.hibernate.HibernateBaseDao;
import com.zjht.adv.page.Pagination;

public class UserDaoImpl extends HibernateBaseDao<User, Long> implements UserDao {

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<User> getList() {
		Finder f = Finder.create("from User bean where 1=1");
		f.append(" order by bean.id desc");
		return find(f);

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getList(User bean) {
		Finder f = Finder.create("from User bean where 1=1");
		if (bean!=null) {
			if (bean.getQueryRootUser()!=null) {
				if (bean.getQueryRootUser()) {
					f.append(" and bean.parentUser.id is null");
				}
			}
			if (bean.getUserType()!=null) {
				f.append(" and bean.userType=:userType").setParam("userType", bean.getUserType());
			}
		}
		f.append(" order by bean.id asc");
		return find(f);

	}

	@Override
	public Pagination getPage(User bean, int pageNo, int pageSize) {
		Finder f = Finder.create("from User bean where 1=1 ");
		if (bean!=null) {
            if (StringUtils.isNotBlank(bean.getUserName())) {
                f.append(" and bean.userName like '%").append(bean.getUserName()).append("%'");
            }
            if (StringUtils.isNotBlank(bean.getMobile())) {
                f.append(" and bean.mobile = :mobile").setParam("mobile", bean.getMobile());
            }
            if (StringUtils.isNotBlank(bean.getEmail())) {
                f.append(" and bean.email = :email").setParam("email", bean.getEmail());
            }
            if (bean.getUserType()!=null) {
                f.append(" and bean.userType=:userType").setParam("userType", bean.getUserType());
            }
            if (bean.getStatus()!=null) {
                f.append(" and bean.status=:status").setParam("status", bean.getStatus());
            }
        }
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}

	@Override
	public User findById(Long id) {
		User entity = get(id);
		return entity;
	}

	@Override
	public User save(User bean) {
		getSession().save(bean);
		return bean;
	}

	/**
	 * 通过用户名查询用户信息列表
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByUName(String name) {
		Finder f = Finder.create("from User bean where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			f.append(" and bean.userName = :userName").setParam("userName", name);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@Override
	public void delete(Long id) {
		//
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public User findByCode(String code) {
		Finder f = Finder.create("from User bean where 1=1");
		if (code != null) {
			f.append(" and bean.code=:code");
			f.setParam("code", code);
		}
		return (User) find(f);
	}

	@Override
	public User findByUserName(String userName) {
		Finder f = Finder.create("from User bean where 1=1");
		if (userName != null) {
			f.append(" and bean.userName=:userName");
			f.setParam("userName", userName);
		}
		@SuppressWarnings("unchecked")
		List<User> list = find(f);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findByUserNameAndPassword(String userName, String userPwd) {
		String passwordMd5 = DigestUtils.md5Hex(userPwd); // 要使用MD5摘要
		Finder f = Finder.create("from User bean where bean.userName=:loginNanme and bean.userPwd=:userPwd");
		if (userName != null || userPwd != null) {
			f.setParam("loginNanme", userName);
			f.setParam("userPwd", passwordMd5);
		}
		@SuppressWarnings("unchecked")
		List<User> list = find(f);
		if (list != null && list.size() == 1) {
			return (User) find(f).get(0);
		}
		return null;
	}
	
	
	@Override
    public User findBySuperCode(String superCode) {
        Finder f = Finder.create("from User bean where 1=1");
        if (superCode != null) {
            f.append(" and bean.myCode=:superCode");
            f.setParam("superCode", superCode);
        }
        @SuppressWarnings("unchecked")
        List<User> list = find(f);
        if (list != null && list.size() == 1) {
            return (User) find(f).get(0);
        }
        return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> fingByMyCode(String myCode) {
		Finder f = Finder.create("select bean.id from User bean where 1=1");
        if (myCode != null) {
            f.append(" and bean.superCode=:myCode");
            f.setParam("myCode", myCode);
        }
		return find(f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllMyCodeList() {
		Finder f = Finder.create("select bean.myCode from User bean where 1=1");
        f.append(" and bean.myCode is not null");
		return find(f);
	}

	@Override
	public Integer updateParentUserEmpty(User bean) {
		Query q = getSession().createQuery("update User bean set bean.parentUser.id = null where bean.id=:id").setParameter("id", bean.getId());
		return q.executeUpdate();
	}
}
