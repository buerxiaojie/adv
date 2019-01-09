package com.zjht.adv.common.fckeditor;

import javax.servlet.http.HttpServletRequest;
import net.fckeditor.requestcycle.UserAction;

public class SiteUserAction implements UserAction{

    @Override
	public boolean isCreateFolderEnabled(HttpServletRequest request){
        return false;
    }

    @Override
	public boolean isEnabledForFileBrowsing(HttpServletRequest request){
        return false;
    }

    @Override
	public boolean isEnabledForFileUpload(HttpServletRequest request){
        return true;
    }
}
