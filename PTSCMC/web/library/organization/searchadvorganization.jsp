<%-- 
    Document   : searchadvorganization
    Created on : Aug 31, 2009, 8:47:17 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchOrganizationForm' name='searchOrganizationForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><bean:message key="message.organization.name"/></td>
            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.ORGANIZATION%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.organization.abbreviate"/></td>
            <td height="22" colspan="5"><html:text property="abbreviate" size="80" name="<%=Constants.ORGANIZATION%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.organization.parentId"/></td>
            <td height="22" colspan="5">
                <html:select property="parentId" name="<%=Constants.ORGANIZATION%>">
                    <html:options collection="<%=Constants.ORGANIZATION_PARENT%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.organization.status"/></td>
            <td height="22" colspan="5">
                <html:select property="status" name="<%=Constants.ORGANIZATION%>">
                    <html:options collection="<%=Constants.ORGANIZATION_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.organization.kind"/></td>
            <td height="22" colspan="5">
                <html:select property="kind" name="<%=Constants.ORGANIZATION%>">
                    <html:options collection="<%=Constants.ORGANIZATION_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvOrganization();"/>
</form>
