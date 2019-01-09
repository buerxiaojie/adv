package com.zjht.adv.action.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.entity.User;
import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.manager.SysMenuMng;
import com.zjht.adv.util.PropertyUtil;
import com.zjht.adv.util.ResponseUtils;
import com.zjht.adv.util.WebErrors;

@Controller
@RequestMapping(value = "/sysmenu/*")
public class SysMenuAct {

	@Autowired
	private SysMenuMng sysMenuMng;
	@Autowired
	private AdminLogMng adminLogMng;
	
	@RequestMapping(value = "v_list.do")
	public String list(SysMenu menu,HttpServletRequest request, ModelMap model){
		List<SysMenu> list = Lists.newArrayList();
		List<SysMenu> sourcelist = sysMenuMng.getList();
		SysMenu.sortList(list, sourcelist, 1);
        model.addAttribute("mlist", list);
		return "menu/list";
	}
	@RequestMapping(value = "v_add.do", method = RequestMethod.GET)
	public String toadd(Integer parentId,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		SysMenu menu=new SysMenu();
		if (parentId!=null) {
			SysMenu parent=sysMenuMng.findById(parentId);
			menu.setParent(parent);
		}
		model.addAttribute("menu",menu);
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		return "menu/add";
	}
	@RequestMapping(value = "o_add.do", method = RequestMethod.POST)
	public String add(SysMenu menu,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user=AdminThread.get();
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		model.addAttribute("menu", menu);
		if (menu!=null) {
			String pid=request.getParameter("pid");
			if (StringUtils.isBlank(pid)) {
				model.addAttribute("opResult", "请选择上级菜单");
				return "menu/add";
			}
			SysMenu parent=sysMenuMng.findById(Integer.parseInt(pid));
			if (parent==null) {
				model.addAttribute("opResult", "找不到指定上级菜单，请勿非法操作！");
				return "menu/add";
			}
			menu.setParent(parent);
			if (StringUtils.isBlank(menu.getName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "menu/add";
			}
			if (menu.getSort()==null) {
				model.addAttribute("opResult", "请输入排序序号！");
				return "menu/add";
			}
			
			menu.setCreateTime(new Date());
			menu.setUser(user);
			menu.setDelStatus(SysMenu.DEL_UNDONE);
			sysMenuMng.save(menu);
			adminLogMng.save("添加系统菜单，id为："+menu.getId(), AdminLog.TYPE_SAVE, AdminLog.RESULT_SUCC);
			model.addAttribute("message", "录入成功，是否继续录入？");
		}
		return toadd(null,request,response,model);
	}
	@RequestMapping(value = "v_edit.do", method = RequestMethod.GET)
	public String toedit(Integer id,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (id==null) {
			model.addAttribute("opResult", "参数丢失，请勿非法操作！");
			return list(null,request,model);
		}
		SysMenu menu=sysMenuMng.findById(id);
		if (menu==null) {
			model.addAttribute("opResult", "找不到相关记录，请刷新后再试！");
			return list(null,request,model);
		}
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		model.addAttribute("menu", menu);
		return "menu/edit";
	}
	
	@RequestMapping(value = "o_edit.do", method = RequestMethod.POST)
	public String edit(SysMenu menu,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		User user=AdminThread.get();
		if (menu!=null) {
			SysMenu m=sysMenuMng.findById(menu.getId());
			if (m==null) {
				model.addAttribute("opResult", "找不到指定菜单信息，请勿非法操作！");
				return "menu/edit";
			}
			menu.setParent(m.getParent());
			menu.setParentIds(m.getParentIds());
			model.addAttribute("menu", menu);
			String pid=request.getParameter("pid");
			if (StringUtils.isBlank(pid)) {
				model.addAttribute("opResult", "请选择上级菜单");
				return "menu/edit";
			}
			if (StringUtils.isNumeric(pid)) {
				SysMenu parent=sysMenuMng.findById(Integer.parseInt(pid));
				if (parent==null) {
					model.addAttribute("opResult", "找不到指定上级菜单，请勿非法操作！");
					return "menu/edit";
				}else{
					menu.setParent(parent);
				}
			}else{
				model.addAttribute("opResult", "上级菜单标识不是数字，请勿非法操作！");
				return "menu/edit";
			}
			if (StringUtils.isBlank(menu.getName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "menu/edit";
			}
			if (menu.getSort()==null) {
				model.addAttribute("opResult", "请输入排序序号！");
				return "menu/edit";
			}
			menu.setUpdateTime(new Date());
			menu.setUser(user);
			sysMenuMng.updateByUpdater(menu,m.getParentIds());
			adminLogMng.save("修改系统菜单，id为："+menu.getId(), AdminLog.TYPE_UPDATE, AdminLog.RESULT_SUCC);
			model.addAttribute("message", "修改成功，是否继续编辑？");
		}
		return toedit(menu.getId(),request,response,model);
	}
	
	@RequestMapping(value = "o_delete.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String del(Integer[] ids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Integer> listIds=null;
		if (ids!=null) {
			listIds=new ArrayList<Integer>();
			for (Integer id : ids) {
				listIds.add(id);
			}
		}
		if (listIds!=null&&listIds.size()>0) {
			this.sysMenuMng.deleteByIds(listIds);
		}
		model.addAttribute("opResult", "删除成功！");
		adminLogMng.save("删除了系统菜单，id为："+Arrays.toString(ids), AdminLog.TYPE_DEL, AdminLog.RESULT_SUCC);
		return list(null,request,model);
	}

	@RequestMapping(value = "v_choose.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String tochoose(Long extId,String selectIds,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		model.addAttribute("selectIds", selectIds);
		model.addAttribute("extId", extId);
		return "menu/choose";
	}
	@RequestMapping(value = "v_treemenu.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void treemenu(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException{
		JSONObject ajaxResult = new JSONObject();
		List<SysMenu> list=sysMenuMng.getList();
		if (list!=null&&list.size()>0) {
			JSONArray listj = new JSONArray();
			for (SysMenu menu : list) {
				JSONObject entity = new JSONObject();
				entity.put("id", menu.getId());
				entity.put("name", menu.getName());
				if (menu.getParent()!=null) {
					entity.put("pId", menu.getParent().getId());
				}else{
					entity.put("pId","");
				}
				listj.put(entity);
			}
			ajaxResult.put("treeData", listj);
		}
		ResponseUtils.renderJson(response, ajaxResult.toString());
	}
	@RequestMapping(value = "o_change_sort.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String updateSort(Integer[] wids, Integer[] priority, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "wids")) {
			return errors.showErrorPage(model);
		}
		if (errors.ifEmpty(priority, "priority")) {
			return errors.showErrorPage(model);
		}
		if (wids.length != priority.length) {
			errors.addErrorString("请勿非法操作！");
			return errors.showErrorPage(model);
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			if (errors.hasErrors()) {
				return errors.showErrorPage(model);
			}
			SysMenu entity = sysMenuMng.findById(wids[i]);
			errors.ifNotExist(entity, SysMenu.class, wids[i]);
			if (priority[i] == null) {
				priority[i] = 0;
			}
		}
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		sysMenuMng.updateSort(wids, priority);
		adminLogMng.save("修改系统菜单排序，id为："+Arrays.toString(wids), AdminLog.TYPE_UPDATE, AdminLog.RESULT_SUCC);
		model.addAttribute("message", "global.success");
		return list(null, request, model);
	}
}
