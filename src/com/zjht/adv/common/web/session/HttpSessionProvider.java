package com.zjht.adv.common.web.session;

import java.io.Serializable;
import javax.servlet.http.*;

/**
 * HttpSession提供类
 * 
 * @author lijunjie
 * 
 */
public class HttpSessionProvider implements SessionProvider {
	@Override
	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (Serializable) session.getAttribute(name);
		} else {
			return null;
		}
    }

    @Override
	public void setAttribute(HttpServletRequest request,
    		HttpServletResponse response, String name, Serializable value){
        HttpSession httpsession = request.getSession();
        httpsession.setAttribute(name, value);
    }

	@Override
	public String getSessionId(HttpServletRequest request,
			HttpServletResponse response) {
		return request.getSession().getId();
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
}
