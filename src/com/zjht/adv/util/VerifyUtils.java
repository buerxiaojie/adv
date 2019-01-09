package com.zjht.adv.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class VerifyUtils {
	public static boolean isMobile(String mobile){
		if(StringUtils.isEmpty(mobile)){
			return false;
		}
		String regex = PropertyUtil.getPropertyValue("regex","regex.mobile").trim();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(mobile);

		boolean regResult = m.matches();
		
		return regResult;
	}

	public static boolean isEmail(String email){
		if(StringUtils.isEmpty(email)){
			return false;
		}
		String regex = PropertyUtil.getPropertyValue("regex","regex.email").trim();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		
		boolean regResult = m.matches();
		
		return regResult;
	}
}
