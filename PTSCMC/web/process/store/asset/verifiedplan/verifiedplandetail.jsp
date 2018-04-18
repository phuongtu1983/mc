<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@page import="com.venus.mc.util.PermissionUtil"%>
<%--<h3><bean:message key="message.detailverifiedplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<form action="addAssetVerifiedPlan.do" name="addAssetVerifiedPlan">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.assetName"/></td>
                            <td height="22" colspan="3"><input readonly type="textbox" id="assetName" size="112" /></td>
                        </tr>
                        <tr>
                            <td><bean:message key="message.verifiedplan.timePlan"/></td>
                            <td><html:text property="timePlan" readonly="true" size="10" styleId="timePlan2" onclick="javascript: showCalendar('timePlan2')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
                            <td width="20%"><bean:message key="message.verifiedplan.timeTrue"/></td>
                            <td><html:text property="timeTrue" readonly="true" size="10" styleId="timeTrue2" onclick="javascript: showCalendar('timeTrue2')" name="<%=Constants.VERIFIEDPLAN%>" /></td>
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
                                            <html:select onchange="document.forms['addAssetVerifiedPlan'].performKind.item(0).checked=true;" property="orgId" name="<%=Constants.VERIFIEDPLAN%>" >
                                                <html:options collection="<%=Constants.VERIFIED_ORG_LIST%>" property="value" labelProperty="label"/>
                                            </html:select>
                                        </tr>
                                        <tr>
                                            <td class="lbl10"><html:radio property="performKind" name="<%=Constants.VERIFIEDPLAN%>" value="2"><bean:message key="message.verifiedplan.performPart"/></html:radio></td>
                                            <td height="22" colspan="3"><html:text property="performPart" onchange="document.forms['addAssetVerifiedPlan'].performKind.item(1).checked=true;" size="100" name="<%=Constants.VERIFIEDPLAN%>"/></td>
                                        </tr>
                                    </table>
                                    </p>
                                </fieldset>
                            </td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_ASSET)) {%>
                        <html:image onclick="return saveAssetVerifiedPlan();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadAssetVerifiedPlan();"/>
                </div></td></tr></table>
                <html:hidden property="vpId" name="<%=Constants.VERIFIEDPLAN%>" />
                <html:hidden property="equId" name="<%=Constants.VERIFIEDPLAN%>" />
                <html:hidden property="assId" name="<%=Constants.VERIFIEDPLAN%>" />
</form>