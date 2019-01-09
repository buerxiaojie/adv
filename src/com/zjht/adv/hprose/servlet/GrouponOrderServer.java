package com.zjht.adv.hprose.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zjht.adv.hprose.server.GrouponOrderForHprose;
import com.zjht.adv.hprose.server.impl.HproseHttpMethods;
import com.zjht.adv.hprose.server.impl.HproseHttpServiceImpl;
import com.zjht.adv.hprose.server.impl.HttpContext;

@Controller
@Scope("prototype")
public class GrouponOrderServer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2901621230758553148L;

	@Autowired
	private HproseHttpServiceImpl service;
	
	@Autowired
	private HproseHttpMethods methods;
	
	@Autowired
	GrouponOrderForHprose grouponOrderForHprose;

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		methods.addInstanceMethods(grouponOrderForHprose);
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
