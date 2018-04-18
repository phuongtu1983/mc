<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listorigin.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchOrigin.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchOrigin();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delOrigins();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_MATERIAL_ORIGIN)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addOrigin();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.origin.nameEn" value='1'/>
                    <html:option key="message.origin.nameVn" value='2'/>
                </html:select>
                <html:text property="searchvalue" size="60"/>
                <html:submit onclick="return searchOrigin();"><bean:message key="message.search"/></html:submit>
                
        </div></td>
        </tr>
    </table>
</html:form>
<form name='originsForm' id='originsForm'><div id='originList'></div></form>