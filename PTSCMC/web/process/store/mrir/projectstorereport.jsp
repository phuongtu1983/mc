<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="message.menu414.u_text"/>/<bean:message key="message.menu4413.text.s"/></h3>
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
            <td><bean:message key="message.status"/></td>
            <td colspan="3">
                <select name="status">
                    <logic:present name="<%=Constants.OSD_STATUS_LIST%>">
                        <logic:iterate id="mk_iter" name="<%=Constants.OSD_STATUS_LIST%>">
                            <option class="lbl9" value="${mk_iter.value}">${mk_iter.label}</option>
                        </logic:iterate>
                    </logic:present>
                </select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.osd.createddate"/> <bean:message key="message.from"/></td>
            <td><input type="text" name="osdFromDate" size="10" id="mcMsvFromDate" onclick="javascript: showCalendar(this)"/></td>
            <td><bean:message key="message.to"/></td>
            <td><input type="text" name="osdEndDate" size="10" id="mcMvsEndDate" onclick="javascript: showCalendar(this)"/></td>
        </tr>
        
        <tr>
            <td><img onclick="return printMCProjectStoreMrirReport();" src="images/but_print.gif"/></td>
        </tr>
    </table>
</form>