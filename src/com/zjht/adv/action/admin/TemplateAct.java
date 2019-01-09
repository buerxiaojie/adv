package com.zjht.adv.action.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.zjht.adv.common.file.FileWrap;
import com.zjht.adv.common.web.WebSite;
import com.zjht.adv.entity.AdminLog;
import com.zjht.adv.manager.AdminLogMng;
import com.zjht.adv.util.RequestUtils;
import com.zjht.adv.util.ResponseUtils;
import com.zjht.adv.util.TemplateUtils;

/**
 * 模板Action类
 * 
 * @author liufang
 * 
 */
@Controller
public class TemplateAct implements ServletContextAware {
	private static final Logger log = LoggerFactory.getLogger(TemplateAct.class);
	/**
	 * 相对地址字符串
	 */
	private static final String REL_PATH = "relPath";

	@RequestMapping("/template/v_left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		String tplReal = servletContext.getRealPath(WebSite.getTemplateRel());
		String tplName = "模板";
		FileWrap wrap = TemplateUtils.getTplFileWrap(tplReal, tplName);
		model.addAttribute("treeRoot", wrap);
		return "template/left";
	}

	@RequestMapping("/template/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		String relPath = (String) model.get(REL_PATH);
		if (relPath == null) {
			relPath = RequestUtils.getQueryParam(request, REL_PATH);
			if (relPath == null) {
				relPath = "";
			}
		}
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		String base = servletContext.getRealPath(WebSite.getTemplateRel());
		List<FileWrap> list = new FileWrap(new File(real), base).getChild();
		model.addAttribute("list", list);
		model.addAttribute("relPath", relPath);
		return "template/list";
	}

	@RequestMapping("/template/o_create_dir.do")
	public String createDir(String relPath, String dirName, HttpServletRequest request, ModelMap model) {
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		File file = new File(real, dirName);
		// 创建文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
		adminLogMng.save("创建文件夹"+file.getPath(), AdminLog.TYPE_SAVE, AdminLog.RESULT_SUCC);
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping("/template/v_rename.do")
	public String renameInput(HttpServletRequest request, ModelMap model) {
		String relPath = RequestUtils.getQueryParam(request, REL_PATH);
		String filename = RequestUtils.getQueryParam(request, "filename");
		model.addAttribute("relPath", relPath);
		model.addAttribute("origName", filename);
		return "template/rename";
	}

	@RequestMapping("/template/o_rename.do")
	public String renameUpdate(String relPath, String origName, String newName, HttpServletRequest request, ModelMap model) {
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		File origFile = new File(real, origName);
		// TODO 验证原文件是否存在
		// 重命名
		File newFile = new File(real, newName);
		origFile.renameTo(newFile);
		adminLogMng.save("重命名文件"+origFile.getPath()+"改为"+newFile.getPath(), AdminLog.TYPE_UPDATE, AdminLog.RESULT_SUCC);
		log.info("rename template dir {} to {}", origFile.getAbsolutePath(), newFile.getAbsolutePath());
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping("/template/v_add.do")
	public String add(String relPath, HttpServletRequest request, ModelMap model) {
		model.addAttribute("relPath", relPath);
		return "template/add";
	}

	@RequestMapping("/template/o_save.do")
	public String save(String relPath, String filename, String content, HttpServletRequest request, ModelMap model) {
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		try {
			File file = new File(real, filename + WebSite.TPL_SUFFIX);
			FileUtils.writeStringToFile(file, content, "UTF-8");
			adminLogMng.save("添加文件"+file.getPath(), AdminLog.TYPE_SAVE, AdminLog.RESULT_SUCC);
			log.info("save template file success: {}", file.getAbsolutePath());
		} catch (IOException e) {
			log.error("write template file error", e);
		}
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}

	@RequestMapping("/template/v_edit.do")
	public String edit(HttpServletRequest request, ModelMap model) {
		String relPath = RequestUtils.getQueryParam(request, REL_PATH);
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		File file = new File(real);
		String filename = file.getName();
		model.addAttribute("relPath", relPath.substring(0, relPath.length() - filename.length() - 1));
		model.addAttribute("filename", filename);
		try {
			String content = FileUtils.readFileToString(file, "UTF-8");
			model.addAttribute("content", content);
		} catch (IOException e) {
			log.error("read template file error", e);
		}
		return "template/edit";
	}

	@RequestMapping("/template/o_update.do")
	public String update(String relPath, String filename, String content, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		File file = new File(real, filename);
		try {
			FileUtils.writeStringToFile(file, content, "UTF-8");
			adminLogMng.save("修改文件"+file.getPath(), AdminLog.TYPE_UPDATE, AdminLog.RESULT_SUCC);
		} catch (IOException e) {
			log.error("write template file error", e);
		}
		ResponseUtils.renderJson(response, "true");
		// AJAX请求，不返回页面。
		return null;
	}

	@RequestMapping("/template/o_delete.do")
	public String delete(String relPath, String[] ids, HttpServletRequest request, ModelMap model) {
		String relPathQuery = RequestUtils.getQueryParam(request, REL_PATH);
		String filenameQuery = RequestUtils.getQueryParam(request, "ids");
		if (relPathQuery != null) {
			relPath = relPathQuery;
			ids = new String[] { filenameQuery };
		}
		String real = servletContext.getRealPath(WebSite.getTemplateRel(relPath));
		File file;
		for (String name : ids) {
			file = new File(real, name);
			if (FileUtils.deleteQuietly(file)) {
				adminLogMng.save("删除文件"+file.getPath(), AdminLog.TYPE_DEL, AdminLog.RESULT_SUCC);
				log.info("delete template file success: {}", file.getAbsolutePath());
			} else {
				adminLogMng.save("删除文件"+file.getPath(), AdminLog.TYPE_DEL, AdminLog.RESULT_FAIL);
				log.info("delete template file failed: {}", file.getAbsolutePath());
			}
		}
		model.addAttribute(REL_PATH, relPath);
		return list(request, model);
	}
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private AdminLogMng adminLogMng;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}