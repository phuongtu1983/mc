<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.requireverified.RequireVerifiedFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="requireVerifiedFormTitle"><h3><bean:message key="message.requireverified.title"/></h3></div>
<div id="rvErrorMessageDiv"></div>
<form name="requireVerifiedForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.requireverified.rvNumber"/></td>
            <td colspan="0"><html:text size="20" property="rvNumber" name="<%=Constants.REQUIREVERIFIED%>"/></td>
            <td class="lbl10"><bean:message key="message.requireverified.requireOrg"/></td>
            <td colspan="0"><html:text property="orgName" readonly="true" size="20" name="<%=Constants.REQUIREVERIFIED%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.requireverified.requireDate"/></td>
            <td colspan="3"><html:text property="createdDate" size="10" styleId="createdDate" onclick="javascript: showCalendar('createdDate')" name="<%=Constants.REQUIREVERIFIED%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.requireverified.reason"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="reason" name="<%=Constants.REQUIREVERIFIED%>"/></td>
        </tr>
        
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.requireverified.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRequireVerifiedMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipmentOfOrg('setSelectedRequireVerifiedMaterial');"/>
                    </div>
                    <p><div id="listRequireVerifiedMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>           
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.REQUIREVERIFIED%>" value="0" property="rvId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>

    </table>
    <%
            RequireVerifiedFormBean form = (RequireVerifiedFormBean) request.getAttribute(Constants.REQUIREVERIFIED);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST, form.getCreatedEmpId())) {
    %>
    <html:image onclick="return saveRequireVerified();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRequireVerifiedList();"/>
    <html:hidden property="rvId" name="<%=Constants.REQUIREVERIFIED%>"/>
</form>
<div id="requireVerifiedMaterialHideDiv" style="display:none"></div>