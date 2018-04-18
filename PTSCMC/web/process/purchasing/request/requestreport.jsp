<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.requestadd.title"/>/<bean:message key="message.menu311.text.s"/></h3>
<form name="mcReportForm">
    <table>
        <tr>
            <td><bean:message key="message.project"/></td>
            <td colspan="3">
                <%@include  file="/process/store/report/cbxproject.jsp"%>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request"/></td>
            <td colspan="3">
                <input type="text" name="mcReportRequest" size="40"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.number"/></td>
            <td colspan="3">
                <input type="text" name="mcReportContract" size="40"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.u_vendor"/></td>
            <td colspan="3">
                <input type="text" name="mcReportVendor" size="40"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request.deliveryDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="deliveryFromDate" size="10" id="mcDeliveryFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="deliveryEndDate" size="10" id="mcDeliveryEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.contract.report.effectedDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="contractFromDate" size="10" id="mcContractFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="contractEndDate" size="10" id="mcContractEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.mrir.report.createdDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="mrirFromDate" size="10" id="mcMrirFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="mrirEndDate" size="10" id="mcMrirEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.msv.report.createddate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="msvFromDate" size="10" id="mcMsvFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="msvEndDate" size="10" id="mcMvsEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.appendix.deliveryDateAsContract"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="deliveryDateAsContractFromDate" size="10" id="deliveryDateAsContractFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="deliveryDateAsContractToDate" size="10" id="deliveryDateAsContractToDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td  colspan="4">
                <img onclick="return printRequestReport();" src="images/but_print.gif"/>
                <!--<img onclick="return showRequestReport();" src="images/but_find.gif"/>-->
            </td>
        </tr>
    </table>
</form>
