package com.zjht.adv.hprose.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zjht.adv.hprose.server.MethodForHprose;
import com.zjht.adv.hprose.server.impl.HproseHttpMethods;
import com.zjht.adv.hprose.server.impl.HproseHttpServiceImpl;
import com.zjht.adv.hprose.server.impl.HttpContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class YouoilHproseServer extends HttpServlet {
	
	@Autowired
	private HproseHttpServiceImpl service;
	
	@Autowired
	private HproseHttpMethods methods;
	
	@Autowired
	MethodForHprose methodForHprose;

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		methods.addInstanceMethods(methodForHprose);
		service.handle(new HttpContext(request, response, this.getServletConfig(), this.getServletContext()), methods);
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Hprose Servlet 1.0";
	}
}
