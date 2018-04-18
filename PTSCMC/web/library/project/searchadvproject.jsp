<%-- 
    Document   : searchadvproject
    Created on : Jun 27, 2009, 11:29:29 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchProjectForm' name='searchProjectForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><bean:message key="message.project.l_name"/></td>
            <td height="22" colspan="5"><html:text property="name" size="80" name="<%=Constants.PROJECT%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.project.startDate"/></td>
            <td height="22" colspan="5"><html:text property="startDate" size="80" styleId="startDateProject" onclick="javascript: showCalendar('startDateProject')" name="<%=Constants.PROJECT%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.project.endDate"/></td>
            <td height="22" colspan="5"><html:text property="endDate" size="80" styleId="endDateProject" onclick="javascript: showCalendar('endDateProject')" name="<%=Constants.PROJECT%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.project.status"/></td>
            <td height="22" colspan="5">
                <html:select property="status" name="<%=Constants.PROJECT%>">
                    <html:options collection="<%=Constants.PROJECT_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvProject();"/>
</form>
