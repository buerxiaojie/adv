package com.zjht.adv.common.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import com.zjht.adv.util.WebErrors;

/**
 * 网站模板工具类
 * 
 * @author lijunjie
 * 
 */
public class WebSite {
	public static final String PATH_SP = "/";// /
	public static final String WEBINF_BASE = "WEB-INF";// WEB-INF
	public static final String PAGES_BASE = "pages";//
	public static final String RES_BASE = "res";// res
	public static final String FRONT_BASE = "front";// front
	public static final String MOBILE_BASE = "mobile";// mobile
	public static final String UPLOAD_PATH = "upload";// upload
	public static final String TPL_SUFFIX = ".html";// .html

	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回/WEB-INF/pages/front/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getFrontTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
		if (path.startsWith("/")) {
			sbuider.append(path).append(TPL_SUFFIX);
		} else {
			sbuider.append("/").append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	
	/**
	 * 指定文件夹路径，页面使用.html后缀 参数path不能带页面名后缀
	 * 示例：path=team/index 方法返回team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
	public static String getMobileTemplate(String path) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		StringBuilder sbuider = new StringBuilder();
		if (path.startsWith("/")) {
			sbuider.append(path.substring(1)).append(TPL_SUFFIX);
		} else {
			sbuider.append(path).append(TPL_SUFFIX);
		}
		return sbuider.toString();
	}
	
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径
	 * 
	 * @param path
	 *            页面路径
	 * @return 模板全路径
	 */
    public static String getTemplateRel(){
        return getTemplateRel(null);
    }
    
	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，
	 * 示例：path=team/index 方法返回/WEB-INF/pages/front/team/index
	 * 
	 * @param path
	 *            文件夹路径
	 * @return 模板全路径
	 */
    public static String getTemplateRel(String s){
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
        if(!StringUtils.isBlank(s)){
            if(!s.startsWith("/")){
            	sbuider.append("/");
            }
            sbuider.append(s);
        }
        return sbuider.toString();
    }

	/**
	 * 指定/WEB-INF/pages/front/文件夹路径，页面使用自定义的后缀 \n 示例：path=team/index
	 * 方法返回/WEB-INF/pages/front/team/index.html
	 * 
	 * @param path
	 *            页面路径
	 * @param tplsuffix
	 *            页面后缀
	 * @return 模板全路径
	 */
	public static String getFrontTemplate(String path, String tplsuffix) {
		if (StringUtils.isBlank(path)) {
			return null;
		}
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(WEBINF_BASE).append("/").append(PAGES_BASE).append("/").append(FRONT_BASE);
		if (path.startsWith("/")) {

		} else {
			sbuider.append("/").append(path).append(tplsuffix);
		}
		return sbuider.toString();
	}

	/**
	 * 上传文件根目录
	 * 
	 * @return
	 */
	public static String getUploadBasePath() {
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(RES_BASE).append("/").append(UPLOAD_PATH);
		return sbuider.toString();
	}

	/**
	 * 上传文件根目录
	 * 
	 * @param path
	 *            上传分文件夹
	 * @return
	 */
	public static String getUploadPath(String path) {
		StringBuilder sbuider = new StringBuilder("/");
		sbuider.append(RES_BASE).append("/").append(UPLOAD_PATH);
		if (!StringUtils.isBlank(path)) {
			if (!path.startsWith("/")) {
				sbuider.append("/");
			}
			sbuider.append(path);
		}
		return sbuider.toString();
	}

	/**
	 * 根据相对路径全路径
	 * 
	 * @param relPath
	 * @return
	 */
	public static String getUrlPath(String relPath, HttpServletRequest request) {
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(PATH_SP).append(RES_BASE).append(PATH_SP).append(UPLOAD_PATH);
		if (relPath.startsWith("/")) {
			sbuilder.append(relPath);
		} else {
			sbuilder.append(PATH_SP).append(relPath);
		}
		return sbuilder.toString();
	}
	
	/**
	 * 显示前台错误页
	 * @param weberrors
	 * @param model
	 * @param request
	 * @return
	 */
	public static String showFrontError(WebErrors weberrors,ModelMap model, HttpServletRequest request) {
		weberrors.toModel(model);
		return getFrontTemplate("common/error_message");
	}
	
	/**
	 * 显示前台错误页
	 * @param weberrors
	 * @param model
	 * @param request
	 * @return
	 */
	public static String showMobileError(WebErrors weberrors,ModelMap model, HttpServletRequest request) {
		weberrors.toModel(model);
		return getMobileTemplate("common/error_message");
	}

	/**
	 * 把参数转存到model里
	 * 
	 * @param relPath
	 * @return
	 */
	@SuppressWarnings({ "unchecked"})
	public static void setParameters(HttpServletRequest request, ModelMap model) {
		Enumeration<String> parameterNames = request.getParameterNames();
		if (parameterNames != null) {
			for (; parameterNames.hasMoreElements();) {
				String name = parameterNames.nextElement();
				model.addAttribute(name, request.getParameter(name));
			}
		}
	}
	
	/**
	 * 设置前台数据
	 * 
	 * @param request
	 * @param model
	 */
	public static void setFrontDate(HttpServletRequest request, ModelMap model) {
      
	}

	public static String getDomainUrl(boolean flag, HttpServletRequest request) {
		StringBuilder sbuilder = new StringBuilder("/");
		if (flag) {
			sbuilder.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort())
					.append(request.getContextPath()).append("/");
		}
		return sbuilder.toString();
	}
}
