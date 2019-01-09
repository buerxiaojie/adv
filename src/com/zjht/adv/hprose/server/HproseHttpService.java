package com.zjht.adv.hprose.server;

import java.io.IOException;

import com.zjht.adv.hprose.server.impl.HproseHttpMethods;
import com.zjht.adv.hprose.server.impl.HttpContext;

public interface HproseHttpService {
	
	public void handle(HttpContext httpContext, HproseHttpMethods methods) throws IOException ;
	
	 public void handle(HttpContext httpContext) throws IOException ;

}
