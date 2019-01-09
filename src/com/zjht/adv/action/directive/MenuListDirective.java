package com.zjht.adv.action.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.zjht.adv.common.web.threadvariable.AdminThread;
import com.zjht.adv.entity.Role;
import com.zjht.adv.entity.SysMenu;
import com.zjht.adv.entity.User;
import com.zjht.adv.entity.UserRole;
import com.zjht.adv.manager.RoleMenuMng;
import com.zjht.adv.common.web.freemarker.DirectiveUtils;
import com.zjht.adv.common.file.FileWrap;
import com.zjht.adv.common.web.WebSite;
import com.zjht.adv.util.TemplateUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MenuListDirective extends WebDirective{
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "MenuList";
    @Autowired
    private RoleMenuMng roleMenuMng;
	@Autowired
	private ServletContext servletContext;
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Integer rootId = getInt("rootId", params);//根菜单id
		String path = getString("path", params);//访问路径
		List<SysMenu> listMenu=Lists.newArrayList();//返回的列表菜单
		FileWrap wrap =null;
		User user=AdminThread.get();
		Set<UserRole> set=user.getUserRoles();
		boolean normal=true;
		if (set!=null&&set.size()>0) {
			List<Role> listRole=Lists.newArrayList();
			for (UserRole ur : set) {
				listRole.add(ur.getRole());
			}
			List<SysMenu> listRoleMenu=Lists.newArrayList();
			if (rootId!=null) {
				//二级菜单
				if (path!=null&&"template/v_left.do".equals(path)) {
					String tplReal = servletContext.getRealPath(WebSite.getTemplateRel());
					String tplName = "模板";
					wrap = TemplateUtils.getTplFileWrap(tplReal, tplName);
					normal=false;
				}else{
					listRoleMenu=roleMenuMng.findByRoles(listRole);
					if (listRoleMenu!=null&&listRoleMenu.size()>0) {
						for (SysMenu sysMenu : listRoleMenu) {
							if (sysMenu.getDeep()==3&&sysMenu.getParent().getId().intValue()==rootId.intValue()&&sysMenu.getViewStatus()==SysMenu.VIEW_TRUE) {
								listMenu.add(sysMenu);
							}
						}
					}
				}
			}else{
				//一级菜单
				listRoleMenu=roleMenuMng.findByRoles(listRole);
				if (listRoleMenu!=null&&listRoleMenu.size()>0) {
					for (SysMenu sysMenu : listRoleMenu) {
						if (sysMenu.getDeep()==2&&sysMenu.getViewStatus()==SysMenu.VIEW_TRUE) {
							listMenu.add(sysMenu);
						}
					}
				}
			}
		}
		@SuppressWarnings("unchecked")
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
		if (normal) {
			paramWrap.put(OUT_LIST, ObjectWrapper.DEFAULT_WRAPPER.wrap(listMenu));
		}else{
			paramWrap.put(OUT_BEAN, ObjectWrapper.DEFAULT_WRAPPER.wrap(wrap));
		}
		Map<String, TemplateModel> origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
		renderBody(env, loopVars, body);
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
	private void renderBody(Environment env, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		body.render(env.getOut());
	}
}
