<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchVenContact.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchVenContact();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVenContacts();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return venContactForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.vendor.contact.name" value='1'/>
                        <html:option key="message.vendor.contact.position" value='2'/>
                        <html:option key="message.vendor.contact.handPhone" value='3'/>
                        <html:option key="message.vendor.contact.email" value='4'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchVenContact();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvVenContactForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='venContactsForm' id='venContactsForm'><div id='venContactList'></div></form>