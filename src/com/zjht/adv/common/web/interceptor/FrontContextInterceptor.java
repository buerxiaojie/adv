package com.zjht.adv.common.web.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.session.SessionProvider;
import com.zjht.adv.common.web.threadvariable.MemberThread;
import com.zjht.adv.entity.User;

/**
 * 商城前台拦截器
 * 
 * 处理Config、和登录信息
 * 
 * @author LIJUNJIE
 * 
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SessionProvider session;
	
	private String payUrl;

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
		User user = (User) session.getAttribute(request, Constants.MEMBER_SESSION_KEY);
		// 用户存在
		if (user != null) {
			// 将用户放入ThreadLocal
			MemberThread.set(user);
			// 用户认证放入request
			request.setAttribute(Constants.AUTH_KEY, user.getUserName() + user.getId());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		//excutePayReturn(request, response, handler, mav);

		// 获取用户信息
		User user = MemberThread.get();
		// 请求转发
		if (user != null && mav != null && mav.getModelMap() != null && mav.getViewName() != null && !mav.getViewName().startsWith("redirect:")) {
			mav.getModelMap().addAttribute(Constants.AUTH_KEY, user.getUserName() + user.getId());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MemberThread.remove();
		// GroupThread.remove();
	}

//	private void excutePayReturn(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) {
//		String s = request.getRequestURI();
//		if (StringUtils.isBlank(s))
//			return;
//		int i = StringUtils.lastIndexOf(s, "/");
//		if (i > 1) {
//			String url = s.substring(i + 1, s.length());
//			if (url.equals(payUrl)) {
//				activityOrderMng.excutePayReturn(mav);
//			}
//		}
//
//	}

}
