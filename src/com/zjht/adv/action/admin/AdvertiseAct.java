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
import com.zjht.adv.util.PropertyUtil;

@Controller
@RequestMapping(value = "/advertise/*")
public class AdvertiseAct {

	@Autowired
	private AdvertiseMng advertiseMng;
	@Autowired
	private AdvPositionMng advPositionMng;
	
	@RequestMapping(value = "v_list.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Integer pageNo,Advertise adv,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		if (adv==null) {
			adv=new Advertise();
		}
		if (adv.getPositionId()!=null&&adv.getPositionId()!=0L) {
			adv.setPosition(new AdvPosition(adv.getPositionId()));
		}
		Pagination pagination = advertiseMng.getPage(adv,SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
		List<AdvPosition> apList=new ArrayList<AdvPosition>();
		AdvPosition ap=new AdvPosition();
		ap.setId(0L);
		ap.setName("全部");
		apList.add(ap);
		List<AdvPosition> dbList=advPositionMng.getList(null);
		if (dbList!=null&&dbList.size()>0) {
			apList.addAll(dbList);
		}
		model.addAttribute("apList",apList);
		model.addAttribute("pageNo", SimplePage.cpn(pageNo));
		model.addAttribute("pagination", pagination);
		model.addAttribute("adv", adv);
		return "advertise/list";
	}
	@RequestMapping(value = "v_add.do", method = RequestMethod.GET)
	public String toadd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		Advertise adv=new Advertise();
		model.addAttribute("adv",adv);
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		genCommonData(request,response,model);
		return "advertise/add";
	}
	@RequestMapping(value = "o_add.do", method = RequestMethod.POST)
	public String add(Advertise adv,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user=AdminThread.get();
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		genCommonData(request,response,model);
		model.addAttribute("adv", adv);
		if (adv!=null) {
			if (StringUtils.isBlank(adv.getAdvName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "advertise/add";
			}
			if (StringUtils.isBlank(adv.getAdvImg())) {
				model.addAttribute("opResult", "请上传图片！");
				return "advertise/add";
			}
			if (adv.getAdvSort()==null) {
				model.addAttribute("opResult", "请输入排序序号！");
				return "advertise/add";
			}
			if (adv.getAdvClick()==null) {
				adv.setAdvClick(0);
			}
			if (adv.getPositionId()!=null) {
				adv.setPosition(new AdvPosition(adv.getPositionId()));
			}
			adv.setAdvCreateTime(new Date());
			adv.setUser(user);
			adv.setAdvStatus(Advertise.STATUS_NORMAL);
			advertiseMng.save(adv);
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
		genCommonData(request,response,model);
		Advertise adv=advertiseMng.findById(id);
		if (adv==null) {
			model.addAttribute("opResult", "找不到相关记录，请刷新后再试！");
			return list(1,null,request,response,model);
		}
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		model.addAttribute("adv", adv);
		return "advertise/edit";
	}
	
	@RequestMapping(value = "o_edit.do", method = RequestMethod.POST)
	public String edit(Advertise adv,String rowids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("uploadRoot", PropertyUtil.getProperty("youoil.upload.path"));
		model.addAttribute("adv", adv);
		genCommonData(request,response,model);
		User user=AdminThread.get();
		if (adv!=null) {
			if (StringUtils.isBlank(adv.getAdvName())) {
				model.addAttribute("opResult", "请输入名称！");
				return "advertise/edit";
			}
			if (StringUtils.isBlank(adv.getAdvImg())) {
				model.addAttribute("opResult", "请上传图片！");
				return "advertise/edit";
			}
			if (adv.getAdvSort()==null) {
				model.addAttribute("opResult", "请输入排序序号！");
				return "advertise/edit";
			}
			if (adv.getAdvClick()==null) {
				adv.setAdvClick(0);
			}
			if (adv.getPositionId()!=null) {
				adv.setPosition(new AdvPosition(adv.getPositionId()));
			}
			adv.setAdvUpdateTime(new Date());
			adv.setUser(user);
			advertiseMng.updateByUpdater(adv);
			model.addAttribute("message", "保存成功，是否继续编辑？");
		}
		return toedit(adv.getId(),request,response,model);
	}
	
	@RequestMapping(value = "o_delete.do", method = {RequestMethod.POST,RequestMethod.GET})
	public String del(Long[] ids,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<Long> listids=null;
		if (ids!=null) {
			listids=new ArrayList<Long>();
			for (Long id : ids) {
				listids.add(id);
			}
		}
		if (listids!=null&&listids.size()>0) {
			advertiseMng.delByIds(listids);
		}
		model.addAttribute("opResult", "删除成功！");
		return list(1,null,request,response,model);
	}
	
	private void genCommonData(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<AdvPosition> apList=advPositionMng.getList(null);
		model.addAttribute("apList",apList);
	}
}
