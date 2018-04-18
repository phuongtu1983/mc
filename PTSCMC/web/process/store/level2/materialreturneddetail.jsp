<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.process.store.level2.ReturnedMaterialStore2FormBean"%>
<div id="umsFormTitle"><h3><bean:message key="message.rmsdetail.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="rmsForm">
    <table width="100%">
        <tr>
            <td width="80px"><bean:message key="message.storelevel2.name"/></td>
            <td><html:text size="50" property="organizationName" disabled="true" name="<%=Constants.RETURNED_MATERIAL_STORE%>"/></td>
            <td width="80px"><bean:message key="message.materialreturned.date"/></td>
            <td><html:text property="updateDate" size="10" styleId="updateDateRms" onclick="javascript: showCalendar(this)" name="<%=Constants.RETURNED_MATERIAL_STORE%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.materialreturned.number"/></td>
            <td colspan="3"><html:text size="50" property="rsmNumber" name="<%=Constants.RETURNED_MATERIAL_STORE%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.miv.project"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="proId"  name="<%=Constants.RETURNED_MATERIAL_STORE%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>                            
            <td class="lbl10"><bean:message key="message.employee.unit"/></td>
            <td>
                <html:select style="width: 170px;" property="orgId" name="<%=Constants.RETURNED_MATERIAL_STORE%>" onchange="return rmsOrganizationChanged(this,null,'rmsCreatedEmp')" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select></td>
            <td class="lbl10"><bean:message key="message.rmsdetail.employee"/></td>
            <td>
                <html:select style="width: 170px;" property="createdEmp" name="<%=Constants.RETURNED_MATERIAL_STORE%>" styleId="rmsCreatedEmp">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.materialreturned.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delRmsMaterial();"/>
                        <html:image src="images/ico_them.gif" onclick="return searchReturnedMaterial();"/>
                    </div>
                    <p><div id="listRmsMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/level2/returneddetails.jsp" %></div></p>
                </fieldset>
            </td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.RETURNED_MATERIAL_STORE%>" value="0" property="rmsId">
        <img onclick="return printRms();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
        ReturnedMaterialStore2FormBean form = (ReturnedMaterialStore2FormBean) request.getAttribute(Constants.RETURNED_MATERIAL_STORE);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_STOCK_STORE2, form.getCreatedEmp())) {
    %>
    <html:image onclick="return saveRms();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <img src="images/but_back.gif" onclick="return loadMaterialStoreReturnedList(<bean:write name="<%=Constants.RETURNED_MATERIAL_STORE%>" property="sto2Id"/>);"/>
    <html:hidden property="rmsId" name="<%=Constants.RETURNED_MATERIAL_STORE%>"/>
    <html:hidden property="sto2Id" name="<%=Constants.RETURNED_MATERIAL_STORE%>"/>
</form>
<div id="rusMaterialHideDiv" style="display:none"></div>