package com.zjht.adv.hprose.client;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zjht.adv.hprose.client.HproseHttpClient;
import com.zjht.adv.service.ApiConfig;
import com.zjht.adv.util.PropertyUtil;


@Service
public class MethodForEpayHproseClient {
	protected static final Logger log = LoggerFactory.getLogger(MethodForEpayHproseClient.class);
	
	/**
	 * 接口服务心跳检验测试
	 * @return
	 * @throws Exception
	 */
	public boolean epayListenerTask() throws Exception{
        HproseHttpClient client = new HproseHttpClient();
        client.setTimeout(20000);//设置超时时间20s
        Properties prop=PropertyUtil.getPropertes("esb","api");
        String key=prop.getProperty(ApiConfig.APP_KEY_EPAY);
        String oilnetServerUrl=prop.getProperty(ApiConfig.APPURL_KEY_EPAY);
        String appNo=prop.getProperty(ApiConfig.APPNO_KEY_EPAY);
        String ver=prop.getProperty(ApiConfig.VER_KEY_EPAY);
        client.useService(oilnetServerUrl);
        String msg="{}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("methodName", "heartbeat");
        map.put("appNo", appNo);
        map.put("ver", ver);
        map.put("sign", ApiConfig.genSign(key, msg));
        map.put("msg", msg);
        Gson gson = new Gson();
        String param = gson.toJson(map);
        Object result = (Object) client.invoke("getMethodForHprose", new Object[] { param });
        return result!=null; 
    }
	
    public static void main(String[] args) throws Exception {
    	MethodForEpayHproseClient test = new MethodForEpayHproseClient();
        test.testCouponPost();
    }

    public void testCouponPost() throws Exception {
    	//youoil系统hprose调用测试
        HproseHttpClient client = new HproseHttpClient();
    	String key="C80yCCMywaSRg1eAfyxJrQT4BNVhASOF";
        client.useService("http://172.16.101.183:8088/youoilservice/orderServer");
        String msg="{\"orderId\":\"201411042318162477\"}";//{\"orderId\":\"201408061519256218\"}
        Map<String, String> map = new HashMap<String, String>();
        map.put("methodName", "getIDCardCode");
        map.put("appNo", "10001");
        map.put("ver", "1.0");
        map.put("sign", ApiConfig.genSign(key, msg));//Fo+kABRJqWgAZFxcTEk4A77/plQ=
        map.put("msg", msg);
        map.put("format", "json");
        Gson gson = new Gson();
        String param = gson.toJson(map);
        Object result = (Object) client.invoke("getMethodForHprose", new Object[] { param });
        System.out.println("result = " + result);
        //epay系统hprose调用测试
//        key="C80yCCMywaSRg1eAfyxJrQT4BNVhASOF";
//        client.useService("http://172.16.104.147/epayGateway/epayHproseServer");
//        msg="{\"orderId\":\"201408291719230850\",\"custName\":\"13538852364\",\"phone\":\"12345678910\",\"productCode\":\"LJB5000\",\"address\":\"广州市天河区龙口西路\",\"orderNum\":\"1\",\"orderStatus\":\"5\"}";
//        map = new HashMap<String, String>();
//        map.put("methodName", "openCardService");
//        map.put("sign", ApiConfig.genSign(key, msg));//Fo+kABRJqWgAZFxcTEk4A77/plQ=
//        map.put("msg", msg);
//        gson = new Gson();
//        param = gson.toJson(map);
//        result = (Object) client.invoke("getMethodForHprose", new Object[] { param });
//        System.out.println("result = " + result);
//        
//        client.useService("http://172.16.104.147/epayGateway/epayHproseServer");
//        msg="";
//        map = new HashMap<String, String>();
//        map.put("methodName", "heartbeat");
//        map.put("sign", ApiConfig.genSign(key, msg));//Fo+kABRJqWgAZFxcTEk4A77/plQ=
//        map.put("msg", msg);
//        gson = new Gson();
//        param = gson.toJson(map);
//        result = (Object) client.invoke("getMethodForHprose", new Object[] { param });
//        System.out.println("result = " + result);
    }

}
