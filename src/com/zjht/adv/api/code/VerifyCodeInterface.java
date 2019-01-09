package com.zjht.adv.api.code;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface VerifyCodeInterface {
	/**
	 * 接口签名校验
	 * @param request  接口请求对象
	 * @param appKey  终端秘钥
	 * @param algorithm  加密类型（MD5和SHA-1）
	 * @return 校验通过返回true，否则返回false
	 */
	public boolean verifySign(HttpServletRequest request,String appKey,String algorithm);
	/**
	 * Hprose接口签名校验
	 * @param retMap 提交参数键值对
	 * @param appKey  终端秘钥
	 * @param algorithm  加密类型（MD5和SHA-1）
	 * @return 校验通过返回true，否则返回false
	 */
	public boolean verifySignForHprose(Map<String, Object> retMap,String appKey,String algorithm);
}
