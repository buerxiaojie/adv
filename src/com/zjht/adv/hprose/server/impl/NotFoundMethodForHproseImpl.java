package com.zjht.adv.hprose.server.impl;

import org.springframework.stereotype.Service;

import com.zjht.adv.hprose.server.NotFoundMethodForHprose;

@Service
public class NotFoundMethodForHproseImpl implements NotFoundMethodForHprose {

	@Override
	public String getMethodForHprose(String param) {
		return "{\"respCode\":\"99999\",\"respMsg\":\"找不到请求的服务方法，请检查url是否正确！\"}";
	}

}
