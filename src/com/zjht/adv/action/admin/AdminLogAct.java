package com.zjht.adv.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.page.SimplePage;
import com.zjht.adv.util.CookieUtils;

@Controller
@RequestMapping(value = "/adminLog/*")
public class AdminLogAct {

	@RequestMapping(value = "v_list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String vList(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer pageNo, String userName, Integer type,
			String details, Date startTime, Date endTime) {
		Pagination pagination = adminLogMng.getPage(userName, type, details, startTime, endTime, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pageNo", SimplePage.cpn(pageNo));
		model.addAttribute("userName",userName);
		model.addAttribute("type", type);
		model.addAttribute("details", details);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("pagination", pagination);
		return "adminLog/list";
	}

	@Autowired
	private AdminLogMng adminLogMng;
}
