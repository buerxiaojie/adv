package com.zjht.adv.action.front;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjht.adv.common.web.WebSite;
import com.zjht.adv.common.web.session.HttpSessionProvider;


@Controller
public class IndexAct {

	private static final Logger log = LoggerFactory.getLogger(IndexAct.class);
    @Autowired
    private HttpSessionProvider session;
	
    @RequestMapping(value="/welcome/{id}.html")
    public String index(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response, ModelMap model){
    	model.addAttribute("id", id);
    	log.trace("id:"+id);
    	session.setAttribute(request, response, "id", id);
    	return WebSite.getFrontTemplate("index");
    }
}
