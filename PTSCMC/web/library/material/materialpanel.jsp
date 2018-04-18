<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listmaterial.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMaterial.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchMaterial();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delMaterials();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addMaterial();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.material.code" value='1'/>
                    <html:option key="message.material.nameVn" value='2'/>
                    <html:option key="message.material.nameEn" value='3'/>
                    <html:option key="message.groupmaterial" value='6'/>
                </html:select>
                <html:text property="searchvalue" size="40" />
                <html:submit onclick="return searchMaterial();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvMaterialForm();"><bean:message key="message.detailSearch"/></html:submit>
                <img onclick="return exportMaterial();" src="images/but_print.gif"/>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='materialsForm' id='materialsForm'><div id='materialList'></div></form>