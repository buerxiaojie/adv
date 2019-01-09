package com.zjht.adv.common.fckeditor;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.AbstractLocalFileSystemConnector;
import net.fckeditor.handlers.RequestCycleHandler;
import net.fckeditor.handlers.ResourceType;
import net.fckeditor.requestcycle.ThreadLocalData;
import net.fckeditor.tool.UtilsFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zjht.adv.common.file.FileNameUtils;
import com.zjht.adv.common.web.Constants;

public class SiteConnector extends AbstractLocalFileSystemConnector{
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    protected ServletContext servletContext;

    @Override
	public String fileUpload(ResourceType resourcetype, String s, String s1, InputStream inputstream)
        throws InvalidCurrentFolderException, WriteException{
        String s2 = getRealUserFilesAbsolutePath(RequestCycleHandler.getUserFilesAbsolutePath(ThreadLocalData.getRequest()));
		String dateDir = FileNameUtils.genPathName();
		String relPath= s2 + Constants.SPT + dateDir;
        File file = getOrCreateResourceTypeDir(relPath, resourcetype);
        File file1 = new File(file, s);
        if(!file1.exists() || !file1.isDirectory()){
        	file1.mkdirs();
        }
        s1 = FileNameUtils.genFileNameWithOutPath(FilenameUtils.getExtension(s1));
        File file2 = new File(file1, s1);
        File file3 = UtilsFile.getUniqueFile(file2.getAbsoluteFile());
        try{
            IOUtils.copyLarge(inputstream, new FileOutputStream(file3));
        }catch(IOException e){
            throw new WriteException();
        }
        return Constants.SPT + file3.getName();
    }

    @Override
	public void init(ServletContext servletcontext) throws Exception{
        this.servletContext = servletcontext;
        String s = getRealUserFilesAbsolutePath(PropertiesLoader.getUserFilesPath());
        if(s == null){
            log.error("The context root cannot be resolved against the local filesystem");
            log.info("Your servlet container/application server does not expand deployed war files");
            log.debug("Use another Connector implementation (e.g. LocalConnector) and consult http://www.fckeditor.net/forums/viewtopic.php?f=6&t=11568");
            throw new NullPointerException("The real context root cannot be resolved against the local filesystem");
        }
    }

    @Override
	protected String getRealUserFilesAbsolutePath(String s){
        return this.servletContext.getRealPath(s);
    }

}
