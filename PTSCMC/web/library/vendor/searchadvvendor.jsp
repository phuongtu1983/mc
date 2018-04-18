<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchVendorForm' name='searchVendorForm'>
    <table>
        <tr>
            <td><bean:message key="message.vendor.l_name"/></td>
            <td colspan="5"><html:text property="name" size="80" name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.presenter"/></td>
            <td colspan="5"><html:text property="presenter" size="80" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.email"/></td>
            <td colspan="5"><html:text property="email" size="30"  name="<%=Constants.VENDOR%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.web"/></td>
            <td colspan="5"><html:text property="web" size="30" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.phone"/></td>
            <td><html:text property="phone" size="20" name="<%=Constants.VENDOR%>" /></td>
            <td><bean:message key="message.vendor.fax"/></td>
            <td><html:text property="fax" size="20" name="<%=Constants.VENDOR%>" /></td>
            <td><bean:message key="message.status"/></td>
            <td>
                <html:select property="status" name="<%=Constants.VENDOR%>">
                    <html:options collection="<%=Constants.VENDOR_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.address"/></td>
            <td colspan="5"><html:text property="address" size="20" name="<%=Constants.VENDOR%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.charterCapital"/></td>
            <td colspan="5"><html:text property="charterCapital" size="20" name="<%=Constants.VENDOR%>" /></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvVendor();"/>
</form>