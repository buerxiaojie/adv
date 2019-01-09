package com.zjht.adv.common.cache.impl;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zjht.adv.common.cache.CoreCacheSvc;

@Service
public class CoreCacheSvcImpl implements CoreCacheSvc{
	
    private static final String WEBSITE_COUNT = "core_website_count";
    private Ehcache globalCache;
    
	@Autowired
    public void setGlobalCache(@Qualifier("global") Ehcache globalCache){
        this.globalCache = globalCache;
    }

    @Override
	public void putWebsiteCount(int paramInt){
        this.globalCache.put(new Element(WEBSITE_COUNT, Integer.valueOf(paramInt)));
    }

    @Override
	public Integer getWebsiteCount(){
        Element element = this.globalCache.get(WEBSITE_COUNT);
        if(element != null){
            return (Integer)element.getValue();
        }else{
            return null;
        }
    }

}
