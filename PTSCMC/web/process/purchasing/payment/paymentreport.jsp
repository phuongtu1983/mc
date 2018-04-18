<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.requestadd.title"/>/<bean:message key="message.menu311.text.s"/></h3>
<form name="paymentReportForm">
    <table>
        <tr>
            <td><bean:message key="message.createdDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="createdFromDate" size="10" id="paymentCreatedFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="createdEndDate" size="10" id="paymentCreatedEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td  colspan="4">
                <img onclick="return printPaymentReport();" src="images/but_print.gif"/>
            </td>
        </tr>
    </table>
</form>
