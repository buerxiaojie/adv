package com.zjht.adv.entity;

import java.util.ArrayList;
import java.util.List;

import com.zjht.adv.entity.base.BaseUser;

public class User extends BaseUser {

    /**
     * 
     */
    private static final long serialVersionUID = 7115618498755893914L;
    
    /**
     * 前台普通用户
     */
    public static final int TYPE_ORDINARY = 0;
    
    /**
     * 后台管理账户
     */
    public static final int TYPE_ADMIN = 1;
    
    
    /**
     * 失效
     */
    public static final int STUTAS_FAIL = 0;
    
    /**
     * 正常
     */
    public static final int STUTAS_NORMAL = 1;
    
    /**
     * 锁定
     */
    public static final int STUTAS_LOCK = 2;
    
    /**
     * 待审核
     */
    public static final int STUTAS_REVIEW = 3;
    
    /**
     * 黑名单
     */
    public static final int STUTAS_BLACKLIST = 4;
    //获取管理员
    private Boolean queryRootUser;
    /**
     * 
     * @return
     */
    public List<Long> getListRoles(){
    	List<Long> listRoles=new ArrayList<Long>();
    	if (getUserRoles()!=null&&getUserRoles().size()>0) {
			for (UserRole userRole : getUserRoles()) {
				listRoles.add(userRole.getRole().getId());
			}
		}
    	return listRoles;
    }
    /**
     * 用户状态
     * @return
     */
    public String getStatusStr(){
    	if (getStatus()==null) {
			return "未知状态";
		}
    	int status=getStatus().intValue();
    	String reVal=null;
    	switch (status) {
		case STUTAS_NORMAL:
			reVal="在用";
			break;
		case STUTAS_LOCK:
			reVal="已锁定";
			break;
		case STUTAS_FAIL:
			reVal="已失效";
			break;
		case STUTAS_REVIEW:
			reVal="待审核";
			break;
			
		case STUTAS_BLACKLIST:
			reVal="黑名单";
			break;

		default:
			reVal="未知状态";
			break;
		}
    	return reVal;
    }
    
    public String getEncryptName(){
    	String name=getUserName();
    	if (name!=null&&name.length()>7) {
			name=name.substring(0,3)+"****"+name.substring(6,name.length());
		}
    	return name;
    }
	public Boolean getQueryRootUser() {
		return queryRootUser;
	}
	public void setQueryRootUser(Boolean queryRootUser) {
		this.queryRootUser = queryRootUser;
	}
}
