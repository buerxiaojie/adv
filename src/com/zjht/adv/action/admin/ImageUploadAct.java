package com.zjht.adv.action.admin;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.zjht.adv.common.file.FileNameUtils;
import com.zjht.adv.common.images.ImageUtils;
import com.zjht.adv.common.web.Constants;
import com.zjht.adv.common.web.WebSite;
import com.zjht.adv.util.PropertyUtil;
import com.zjht.adv.util.WebErrors;

@Controller
public class ImageUploadAct implements ServletContextAware {
	private static final Logger log = LoggerFactory
			.getLogger(ImageUploadAct.class);
	/**
	 * 结果页
	 */
	private static final String RESULT_PAGE = "common/iframe_upload";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequestMapping("/common/o_upload_image.do")
	public String execute(
			String fileName,
			Integer uploadNum,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(fileName, file, request);
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_PAGE;
		}
		String uploadpath=PropertyUtil.getProperty("youoil.upload.path");
		String real = servletContext.getRealPath(uploadpath);
		String dateDir = FileNameUtils.genPathName();
		// 创建目录
		File root = new File(real, dateDir);
		if (!root.exists()) {
			root.mkdirs();
		}
		// 取文件名
		if (StringUtils.isBlank(fileName)) {
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			fileName = FileNameUtils.genFileNameWithOutPath(ext);
		} else {
			fileName = FilenameUtils.getName(fileName);
		}
		// 保存为临时文件
		File tempFile = new File(root, fileName);
		// 相对路径
		String relPath = Constants.SPT + dateDir + Constants.SPT +  fileName;
		//全路径
		String allrelPath = WebSite.getUrlPath(relPath,request);
		try {
			file.transferTo(tempFile);
			model.addAttribute("uploadPath", relPath);
			model.addAttribute("AlluploadPath", allrelPath);
			model.addAttribute("uploadNum", uploadNum);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_PAGE;
	}

	private WebErrors validate(String fileName, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(fileName)) {
			fileName = file.getOriginalFilename();
		}
		String ext = FilenameUtils.getExtension(fileName);
		if (!ImageUtils.isImage(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		return errors;
	}

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
