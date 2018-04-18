<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="message.menu441.u_text"/>/<bean:message key="message.menu4415.text.s"/></h3>
<form name="mcReportForm">
    <table>
        <tr>
            <td><bean:message key="message.project"/></td>
            <td colspan="3">
                <select name="mcReportProject" onchange="return mcReportProjectChanged(this)">
                    <logic:iterate id="project_iter" name="<%=Constants.PRO_LIST%>">
                        <option class="lbl9" value="${project_iter.value}">${project_iter.label}</option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.store"/></td>
            <td colspan="3" id="projectStore">
                <%@include  file="/process/store/report/cbxstore.jsp"%>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.request.materialKind"/></td>
            <td colspan="3">
                <select name="mcReportMaterialKind">
                    <logic:present name="<%=Constants.MATERIAL_KIND_LIST%>">
                        <logic:iterate id="mk_iter" name="<%=Constants.MATERIAL_KIND_LIST%>">
                            <option class="lbl9" value="${mk_iter.type}">${mk_iter.typeName}</option>
                        </logic:iterate>
                    </logic:present>
                </select>
            </td>
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
        </tr>
        <tr>
            <td><img onclick="return printMCMaterialBalanceReport();" src="images/but_print.gif"/></td>
        </tr>
    </table>
</form>