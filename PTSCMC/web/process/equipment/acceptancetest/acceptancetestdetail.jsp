<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.acceptancetest.AcceptanceTestFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="acceptancetestFormTitle"><h3><bean:message key="message.detailacceptancetest.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="requirerepairForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.acceptancetest.atNumber"/></td>
            <td colspan="0"><html:text size="20" property="atNumber" name="<%=Constants.ACCEPTANCETEST%>"/></td>
            <td class="lbl10"><bean:message key="message.acceptancetest.testDate"/></td>
            <td colspan="0"><html:text property="testDate" size="10" styleId="testDate" onclick="javascript: showCalendar('testDate')" name="<%=Constants.ACCEPTANCETEST%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.acceptancetest.createdTime"/></td>
            <td colspan="0"><html:text size="20" property="createdTime" name="<%=Constants.ACCEPTANCETEST%>"/></td>
            <td class="lbl10"><bean:message key="message.surveyreport.srNumber"/></td>
            <td colspan="0"><html:select styleClass="lbl9"  property="srId" name="<%=Constants.ACCEPTANCETEST%>">
                    <html:options styleClass="lbl9" collection="<%=Constants.SURVEYREPORT_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.acceptancetest.createdLocation"/></td>
            <td colspan="6"><html:textarea rows="3" cols="80" property="createdLocation" name="<%=Constants.ACCEPTANCETEST%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.acceptancetest.managerEmp"/></td>
            <td height="22" colspan="0">
                <html:select  property="managerEmp"  name="<%=Constants.ACCEPTANCETEST%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>

                <td height="22"><bean:message key="message.acceptancetest.managerQAEmp"/></td>
            <td height="22" colspan="0">
                <html:select  property="managerQAEmp"  name="<%=Constants.ACCEPTANCETEST%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>

            <tr>
                <td height="22"><bean:message key="message.acceptancetest.managerEquipmentEmp"/></td>
            <td height="22" colspan="0">
                <html:select  property="managerEquipmentEmp"  name="<%=Constants.ACCEPTANCETEST%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            <tr>
                <td height="22"><bean:message key="message.acceptancetest.resultAfterRepair"/></td>
            <td colspan="6"><html:textarea rows="3" cols="80" property="resultAfterRepair" name="<%=Constants.ACCEPTANCETEST%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.acceptancetest.comment"/></td>
            <td height="22" colspan="0">
                <html:select  property="result"  name="<%=Constants.ACCEPTANCETEST%>">
                    <html:options collection="<%=Constants.RESULT_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>
            <tr>
                <td height="22"></td>
                <td colspan="0"><html:textarea rows="3" cols="80" property="comment" name="<%=Constants.ACCEPTANCETEST%>"/></td>
        </tr>

        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.acceptancetest.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delAcceptanceTestMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipmentOfSrId('setSelectedRequireRepairEquipment');"/>
                    </div>
                    <p><div id="listAcceptanceTestMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.ACCEPTANCETEST%>" value="0" property="atId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%
                AcceptanceTestFormBean form = (AcceptanceTestFormBean) request.getAttribute(Constants.ACCEPTANCETEST);
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_SURVEYREPORT, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveAcceptanceTest();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadAcceptanceTestList();"/>
    <html:hidden property="atId" name="<%=Constants.ACCEPTANCETEST%>"/>
</form>
<div id="equipmentHideDiv" style="display:none"></div>