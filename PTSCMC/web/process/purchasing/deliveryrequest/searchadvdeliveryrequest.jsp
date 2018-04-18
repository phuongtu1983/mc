<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<form name="searchDeliveryRequestForm">
   <table width="100%">
        <tr>
            <td><bean:message key="message.deliveryrequest.number"/></td>
            <td colspan="3"><html:text size="20" property="contractNumber" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.deliveryrequest.createdDate"/> <bean:message key="message.from"/></td>
            <td><html:text property="effectedFromDate" size="10" styleId="effectedFromDate" onclick="javascript: showCalendar('effectedFromDate')" name="<%=Constants.CONTRACT%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="effectedToDate" size="10" styleId="effectedToDate" onclick="javascript: showCalendar('effectedToDate')" name="<%=Constants.CONTRACT%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.vendor"/></td>
            <td colspan="3">
                <html:select property="venId" name="<%=Constants.CONTRACT%>">
                    <html:options collection="<%=Constants.VENDOR_LIST%>" property="venId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/> <bean:message key="message.from"/></td>
            <td><html:text size="20" property="totalFrom" name="<%=Constants.CONTRACT%>"/></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text size="20" property="totalTo" name="<%=Constants.CONTRACT%>"/></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvDeliveryRequest();"/>
    <html:hidden property="kind" name="<%=Constants.CONTRACT%>"/>
</form>