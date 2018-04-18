<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>

<%
    String ftype = MCUtil.getParameter("ftype",request);
    String pid = MCUtil.getParameter("pid",request);    
    String fdiv = MCUtil.getParameter("fdiv",request);    
%>      
<fieldset>
    <legend class="lbl10b"><img src="img/attach_logo.png"/>&nbsp;<bean:message key="message.file"/>&nbsp;</legend>                        
    <table cellspacing="0" cellpadding="0" >
        <tr>
            <td class="lbl10"><bean:message key="message.file.list"/>&nbsp;</td>
            <td class="lbl10"><p style="margin-top:0; margin-bottom:0" align="left">                                                  
                    <display:table name="<%=Constants.ATTACH_FILE_LIST%>" id="fileAttachment" class="none" decorator="com.venus.mc.upload.decorator.FileDecorator">
                        <display:setProperty name="basic.show.header" value="false" />
                        <display:setProperty name="paging.banner.items_name" value=""/>
                        <display:setProperty name="paging.banner.item_name" value=""/>
                        <display:setProperty name="basic.msg.empty_list_row" ><bean:message key='message.file.empty'/></display:setProperty>
                        <display:setProperty name="css.tr.odd" value ="" />
                        <display:setProperty name="css.tr.even" value ="" /> 
                        <display:column titleKey="message.file" property="link" />                            
                    </display:table>
                </p>
            </td>
        </tr>                
        <tr><td>&nbsp;</td>
            <td class="lbl10">
                <a class="my3" href="uploadForm.do?ftype=<%=ftype%>&pid=<%=pid%>&session=<%=session.getAttribute(Constants.SESSION_ID)%>" target="new_dialog" onclick="showUploadDialog(<%=ftype%>,<%=pid%>,'<%=fdiv%>');">
                    <bean:message key="message.file.add"/>                    
                </a>
            </td>
        </tr>
        <tr><td>&nbsp;</td>
            <td class="lbl8"><bean:message key="message.file.loaddone"/></td>
        </tr>    
    </table>
</fieldset>
