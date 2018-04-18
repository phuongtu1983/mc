<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.intermemo.IntermemoFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="intermemoFormTitle"><h3><bean:message key="message.intermemoadd.title"/>/
        <logic:greaterThan name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <bean:message key="message.detail.s"/>
        </logic:greaterThan>
        <logic:equal name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <bean:message key="message.add.s"/>
        </logic:equal>
    </h3></div>
<div id="errorMessageDiv"></div>
<form name="intermemoForm">
    <table width="100%" style="width:100%">
        <tr>
            <td class="lbl10"><bean:message key="message.request.number"/></td>
            <td><html:text size="20" property="requestNumber" name="<%=Constants.REQUEST%>"/></td>
            <td class="lbl10"><bean:message key="message.request.createdDate"/></td>
            <td><html:text property="createdDate" styleId="createdDateRequest" size="10" disabled="true" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.project"/></td>
            <td>
                <html:select property="proId" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.request.approveEmployee"/></td>
            <td>
                <html:select property="approveEmp" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.request.createdEmp"/></td>
            <td>
                <html:select property="createdEmp" name="<%=Constants.REQUEST%>" onchange="return employeeChanged(this);">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.request.organization"/></td>
            <td>
                <html:select property="createdOrg" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.request.assignedEmp"/></td>
            <td colspan="3">
                <html:select property="assignedEmp" name="<%=Constants.REQUEST%>" >
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.intermemo.title"/></td>
            <td colspan="3"><html:text size="60" property="subject" name="<%=Constants.REQUEST%>"/></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.request.statusSuggest"/></legend>
                    <p>
                        <table width="100%">
                            <tr>
                                <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="1"><bean:message key="message.request.statusSuggest1"/></html:radio></td>
                                <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="2"><bean:message key="message.request.statusSuggest2"/></html:radio></td>
                                <td class="lbl10"><html:radio property="statusSuggest" name="<%=Constants.REQUEST%>" value="3"><bean:message key="message.request.statusSuggest3"/></html:radio></td>
                            </tr>
                        </table>
                    </p>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.request.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delIntermemoMaterial();"/>
                        <img src="images/ico_them.gif"  onclick="return selectMaterial('setSelectedIntermemoMaterial','<bean:message key="message.intermemoadd.title"/>/<bean:message key="message.material.choose"/>');"/>
                    </div>
                    <p><div id="listIntermemoMaterialDataDiv" style="width:700px;height:300px;overflow:auto"></div></p>
                </fieldset>
            </td>
        </tr>
        <logic:greaterThan name="<%=Constants.REQUEST%>" value="0" property="reqId">
            <tr><td colspan="4">&nbsp;</td></tr>
            <tr>
                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>            
            </tr>
        </logic:greaterThan>
    </table>
    <logic:greaterThan name="<%=Constants.REQUEST%>" value="0" property="reqId">
        <img onclick="return printIntermemoForm();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
            IntermemoFormBean form = (IntermemoFormBean) request.getAttribute(Constants.REQUEST);
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_INTERMEMO, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveIntermemo();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadIntermemoList();"/>
    <html:hidden property="reqId" name="<%=Constants.REQUEST%>"/>
</form>
<div id="intermemoMaterialHideDiv" style="display:none"></div>