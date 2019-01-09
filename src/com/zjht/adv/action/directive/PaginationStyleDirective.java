package com.zjht.adv.action.directive;

import java.io.IOException;
import java.util.Map;

import com.zjht.adv.common.web.freemarker.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;

public class PaginationStyleDirective extends WebDirective {
	/**
	 * 模板名称
	 */
	public static final String TPL_NAME = "PaginationStyle";
	public static final String PAGINATION_PATH = "/WEB-INF/pages/front/style_pagination/style";
	public static final String PARAM_SYTLE_NAME = "style";

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params,
			TemplateModel[] atemplatemodel, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Map model = DirectiveUtils.addParamsToVariable(env, params);
		env.include(getStylePath(env, params), "UTF-8", true);
		DirectiveUtils.removeParamsFromVariable(env, params, model);
	}

	private String getStylePath(Environment env, @SuppressWarnings("rawtypes") Map params)
			throws TemplateException {
		TemplateModel templatemodel = (TemplateModel) params.get(PARAM_SYTLE_NAME);
		if (templatemodel == null)
			return "/WEB-INF/pages/front/style_pagination/style1.html";
		if (templatemodel instanceof TemplateScalarModel)
			return PAGINATION_PATH
					+ ((TemplateScalarModel) templatemodel).getAsString()
					+ ".html";
		else
			throw new TemplateModelException("The \"" + PARAM_SYTLE_NAME
					+ "\" parameter must be a string.");
	}
}
