<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<form name="searchTenderPlanForm">
   <table width="100%">
        <tr>
            <td width="120px"><bean:message key="message.tenderplan.number"/></td>
            <td colspan="3"><html:text size="20" property="tenderNumber" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.createdDate"/> <bean:message key="message.from"/></td>
            <td colspan="2"><input type="textbox" size="20" name="fromDate" id="fromDateTenderPlan" onclick="javascript:showCalendar('fromDateTenderPlan')"/></td>
            <td><bean:message key="message.to"/></td>
            <td colspan="2"><input type="textbox" size="20" name="toDate" id="toDateTenderPlan" onclick="javascript:showCalendar('toDateTenderPlan')"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.packName"/></td>
            <td colspan="3"><html:text size="20" property="packname" name="<%=Constants.TENDERPLAN%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.tenderplan.form"/></td>
            <td colspan="3">
                <html:select property="form" name="<%=Constants.TENDERPLAN%>">
                    <html:options collection="<%=Constants.TENDERPLAN_FORM%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvTenderPlan();"/>
</form>