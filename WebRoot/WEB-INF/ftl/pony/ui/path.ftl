<#--
返回图片上传的根目录
-->
<#macro path type="" src="">
<#if type=="imgupload">
<#t/>/res/upload${src}<#t/>
<#elseif type=="css">
<#t/>/res/front/v2015/css/${src}<#t/>
<#elseif type=="jscommon">
<#t/>/res/front/v2015/commonjs/${src}<#t/>
<#elseif type=="images">
<#t/>/res/front/v2015/images/${src}<#t/>
<#elseif type=="js">
<#t/>/res/front/v2015/js/${src}<#t/>
</#if>
</#macro>
