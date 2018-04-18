<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchVenContactForm' name='searchVenContactForm'>
    <table>
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
            <td colspan="3"><html:text property="email" size="80" name="<%=Constants.VENDOR_CONTACT%>" /></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvVenContact();"/>
    <html:hidden property="venId" name="<%=Constants.VENDOR_CONTACT%>"/>
</form>