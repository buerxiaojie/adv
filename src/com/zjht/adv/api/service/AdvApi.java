package com.zjht.adv.api.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zjht.adv.util.ResponseUtils;

/**
 * 
 * 广告业务接口
 * 
 * @author lijunjie
 *
 */
@Controller
public class AdvApi {
    @RequestMapping(value = "/youoil/1.0/showImage",method={RequestMethod.POST,RequestMethod.GET})
    public void showImage(String methodName,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
    	String format=request.getParameter("format");//xml、json两种方式
    	if (StringUtils.isBlank(format)||"json".equals(format)) {
			JSONObject jsonResult = new JSONObject();
			//参数校验
        	String msg=request.getParameter("msg");
        	if (StringUtils.isBlank(msg)) {
        		jsonResult.put("respCode", "20000");//参数有误
	        	jsonResult.put("respDesc", "msg参数丢失！");
		    	ResponseUtils.renderJson(response, jsonResult.toString());
		    	return;
    		}
        	JSONObject jsonMsg=null;
        	try {
        		jsonMsg=new JSONObject(msg.trim());
			} catch (JSONException e) {
				jsonResult.put("respCode", "20001");//参数有误
	        	jsonResult.put("respDesc", "msg参数格式不正确！");
		    	ResponseUtils.renderJson(response, jsonResult.toString());
		    	return;
			}
        	String orderId=jsonMsg.has("orderId")?jsonMsg.getString("orderId"):null;//订单号
        	if (StringUtils.isBlank(orderId)) {
        		jsonResult.put("respCode", "20002");//参数有误
	        	jsonResult.put("respDesc", "参数orderId不能为空！");
		    	ResponseUtils.renderJson(response, jsonResult.toString());
		    	return;
			}
		}else{
			Document document = DocumentHelper.createDocument(); 
	        Element rootElement = document.addElement("response"); 
	        //rootElement.addComment(""); 
	        Element respCodeElement = rootElement.addElement("respCode"); 
	        respCodeElement.addText("20100");
	        Element ftpElement = rootElement.addElement("respMsg"); 
	        ftpElement.addText("暂未开通此服务！");
			ResponseUtils.renderXml(response, document.asXML());
		}
    }
}
