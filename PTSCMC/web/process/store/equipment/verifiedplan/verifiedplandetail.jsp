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
<%@ page import="com.venus.mc.bean.VerifiedPlanBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%--<h3><bean:message key="message.detailverifiedplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<form name="verifiedPlanForm">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.equipmentName"/></td>
                            <td height="22" colspan="3"><input readonly type="textbox" id="equipmentName" size="112" /></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.verifiedplan.timePlan"/></td>
                            <td><html:text property="timePlan" readonly="true" size="10" styleId="timePlan2" onclick="javascript: showCalendar('timePlan2')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
                            <td width="20%"><bean:message key="message.verifiedplan.timeEffect"/></td>
                            <td height="22" colspan="1"><html:text property="timeEffect" size="20" onchange="javascript:document.forms['verifiedPlanForm'].timeNext.value = addMonth(document.forms['verifiedPlanForm'].timePlan.value , document.forms['verifiedPlanForm'].timeEffect.value)" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.verifiedplan.timeNext"/></td>
                            <td colspan="3"><html:text property="timeNext" readonly="true" size="10" styleId="timeNext2" onclick="javascript: showCalendar('timeNext2')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.verifiedplan.content"/></td>
                            <td height="22" colspan="3"><html:text property="content" size="112" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.verifiedplan.cost"/></td>
                            <td height="22"><html:text property="cost" size="40" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                            <td height="22"><bean:message key="message.verifiedplan.status"/></td>
                            <td height="22" colspan="1">
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
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
            VerifiedPlanBean form = (VerifiedPlanBean) request.getAttribute(Constants.VERIFIEDPLAN);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                        %>
                        <html:image onclick="return saveVerifiedPlan();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hidePopupForm();"/>
                </div></td></tr></table>
                <html:hidden property="vpId" name="<%=Constants.VERIFIEDPLAN%>" />
                <html:hidden property="equId" name="<%=Constants.VERIFIEDPLAN%>" />
</form>