package com.zjht.adv.action.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.User;
import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.manager.RoleMng;
import com.zjht.adv.manager.UserMng;
import com.zjht.adv.page.Pagination;
import com.zjht.adv.page.SimplePage;
import com.zjht.adv.util.CookieUtils;

@Controller
@RequestMapping(value = "/user/*")
public class UserAct {

    private static final Logger log = LoggerFactory.getLogger(UserAct.class);

    @Autowired
    private UserMng             userMng;

    @Autowired
    private AdminLogMng         adminLogMng;

    @Autowired
    private RoleMng             roleMng;


    /* 后台管理员资料列表(分页) */
    @RequestMapping(value = "v_list.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String vList(User user, HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer pageNo) {
        if (user == null) {
            user = new User();
        }
        user.setUserType(User.TYPE_ADMIN);
        Pagination pagination = this.userMng.getPage(user, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pagination", pagination);
        model.addAttribute("user", user);
        return "user/list";
    }

    /* 注册用户资料修改页面 */
    @RequestMapping(value = "v_edit.do", method = RequestMethod.GET)
    public String vEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer pageNo, Long id) {
        User user = userMng.findById(id);
        List<Role> roleList = roleMng.getList(null);
        model.addAttribute("roleList", roleList);
        User bean=new User();
        bean.setUserType(User.TYPE_ADMIN);
        bean.setQueryRootUser(true);
        List<User> rootUsers = userMng.getList(bean);
        model.addAttribute("rootUsers", rootUsers);
        model.addAttribute("pageNo", SimplePage.cpn(pageNo));
        model.addAttribute("user", user);
        return "user/edit";
    }

    /* 保存用户资料修改页面 */
    @RequestMapping(value = "o_edit.do", method = RequestMethod.POST)
    public String oEdit(User user, Long[] roles,Long parentUserId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.addAttribute("user", user);
        User user_old = AdminThread.get();
        if (user != null && user.getId() != null) {
            User u = userMng.findById(user.getId());
            if (u == null) {
                model.addAttribute("opResult", "参数丢失，请勿非法操作！");
                return vEdit(request, response, model, 1, user.getId());
            }
            if (parentUserId!=null) {
            	User parentUser = new User();
            	parentUser.setId(parentUserId);
				user.setParentUser(parentUser);
			}else{
				userMng.updateParentUserEmpty(user);
			}
            userMng.update(user, roles);
            adminLogMng.save(user_old.getUserName() + " 修改管理员账号，修改用户id为：" + user.getId() + (roles != null ? "，分配角色权限id为：" + Arrays.toString(roles) : "，未分配权限。"), AdminLog.TYPE_UPDATE,
                    AdminLog.RESULT_SUCC);
            model.addAttribute("opResult", "修改成功！");
        } else {
            model.addAttribute("opResult", "参数丢失，请勿非法操作！");
            log.error("后台修改用户资料失败，参数丢失！");
            return vList(null, request, response, model, 1);
        }
        return vList(null, request, response, model, 1);
    }

    /**
     * 添加新用户
     */
    @RequestMapping(value = "v_add.do", method = RequestMethod.GET)
    public String toadd(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<Role> roleList = roleMng.getList(null);
        model.addAttribute("roleList", roleList);
        User bean=new User();
        bean.setUserType(User.TYPE_ADMIN);
        bean.setQueryRootUser(true);
        List<User> rootUsers = userMng.getList(bean);
        model.addAttribute("rootUsers", rootUsers);
        return "user/add";
    }

    /**
     * 保存新添加用户
     */
    @RequestMapping(value = "o_add.do", method = RequestMethod.POST)
    public String add(User user, Long[] roles,Long parentUserId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user_old = AdminThread.get();
        if (user != null) {
            if (StringUtils.isBlank(user.getUserName())) {
                model.addAttribute("opResult", "请输入用户名！");
                return toadd(request, response, model);
            }
            List<User> us = userMng.findByUName(user.getUserName());
            if (us != null && us.size() > 0) {
                model.addAttribute("opResult", "该用户名已存在，请换用其它名称！");
                return toadd(request, response, model);
            }
            if (parentUserId!=null) {
            	User parentUser = new User();
            	parentUser.setId(parentUserId);
				user.setParentUser(parentUser);
			}
            user.setUserPwd(DigestUtils.md5Hex(user.getUserPwd()));
            user.setUserType(User.TYPE_ADMIN);
            user.setStatus(User.STUTAS_NORMAL);
            user.setCreateTime(new Date());
            userMng.save(user, roles);
            adminLogMng
                    .save(user_old.getUserName() + " 新增管理员账号，新增id为：" + user.getId() + (roles != null ? "，分配角色权限id为：" + Arrays.toString(roles) : "，未分配权限。"), AdminLog.TYPE_SAVE, AdminLog.RESULT_SUCC);
            model.addAttribute("message", "添加成功，是否继续录入？");
        }
        return toadd(request, response, model);
    }

    /**
     * 前台用户管理列表
     */
    @RequestMapping(value = "frontList.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String frontList(User user, HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer pageNo) {
        if (user == null) {
            user = new User();
        }
        user.setUserType(User.TYPE_ORDINARY);
        Pagination pagination = this.userMng.getPage(user, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pagination", pagination);
        model.addAttribute("user", user);
        return "user/frontList";
    }

    /**
     * 前台用户修改
     */
    @RequestMapping(value = "frontEdit.do", method = RequestMethod.GET)
    public String frontEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model, Long id) {
        User user = userMng.findById(id);
        model.addAttribute("user", user);
        return "user/frontEdit";
    }

    @RequestMapping(value = "o_frontEdit.do", method = RequestMethod.POST)
    public String frontEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model, User user) {
        model.addAttribute("user", user);
        if (user == null) {
            model.addAttribute("opResult", "参数错误，未找到相应对象！");
            return frontList(null, request, response, model, 1);
        }
        userMng.update(user);
        model.addAttribute("opResult", "修改成功！");
        return frontList(null, request, response, model, 1);
    }

}
