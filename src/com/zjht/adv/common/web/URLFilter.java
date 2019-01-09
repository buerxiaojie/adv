package com.zjht.adv.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此类是用于对二级域名进行跳转
 * 
 * @author yangxiaoyong
 * @version 创建时间：2014年5月30日 下午2:43:15 参考：www.sql8.net
 */
public class URLFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	/** 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain) 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String domain = "yzf.youoil.cn";
		String serverName = httpServletRequest.getServerName().toLowerCase();
		String url = httpServletRequest.getRequestURI();
		String gotoUrl = "";
		if (serverName.indexOf(domain) >= 0) {
			if (url.equals("/") || url.toLowerCase().equals("/index.htm")) {
				gotoUrl = "http://www.youoil.cn/front/yzf/index";
				httpServletResponse.sendRedirect(gotoUrl);
				return;
			}
		}
		// 不处理
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {}
}
