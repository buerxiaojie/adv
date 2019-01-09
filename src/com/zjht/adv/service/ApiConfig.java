package com.zjht.adv.service;

import com.zjht.adv.util.MessageDigestHelper;

/**
 * API接口属性文件中Key配置
 * @author lijunjie
 *
 */
public class ApiConfig {
	/**
	 * SHA-1签名
	 */
	public static String ALGORITHM="SHA-1";
	/**
	 * 终端编号
	 */
	public static String APPNO_KEY="esb.zjhtplatform.appNo";
	/**
	 * 终端接入密钥
	 */
	public static String APP_KEY="esb.zjhtplatform.key";
	/**
	 * 接口ip地址（含协议、地址、端口号、上下文）
	 */
	public static String APPURL_KEY="esb.zjhtplatform.ipAddr";
	/**
	 * 图片接口ip地址（含协议、地址、端口号、上下文）
	 */
	public static String APPURL_PIC_KEY="esb.zjhtplatform.pic.ipAddr";
	/**
	 * 版本1.0
	 */
	public static String VER_KEY="esb.zjhtplatform.ver.fir";
	/**
	 * 服务（所有商品信息列表查询）
	 */
	public static String SERVICE_QUERYALLCOUPONS="esb.zjhtplatform.service.queryAllCoupons";
	/**
	 * 服务（广告区商品信息列表查询）
	 */
	public static String SERVICE_QUERYADVERTCOUPONS="esb.zjhtplatform.service.queryAdvertCoupons";
	/**
	 * 服务（商品类别名称查询）
	 */
	public static String SERVICE_QUERYALLCATEGORIES="esb.zjhtplatform.service.queryAllCategories";
	/**
	 * 服务（分类商品信息列表查询）
	 */
	public static String SERVICE_QUERYCATEGORYCOUPONLIST="esb.zjhtplatform.service.queryCategoryCouponList";
	/**
	 * 服务（商品明细查询）
	 */
	public static String SERVICE_QUERYCOUPONDETAIL="esb.zjhtplatform.service.queryCouponDetail";
	/**
	 * 服务（商户门店查询）
	 */
	public static String SERVICE_QUERYSHOP="esb.zjhtplatform.service.queryShop";
	/**
	 * 服务（商品预下单）
	 */
	public static String SERVICE_ORDERCOUPONPRE="esb.zjhtplatform.service.orderCouponPre";
	/**
	 * 服务（订单信息列表查询）
	 */
	public static String SERVICE_QUERYORDERCOUPONLIST="esb.zjhtplatform.service.queryOrderCouponList";
	/**
	 * 服务（商品下单确认）
	 */
	public static String SERVICE_CONFIRMCOUPONORDER="esb.zjhtplatform.service.confirmCouponOrder";
	/*
	 * 服务（二维码信息列表查询）
	 */
	public static String SERVICE_QUERYBARCODELIST="esb.zjhtplatform.service.queryBarCodeList";
	/*
	 * 服务（二维码明细查询）
	 */
	public static String SERVICE_QUERYBARCODEDETAIL="esb.zjhtplatform.service.queryBarCodeDetail";
	/**
	 * 服务（图片查看）
	 */
	public static String SERVICE_VIEWPICFILECONTENT="esb.zjhtplatform.service.viewPicFileContent";
	/**
	 * 服务（用户积分兑换）
	 */
	public static String SERVICE_GETPOINTEXCHANGE="esb.zjhtplatform.service.getPointExchange";
	/**
	 * 服务（短信重发）
	 */
	public static String SERVICE_RESEND="esb.zjhtplatform.service.resend";
	/**
	 * 服务（查询辅助码明细接口(翼汇通之陶陶居项目)）
	 */
	public static String SERVICE_QUERYASSISTCODEDETAIL="esb.zjhtplatform.service.queryAssistCodeDetail";
	/**
	 * 服务（电子券验码消费接口(翼汇通之陶陶居项目)）
	 */
	public static String SERVICE_TICKETCONSUME="esb.zjhtplatform.service.ticketConsume";
	
	/**
	 * 天猫积分用户积分流水（第三方交易流水）yxy 2014-07-21
	 */
	public static String SERVICE_ADDTHIRDPARTYTRANSACTION="esb.zjhtplatform.service.addThirdPartyTransaction";
	/**
	 * 根据手机号获取手机信息，包括归属地和运营商。yxy 2014-08-6
	 */
	public static String SERVICE_GETMOBILEINFO="esb.zjhtplatform.service.getMobileInfo";
	
	/**
	 * 服务 手机短信发送
	 */
	public static String SERVICE_SENDMOBILESMS="esb.zjhtplatform.service.sendMobileSms";

	/**
	 * 终端接入Epay密钥
	 */
	public static String APP_KEY_EPAY="epay.zjhtplatform.appKey";
	/**
	 * 终端接入Epay appNo
	 */
	public static String APPNO_KEY_EPAY="epay.zjhtplatform.appNo";

	/**
	 * Epay接口ip地址
	 */
	public static String APPURL_KEY_EPAY="epay.zjhtplatform.ipAddr";

	/**
	 * 版本1.0
	 */
	public static String VER_KEY_EPAY="epay.zjhtplatform.ver.fir";
	
	/**
	 * EPAY油汇宝卡激活记录查询
	 */
	public static String APP_ESB_CARD_ACTIVE_RECORD_URL="epay.zjhtplatform.card.active.record.ipaddress";
	
	/**
	 * 生成签名
	 * @param key 终端接入密钥
	 * @param msg msg参数（json格式）
	 * @return
	 */
	public static String genSign(String key,String msg){
		return MessageDigestHelper.encode(ALGORITHM, key+msg+key, null);
	}
}
