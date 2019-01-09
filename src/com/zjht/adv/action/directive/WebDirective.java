package com.zjht.adv.action.directive;

import com.zjht.adv.common.web.freemarker.*;

import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContext;
import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

public abstract class WebDirective implements TemplateDirectiveModel {
	protected final Logger log = LoggerFactory.getLogger(getClass());
    
    public static final String LOCATION = "location";
    public static final String URL_PREFIX = "urlPrefix";
    public static final String URL_SUFFIX = "urlSuffix";
    public static final String PAGE_NO = "pageNo";
    public static final String SYS_RES_ROOT = "sysResRoot";
    public static final String ROOT = "root";
    public static final String WEB = "web";
    public static final String BASE_DOMAIN = "baseDomain";
    public static final String LOGIN_URL = "loginUrl";
    public static final String CONFIG = "config";
    public static final String MEMBER = "member";
    public static final String GROUP = "group";
    public static final String PARAM_WEB_ID = "webId";
    public static final String PARAM_TPL = "tpl";
    public static final String PARAM_TPL_SUB = "tplSub";
    public static final String PARAM_COUNT = "count";
    public static final int MAX_COUNT = 200;
    public static final boolean PARAM_TPL_DEF = false;
    public static final String OUT_LIST = "tag_list";
    public static final String OUT_PAGINATION = "tag_pagination";
    public static final String OUT_BEAN="tag_bean";

    protected void renderBody(Environment env, TemplateDirectiveBody body)
        throws TemplateException, IOException{
    	body.render(env.getOut());
    }

    protected RequestContext getContext(Environment env)
        throws TemplateException{
        TemplateModel templatemodel = env.getGlobalVariable("springMacroRequestContext");
        if(templatemodel instanceof AdapterTemplateModel){
            return (RequestContext)((AdapterTemplateModel)templatemodel).getAdaptedObject(RequestContext.class);
        }else{
            throw new TemplateModelException("RequestContext 'springMacroRequestContext' not found in DataModel.");
        }
    }

    protected int getPageNo(Environment env)
        throws TemplateException {
        TemplateModel templatemodel = env.getGlobalVariable("pageNo");
        if(templatemodel instanceof TemplateNumberModel){
            return ((TemplateNumberModel)templatemodel).getAsNumber().intValue();
        }else{
        	return 1;
        }
    }

	protected int getCount(@SuppressWarnings("rawtypes") Map params) throws TemplateException{
        Integer integer = getInt("count", params);
        if(integer == null){
        	return 20;
        }
        if(integer.intValue() > 200){
            integer = Integer.valueOf(1);
        }else{
           if(integer.intValue() < 1){
               integer = Integer.valueOf(200);
           }
        }
        return integer.intValue();
    }

	protected boolean isInvokeTpl(@SuppressWarnings("rawtypes") Map params) throws TemplateException{
        TemplateModel templatemodel = (TemplateModel)params.get("tpl");
        if(templatemodel == null){
            return false;
        }
        if(templatemodel instanceof TemplateScalarModel){
            return DirectiveUtils.getBoolean((TemplateScalarModel)templatemodel);
        }else{
            return false;
        }
    }

	protected String getSubTpl(@SuppressWarnings("rawtypes") Map params) throws TemplateException{
        TemplateModel templatemodel = (TemplateModel)params.get("tplSub");
        if(templatemodel == null){
            return null;
        }
        if(templatemodel instanceof TemplateScalarModel){
            return ((TemplateScalarModel)templatemodel).getAsString();
        }else{
            throw new MustStringException("tplSub");
        }
    }

	protected String getString(String s, @SuppressWarnings("rawtypes") Map params) throws TemplateException{
        TemplateModel templatemodel = (TemplateModel)params.get(s);
        if(templatemodel == null){
            return null;
        }
        if(templatemodel instanceof TemplateScalarModel){
            return ((TemplateScalarModel)templatemodel).getAsString();
        }else{
            throw new MustStringException(s);
        }
    }

	protected Long getLong(String s, @SuppressWarnings("rawtypes") Map params) throws TemplateException {
        TemplateModel templatemodel = (TemplateModel)params.get(s);
        if(templatemodel == null){
            return null;
        }
        if(templatemodel instanceof TemplateScalarModel){
            String s1 = ((TemplateScalarModel)templatemodel).getAsString();
            if(StringUtils.isBlank(s1)){
                return null;
            }
            try{
                return Long.valueOf(Long.parseLong(s1));
            }catch(NumberFormatException e){
                throw new MustNumberException(s);
            }
        }
        if(templatemodel instanceof TemplateNumberModel){
            return Long.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().longValue());
        }else{
            throw new MustNumberException(s);
        }
    }

	protected Integer getInt(String s, @SuppressWarnings("rawtypes") Map params) throws TemplateException {
        TemplateModel templatemodel = (TemplateModel)params.get(s);
        if(templatemodel == null){
            return null;
        }
        if(templatemodel instanceof TemplateScalarModel){
            String s1 = ((TemplateScalarModel)templatemodel).getAsString();
            if(StringUtils.isBlank(s1)){
                return null;
            }
            try{
                return Integer.valueOf(Integer.parseInt(s1));
            }catch(NumberFormatException e){
                throw new MustNumberException(s);
            }
        }
        if(templatemodel instanceof TemplateNumberModel){
            return Integer.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue());
        }else{
            throw new MustNumberException(s);
        }
    }

	protected Boolean getBool(String s, @SuppressWarnings("rawtypes") Map params)  throws TemplateException{
        TemplateModel templatemodel = (TemplateModel)params.get(s);
        if(templatemodel == null){
            return null;
        }
        if(templatemodel instanceof TemplateScalarModel){
            String s1 = ((TemplateScalarModel)templatemodel).getAsString();
            if(StringUtils.isBlank(s1)){
                return null;
            }else{
                return Boolean.valueOf(!s1.equals("0"));
            }
        }
        if(templatemodel instanceof TemplateNumberModel){
            return Boolean.valueOf(((TemplateNumberModel)templatemodel).getAsNumber().intValue() != 0);
        }else{
            throw new MustNumberException(s);
        }
    }
}
