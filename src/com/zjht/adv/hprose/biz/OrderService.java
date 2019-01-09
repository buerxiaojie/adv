package com.zjht.adv.hprose.biz;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.zjht.adv.hprose.common.HproseService;

@HproseService
public class OrderService {

	/**
	 * ver 1.0 版本号 methodName getIDCardCode 
	 * 
	 * @param retMap
	 * @return
	 */
	public String getIDCardCode10(Map<String, Object> retMap) {
		if (!"xml".equals(retMap.get("format"))) {
			Gson g = new Gson();
			Map<String, Object> jsonResult = new HashMap<String, Object>();
			Object objMsg = retMap.get("msg");
			// 参数校验
			String msg = objMsg == null ? null : objMsg.toString();
			if (StringUtils.isBlank(msg)) {
				jsonResult.put("respCode", "20000");// 参数有误
				jsonResult.put("respDesc", "msg参数丢失！");
				// sbuffer.append("{\"respCode\":\"20000\",\"respDesc\":\"msg参数丢失！\"}");
				return g.toJson(jsonResult);
			}
			JSONObject jsonMsg = null;
			try {
				jsonMsg = new JSONObject(msg.trim());
				System.out.println(jsonMsg.get("test"));
			} catch (JSONException e) {
				jsonResult.put("respCode", "20001");// 参数有误
				jsonResult.put("respDesc", "msg参数格式不正确！");
				return g.toJson(jsonResult);
			}
			return g.toJson(jsonResult);
		} else {
			/** 建立document对象 */
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("response");
			// rootElement.addComment("");
			Element respCodeElement = rootElement.addElement("respCode");
			respCodeElement.addText("20100");
			Element ftpElement = rootElement.addElement("respMsg");
			ftpElement.addText("暂未开通XML服务！");
			return document.asXML();
		}
	}

	/**
	 * ver 1.0 版本号 methodName heartBeat 心跳检测方法
	 * 
	 * @param retMap
	 * @return
	 */
	public String heartBeat10(Map<String, Object> retMap) {
		if (!"xml".equals(retMap.get("format"))) {
			return "{\"respCode\":\"00\"}";
		} else {
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("response");
			// rootElement.addComment("");
			Element respCodeElement = rootElement.addElement("respCode");
			respCodeElement.addText("00");
			Element ftpElement = rootElement.addElement("respMsg");
			ftpElement.addText("网络连接正常");
			return document.asXML();
		}
	}
}
