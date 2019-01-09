package com.zjht.adv.hprose.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.stereotype.Component;

//@Target(ElementType.TYPE)  
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HproseService {
	
}
