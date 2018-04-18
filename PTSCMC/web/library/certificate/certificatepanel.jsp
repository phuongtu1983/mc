<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listcertificate.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchCertificate.do">
    <table>            
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_CERTIFICATE)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delCertificates();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_CERTIFICATE)) {%>
                <html:image src="images/ico_them.gif"  onclick="return certificateForm();"/>
                 <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.certificate.cerName" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" size="60"/>
                    <html:submit onclick="return searchCertificate();"><bean:message key="message.search"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='certificatesForm' id='certificatesForm'><div id='certificateList'></div></form>