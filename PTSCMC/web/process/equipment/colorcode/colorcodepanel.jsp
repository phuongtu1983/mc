<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listcolorcode.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchColorCode.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchColorCode();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_COLOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delColorCodes();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_COLOR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addColorCode();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.colorcode.colorCode" value='1'/>
                    <html:option key="message.colorcode.timeApplication" value='2'/>
                </html:select>
                <html:text property="searchvalue" />
                <html:submit onclick="return searchColorCode();"><bean:message key="message.search"/></html:submit>
                
        </div></td>
        </tr>
    </table>
</html:form>
<form name='colorCodesForm' id='colorCodesForm'><div id='colorCodeList'></div></form>