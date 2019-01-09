package com.zjht.adv.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjht.adv.dao.MobileVerifyDao;
import com.zjht.adv.entity.MobileVerify;
import com.zjht.adv.manager.MobileVerifyMng;
import com.zjht.adv.util.CodeBuilder;
import com.zjht.adv.util.PropertyUtil;
import com.zjht.adv.util.SmsSenderUtil;

@Service
@Transactional
public class MobileVerifyMngImpl implements MobileVerifyMng {
	//重发短信时间间隔
	private static String RESENDTIME = PropertyUtil.getPropertyValue("sms", "mobile.code.interval");
	//有效时间
	private static String EXPIREDTIME = PropertyUtil.getPropertyValue("sms", "mobile.code.expired");
	//短信内容模板
	private static String CONTENT = PropertyUtil.getPropertyValue("sms", "mobile.code.content");

	@Override
	public void delete(MobileVerify mobileVerify) {
		if (mobileVerify != null) {
			mobileVerifyDao.deleteById(mobileVerify.getId());
		}
	}

	@Override
	public void clearCode(String mobile) {
		MobileVerify bean = findByMobile(mobile);
		delete(bean);
	}

	@Override
	public void clearExpired() {
		if (StringUtils.isNotBlank(EXPIREDTIME)) {
			Long time = Long.valueOf(EXPIREDTIME);
			List<MobileVerify> list = mobileVerifyDao.findExpiredMobile(time);
			if (list != null) {
				for (MobileVerify mobileVerify : list) {
					delete(mobileVerify);
				}
			}
		}
	}

	@Override
	public MobileVerify findByMobile(String mobile) {
//		clearExpired();
		return mobileVerifyDao.findByMobile(mobile);
	}

	@Override
	public Map<String, String> verifySend(String mobile, String ipAddr) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("result", "1");
		data.put("msg", "可以发送");
		Long time = Long.valueOf(RESENDTIME);
		MobileVerify mobileBean = findByMobile(mobile);
		if (mobileBean != null) {
			if (mobileBean.getSendTime() != null && (System.currentTimeMillis() - mobileBean.getSendTime().getTime() < time)) {
				data.put("result", "2");
				data.put("msg", "频繁操作，请稍后再试");
			}
		}
		if (StringUtils.isNotBlank(ipAddr)) {
			MobileVerify ipAddrBean = mobileVerifyDao.findByIp(ipAddr);
			if (ipAddrBean != null) {
				if (ipAddrBean.getSendTime() != null && (System.currentTimeMillis() - ipAddrBean.getSendTime().getTime() < time)) {
					data.put("result", "2");
					data.put("msg", "频繁操作，请稍后再试");
				}
			}
		}
		return data;
	}

	@Override
	public MobileVerify sendVerifyCode(String mobile, String ipAddr) {
		MobileVerify bean = findByMobile(mobile);//根据手机号查找短信发送记录
		if (bean != null) {//存在发送记录，更新短信发送的验证码和内容
			bean.setCode(CodeBuilder.sixNO());
			bean.setContent(CONTENT.replace("{code}", bean.getCode()));
			// bean.setContent(" 测试:"+bean.getCode());
			bean.setSendTime(new Date());
			bean.setIpAddr(ipAddr);
			bean.setStatus(MobileVerify.SENDSTATUS_FAILED);
			mobileVerifyDao.update(bean);
		} else {//不存在发送记录，新增短信发送的验证码和内容记录
			bean = new MobileVerify();
			bean.setMobile(mobile);
			bean.setCode(CodeBuilder.sixNO());
			bean.setContent(CONTENT.replace("{code}", bean.getCode()));
			// bean.setContent(" 测试:"+bean.getCode());
			bean.setSendTime(new Date());
			bean.setIpAddr(ipAddr);
			bean.setStatus(MobileVerify.SENDSTATUS_FAILED);
			mobileVerifyDao.save(bean);
		}
		/*
		 * 发送短信（发送接口）
		 */
		smsSender.send(bean.getMobile(), bean.getContent(), 1);
		bean.setStatus(MobileVerify.SENDSTATUS_SUCCESS);//短信发送成功
		mobileVerifyDao.update(bean);//更新短信发送状态
		return bean;
	}

	@Override
	public boolean verifyCode(String mobile, String code) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		MobileVerify bean = mobileVerifyDao.findByMobile(mobile);
		if (bean == null) {
			return false;
		}
		if (StringUtils.isBlank(code)) {
			return false;
		}
		return bean.getCode().equals(code);
	}

	@Autowired
	private MobileVerifyDao mobileVerifyDao;
	@Autowired
	private SmsSenderUtil smsSender;

	@Override
	public void sendTest(String mobile, String content) {
		smsSender.send(mobile, content, 1);
	}
}
