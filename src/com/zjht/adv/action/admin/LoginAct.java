package com.zjht.adv.action.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.session.HttpSessionProvider;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.manager.RoleMenuMng;
import com.zjht.adv.manager.UserMng;
import com.zjht.adv.util.AjaxResult;
import com.zjht.adv.util.ResponseUtils;

@Controller
@RequestMapping(value = "/*")
public class LoginAct {

	@Autowired
	private UserMng userMng;

	@Autowired
	private AdminLogMng adminLogMng;

	@Autowired
	private HttpSessionProvider session;

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "login";
	}

	/**
	 * 登陆
	 * 
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, ModelMap model, String userName, String userPwd) throws DateParseException {
		session.logout(request, response);
		AjaxResult<User> ajaxResult = new AjaxResult<User>();
		if (StringUtils.isNotBlank(userName) || StringUtils.isNotBlank(userPwd)) {
			User user = userMng.findByUserName(userName);
			if (user == null) {
				adminLogMng.save(userName + " 用户不存在", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
				ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
				ajaxResult.setMsg("用户不存在");
				ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
				return;
			}
			if (user.getUserType() == 1&&user.getStatus()!=null&&user.getStatus().intValue()==User.STUTAS_NORMAL) {
				if (user != null && user.getUserPwd().equals(DigestUtils.md5Hex(userPwd))) {
					/**
					 * 登录成功
					 */
					session.setAttribute(request, response, Constants.ADMIN_SESSION_KEY, user);
					adminLogMng.save(user, userName + " 登录成功", AdminLog.TYPE_LOGIN, AdminLog.RESULT_SUCC);
					ajaxResult.setStatus(AjaxResult.STATUS_SUCCESS);
					ajaxResult.setMsg("登录成功！");
					ajaxResult.setAutoUrl("index.do");
					ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
					return;
				} else {
					adminLogMng.save(user, userName + " 密码不正确", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
					ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
					ajaxResult.setMsg("密码不正确！");
					ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
					return;
				}
			} else {
				adminLogMng.save(user, userName + " 无权限登录", AdminLog.TYPE_LOGIN, AdminLog.RESULT_FAIL);
				ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
				ajaxResult.setMsg("对不起！您无权限登录此页面！");
				ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
				return;
			}
		}else{
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
			ajaxResult.setMsg("用户名和密码不能为空！");
			ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
			return;
		}
		
	}

	/**
	 * 登陆管理中心
	 * 
	 */
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user = AdminThread.get();
		if (user != null) {
			model.addAttribute("admin", user);
			//菜单
			Set<UserRole> set=user.getUserRoles();
			if (set!=null&&set.size()>0) {
				List<SysMenu> listMenu=Lists.newArrayList();
				List<Role> listRole=Lists.newArrayList();
				for (UserRole ur : set) {
					listRole.add(ur.getRole());
				}
				listMenu=rmMng.findByRoles(listRole);
				List<SysMenu> topMenus=Lists.newArrayList();
				if (listMenu!=null&&listMenu.size()>0) {
					for (SysMenu sysMenu : listMenu) {
						if (sysMenu.getDeep()==2&&sysMenu.getViewStatus()==SysMenu.VIEW_TRUE) {
							topMenus.add(sysMenu);
						}
					}
				}
				model.addAttribute(Constants.MENU_LIST, topMenus);
			}
			return "index";
		}
		return "redirect:login.do";
	}

	/**
	 * 退出登录
	 * 
	 */
	@RequestMapping("logout.do")
	public String loginOut(HttpServletResponse response, HttpServletRequest request) {
		if (session != null) {
			session.logout(request, response);
		}
		return "redirect:login.do";
	}

	@RequestMapping(value = "main.do", method = RequestMethod.GET)
	public String main(Integer mid,String path,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("mid", mid);
		if (StringUtils.isNotBlank(path)) {
			model.addAttribute("path", path);
		}
		return "main";
	}

	@RequestMapping(value = "left.do", method = RequestMethod.GET)
	public String left(Integer mid,String path,HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		User user=AdminThread.get();
		//?mid=294&path=template/v_left.do
		if (path!=null&&"template/v_left.do".equals(path)) {
			return "redirect:/admin/template/v_left.do";
		}
    	//菜单
		Set<UserRole> set=user.getUserRoles();
		if (set!=null&&set.size()>0&&mid!=null) {
			List<SysMenu> listMenu=Lists.newArrayList();
			List<Role> listRole=Lists.newArrayList();
			for (UserRole ur : set) {
				listRole.add(ur.getRole());
			}
			listMenu=rmMng.findByRoles(listRole);
			List<SysMenu> leftMenus=Lists.newArrayList();
			if (listMenu!=null&&listMenu.size()>0) {
				for (SysMenu sysMenu : listMenu) {
					if (sysMenu.getDeep()==3&&sysMenu.getParent().getId().intValue()==mid.intValue()&&sysMenu.getViewStatus()==SysMenu.VIEW_TRUE) {
						leftMenus.add(sysMenu);
					}
				}
			}
			model.addAttribute(Constants.MENU_LIST, leftMenus);
		}
		if (StringUtils.isNotBlank(path)) {
			model.addAttribute("path", path);
		}
		return "left";
	}

	@RequestMapping(value = "right.do", method = RequestMethod.GET)
	public String right(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "right";
	}
	/**
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "random.do", method = RequestMethod.GET)
	public void random(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			int width = 50;
			int height = 18;
			// 取得一个4位随机字母数字字符串
			String s = RandomStringUtils.random(4, true, true);

			// 保存入session,用于与用户的输入进行比较.
			// 注意比较完之后清除session.
			HttpSession session = request.getSession(true);
			session.setAttribute("validateCode", s);

			response.setContentType("images/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			// 设定字体
			Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体
			g.setFont(mFont);

			// 画边框
			// g.setColor(Color.BLACK);
			// g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			// 生成随机类
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(s, 2, 16);

			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	/**
	 * 提供后异步验证图形验证码
	 * @param request
	 * @param response
	 * @param model
	 * @param code
	 */
	@RequestMapping(value = "verifyImgCode.do", method = { RequestMethod.GET, RequestMethod.POST })
	public void verifySendCode(HttpServletRequest request, HttpServletResponse response, ModelMap model,String code) {
		AjaxResult<User> ajaxResult = new AjaxResult<User>();
		HttpSession session = request.getSession();
		if (StringUtils.isBlank(code)) {
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
			ajaxResult.setMsg("验证码不正确");
		} else if(session.getAttribute("validateCode") == null || !((String) session.getAttribute("validateCode")).equalsIgnoreCase(code)) {
			ajaxResult.setStatus(AjaxResult.STATUS_FAILED);
			ajaxResult.setMsg("验证码不正确");
		} else {
			ajaxResult.setStatus(AjaxResult.STATUS_SUCCESS);
		}
		ResponseUtils.renderJson(response, new JSONObject(ajaxResult).toString());
	}
    @Autowired
    private RoleMenuMng rmMng;
}
