<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="venContactFormError"></div>
<form name="venContactForm">
    <table>
        <tr>
            <td><bean:message key="message.vendor.l_name"/></td>
            <td colspan="3"><html:text property="vendorName" disabled="true" size="80" name="<%=Constants.VENDOR_CONTACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.contact.name"/></td>
            <td colspan="3"><html:text property="name" size="80" name="<%=Constants.VENDOR_CONTACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.contact.position"/></td>
            <td><html:text property="position" size="20" name="<%=Constants.VENDOR_CONTACT%>" /></td>
            <td><bean:message key="message.vendor.contact.handPhone"/></td>
            <td><html:text property="handPhone" size="20" name="<%=Constants.VENDOR_CONTACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.contact.officePhone"/></td>
            <td><html:text property="officePhone" size="20" name="<%=Constants.VENDOR_CONTACT%>" /></td>
            <td><bean:message key="message.vendor.contact.homePhone"/></td>
            <td><html:text property="homePhone" size="20" name="<%=Constants.VENDOR_CONTACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.contact.email"/></td>
            <td><html:text property="email" size="40" name="<%=Constants.VENDOR_CONTACT%>" /></td>
            <td><bean:message key="message.vendor.contact.birthday"/></td>
            <td><html:text property="birthday" size="10" styleId="birthday" onclick="javascript: showCalendar('birthday')" name="<%=Constants.VENDOR_CONTACT%>" /></td>
        </tr>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <html:image onclick="return saveVenContact();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadVendorContactList();"/>
    <html:hidden property="contId" name="<%=Constants.VENDOR_CONTACT%>"/>
    <html:hidden property="venId" styleId="venIdContact" name="<%=Constants.VENDOR_CONTACT%>"/>
</form>