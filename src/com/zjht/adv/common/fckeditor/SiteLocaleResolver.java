package com.zjht.adv.common.fckeditor;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import net.fckeditor.localization.LocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.LocaleEditor;

public class SiteLocaleResolver implements LocaleResolver{
    private  static final Logger log = LoggerFactory.getLogger(SiteLocaleResolver.class);

    @Override
	public Locale resolveLocale(HttpServletRequest request){
        String language = "zh_CN";
        LocaleEditor localeeditor = new LocaleEditor();
        localeeditor.setAsText(language);
        Locale locale = (Locale)localeeditor.getValue();
        log.debug("get admin locale: '{}'", locale);
        return locale;
    }
}
