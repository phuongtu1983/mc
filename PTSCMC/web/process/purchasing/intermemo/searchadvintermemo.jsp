<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<form name="searchIntermemoForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.request.number"/></td>
            <td colspan="5"><html:text size="20" property="requestNumber" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.request.createdDate"/> <bean:message key="message.from"/></td>
            <td colspan="2"><input type="textbox" size="20" name="fromDate" id="fromDateRequest" onclick="javascript:showCalendar('fromDateRequest')"/></td>
            <td><bean:message key="message.to"/></td>
            <td colspan="2"><input type="textbox" size="20" name="toDate" id="toDateRequest" onclick="javascript:showCalendar('toDateRequest')"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.request.createdEmp"/></td>
            <td colspan="5">
                <html:select property="createdEmp" name="<%=Constants.REQUEST%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.intermemo.title"/></td>
            <td colspan="5"><html:text size="60" property="subject" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.request.statusSuggest"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="1"><bean:message key="message.request.statusSuggest1"/></html:radio></td>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="2"><bean:message key="message.request.statusSuggest2"/></html:radio></td>
                                <td><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="3"><bean:message key="message.request.statusSuggest3"/></html:radio></td>
                            </tr>
                        </table>
                    </p>
                </fieldset>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvIntermemo();"/>
</form>