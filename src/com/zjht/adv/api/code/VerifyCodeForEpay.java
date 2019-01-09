package com.zjht.adv.api.code;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.zjht.adv.util.MessageDigestHelper;

public class VerifyCodeForEpay implements VerifyCodeInterface {

	@Override
	public boolean verifySign(HttpServletRequest request,String appKey,String algorithm) {
		@SuppressWarnings("deprecation")
		String sign=URLEncoder.encode(request.getParameter("sign"));
		String msg=request.getParameter("msg");
		if (StringUtils.isBlank(msg)) {
			msg="";
		}
		String codeBase=appKey+msg+appKey;
		@SuppressWarnings("deprecation")
		String trueSign = URLEncoder.encode(MessageDigestHelper.encode(algorithm, codeBase, null));
		return trueSign.equals(sign);
	}

	@Override
	public boolean verifySignForHprose(Map<String, Object> retMap,String appKey,String algorithm) {
		Object objSign=retMap.get("sign");
		String sign=objSign==null?"":objSign.toString();
		Object objMsg=retMap.get("msg");
		String msg=objMsg==null?"":objMsg.toString();
		String codeBase=appKey+msg+appKey;
		String trueSign = MessageDigestHelper.encode(algorithm, codeBase, null);
		return trueSign.equals(sign);
	}

}
