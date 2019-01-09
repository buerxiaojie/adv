package com.zjht.adv.action.admin;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.manager.RoleMenuMng;

@Controller
public class FrameAct {

    @RequestMapping("/frame/oil_main.do")
    public String oilMain(ModelMap model) {
        User user = AdminThread.get();
        //菜单
        Set<UserRole> set = user.getUserRoles();
        if (set != null && set.size() > 0) {
            List<SysMenu> listMenu = Lists.newArrayList();
            List<Role> listRole = Lists.newArrayList();
            for (UserRole ur : set) {
                listRole.add(ur.getRole());
            }
            listMenu = rmMng.findByRoles(listRole);
            model.addAttribute(Constants.MENU_LIST, listMenu);
        }
        return "frame/oil_main";
    }

    @RequestMapping("/frame/oil_left.do")
    public String oilLeft(ModelMap model) {
        AdminThread.get();

        return "frame/oil_left";
    }

    @RequestMapping("/frame/oil_right.do")
    public String oilRight(ModelMap model) {
        return "frame/oil_right";
    }

    @RequestMapping("/frame/cardApply_main.do")
    public String cardApplyMain(ModelMap model) {
        return "frame/cardApply_main";
    }

    @RequestMapping("/frame/cardApply_left.do")
    public String cardApplyLeft(ModelMap model) {
        return "frame/cardApply_left";
    }

    @RequestMapping("/frame/cardApply_right.do")
    public String cardApplyRight(ModelMap model) {
        return "frame/cardApply_right";
    }

    @RequestMapping("/frame/groupon_main.do")
    public String grouponMain(ModelMap model) {
        return "frame/groupon_main";
    }

    @RequestMapping("/frame/groupon_left.do")
    public String grouponLeft(ModelMap model) {
        return "frame/groupon_left";
    }

    @RequestMapping("/frame/groupon_right.do")
    public String grouponRight(ModelMap model) {
        return "frame/groupon_right";
    }

    @RequestMapping("/frame/web_main.do")
    public String webMain(ModelMap model) {
        return "frame/web_main";
    }

    @RequestMapping("/frame/web_left.do")
    public String webLeft(ModelMap model) {
        return "frame/web_left";
    }

    @RequestMapping("/frame/web_right.do")
    public String webRight(ModelMap model) {
        return "frame/web_right";
    }

    @RequestMapping("/frame/config_main.do")
    public String configMain(ModelMap model) {
        return "frame/config_main";
    }

    @RequestMapping("/frame/config_left.do")
    public String configLeft(ModelMap model) {
        return "frame/config_left";
    }

    @RequestMapping("/frame/config_right.do")
    public String configRight(ModelMap model) {
        return "frame/config_right";
    }

    @RequestMapping("/frame/template_main.do")
    public String templateMain(ModelMap model) {
        return "frame/template_main";
    }

    @RequestMapping("/frame/user_main.do")
    public String userMain(ModelMap model) {
        return "frame/user_main";
    }

    @RequestMapping("/frame/user_left.do")
    public String userLeft(ModelMap model) {
        return "frame/user_left";
    }

    @RequestMapping("/frame/user_right.do")
    public String userRight(ModelMap model) {
        return "frame/user_right";
    }

    @RequestMapping("/frame/cardActive_main.do")
    public String cardActiveMain(ModelMap model) {
        return "frame/cardActive_main";
    }

    @RequestMapping("/frame/cardActive_left.do")
    public String cardActiveLeft(ModelMap model) {
        return "frame/cardActive_left";
    }

    @RequestMapping("/frame/cardActive_right.do")
    public String cardActiveRight(ModelMap model) {
        return "frame/cardActive_right";
    }


    @Autowired
    private RoleMenuMng rmMng;

}
