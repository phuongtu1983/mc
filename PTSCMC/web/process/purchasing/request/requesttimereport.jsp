<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.requestadd.title"/>/<bean:message key="message.menu312.text.s"/></h3>
<form name="mcReportForm">
    <table>
        <tr>
            <td><bean:message key="message.project"/></td>
            <td colspan="3">
                <select name="mcReportProject" style="width: 170px;">
                    <logic:iterate id="project_iter" name="<%=Constants.PRO_LIST%>">
                        <option class="lbl9" value="${project_iter.value}">${project_iter.label}</option>
                    </logic:iterate>
                </select> 
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request"/></td>
            <td colspan="3" id="projectRequest">
                <!--<%@include  file="/process/store/report/cbxrequest.jsp"%>-->
                <input type="text" name="mcReportRequest" size="25"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request.deliveryDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="deliveryFromDate" size="10" id="mcDeliveryFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="deliveryEndDate" size="10" id="mcDeliveryEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <!--<tr>
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
            <td><bean:message key="message.rfm.report.createdDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="rfmFromDate" size="10" id="mcRfmFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="rfmEndDate" size="10" id="mcRfmEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.miv.report.createdDate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="mivFromDate" size="10" id="mcMivFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="mivEndDate" size="10" id="mcMivEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>-->
        <tr>
            <td><img onclick="return printRequestTime();" src="images/but_print.gif"/></td>
        </tr>
    </table>
</form>