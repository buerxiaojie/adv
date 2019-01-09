package com.zjht.adv.common.fckeditor;

import javax.servlet.http.HttpServletRequest;
import net.fckeditor.requestcycle.UserPathBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjht.adv.common.file.FileNameUtils;
import com.zjht.adv.common.web.WebSite;

public class SitePathBuilder implements UserPathBuilder{
	
    public static final Logger log = LoggerFactory.getLogger(SitePathBuilder.class);

    @Override
	public String getUserFilesAbsolutePath(HttpServletRequest httpservletrequest){
        return WebSite.getUploadBasePath();
    }

    @Override
	public String getUserFilesPath(HttpServletRequest httpservletrequest){
        return WebSite.getUploadPath(FileNameUtils.genPathName());
    }

}
