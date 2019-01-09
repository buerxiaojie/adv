package com.zjht.adv.common.resolver;

import javax.servlet.ServletContext;
import org.springframework.web.context.ServletContextAware;

public class ServletContextRealPathResolver implements RealPathResolver, ServletContextAware{

    private ServletContext context;
    @Override
	public String get(String realPath){
        return context.getRealPath(realPath);
    }

    @Override
	public void setServletContext(ServletContext context){
        this.context = context;
    }
}
