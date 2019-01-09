package com.zjht.adv.util;

import javax.servlet.http.HttpServletRequest;

public class ClientUtil {
	/**
     * 获取客户端平台
     * @param request
     * @return
     */
    public static String getClientplatform(HttpServletRequest request){
    	String requestHeader = request.getHeader("user-agent");
		String[] deviceArray = new String[]{"android","iphone","ipad","windows phone"};
		if(requestHeader != null){
			requestHeader = requestHeader.toLowerCase();
			for(int i=0;i<deviceArray.length;i++){
				if(requestHeader.indexOf(deviceArray[i])>0){
					return deviceArray[i];
				}
			}
		}
		return "pc";
    }
}
