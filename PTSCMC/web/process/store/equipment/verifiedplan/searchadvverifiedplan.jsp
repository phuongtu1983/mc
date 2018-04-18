<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchVerifiedPlanForm' name='searchVerifiedPlanForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.verifiedplan.timePlan"/></td>
                            <td><html:text property="timePlan" readonly="true" size="10" styleId="timePlan" onclick="javascript: showCalendar('timePlan')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
                            <td width="20%"><bean:message key="message.verifiedplan.timeTrue"/></td>
                            <td><html:text property="timeTrue" readonly="true" size="10" styleId="timeTrue" onclick="javascript: showCalendar('timeTrue')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.verifiedplan.content"/></td>
                            <td height="22" colspan="3"><html:text property="content" size="112" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.verifiedplan.cost"/></td>
                            <td height="22"><html:text property="cost" size="40" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                            <td height="22"><bean:message key="message.verifiedplan.status"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="status" name="<%=Constants.VERIFIEDPLAN%>">
                                    <html:options collection="<%=Constants.VERIFIED_STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.verifiedplan.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <fieldset>
                                    <legend class="lbl10b" style="color:blue;"><bean:message key="message.verifiedplan.performKind"/></legend>
                                    <p>
                                    <table width="100%" border="0">
                                        <tr>
                                            <td class="lbl10" width="148px"><html:radio property="performKind" name="<%=Constants.VERIFIEDPLAN%>" value="1"><bean:message key="message.verifiedplan.orgId"/></html:radio></td>
                                            <html:select onchange="document.forms['addVerifiedPlan'].performKind.item(0).checked=true;" property="orgId" name="<%=Constants.VERIFIEDPLAN%>" >
                                                <html:options collection="<%=Constants.VERIFIED_ORG_LIST%>" property="value" labelProperty="label"/>
                                            </html:select>
                                        </tr>
                                        <tr>
                                            <td class="lbl10"><html:radio property="performKind" name="<%=Constants.VERIFIEDPLAN%>" value="2"><bean:message key="message.verifiedplan.performPart"/></html:radio></td>
                                            <td height="22" colspan="3"><html:text property="performPart" onchange="document.forms['addVerifiedPlan'].performKind.item(1).checked=true;" size="100" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                                        </tr>
                                    </table>
                                    </p>
                                </fieldset>
                            </td>
                        </tr>
                    </table>
                </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvVerifiedPlan();"/>
                <html:hidden property="vpId" name="<%=Constants.VERIFIEDPLAN%>" />
                <html:hidden property="equId" name="<%=Constants.VERIFIEDPLAN%>" />
                <html:hidden property="assId" name="<%=Constants.VERIFIEDPLAN%>" />
</form>