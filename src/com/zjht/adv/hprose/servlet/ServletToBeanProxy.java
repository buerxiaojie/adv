package com.zjht.adv.hprose.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 我们自己实现的一个代理类用于将Servlet转为Spring管理的Servlet Bean
 */
public class ServletToBeanProxy extends HttpServlet {

	private static final long serialVersionUID = -101451057209171924L;
	private String targetBean;//当前客户端请求的Servlet名字
    private Servlet proxy;//代理Servlet
    
 	@Override
	public void init() throws ServletException {
		super.init();
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //初始化Spring容器
//      this.targetBean = getServletName();
//      this.proxy = (Servlet) wac.getBean(targetBean);//调用ServletBean
//      proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext()); 
//		this.targetBean = arg0.getParameter("serverBean");  //从请求中，获取
		String url=request.getRequestURI();
		String[] arr=url.split("/");
		if (arr.length!=3) {
			targetBean="notFoundHproseServer";
			proxy = (Servlet) ctx.getBean(targetBean);//调用ServletBean
			proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean
			proxy.service(request, response);//在service方法中调用bean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
		}else{
			String service=arr[2];//服务名
			targetBean=service;
			try {
				proxy = (Servlet) ctx.getBean(targetBean);//调用ServletBean
				proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean
				proxy.service(request, response);//在service方法中调用bean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
			} catch (Exception e) {
				this.targetBean="notFoundHproseServer";
				this.proxy = (Servlet) ctx.getBean(targetBean);//调用ServletBean
				proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean
				proxy.service(request, response);//在service方法中调用bean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
			}
		}
	}
	
}