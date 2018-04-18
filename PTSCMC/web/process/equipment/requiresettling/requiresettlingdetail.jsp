<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.process.equipment.requiresettling.RequireSettlingFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="requiresettlingFormTitle"><h3><bean:message key="message.detailrequiresettling.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="requiresettlingForm">
    <table width="100%" style="width:100%">
        <tr>
            <td height="22"><bean:message key="message.requiresettling.proId"/></td>
            <td height="22" colspan="0">
                <html:select  property="proId"  name="<%=Constants.REQUIRESETTLING%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>

                <td class="lbl10"><bean:message key="message.requiresettling.rsNumber"/></td>
            <td colspan="0"><html:text size="20" property="rsNumber" name="<%=Constants.REQUIRESETTLING%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.requiresettling.requireDate"/></td>
            <td colspan="0"><html:text property="requireDate" size="10" styleId="requireDate" onclick="javascript: showCalendar('requireDate')" name="<%=Constants.REQUIRESETTLING%>"/></td>
            <td height="22"><bean:message key="message.requiresettling.srId"/></td>
            <td height="22" colspan="0">
                <html:select  property="srId"  name="<%=Constants.REQUIRESETTLING%>">
                    <html:options collection="<%=Constants.SURVEYREPORT_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>
            <tr>
                <td height="22"><bean:message key="message.requiresettling.performOrg"/></td>
            <td height="22" colspan="0">
                <html:select  property="performOrg"  name="<%=Constants.REQUIRESETTLING%>">
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
                <td height="22"><bean:message key="message.requiresettling.requireOrg"/></td>
            <td height="22" colspan="0">
                <html:select  property="requireOrg"  name="<%=Constants.REQUIRESETTLING%>">
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>
            <tr>
                <td colspan="6">
                    <fieldset>
                        <legend class="lbl10b"><bean:message key="message.requiresettling.status"/></legend>
                    <p>
                    <table width="100%">
                        <tr>
                            <td class="lbl10"><html:radio property="status" name="<%=Constants.REQUIRESETTLING%>" value="1"><bean:message key="message.requiresettling.status1"/></html:radio></td>
                            <td class="lbl10"><html:radio property="status" name="<%=Constants.REQUIRESETTLING%>" value="2"><bean:message key="message.requiresettling.status2"/></html:radio></td>
                            <td class="lbl10"><html:radio property="status" name="<%=Constants.REQUIRESETTLING%>" value="3"><bean:message key="message.requiresettling.status3"/></html:radio></td>
                        </tr>
                    </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend><bean:message key="message.requiresettling.details"/></legend>
                    <table>
                        <tr>
                            <td>
                                <html:image src="images/ico_xoa.gif" onclick="return delRequireSettlingDetail();"/>
                                <html:image src="images/ico_them.gif" onclick="return addRequireSettlingDetail();"/>                                
                            </td>
                        </tr>
                    </table>
                    <div id="requireSettlingDetailList" style="width:700px;height:300px;overflow:auto"></div>
                </fieldset>
            </td>
        </tr>
        <!-- file attachment -->
        <logic:greaterThan name="<%=Constants.REQUIRESETTLING%>" value="0" property="rsId">
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%
                RequireSettlingFormBean form = (RequireSettlingFormBean) request.getAttribute(Constants.REQUIRESETTLING);
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveRequireSettling();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadRequireSettlingList();"/>
    <html:hidden property="rsId" name="<%=Constants.REQUIRESETTLING%>"/>
</form>
<div id="equipmentHideDiv" style="display:none"></div>