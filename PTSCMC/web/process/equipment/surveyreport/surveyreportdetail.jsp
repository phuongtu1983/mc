<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.surveyreport.SurveyReportFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="surveyreportFormTitle"><h3><bean:message key="message.detailsurveyreport.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="surveyreportForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.surveyreport.srNumber"/></td>
            <td colspan="0"><html:text size="20" property="srNumber" name="<%=Constants.SURVEYREPORT%>"/></td>
            <td class="lbl10"><bean:message key="message.surveyreport.surveyDate"/></td>
            <td colspan="0"><html:text property="surveyDate" size="10" styleId="surveyDate" onclick="javascript: showCalendar('surveyDate')" name="<%=Constants.SURVEYREPORT%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.surveyreport.createdTime"/></td>
            <td colspan="0"><html:text size="20" property="createdTime" name="<%=Constants.SURVEYREPORT%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.surveyreport.createdLocation"/></td>
            <td colspan="6"><html:textarea rows="3" cols="80" property="createdLocation" name="<%=Constants.SURVEYREPORT%>"/></td>
        </tr>
        <tr>
            <td height="22"><bean:message key="message.surveyreport.managerEmp"/></td>
            <td height="22" colspan="0">
                <html:select  property="managerEmp"  name="<%=Constants.SURVEYREPORT%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>

                <td height="22"><bean:message key="message.surveyreport.managerEquipmentEmp"/></td>
            <td height="22" colspan="0">
                <html:select  property="managerEquipmentEmp"  name="<%=Constants.SURVEYREPORT%>">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>

            <tr>
                <td height="22"><bean:message key="message.surveyreport.reasonToRepair"/></td>
            <td colspan="0"><html:textarea rows="3" cols="80" property="reasonToRepair" name="<%=Constants.SURVEYREPORT%>"/></td>
        </tr>        
        <tr>
            <td height="22"><bean:message key="message.surveyreport.request"/></td>
            <td height="22" colspan="0">
                <html:select  property="request"  name="<%=Constants.SURVEYREPORT%>">
                    <html:options collection="<%=Constants.REQUEST_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>
            <tr>
                <td height="22"></td>
                <td colspan="0"><html:textarea rows="3" cols="80" property="comment" name="<%=Constants.SURVEYREPORT%>"/></td>
        </tr>

        <tr>
            <td colspan="7">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.surveyreport.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delSurveyReportMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectMaterialSr('setSelectedSurveyReportMaterial');"/>
                    </div>
                    <p><div id="listSurveyReportMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>

        <tr>
            <td colspan="7">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.surveyreport.equipments"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delSurveyReportEquipment();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipment('setSelectedSurveyReportEquipment');"/>
                    </div>
                    <p><div id="listSurveyReportEquipmentDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.SURVEYREPORT%>" value="0" property="srId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%
                SurveyReportFormBean form = (SurveyReportFormBean) request.getAttribute(Constants.SURVEYREPORT);
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveSurveyReport();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadSurveyReportList();"/>
    <html:hidden property="srId" name="<%=Constants.SURVEYREPORT%>"/>
</form>
<div id="materialHideDiv" style="display:none"></div>
<div id="equipmentHideDiv" style="display:none"></div>