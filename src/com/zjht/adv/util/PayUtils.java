package com.zjht.adv.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjht.adv.common.web.Constants;
import com.zjht.adv.entity.PaymentInfo;
import com.zjht.adv.entity.PaymentInfoForPost;
import com.zjht.adv.entity.PaymentInfoForQpay;

public class PayUtils {
	private static final Logger log = LoggerFactory.getLogger(PayUtils.class);

	/**
	 * 准备支付信息
	 * 
	 * @param request
	 * @param orderNo
	 * @param amount
	 * @param handler
	 */
	public static void preparePayData(HttpServletRequest request, final PaymentInfo paymentInfo) {
		// 生成校验字符串
		String code = CodeBuilder.encryptMD5(paymentInfo.getOrderNo() + paymentInfo.getExtend1() + (int) (Double.valueOf(paymentInfo.getAmount()) * 100)
				+ paymentInfo.getOrderTime());
		// 保存到支付数据到SESSION
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_VERIFYCODE, code);
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_PAYMENTINFO, paymentInfo);

		log.info(String.format("点击支付信息准备：{orderNo:'%1$s',amount:%2$s,handler:'%3$s',code:'%4$s',timestamp:'%5$s'}", paymentInfo.getOrderNo(),
				paymentInfo.getAmount(), paymentInfo.getExtend1(), code, paymentInfo.getOrderTime()));
	}
	/**
	 * 准备支付信息(快捷支付【新增2014-10-23 9:42:07】)
	 * 
	 * @param request
	 * @param orderNo
	 * @param amount
	 * @param handler
	 */
	public static void prepareQPayData(HttpServletRequest request, final PaymentInfoForQpay paymentInfo){
		
		// 生成校验字符串
		String code = paymentInfo.getSignContent();
		// 保存到支付数据到SESSION
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_VERIFYCODE, code);
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_PAYMENTINFO, paymentInfo);
		log.info(String.format("手机快捷支付信息准备：{orderNo:'%1$s',amount:%2$s,handler:'%3$s',code:'%4$s',timestamp:'%5$s'}", paymentInfo.getOrderNo(),
				paymentInfo.getAmount(), paymentInfo.getExtend1(), code, paymentInfo.getOrderTime()));
	}
	

	/**
	 * 准备支付信息(邮储支付【新增2014年11月27日11:47:08】)
	 * 
	 * @param request
	 * @param orderNo
	 * @param amount
	 * @param handler
	 */
	public static void preparePostPayData(HttpServletRequest request, final PaymentInfoForPost paymentInfo){
		
		// 生成校验字符串
		String code = paymentInfo.getSignContent();
		// 保存到支付数据到SESSION
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_VERIFYCODE, code);
		RequestUtils.addSessionItem(request, Constants.SESSION_PAY_PAYMENTINFO, paymentInfo);
		log.info(String.format("邮储支付信息准备：{orderNo:'%1$s',amount:%2$s,handler:'%3$s',code:'%4$s',timestamp:'%5$s'}", paymentInfo.getOrderNo(),
				paymentInfo.getAmount(), paymentInfo.getExtend1(), code, paymentInfo.getOrderTime()));
	}
	/**
	 * 清除支付信息
	 * 
	 * @param request
	 */
	public static void clearPayData(HttpServletRequest request) {
		RequestUtils.removeSessionItem(request, Constants.SESSION_PAY_VERIFYCODE);
		RequestUtils.removeSessionItem(request, Constants.SESSION_PAY_PAYMENTINFO);
	}

	/**
	 * 验证支付用到数据
	 * 
	 * @param orderNo
	 * @param handler
	 * @param amount
	 * @param code
	 * @return
	 */
	public static boolean validate(final PaymentInfo paymentInfo, final String code) {
		if (StringUtils.isBlank(paymentInfo.getOrderNo())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getExtend1())) {
			return false;
		}
		if (StringUtils.isBlank("" + paymentInfo.getAmount())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getOrderTime())) {
			return false;
		}
		if (StringUtils.isBlank(code)) {
			return false;
		}
		String newCode = CodeBuilder.encryptMD5(paymentInfo.getOrderNo() + paymentInfo.getExtend1() + (int) (Double.valueOf(paymentInfo.getAmount()) * 100)
				+ paymentInfo.getOrderTime());

		log.debug("SESSION串：" + code);
		log.debug("生成串：" + newCode);

		if (code.equals(newCode)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证支付用到数据(快捷支付【新增2014-10-23 9:42:07】)
	 * 
	 * @param orderNo
	 * @param handler
	 * @param amount
	 * @param code
	 * @return
	 */
	public static boolean validateQPay(final PaymentInfoForQpay paymentInfo, final String code) {
		if (StringUtils.isBlank(paymentInfo.getOrderNo())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getExtend1())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getAmount())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getOrderTime())) {
			return false;
		}
		if (StringUtils.isBlank(code)) {
			return false;
		}
		String newCode=paymentInfo.getSignContent();
		log.debug("SESSION串：" + code);
		log.debug("生成串：" + newCode);

		if (code.equals(newCode)) {
			return true;
		}
		return false;
	}
	

	/**
	 * 验证支付用到数据(邮储支付【新增2014-11-27 11:59:02】)
	 * 
	 * @param orderNo
	 * @param handler
	 * @param amount
	 * @param code
	 * @return
	 */
	public static boolean validatePostPay(final PaymentInfoForPost paymentInfo, final String code) {
		if (StringUtils.isBlank(paymentInfo.getOrderNo())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getExtend1())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getAmount())) {
			return false;
		}
		if (StringUtils.isBlank(paymentInfo.getOrderTime())) {
			return false;
		}
		if (StringUtils.isBlank(code)) {
			return false;
		}
		String newCode=paymentInfo.getSignContent();
		log.debug("SESSION串：" + code);
		log.debug("生成串：" + newCode);

		if (code.equals(newCode)) {
			return true;
		}
		return false;
	}
	
}
