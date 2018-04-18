<%-- 
    Document   : searchadvemployee
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
<form id='searchEmployeeForm' name='searchEmployeeForm'>
    <table border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><bean:message key="message.employee.orgName"/></td>
            <td height="22" colspan="5">
                <html:select property="orgId" name="<%=Constants.EMPLOYEE%>">
                    <html:options collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.posName"/></td>
            <td height="22" colspan="5">
                <html:select property="posId" name="<%=Constants.EMPLOYEE%>">
                    <html:options collection="<%=Constants.POSITION_LIST%>" property="posId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>        
        <tr>
            <td height="22"><bean:message key="message.employee.fullname"/></td>
            <td height="22" colspan="5"><html:text property="fullname" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.nickname"/></td>
            <td height="22" colspan="5"><html:text property="nickname" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.password"/></td>
            <td height="22" colspan="5"><html:text property="password" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.email"/></td>
            <td height="22" colspan="5"><html:text property="email" size="80" name="<%=Constants.EMPLOYEE%>"/></td>
        </tr>        
        <tr>
            <td height="22"><bean:message key="message.employee.officePhone"/></td>
            <td height="22" colspan="5"><html:text property="officePhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.handPhone"/></td>
            <td height="22" colspan="5"><html:text property="handPhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.homePhone"/></td>
            <td height="22" colspan="5"><html:text property="homePhone" size="80" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.status"/></td>
            <td height="22" colspan="5"><html:text property="status" size="30"  name="<%=Constants.EMPLOYEE%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.createdDate"/></td>
            <td height="22" colspan="5"><html:text property="createdDate" size="30" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.modifiedDate"/></td>
            <td height="22" colspan="5"><html:text property="modifiedDate" size="30" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.lastLogonDate"/></td>
            <td height="22" colspan="5"><html:text property="lastLogonDate" size="30" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.firstIp"/></td>
            <td height="22" colspan="5"><html:text property="firstIp" size="30" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.employee.lastIp"/></td>
            <td height="22" colspan="5"><html:text property="lastIp" size="30" name="<%=Constants.EMPLOYEE%>" /></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEmployee();"/>
</form>
