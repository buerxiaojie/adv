package com.zjht.adv.common.web.interceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjht.adv.api.code.VerifyCodeInterface;
import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.manager.ApiChannelMng;
import com.zjht.adv.manager.ApiChannelServiceMng;
import com.zjht.adv.util.ResponseUtils;

/**
 * 接口安全拦截器
 * 
 * @author
 * 
 */
public class ApiSecureInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(ApiSecureInterceptor.class);
	private String format="json";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String appNo=request.getParameter("appNo");
		format=request.getParameter("format");//xml、json两种方式
    	if ("xml".equals(format)) {
    		format="xml";
    	}
		if (StringUtils.isBlank(appNo)) {
			illegalHandle(request, response);
			return false;
		}
		//渠道权限校验开始
		ApiChannel condition=new ApiChannel();
		condition.setAppNo(appNo.trim());
		condition.setStatus(ApiChannel.STATUS_NORMAL);
		//获取指定appNo的账户信息
		List<ApiChannel> list=apiChannelMng.getList(condition);
		ApiChannel ac=null;
		if (list!=null&&list.size()>0) {
			ac=list.get(0);
		}
		//判断是否有权限接入账号
		if (ac==null) {
			notFoundChannelHandle(request, response);
			return false;
		}
		//校验签名信息
		try {
			VerifyCodeInterface vci=(VerifyCodeInterface)Class.forName(ac.getEncryptClassName()).newInstance();
			//校验签名信息
			if (!vci.verifySign(request,ac.getAppKey(),ac.getEncryptType())) {
				errorSignHandle(request, response);
				return false;
			}
		} catch(ClassNotFoundException cne){
			classNotFoundHandle(request, response);
			return false;
		} catch (Exception e) {
			signVerifyErrorHandle(request, response);
			return false;
		}
		String url=request.getRequestURI();
		String[] arr=url.split("/");
		if (arr.length<4) {
			illegalHandle(request, response);
			log.error("url请求格式有误！");
			return false;
		}
		String service=arr[2];//服务名
		String ver=arr[3];//版本号
		String methodName=arr[4];//方法
		ApiChannelService acs=new ApiChannelService();
		acs.setStatus(ApiChannelService.STATUS_ABLE);
		acs.setApiChannel(ac);
		acs.setServiceName(service+"/"+ver+"/"+methodName);
		List<ApiChannelService> listACS=apiChannelServiceMng.getList(acs);
		if (listACS!=null&&listACS.size()>0) {
			return true;
		}else{
			//无权限
			permissionHandle(request, response);
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		modelAndView.addObject(Constants.MEMBER, user);
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	/**
	 * 获取提交参数
	 * @param request
	 * @return 返回json格式
	 * @throws JSONException
	 */
	private JSONObject getRecieveParams(HttpServletRequest request) throws JSONException{
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = request.getParameterNames();
		JSONObject params=new JSONObject();
		if (parameterNames != null) {
			for (; parameterNames.hasMoreElements();) {
				String name = parameterNames.nextElement();
				params.put(name, request.getParameter(name));
			}
		}
		return params;
	}
	/**
	 * 非法请求
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	private void classNotFoundHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("找不到校验类异常处理！");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10000");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("系统异常，未找到指定校验类。");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10000");
			result.put("respMsg", "系统异常，未找到指定校验类。");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	/**
	 * 非法请求
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	private void illegalHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("非法请求拦截处理！");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10001");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("参数丢失，非法请求拦截！");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10001");
			result.put("respMsg", "参数丢失，非法请求拦截！");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	private void notFoundChannelHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("找不到指定渠道信息!");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10002");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("找不到指定渠道信息!");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10002");
			result.put("respMsg", "找不到指定渠道信息!");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	/**
	 * 权限不足
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JSONException
	 */
	private void permissionHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("权限不足");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10003");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("权限不足！");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10003");
			result.put("respMsg", "权限不足！");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	
	private void errorSignHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("签名不正确");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10004");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("签名不正确！");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10004");
			result.put("respMsg", "签名不正确!");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	private void signVerifyErrorHandle(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		log.error("校验签名发生未知异常");
		if ("xml".equals(format)) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10005");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("校验签名发生未知异常，请稍后再试！");
			ResponseUtils.renderXml(response, document.asXML());
		}else{
			JSONObject result=new JSONObject();
			result.put("respCode", "10005");
			result.put("respMsg", "校验签名发生未知异常，请稍后再试！");
			result.put("contents", getRecieveParams(request));
			ResponseUtils.renderJson(response, result.toString());
		}
	}
	@Autowired
	private ApiChannelMng apiChannelMng;
	@Autowired
	private ApiChannelServiceMng apiChannelServiceMng;
}
