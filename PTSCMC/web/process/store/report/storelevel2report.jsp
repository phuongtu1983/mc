<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<h3><bean:message key="message.menu441.u_text"/>/<bean:message key="message.menu4413.text.s"/></h3>
<form name="mcReportForm">
    <table>
        <tr>
            <td><bean:message key="message.project"/></td>
            <td colspan="3">
                <select name="mcReportProject" onchange="return store2ProjectChanged(this)" style="width: 170px;">
                    <logic:iterate id="project_iter" name="<%=Constants.PRO_LIST%>">
                        <option class="lbl9" value="${project_iter.value}">${project_iter.label}</option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.store"/></td>
            <td colspan="3" id="projectStore">
                <select name="mcReportOrganization" id="mcReportOrganization" style="width: 170px;">
                    <logic:iterate id="org_iter" name="<%=Constants.STORE_LIST%>">
                        <option class="lbl9" value="${org_iter.sto2Id}">${org_iter.name}</option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.material.nameVn"/></td>
            <td colspan="3"><input type="text" name="nameVn" size="100" id="nameVn" /></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.material.code"/></td>
            <td><input type="text" name="code" size="30" id="code" /></td>
        </tr>
        <tr><td><img onclick="return printStore2BalanceReport();" src="images/but_print.gif"/></td></tr>
    </table>
</form>