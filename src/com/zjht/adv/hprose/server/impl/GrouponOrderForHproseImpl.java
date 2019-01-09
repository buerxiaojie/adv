package com.zjht.adv.hprose.server.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zjht.adv.api.code.VerifyCodeInterface;
import com.zjht.adv.entity.ApiChannel;
import com.zjht.adv.entity.ApiChannelService;
import com.zjht.adv.hprose.biz.OrderService;
import com.zjht.adv.hprose.server.GrouponOrderForHprose;
import com.zjht.adv.hprose.util.RenderHproseUtil;
import com.zjht.adv.hprose.util.SpringContextUtil;
import com.zjht.adv.manager.ApiChannelMng;
import com.zjht.adv.manager.ApiChannelServiceMng;

@Service
public class GrouponOrderForHproseImpl implements GrouponOrderForHprose {

	private static final Logger log = LoggerFactory.getLogger(GrouponOrderForHproseImpl.class);
	private String bizBeanName="grouponOrderService";//业务处理spring实体类名
	private String service="grouponOrderServer";//服务名

	@Autowired
	private ApiChannelMng apiChannelMng;
	@Autowired
	private ApiChannelServiceMng apiChannelServiceMng;
	
	@Override
	public String getMethodForHprose(String param) {
		if (param == null)
			return null;
		Gson gson = new Gson();
		Map<String, Object> retMap = gson.fromJson(param,new TypeToken<Map<String, Object>>() {}.getType());
		if (retMap==null) {
			return RenderHproseUtil.illegalHandle(retMap,GrouponOrderForHproseImpl.class);
		}
		log.info("--LeJiaCardService服务触发--");
		/** 权限校验开始  **/
		Object objAppNo=retMap.get("appNo");
		String appNo=null;
		if (objAppNo!=null) {
			appNo=objAppNo.toString();
		}
		if (StringUtils.isBlank(appNo)) {
			return RenderHproseUtil.illegalHandle(retMap,GrouponOrderForHproseImpl.class);
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
			return RenderHproseUtil.notFoundChannelHandle(retMap,GrouponOrderForHproseImpl.class);
		}
		//校验签名信息
		try {
			VerifyCodeInterface vci=(VerifyCodeInterface)Class.forName(ac.getEncryptClassName()).newInstance();
			//校验签名信息
			if (!vci.verifySignForHprose(retMap,ac.getAppKey(),ac.getEncryptType())) {
				return RenderHproseUtil.errorSignHandle(retMap,GrouponOrderForHproseImpl.class);
			}
		} catch(ClassNotFoundException cne){
			return RenderHproseUtil.verifyClassNotFoundHandle(retMap,GrouponOrderForHproseImpl.class);
		} catch (Exception e) {
			return RenderHproseUtil.signVerifyErrorHandle(retMap,GrouponOrderForHproseImpl.class);
		}
		Object objVer=retMap.get("ver");
		String ver=objVer==null?"":objVer.toString();//版本号
		Object objMethodName=retMap.get("methodName");
		String methodName=objMethodName==null?"":objMethodName.toString();//方法
		if (StringUtils.isBlank(ver)||StringUtils.isBlank(methodName)) {
			return RenderHproseUtil.illegalHandle(retMap,GrouponOrderForHproseImpl.class);
		}
		ApiChannelService acs=new ApiChannelService();
		acs.setStatus(ApiChannelService.STATUS_ABLE);
		acs.setApiChannel(ac);
		acs.setServiceName(service+"/"+ver+"/"+methodName);
		List<ApiChannelService> listACS=apiChannelServiceMng.getList(acs);
		if (!(listACS!=null&&listACS.size()>0)) {
			//无权限
			return RenderHproseUtil.permissionHandle(retMap,GrouponOrderForHproseImpl.class);
		}
		Class<?>[] paramTypes = new Class<?>[] { Map.class };
		String result =null;
		try {
			methodName=methodName+ver.replaceAll("[^\\d]","");
			result = invokeMethod(bizBeanName,methodName, paramTypes,retMap);
			return result;
		} catch (NoSuchMethodException e) {
			return RenderHproseUtil.noSuchMethodExceptionHandle(retMap, e, OrderService.class);
		} catch (IllegalAccessException e) {
			return RenderHproseUtil.illegalAccessExceptionHandle(retMap, e, GrouponOrderForHproseImpl.class);
		} catch (InvocationTargetException e) {
			return RenderHproseUtil.invocationTargetExceptionHandle(retMap, e, GrouponOrderForHproseImpl.class);
		} catch (RuntimeException e) {
			return RenderHproseUtil.runtimeExceptionHandle(retMap, e, GrouponOrderForHproseImpl.class);
		} catch (Exception e) {
			return RenderHproseUtil.exceptionHandle(retMap, e, GrouponOrderForHproseImpl.class);
		} 
	}
	
	private String invokeMethod(String beanName,String methodName,Class<?>[] paramTypes, Object... parameters) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object bean = SpringContextUtil.getBean(beanName);
		@SuppressWarnings("rawtypes")
		Class beanClass = bean.getClass();
		Method method = null;
		Method[] ms = beanClass.getMethods();
		for (Method m : ms) {
			String mName = m.getName();
			if (StringUtils.equals(methodName, mName)) {
				method = m;
				break;
			}
		}
		if (method == null) {
			throw new NoSuchMethodException(this.getClass().getName()+ "." + methodName);
		}
		Object obj = null;
		if (parameters == null || parameters.length < 1) {
			obj = method.invoke(bean);
		} else {
			obj = method.invoke(bean, parameters);
		}
		String result =  (String) obj;
		return result;
	}
}
