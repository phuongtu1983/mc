<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<form name="searchInvoiceForm">
    <table width="100%">
        <tr>
            <td><bean:message key="message.contract.bill.number"/></td>
            <td colspan="3"><html:text size="20" property="invoice" name="<%=Constants.INVOICE%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.number"/></td>
            <td colspan="3"><html:text size="20" property="contractNumber" name="<%=Constants.INVOICE%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.bill.date"/> <bean:message key="message.from"/></td>
            <td><html:text property="paymentFromDate" size="10" styleId="paymentFromDate" onclick="javascript: showCalendar(this)" name="<%=Constants.INVOICE%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="paymentToDate" size="10" styleId="paymentToDate" onclick="javascript: showCalendar(this)" name="<%=Constants.INVOICE%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.bill.invoiceDate"/> <bean:message key="message.from"/></td>
            <td><html:text property="invoiceFromDate" size="10" styleId="invoiceFromDate" onclick="javascript: showCalendar(this)" name="<%=Constants.INVOICE%>" /></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text property="invoiceToDate" size="10" styleId="invoiceToDate" onclick="javascript: showCalendar(this)" name="<%=Constants.INVOICE%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.total"/> <bean:message key="message.from"/></td>
            <td><html:text size="20" property="totalFrom" name="<%=Constants.INVOICE%>"/></td>
            <td><bean:message key="message.to"/></td>
            <td><html:text size="20" property="totalTo" name="<%=Constants.INVOICE%>"/></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvInvoice();"/>
</form>