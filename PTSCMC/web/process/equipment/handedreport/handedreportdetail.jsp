<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.handedreport.HandedReportFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="handedReportFormTitle"><h3><bean:message key="message.handedreport.title"/></h3></div>
<div id="hrErrorMessageDiv"></div>
<form name="handedReportForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.handedreport.number"/></td>
            <td><html:text size="20" property="hrNumber" name="<%=Constants.HANDEDREPORT%>"/></td>
            <td class="lbl10"><bean:message key="message.handedreport.createddate"/></td>
            <td><html:text property="createdDate" size="10" styleId="createdDate" onclick="javascript: showCalendar('createdDate')" name="<%=Constants.HANDEDREPORT%>"/></td>                        
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.handedreport.createdtime"/></td>
            <td><html:text size="20" property="createdTime" name="<%=Constants.HANDEDREPORT%>"/></td>
            <td class="lbl10"><bean:message key="message.handedreport.rtnumber"/></td>
            <td id="rtNumberDiv"><html:select styleClass="lbl9"  property="rtId" name="<%=Constants.HANDEDREPORT%>">            
                    <html:options styleClass="lbl9" collection="<%=Constants.REQUIRETRANSFER_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.handedreport.location"/></td>
            <td colspan="3"><html:text property="createdLocation" size="90" name="<%=Constants.HANDEDREPORT%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.handedreport.orgdelivery"/></td>
            <td><html:text size="30" readonly="true" property="orgName" name="<%=Constants.HANDEDREPORT%>"/></td>
            <td class="lbl10"><bean:message key="message.handedreport.orgreceive"/></td>
            <td><html:select styleClass="lbl9"  property="orgReceiveId" name="<%=Constants.HANDEDREPORT%>" onchange="hrOrgReceiveChanged(this, this.selectedIndex)">            
                <html:options styleClass="lbl9" collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/>
            </html:select>            
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.handedreport.empdelivery"/></td>
            <td><html:text size="30" readonly="true" property="createdEmpName" name="<%=Constants.HANDEDREPORT%>"/></td>
            <td class="lbl10"><bean:message key="message.handedreport.empreceive"/></td>
            <td id="empReceiveDiv"><html:select styleClass="lbl9"  property="empReceiveId" name="<%=Constants.HANDEDREPORT%>">            
                <html:options styleClass="lbl9" collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/>
            </html:select>            
        </tr>        
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.handedreport.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delHandedReportMaterial();"/>
                        <html:image src="images/ico_them.gif"  onclick="return selectEquipmentOfRtId('setSelectedHandedReportMaterial');"/>
                    </div>
                    <p><div id="listHandedReportMaterialDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>           
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.HANDEDREPORT%>" value="0" property="hrId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>
        
    </table>
    <%
            HandedReportFormBean form = (HandedReportFormBean) request.getAttribute(Constants.HANDEDREPORT);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST, form.getCreatedEmpId())) {
    %>
    <logic:present parameter="hrId">
        <html:image  onclick="return printHandedReport();" src="images/but_print.gif"/>
    </logic:present>
    <html:image onclick="return saveHandedReport();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadHandedReportList();"/>
    <html:hidden property="hrId" name="<%=Constants.HANDEDREPORT%>"/>
</form>
<div id="handedReportMaterialHideDiv" style="display:none"></div>