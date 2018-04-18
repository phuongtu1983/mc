<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import ="com.venus.core.util.*"%>
<%@ page import="com.venus.mc.bean.OrganizationBean"%>
<%@ page import="com.venus.mc.bean.RepairPlanBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%--<h3><bean:message key="message.detailrepairplan.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<form name="repairPlansForm">
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
                            <td><bean:message key="message.repairplan.timeRepair"/></td>
                            <td><html:text property="timeRepair" size="20" name="<%=Constants.REPAIRPLAN%>"/>
                                <html:text property="timeUnit" size="20" name="<%=Constants.REPAIRPLAN%>"/>
                            </td>
                            <td width="20%"><bean:message key="message.repairplan.timeTrue"/></td>
                            <td><html:text property="timeTrue" readonly="true" size="10" styleId="timeTrue" onclick="javascript: showCalendar('timeTrue')" name="<%=Constants.REPAIRPLAN%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.repairplan.repairPart"/></td>
                            <td height="22" colspan="3"><html:textarea property="repairPart"  cols="90" rows="5" name="<%=Constants.REPAIRPLAN%>"/></td>
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
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="5" name="<%=Constants.REPAIRPLAN%>"/></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <fieldset>
                                    <legend style="color:blue;"><bean:message key="message.dn.materials"/></legend>
                                    <div>
                                        <html:image src="images/ico_xoa.gif" onclick="return delRepairMaterials();"/>
                                        <html:image src="images/ico_them.gif" onclick="return selectRepairMaterial('setSelectedRepairMaterial');"/>
                                    </div>
                                    <p><div id="listRepairMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/equipment/repairplan/details.jsp" %></div></p>
                                </fieldset>
                            </td>
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
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                    <%
            RepairPlanBean form = (RepairPlanBean) request.getAttribute(Constants.REPAIRPLAN);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
                    %>
                    <html:image onclick="return saveRepairPlan();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return hidePopupForm();"/>
    </div></td></tr></table>
    <html:hidden property="rpId" name="<%=Constants.REPAIRPLAN%>" />
    <html:hidden property="equId" name="<%=Constants.REPAIRPLAN%>" />
    <html:hidden property="assId" name="<%=Constants.REPAIRPLAN%>" />
</form>
<div id="repairMaterialHideDiv" style="display:none"></div>
<div id="popupDialog" dojoType="dijit.Dialog"></div>