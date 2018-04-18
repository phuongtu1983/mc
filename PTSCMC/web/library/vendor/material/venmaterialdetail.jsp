<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="venMaterialFormError"></div>
<form name="venMaterialForm">
    <table style="width:100%">
        <tr>
            <td><bean:message key="message.vendor.l_name"/></td>
            <td colspan="3"><html:text property="vendorName" size="80" disabled="true" name="<%=Constants.VENDOR_MATERIAL%>"/></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.material.nameVn"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80"  property="nameVn" name="<%=Constants.VENDOR_MATERIAL%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.material.nameEn"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80"  property="nameEn" name="<%=Constants.VENDOR_MATERIAL%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.material.manufacturer"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80"  property="manufacturer" name="<%=Constants.VENDOR_MATERIAL%>" /></td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.material.origin"/></td>
            <td>
                <html:select property="oriId" name="<%=Constants.VENDOR_MATERIAL%>">
                    <html:options collection="<%=Constants.ORIGIN_LIST%>" property="oriId" labelProperty="nameVn"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.vendor.material.group"/></td>
            <td>
                <logic:present name="<%=Constants.VENDOR_GROUP_MATERIAL_LIST%>">
                    <html:select property="groId" name="<%=Constants.VENDOR_MATERIAL%>">
                        <html:options collection="<%=Constants.VENDOR_GROUP_MATERIAL_LIST%>" property="groId" labelProperty="groupName"/>
                    </html:select>
                </logic:present>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.note"/></td>
            <td><html:textarea rows="3" cols="80"  property="note" name="<%=Constants.VENDOR_MATERIAL%>" /></td>
        </tr>
        <logic:greaterThan name="<%=Constants.VENDOR_MATERIAL%>" value="0" property="vmId">
            <tr>
                <td colspan="2"><div id='divVenMatAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
            </tr>
        </logic:greaterThan>
    </table>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
    <html:image onclick="return saveVenMaterial();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadVendorMaterialList();"/>
    <html:hidden property="vmId" name="<%=Constants.VENDOR_MATERIAL%>"/>
    <html:hidden property="venId" styleId="venIdMaterial" name="<%=Constants.VENDOR_MATERIAL%>"/>
</form>