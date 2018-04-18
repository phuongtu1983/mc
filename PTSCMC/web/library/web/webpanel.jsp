<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listweb.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchWeb.do">
    <table>            
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delWebs();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return webForm();"/>
                 <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.web.address" value='1'/>
                        <html:option key="message.web.content" value='2'/>
                        <html:option key="message.web.comment" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" size="60"/>
                    <html:submit onclick="return searchWeb();"><bean:message key="message.search"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='websForm' id='websForm'><div id='webList'></div></form>