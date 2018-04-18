<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.requirerepair.RequireRepairFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="requirerepairFormTitle"><h3><bean:message key="message.detailrequirerepair.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="requirerepairForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.requirerepair.rrNumber"/></td>
            <td colspan="0"><html:text size="20" property="rrNumber" name="<%=Constants.REQUIREREPAIR%>"/></td>
            <td class="lbl10"><bean:message key="message.requirerepair.requireOrg"/></td>
            <td colspan="0"><html:text property="requireOrg" readonly="true" size="20" name="<%=Constants.REQUIREREPAIR%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.requirerepair.requireDate"/></td>
            <td colspan="0"><html:text property="requireDate" size="10" styleId="requireDate" onclick="javascript: showCalendar('requireDate')" name="<%=Constants.REQUIREREPAIR%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.requirerepair.comment"/></td>
            <td colspan="6"><html:textarea rows="3" cols="80" property="comment" name="<%=Constants.REQUIREREPAIR%>"/></td>
        </tr>
        
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.requirerepair.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRequireRepairMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipmentRequireRepair('setSelectedRequireRepairEquipment');"/>
                    </div>
                    <p><div id="listRequireRepairMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.REQUIREREPAIR%>" value="0" property="rrId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%
            RequireRepairFormBean form = (RequireRepairFormBean) request.getAttribute(Constants.REQUIREREPAIR);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveRequireRepair();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRequireRepairList();"/>
    <html:hidden property="rrId" name="<%=Constants.REQUIREREPAIR%>"/>
</form>
<div id="equipmentHideDiv" style="display:none"></div>