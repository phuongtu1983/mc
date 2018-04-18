<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.requiretransfer.RequireTransferFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="requireVerifiedFormTitle"><h3><bean:message key="message.requiretransfer.title"/></h3></div>
<div id="rtErrorMessageDiv"></div>
<form name="requireTransferForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.requiretransfer.rtNumber"/></td>
            <td colspan="0"><html:text size="20" property="rtNumber" name="<%=Constants.REQUIRETRANSFER%>"/></td>
            <td class="lbl10"><bean:message key="message.requiretransfer.requireOrg"/></td>
            <td colspan="0"><html:text property="orgName" readonly="true" size="20" name="<%=Constants.REQUIRETRANSFER%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.requiretransfer.requireDate"/></td>
            <td colspan="3"><html:text property="createdDate" size="10" styleId="createdDate" onclick="javascript: showCalendar('createdDate')" name="<%=Constants.REQUIRETRANSFER%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.requiretransfer.reason"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="reason" name="<%=Constants.REQUIRETRANSFER%>"/></td>
        </tr>
        
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.requiretransfer.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRequireTransferMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipmentRequireTransfer('setSelectedRequireTransferMaterial');"/>
                    </div>
                    <p><div id="listRequireTransferMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>           
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.REQUIRETRANSFER%>" value="0" property="rtId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>

    </table>
    <%
            RequireTransferFormBean form = (RequireTransferFormBean) request.getAttribute(Constants.REQUIRETRANSFER);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST, form.getCreatedEmpId())) {
    %>
    <html:image onclick="return saveRequireTransfer();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRequireTransferList();"/>
    <html:hidden property="rtId" name="<%=Constants.REQUIRETRANSFER%>"/>
     <html:hidden property="orgId" name="<%=Constants.REQUIRETRANSFER%>"/>
</form>
<div id="requireTransferMaterialHideDiv" style="display:none"></div>