package com.zjht.adv.action.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.AdvPosition;
import com.zjht.adv.entity.Advertise;
import com.zjht.adv.entity.User;
import com.zjht.adv.manager.AdvPositionMng;
import com.zjht.adv.manager.AdvertiseMng;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.page.SimplePage;
import com.zjht.adv.util.CookieUtils;

@Controller
@RequestMapping(value = "/advPosition/*")
public class AdvPositionAct {

	@Autowired
	private AdvPositionMng advPositionMng;
	@Autowired
	private AdvertiseMng advertiseMng;
	
	@RequestMapping(value = "v_list.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Integer pageNo,AdvPosition advPosition,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (advPosition==null) {
			advPosition=new AdvPosition();
		}
		Pagination pagination = advPositionMng.getPage(advPosition,SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pageNo", SimplePage.cpn(pageNo));
		model.addAttribute("pagination", pagination);
		model.addAttribute("advPosition", advPosition);
		return "advPosition/list";
	}
	@RequestMapping(value = "v_add.do", method = RequestMethod.GET)
	public String toadd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		AdvPosition advPosition=new AdvPosition();
		model.addAttribute("advPosition",advPosition);
		return "advPosition/add";
	}
	@RequestMapping(value = "o_add.do", method = RequestMethod.POST)
	public String add(AdvPosition advPosition,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user=AdminThread.get();
		model.addAttribute("advPosition", advPosition);
		if (advPosition!=null) {
			if (StringUtils.isBlank(advPosition.getName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "advPosition/add";
			}
			advPosition.setCreateTime(new Date());
			advPosition.setUser(user);
			advPosition.setStatus(AdvPosition.STATUS_ABLE);
			advPositionMng.save(advPosition);
			model.addAttribute("message", "录入成功，是否继续录入？");
		}
		return toadd(request,response,model);
	}
	@RequestMapping(value = "v_edit.do", method = RequestMethod.GET)
	public String toedit(Long id,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (id==null) {
			model.addAttribute("opResult", "参数丢失，请勿非法操作！");
			return list(1,null,request,response,model);
		}
		AdvPosition advPosition=advPositionMng.findById(id);
		if (advPosition==null) {
			model.addAttribute("opResult", "找不到相关记录，请刷新后再试！");
			return list(1,null,request,response,model);
		}
		model.addAttribute("advPosition", advPosition);
		return "advPosition/edit";
	}
	
	@RequestMapping(value = "o_edit.do", method = RequestMethod.POST)
	public String edit(AdvPosition advPosition,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("advPosition", advPosition);
		User user=AdminThread.get();
		if (advPosition!=null) {
			if (StringUtils.isBlank(advPosition.getName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "advertise/edit";
			}
			advPosition.setUpdateTime(new Date());
			advPosition.setUser(user);
			advPositionMng.updateByUpdater(advPosition);
			model.addAttribute("message", "保存成功，是否继续编辑？");
		}
		return toedit(advPosition.getId(),request,response,model);
	}
	
	@RequestMapping(value = "o_delete.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String del(Long[] ids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Long> listids=null;
		boolean flag=true;//检索是否可以删除
		if (ids!=null) {
			listids=new ArrayList<Long>();
			Advertise bean=new Advertise();
			for (Long id : ids) {
				listids.add(id);
				bean.setPosition(new AdvPosition(id));
				List<Advertise> list=advertiseMng.getList(bean);
				if (list!=null&&list.size()>0) {
					flag=false;
				}
			}
		}else{
			model.addAttribute("opResult", "请选择你要删除的记录！");
			return list(1,null,request,response,model);
		}
		if (flag) {
			advPositionMng.delByIds(listids);
		}else{
			model.addAttribute("opResult", "您选择删除的记录存在引用，删除终止！");
			return list(1,null,request,response,model);
		}
		model.addAttribute("opResult", "删除成功！");
		return list(1,null,request,response,model);
	}
}
