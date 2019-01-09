package com.zjht.adv.action.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.RoleMenu;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.manager.RoleMenuMng;
import com.zjht.adv.manager.RoleMng;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.page.SimplePage;
import com.zjht.adv.util.CookieUtils;

@Controller
@RequestMapping(value = "/role/*")
public class RoleAct {

    @Autowired
    private RoleMng    roleMng;

    @Autowired
    private RoleMenuMng roleMenuMng;


    @RequestMapping(value = "v_list.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, Role role, Integer pageNo) {
        if (role == null) {
            role = new Role();
        }
        Pagination pagination = roleMng.getPage(role.getId(), SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pageNo", SimplePage.cpn(pageNo));
        model.addAttribute("pagination", pagination);
        model.addAttribute("role", role);
        return "role/list";
    }

    @RequestMapping(value = "v_add.do", method = RequestMethod.GET)
    public String v_add(HttpServletRequest request, HttpServletResponse response, ModelMap model, Role role, SysMenu menu) {
        model.addAttribute("selectIds", "1");
        model.addAttribute("menu", menu);
        model.addAttribute("role", role);
        return "role/add";
    }

    @RequestMapping(value = "o_add.do", method = RequestMethod.POST)
    public String o_add(HttpServletRequest request, HttpServletResponse response, ModelMap model, Role role, SysMenu menu) {
    	model.addAttribute("menu", menu);
        Role r = new Role();
        if(role != null){
            if(StringUtils.isBlank(role.getRoleName())){
                model.addAttribute("opResult", "请输入用户名！");
                return "role/add";
            }
            if(roleMng.findByRoleName(role.getRoleName()) != null){
                model.addAttribute("opResult", "该用户名已存在，请换用其它名称！");
                return "role/add";
            }
            r.setRoleName(role.getRoleName());
            r.setRoleType(2);
            r.setStatus(1);
            r.setCreateTime(new Timestamp(System.currentTimeMillis()));
            roleMng.save(r);
            String mids=request.getParameter("mid");
            if (StringUtils.isNotBlank(mids)) {
                String[] arr=mids.split(",");
                for (String mid : arr) {
                    if (StringUtils.isNotBlank(mid)) {
                        RoleMenu rm=new RoleMenu();
                        rm.setRole(r);
                        rm.setMenu(new SysMenu(Integer.parseInt(mid)));
                        roleMenuMng.save(rm);
                    }
                }
            }
        }
        return list(request, response, model, null, 1);
    }

    @RequestMapping(value = "v_edit.do", method = RequestMethod.GET)
    public String v_edit(HttpServletRequest request, HttpServletResponse response, ModelMap model, Long id) {
        Role role = roleMng.findById(id);
        model.addAttribute("selectIds", "1");
        model.addAttribute("role", role);
        List<RoleMenu> rm = roleMenuMng.findByRoleId(id);
        StringBuilder sb=new StringBuilder();
        if (rm!=null&&rm.size()>0) {
        	for (RoleMenu roleMenu : rm) {
                SysMenu menu = roleMenu.getMenu();
                sb.append(menu.getId()).append(",");
            }
		}
        model.addAttribute("mids", sb.length()>0?sb.substring(0, sb.length()-1):sb.toString());
        return "role/edit";
    }

    @RequestMapping(value = "o_edit.do", method = RequestMethod.POST)
    public String o_edit(HttpServletRequest request, HttpServletResponse response, ModelMap model, Role role, Long id, RoleMenu roleMenu) {
        Role r = roleMng.findById(role.getId());
        if(r !=null){
            r.setId(role.getId());
            if(StringUtils.isBlank(role.getRoleName())){
                model.addAttribute("opResult", "请输入用户名！");
                return "role/edit";
            }
            r.setRoleName(role.getRoleName());
            r.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            roleMng.update(r);
            model.addAttribute("role", role);
            String mids=request.getParameter("mids");
            List<RoleMenu> list=roleMenuMng.findByRoleId(id);
            for (RoleMenu rm : list) {
            	roleMenuMng.delete(rm.getId());
			}
            if (StringUtils.isNotBlank(mids)) {
                String[] arr=mids.split(",");
                for (String mid : arr) {
                    if (StringUtils.isNotBlank(mid)) {
                        RoleMenu rm=new RoleMenu();
                        rm.setRole(r);
                        rm.setMenu(new SysMenu(Integer.parseInt(mid)));
                        roleMenuMng.save(rm);
                    }
                }
            }
        }
        return list(request, response, model, null, 1);
    }

    @RequestMapping(value = "v_delete.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String v_delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, Long ids[]) {
        if (ids.equals(1)) {
            model.addAttribute("opResult", "此角色为系统管理员，不允许删除，请和相关人员联系！");
            return list(request, response, model, null, 1);
        } else {
            roleMng.deleteIds(ids);
            model.addAttribute("opResult", "删除成功！");
            return list(request, response, model, null, 1);
        }
    }

}
