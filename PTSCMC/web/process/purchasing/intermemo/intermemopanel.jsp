<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.intermemoadd.title"/>/<bean:message key="message.list.s"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchIntermemo.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchIntermemo();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_INTERMEMO)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delIntermemos();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_INTERMEMO)) {%>
                <html:image src="images/ico_them.gif"  onclick="return intermemoForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.request.number" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchIntermemo();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvIntermemoForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='intermemosForm' id='intermemosForm'><div id='intermemoList'></div></form>