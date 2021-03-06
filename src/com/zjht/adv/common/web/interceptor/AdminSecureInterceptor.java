package com.zjht.adv.common.web.interceptor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Lists;
import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.manager.RoleMenuMng;
import com.zjht.adv.util.PropertyUtil;

public class AdminSecureInterceptor extends HandlerInterceptorAdapter {
	private boolean develop = (new Boolean(PropertyUtil.getProperty("isProductDeploy"))).booleanValue();
	private static String urlRule="/admin/logout.do;/admin/index.do;/admin/login.do;/admin/main.do;/admin/left.do;/admin/right.do";

    @Autowired
    private RoleMenuMng rmMng;
    
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
    		 Object obj) throws Exception{
        if(develop){
            return true;
        }
        String autokey = (String) request.getAttribute(Constants.AUTH_KEY);
        String s = "";
        try {
        	s=getURI(request.getRequestURI(), request.getContextPath());
		} catch (IllegalStateException e) {
			response.sendError(404);
			return false;
		}
        if(s.equals("/index.do")||s.equals("/login.do")){
            return true;
        }
        if(StringUtils.isBlank(autokey)){
            redirectToLogin(request, response);
            return false;
        }
        User user=AdminThread.get();
        String url=request.getRequestURI();
        String preUrl=url.split(";")[0];
        //菜单
		Set<UserRole> set=user.getUserRoles();
		boolean flag=false;
		if (urlRule.indexOf(preUrl)!=-1) {
			flag=true;
		}
		if (set!=null&&set.size()>0&&!flag) {
			List<SysMenu> listMenu=Lists.newArrayList();
			List<Role> listRole=Lists.newArrayList();
			for (UserRole ur : set) {
				listRole.add(ur.getRole());
			}
			listMenu=rmMng.findByRoles(listRole);
			if (listMenu!=null&&listMenu.size()>0) {
				for (SysMenu sysMenu : listMenu) {
					String path=sysMenu.getPath();
					if (StringUtils.isNotBlank(path)&&StringUtils.isNotBlank(preUrl)) {
						String[] paths=path.split("@@");
						for (String p : paths) {
							if (StringUtils.isNotBlank(p)) {
								int index=p.indexOf("?");
								if (p.contains("?")&&index!=p.length()-1) {
									String pa=p.substring(0, index);
									String params=p.substring(index+1);
									String[] pavs=params.split("&");
									if (!preUrl.contains(pa)) {
										continue;
									}
									for (String pav : pavs) {
										if (StringUtils.isNotBlank(pav)) {
											if (pav.split("=").length==2) {
												String pValue=request.getParameter(pav.split("=")[0]);
												if (pav.split("=")[1].equals(pValue)) {
													flag=true;
													break;
												}
											}
										}
									}
								}else if ((!p.contains("?"))&&url.indexOf(p)!=-1) {
									flag=true;
								}
							}
						}
					}
				}
			}
		}
		if (!flag) {
			response.sendError(403);
			return false;
		}
        return true;
    }

    public static String getURI(String s, String s1)
        throws IllegalStateException{
        int i = 0;
        int j = 0;
        int k = 1;
        if(!StringUtils.isBlank(s1)){
            k++;
        }
        for(; j < k && i != -1; j++){
            i = s.indexOf('/', i + 1);
        }
        if(i <= 0){
            throw new IllegalStateException("admin access path not like '/admin/...' pattern: "+s);
        }else{
            return s.substring(i);
        }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String s = request.getRequestURI();
        int i = StringUtils.countMatches(s, "/");
        StringBuilder stringbuilder = new StringBuilder();
        int j = 2;
        if(!StringUtils.isBlank(request.getContextPath())){
            j++;
        }
        for(; j < i; j++){
            stringbuilder.append("../");
        }
        stringbuilder.append("index.do");
        response.sendRedirect(stringbuilder.toString());
    }

    public void setDevelop(boolean develop){
        this.develop = develop;
    }
}
