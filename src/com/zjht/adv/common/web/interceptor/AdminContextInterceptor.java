package com.zjht.adv.common.web.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.session.SessionProvider;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.User;

/**
 * 后台信息拦截器
 * 
 * 登录信息、权限信息
 * 
 * @author LIJUNJIE
 * 
 */
public class AdminContextInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws ServletException {
		User user=(User) session.getAttribute(request, Constants.ADMIN_SESSION_KEY);
		// 指定管理员存在
		if (user!=null) {
			// 将管理员信息放入ThreadLocal
			AdminThread.set(user);
			// 管理员认证放入request
			request.setAttribute(Constants.AUTH_KEY, user.getUserName()+user.getId());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		//获取管理员信息
		User user=AdminThread.get();
		//请求转发
		if (user!=null && mav != null && mav.getModelMap() != null
				&& mav.getViewName() != null
				&& !mav.getViewName().startsWith("redirect:")) {
			mav.getModelMap().addAttribute(Constants.AUTH_KEY,
					user.getUserName()+user.getId());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		AdminThread.remove();
	}
	@Autowired
	private SessionProvider session;
	
	private String loginUrl;

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}
