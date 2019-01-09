package com.zjht.adv.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.zjht.adv.common.web.Constants;
import com.zjht.adv.util.PropertyUtil;
import com.zjht.adv.util.QpaySignature;

public class PaymentInfoForQpay {

	private String version;
	private String charset;
	private String currency;
	private String partnerCode;
	private String accountName;
	private String orderNo;
	private String orderTime;
	private String amount;
	private String signType;
	private String returnUrl;
	private String notifyUrl;

	// extend1作为支付回调类型参数判断属于什么业务逻辑
	private String extend1;
	private String extend2;

	private String payTime;
	private String payResultCode;
	private String payResultDesc;
	// 支付平台订单号
	private String paygatewayOrderNo;
	// 支付平台路径
	private String platformPayUrl;

	// 加密后信息
	private String signContent;

	// 私钥名称
	private String privateKeyPath;
	// 私钥密码
	private String privateKeyPsw;
	// 公钥名称
	private String publicKeyName;

	public PaymentInfoForQpay() {
		this.version = "";
		this.charset = "";
		this.currency = "";
		this.partnerCode = "";
		this.accountName = "";
		this.orderNo = "";
		this.orderTime = "";
		this.amount = "";
		this.signType = "";
		this.returnUrl = "";
		this.notifyUrl = "";
		this.extend1 = "";
		this.extend2 = "";

		this.payTime = "";
		this.payResultCode = "";
		this.payResultDesc = "";
		this.paygatewayOrderNo = "";
		this.signContent = "";
	}

	public String getSignStr() {
		StringBuffer signStr = new StringBuffer();
		signStr.append("version").append("=").append(this.version).append("&");
		signStr.append("charset").append("=").append(this.charset).append("&");
		signStr.append("currency").append("=").append(this.currency).append("&");
		signStr.append("partnerCode").append("=").append(this.partnerCode).append("&");
		signStr.append("accountName").append("=").append(this.accountName).append("&");
		signStr.append("orderNo").append("=").append(this.orderNo).append("&");
		signStr.append("orderTime").append("=").append(this.orderTime).append("&");
		signStr.append("amount").append("=").append(this.amount).append("&");
		signStr.append("signType").append("=").append(this.signType).append("&");
		signStr.append("returnUrl").append("=").append(this.returnUrl).append("&");
		signStr.append("notifyUrl").append("=").append(this.notifyUrl).append("&");
		signStr.append("extend1").append("=").append(this.extend1).append("&");
		signStr.append("extend2").append("=").append(this.extend2);
		return signStr.toString();
	}

	public String getCallbackSignStr() {
		StringBuffer signStr = new StringBuffer();
		signStr.append("version").append("=").append(this.version).append("&");
		signStr.append("charset").append("=").append(this.charset).append("&");
		signStr.append("currency").append("=").append(this.currency).append("&");
		signStr.append("partnerCode").append("=").append(this.partnerCode).append("&");
		signStr.append("accountName").append("=").append(this.accountName).append("&");
		signStr.append("orderNo").append("=").append(this.orderNo).append("&");
		signStr.append("orderTime").append("=").append(this.orderTime).append("&");
		signStr.append("amount").append("=").append(this.amount).append("&");
		signStr.append("signType").append("=").append(this.signType).append("&");
		signStr.append("returnUrl").append("=").append(this.returnUrl).append("&");
		signStr.append("notifyUrl").append("=").append(this.notifyUrl).append("&");
		signStr.append("extend1").append("=").append(this.extend1).append("&");
		signStr.append("extend2").append("=").append(this.extend2).append("&");
		signStr.append("payTime").append("=").append(this.payTime).append("&");
		signStr.append("payResultCode").append("=").append(this.payResultCode).append("&");
		signStr.append("payResultDesc").append("=").append(this.payResultDesc);
		return signStr.toString();
	}

	/**
	 * 回调支付签名是否匹配
	 * 
	 * @return
	 */
	public boolean isCallbackMatch() {
		boolean isCallbackMatch = QpaySignature.enCodeByCerForQpay(this.getCallbackSignStr(), this.getSignContent(), this.getPublicKeyName());
		return isCallbackMatch;
	}

	/**
	 * 回调支付状态是否成功
	 * 
	 * @return
	 */
	public boolean isPaySuccess() {
		return Constants.CEP_PAYMENT_SUCCESS.equals(this.payResultCode);
	}

	/**
	 * 金额是否匹配
	 * 
	 * @param amount
	 * @return
	 */
	public boolean isAmountMatch(BigDecimal amount) {
		if (amount == null) {
			return false;
		}
		if (StringUtils.isBlank(this.getAmount())) {
			return false;
		}
		return new BigDecimal(this.getAmount()).compareTo(amount) == 0;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getSignContent() {
		// 如果是回调则不必重新生成signContent
		if (StringUtils.isBlank(this.signContent)) {
			this.signContent = QpaySignature.encodeMsgByPrivateKeyForQpay(this.getPrivateKeyPath(), this.getSignStr(), this.getPrivateKeyPsw());
		}
		return signContent;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayResultCode() {
		return payResultCode;
	}

	public void setPayResultCode(String payResultCode) {
		this.payResultCode = payResultCode;
	}

	public String getPayResultDesc() {
		return payResultDesc;
	}

	public void setPayResultDesc(String payResultDesc) {
		this.payResultDesc = payResultDesc;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public String getPaygatewayOrderNo() {
		return paygatewayOrderNo;
	}

	public void setPaygatewayOrderNo(String paygatewayOrderNo) {
		this.paygatewayOrderNo = paygatewayOrderNo;
	}

	public String getPrivateKeyPath() {
		return privateKeyPath != null ? privateKeyPath : PropertyUtil.getPropertyValueDir("mobile", "qpay_private_key_name");
	}

	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}

	public String getPrivateKeyPsw() {
		return privateKeyPsw != null ? privateKeyPsw : PropertyUtil.getPropertyValueDir("mobile", "qpay_private_key_psw");
	}

	public void setPrivateKeyPsw(String privateKeyPsw) {
		this.privateKeyPsw = privateKeyPsw;
	}

	public String getPublicKeyName() {
		return publicKeyName != null ? publicKeyName : PropertyUtil.getPropertyValueDir("mobile", "qpay_public_key_file_name");
	}

	public void setPublicKeyName(String publicKeyName) {
		this.publicKeyName = publicKeyName;
	}

	public String getPlatformPayUrl() {
		return platformPayUrl != null ? platformPayUrl : PropertyUtil.getPropertyValueDir("mobile", "qpay_platform_pay_url");
	}

	public void setPlatformPayUrl(String platformPayUrl) {
		this.platformPayUrl = platformPayUrl;
	}

}
