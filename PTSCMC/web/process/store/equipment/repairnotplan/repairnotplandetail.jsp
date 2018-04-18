<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.RepairNotPlanBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%--<h3><bean:message key="message.detailrepairnotplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<form name="repairNotPlanForm">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.equipmentName"/></td>
                            <td height="22" colspan="3"><input readonly style="background-color:#F4F4F4" type="textbox" id="equipmentName" size="112" /></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.equipment.usedCode"/></td>
                            <td><input readonly style="background-color:#F4F4F4" type="textbox" id="usedCode" size="40" /></td>
                            <td width="20%"><bean:message key="message.equipment.manageCode"/></td>
                            <td height="22" colspan="1"><input readonly style="background-color:#F4F4F4" type="textbox" id="manageCode" size="40" /></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.repairnotplan.orgUsed"/></td>
                            <td height="22" >
                                <html:select  property="orgUsed" name="<%=Constants.REPAIRNOTPLAN%>">
                                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                                <td height="22"><bean:message key="message.repairnotplan.cost"/></td>
                            <td height="22" colspan="1"><html:text property="cost" size="40" name="<%=Constants.REPAIRNOTPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.repairnotplan.rdNumber"/></td>
                            <td height="22"><html:text property="rdNumber" style="background-color:#F4F4F4" readonly="true" size="40" name="<%=Constants.REPAIRNOTPLAN%>"/></td>
                            <td height="22"><bean:message key="message.repairnotplan.rrNumber"/></td>
                            <td height="22" colspan="1"><html:text property="rrNumber" style="background-color:#F4F4F4" readonly="true" size="40" name="<%=Constants.REPAIRNOTPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.repairnotplan.srNumber"/></td>
                            <td height="22"><html:text property="srNumber" style="background-color:#F4F4F4" readonly="true" size="40" name="<%=Constants.REPAIRNOTPLAN%>"/></td>
                            <td height="22"><bean:message key="message.repairnotplan.atNumber"/></td>
                            <td height="22" colspan="1"><html:text property="atNumber" style="background-color:#F4F4F4" readonly="true" size="40" name="<%=Constants.REPAIRNOTPLAN%>"/></td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
                                    RepairNotPlanBean form = (RepairNotPlanBean) request.getAttribute(Constants.REPAIRNOTPLAN);
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                        %>
                        <html:image onclick="return saveRepairNotPlan();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRepairNotPlan();"/>
                </div></td></tr></table>
                <html:hidden property="rnpId" name="<%=Constants.REPAIRNOTPLAN%>" />
                <html:hidden property="equId" name="<%=Constants.REPAIRNOTPLAN%>" />
                <html:hidden property="rrId" name="<%=Constants.REPAIRNOTPLAN%>" />
                <html:hidden property="rdId" name="<%=Constants.REPAIRNOTPLAN%>" />
                <html:hidden property="srId" name="<%=Constants.REPAIRNOTPLAN%>" />
                <html:hidden property="atId" name="<%=Constants.REPAIRNOTPLAN%>" />
</form>