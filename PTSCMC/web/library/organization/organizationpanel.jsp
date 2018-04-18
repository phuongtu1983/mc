<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listorganization.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchOrganization.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchOrganization();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delOrganizations();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addOrganization();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.organization.l_name" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" size="60"/>
                    <html:submit onclick="return searchOrganization();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvOrganizationForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='organizationsForm' id='organizationsForm'><div id='organizationList'></div></form>