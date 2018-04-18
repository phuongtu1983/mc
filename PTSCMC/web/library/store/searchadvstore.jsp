<%-- 
    Document   : searchadvstore
    Created on : Jun 27, 2009, 8:47:17 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchStoreForm' name='searchStoreForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><bean:message key="message.store.proId"/></td>
            <td height="22" colspan="5">
                <html:select property="proId" name="<%=Constants.STORE%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="proId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.store.l_name"/></td>
            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.STORE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.store.physicalAdd"/></td>
            <td height="22" colspan="5"><html:text property="physicalAdd" size="80" name="<%=Constants.STORE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.store.kind"/></td>
            <td height="22" colspan="5">
                <html:select property="kind" name="<%=Constants.STORE%>">
                    <html:options collection="<%=Constants.STORE_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvStore();"/>
</form>
