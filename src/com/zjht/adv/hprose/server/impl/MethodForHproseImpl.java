package com.zjht.adv.hprose.server.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zjht.adv.hprose.server.MethodForHprose;

@Service
public class MethodForHproseImpl implements MethodForHprose{

	// "{\"methodName\":\"pushCouponOrder\",\"send\":\"true\",\"quantity\":\"1\",\"couponCode\":\"DTdcsq_a5kqw\",\"channel\":\"KFCDT\",\"mobile\":\"15989248048,18127958591\"}";
	@Override
	public String getMethodForHprose(String param) {
		if (param == null)
			return null;
		Gson gson = new Gson();
		Map<String, Object> retMap = gson.fromJson(param,new TypeToken<Map<String, Object>>() {}.getType());
		Object methodName = retMap.get("methodName");
		for (String p : retMap.keySet()) {
			//trace("key:" + p + " values:" + retMap.get(p));
			System.out.println("key:" + p + " values:" + retMap.get(p));
		}
		String result =null;
		try {
			if(methodName.toString().equals("test")){
				this.test(retMap);
			}
		} catch (RuntimeException e) {
			return "{\"respCode\":\"01\",\"respMsg\":\""+e.getMessage()+"\",\"orderId\":\""+retMap.get("orderId")+"\"}";
		}
		return "{\"respCode\":\"00\",\"respMsg\":\""+result+"\",\"orderId\":\""+retMap.get("orderId")+"\"}";
	}
	
	private String test(Map<String, Object> retMap){
		return "test";
	}

}
