package com.zjht.adv.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjht.adv.util.PropertyUtil;

@Controller
@RequestMapping(value = "/demo/*")
public class TestUploadAct {
	@RequestMapping(value = "v_add.do", method = RequestMethod.GET)
	public String vAdd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//设置上传文件夹
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		return "demo/test";
	}
}
