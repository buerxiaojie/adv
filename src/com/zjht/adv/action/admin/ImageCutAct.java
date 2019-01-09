package com.zjht.adv.action.admin;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;

import com.zjht.adv.common.images.ImageScale;
import com.zjht.adv.common.web.WebSite;

@Controller
public class ImageCutAct implements ServletContextAware {
	private static final Logger log = LoggerFactory
			.getLogger(ImageCutAct.class);
	/**
	 * 图片选择页面
	 */
	public static final String IMAGE_SELECT_RESULT = "common/image_area_select";
	/**
	 * 图片裁剪完成页面
	 */
	public static final String IMAGE_CUTED = "common/image_cuted";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";

	@RequestMapping("/common/v_image_area_select.do")
	public String imageAreaSelect(String imgSrcRoot, String imgSrcPath,
			Integer zoomWidth, Integer zoomHeight, Integer uploadNum,
			HttpServletRequest request, ModelMap model) {
		model.addAttribute("imgSrcRoot", imgSrcRoot);
		model.addAttribute("imgSrcPath", imgSrcPath);
		model.addAttribute("zoomWidth", zoomWidth);
		model.addAttribute("zoomHeight", zoomHeight);
		model.addAttribute("uploadNum", uploadNum);
		return IMAGE_SELECT_RESULT;
	}

	@RequestMapping("/common/o_image_cut.do")
	public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft,
			Integer imgWidth, Integer imgHeight, Integer reMinWidth,
			Integer reMinHeight, Float imgScale, Integer uploadNum,
			HttpServletRequest request, ModelMap model) {
		String real = servletContext.getRealPath(WebSite.getUrlPath(imgSrcPath, request));
		File srcFile = new File(real);
		try {
			if (imgWidth > 0) {
				imageScale
						.resizeFix(srcFile, srcFile, reMinWidth, reMinHeight,
								getLen(imgTop, imgScale), getLen(imgLeft,
										imgScale), getLen(imgWidth, imgScale),
								getLen(imgHeight, imgScale));
			} else {
				imageScale.resizeFix(srcFile, srcFile, reMinWidth, reMinHeight);
			}
			model.addAttribute("uploadNum", uploadNum);
		} catch (Exception e) {
			log.error("cut image error", e);
			model.addAttribute(ERROR, e.getMessage());
		}
		return IMAGE_CUTED;
	}

	private int getLen(int len, float imgScale) {
		return Math.round(len / imgScale);
	}

	@Autowired
	private ImageScale imageScale;
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
