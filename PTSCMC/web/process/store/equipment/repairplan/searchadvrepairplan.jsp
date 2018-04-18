<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchRepairPlanForm' name='searchRepairPlanForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.repairplan.timeRepair"/></td>
                            <td><html:text property="timeRepair" size="20" name="<%=Constants.REPAIRPLAN%>"/>
                                <html:text property="timeUnit" size="20" name="<%=Constants.REPAIRPLAN%>"/>
                            </td>
                            <td width="20%"><bean:message key="message.repairplan.timeTrue"/></td>
                            <td><html:text property="timeTrue" readonly="true" size="10" styleId="timeTrue" onclick="javascript: showCalendar('timeTrue')" name="<%=Constants.REPAIRPLAN%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.repairplan.repairPart"/></td>
                            <td height="22" colspan="3"><html:text property="repairPart" size="112" name="<%=Constants.REPAIRPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.repairplan.cost"/></td>
                            <td height="22"><html:text property="cost" size="40" name="<%=Constants.REPAIRPLAN%>"/></td>
                            <td height="22"><bean:message key="message.repairplan.repairKind"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="repairKind" name="<%=Constants.REPAIRPLAN%>">
                                    <html:options collection="<%=Constants.REPAIR_KIND_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.repairplan.status"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="status" name="<%=Constants.REPAIRPLAN%>">
                                    <html:options collection="<%=Constants.REPAIR_STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.repairplan.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.REPAIRPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <fieldset>
                                    <legend class="lbl10b" style="color:blue;"><bean:message key="message.repairplan.performKind"/></legend>
                                    <p>
                                    <table width="100%" border="0">
                                        <tr>
                                            <td class="lbl10" width="148px"><html:radio property="performKind" name="<%=Constants.REPAIRPLAN%>" value="1"><bean:message key="message.repairplan.orgId"/></html:radio></td>
                                            <html:select onchange="document.forms['addRepairPlan'].performKind.item(0).checked=true;" property="orgId" name="<%=Constants.REPAIRPLAN%>" >
                                                <html:options collection="<%=Constants.REPAIR_ORG_LIST%>" property="value" labelProperty="label"/>
                                            </html:select>
                                        </tr>
                                        <tr>
                                            <td class="lbl10"><html:radio property="performKind" name="<%=Constants.REPAIRPLAN%>" value="2"><bean:message key="message.repairplan.performPart"/></html:radio></td>
                                            <td height="22" colspan="3"><html:text property="performPart" onchange="document.forms['addRepairPlan'].performKind.item(1).checked=true;" size="100" name="<%=Constants.REPAIRPLAN%>"/></td>
                                        </tr>
                                    </table>
                                    </p>
                                </fieldset>
                            </td>
                        </tr>
                    </table>
                </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvRepairPlan();"/>
                <html:hidden property="rpId" name="<%=Constants.REPAIRPLAN%>" />
                <html:hidden property="equId" name="<%=Constants.REPAIRPLAN%>" />
                <html:hidden property="assId" name="<%=Constants.REPAIRPLAN%>" />
</form>