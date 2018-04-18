<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.process.store.level2.UsedMaterialStore2FormBean"%>
<div id="umsFormTitle"><h3><bean:message key="message.umsdetail.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="umsForm">
    <table width="100%">
        <tr>
            <td width="80px"><bean:message key="message.storelevel2.name"/></td>
            <td><html:text size="50" property="organizationName" disabled="true" name="<%=Constants.USED_MATERIAL_STORE%>"/></td>
            <td width="80px"><bean:message key="message.materialused.date"/></td>
            <td><html:text property="updateDate" size="10" styleId="updateDateUms" onclick="javascript: showCalendar(this)" name="<%=Constants.USED_MATERIAL_STORE%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.materialused.number"/></td>
            <td><html:text size="50" property="usmNumber" name="<%=Constants.USED_MATERIAL_STORE%>"/></td>
            <td><bean:message key="message.materialused.location"/></td>
            <td><html:text size="50" property="location" name="<%=Constants.USED_MATERIAL_STORE%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.miv.project"/></td>
            <td colspan="3">
                <html:select style="width: 170px;" property="proId"  name="<%=Constants.USED_MATERIAL_STORE%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
        </tr>
        <tr>                            
            <td class="lbl10"><bean:message key="message.employee.unit"/></td>
            <td>
                <html:select style="width: 170px;" property="orgId" name="<%=Constants.USED_MATERIAL_STORE%>" onchange="return umsOrganizationChanged(this,null,'musCreatedEmp')" >
                    <html:options collection="<%=Constants.ORG_LIST%>" property="orgId" labelProperty="name"/>
                </html:select></td>
            <td class="lbl10"><bean:message key="message.umsdetail.employee"/></td>
            <td>
                <html:select style="width: 170px;" property="createdEmp" name="<%=Constants.USED_MATERIAL_STORE%>" styleId="musCreatedEmp">
                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.materialused.materials"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delUmsMaterial();"/>
                        <html:image src="images/ico_them.gif" onclick="return searchUsedMaterial();"/>
                    </div>
                    <p><div id="listUmsMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/store/level2/details.jsp" %></div></p>
                </fieldset>
            </td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.USED_MATERIAL_STORE%>" value="0" property="umsId">
        <img onclick="return printUms();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <html:image onclick="return saveUms();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <img src="images/but_back.gif" onclick="return loadMaterialStoreUsedList(<bean:write name="<%=Constants.USED_MATERIAL_STORE%>" property="sto2Id"/>);"/>
    <html:hidden property="umsId" name="<%=Constants.USED_MATERIAL_STORE%>"/>
    <html:hidden property="sto2Id" name="<%=Constants.USED_MATERIAL_STORE%>"/>
</form>
<div id="musMaterialHideDiv" style="display:none"></div>