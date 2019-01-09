package com.zjht.adv.hprose.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class RenderHproseUtil {

	private static Logger log = LoggerFactory.getLogger(RenderHproseUtil.class);
	/**
	 * 找不到校验类异常处理！
	 * @param request
	 * @param response
	 */
	public static String verifyClassNotFoundHandle(Map<String, Object> retMap,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("找不到校验类异常处理！");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10000");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("系统异常，未找到指定校验类。");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10000");
			result.put("respMsg", "系统异常，未找到指定校验类。");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
	/**
	 * 非法请求
	 * @param request
	 * @param response
	 */
	public static String illegalHandle(Map<String, Object> retMap,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("非法请求拦截处理！");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10001");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("参数丢失，非法请求拦截！");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10001");
			result.put("respMsg", "参数丢失，非法请求拦截！");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
	/**
	 * 找不到指定渠道信息
	 * @param retMap
	 * @return
	 */
	public static String notFoundChannelHandle(Map<String, Object> retMap,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("找不到指定渠道信息!");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10002");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("找不到指定渠道信息!");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10002");
			result.put("respMsg", "找不到指定渠道信息!");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
	/**
	 * 权限不足
	 * @param request
	 * @param response
	 */
	public static String permissionHandle(Map<String, Object> retMap,Class<?> cls) {
		log = LoggerFactory.getLogger(cls);
		log.error("权限不足");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10003");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("权限不足！");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10003");
			result.put("respMsg", "权限不足！");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
	
	public static String errorSignHandle(Map<String, Object> retMap,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("签名不正确");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10004");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("签名不正确!");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10004");
			result.put("respMsg", "签名不正确!");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}

	public static String signVerifyErrorHandle(Map<String, Object> retMap,Class<?> cls) {
		log = LoggerFactory.getLogger(cls);
		log.error("校验签名发生未知异常");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("10005");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("校验签名发生未知异常，请稍后再试！");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "10005");
			result.put("respMsg", "校验签名发生未知异常，请稍后再试！");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
	
	public static String runtimeExceptionHandle(Map<String, Object> retMap,RuntimeException e,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("调用Hporse服务方法发生运行时异常");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("99995");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("调用Hporse服务方法发生运行时异常!");
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "99995");
			result.put("respMsg", "调用Hporse服务方法发生运行时异常!");
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}

	public static String noSuchMethodExceptionHandle(Map<String, Object> retMap,NoSuchMethodException e,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("调用Hporse服务方法时发生找不到指定方法异常！");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("99996");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("调用Hporse服务方法发生找不到指定方法异常！异常详情："+e.getMessage());
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "99996");
			result.put("respMsg", "调用Hporse服务方法时发生找不到指定方法异常！异常详情："+e.getMessage());
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}

	public static String illegalAccessExceptionHandle(Map<String, Object> retMap,IllegalAccessException e,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("调用Hporse服务方法发生IllegalAccessException异常");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("99997");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("调用Hporse服务方法发生IllegalAccessException异常，异常详情："+e.getMessage());
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "99997");
			result.put("respMsg", "调用Hporse服务方法发生IllegalAccessException异常，异常详情："+e.getMessage());
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}

	public static String invocationTargetExceptionHandle(Map<String, Object> retMap,InvocationTargetException e,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("调用Hporse服务方法发生InvocationTargetException异常");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("99997");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("调用Hporse服务方法发生IllegalAccessException异常，异常详情："+e.getMessage());
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "99998");
			result.put("respMsg", "调用Hporse服务方法发生InvocationTargetException异常，异常详情："+e.getMessage());
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}

	public static String exceptionHandle(Map<String, Object> retMap,Exception e,Class<?> cls){
		log = LoggerFactory.getLogger(cls);
		log.error("调用Hporse服务方法发生未知异常");
		if ("xml".equals(retMap.get("format"))) {
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("99999");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("调用Hporse服务方法时系统发生未知异常，异常详情："+e.getMessage());
			return document.asXML();
		}else{
			Map<String, Object> result=new HashMap<String, Object>();
			result.put("respCode", "99999");
			result.put("respMsg", "调用Hporse服务方法时系统发生未知异常，异常详情："+e.getMessage());
			result.put("contents", retMap);
			return new Gson().toJson(result);
		}
	}
}
