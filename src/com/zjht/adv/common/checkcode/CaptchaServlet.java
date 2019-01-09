/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 

package com.zjht.adv.common.checkcode;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;
import com.zjht.adv.common.web.session.SessionProvider;

public class CaptchaServlet extends HttpServlet
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5277772535140976550L;
	public CaptchaServlet()
    {
    }

    @Override
	public void init(ServletConfig servletconfig)
        throws ServletException
    {
        super.init(servletconfig);
        org.springframework.web.context.WebApplicationContext webapplicationcontext = WebApplicationContextUtils.getWebApplicationContext(servletconfig.getServletContext());
        captchaService = (MultiTypeCaptchaService)BeanFactoryUtils.beanOfTypeIncludingAncestors(webapplicationcontext, MultiTypeCaptchaService.class);
        session = (SessionProvider)BeanFactoryUtils.beanOfTypeIncludingAncestors(webapplicationcontext,SessionProvider.class);
    }

    @Override
	protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        byte abyte0[] = null;
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try
        {
            String s = session.getSessionId(httpservletrequest, httpservletresponse);
            java.awt.image.BufferedImage bufferedimage = captchaService.getImageChallengeForID(s, httpservletrequest.getLocale());
            ImageIO.write(bufferedimage, "jpeg", bytearrayoutputstream);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            httpservletresponse.sendError(404);
            return;
        }
        catch(CaptchaServiceException captchaserviceexception)
        {
            httpservletresponse.sendError(500);
            return;
        }
        abyte0 = bytearrayoutputstream.toByteArray();
        httpservletresponse.setHeader("Cache-Control", "no-store");
        httpservletresponse.setHeader("Pragma", "no-cache");
        httpservletresponse.setDateHeader("Expires", 0L);
        httpservletresponse.setContentType("image/jpeg");
        ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        servletoutputstream.write(abyte0);
        servletoutputstream.flush();
        servletoutputstream.close();
    }

    public static final String CAPTCHA_IMAGE_FORMAT = "jpeg";
    private MultiTypeCaptchaService captchaService;
    private SessionProvider session;
}


/*
	DECOMPILATION REPORT

	Decompiled from: F:\javaproject\yihaodian\WebContent\WEB-INF\lib\jeecms-common.jar
	Total time: 0 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/