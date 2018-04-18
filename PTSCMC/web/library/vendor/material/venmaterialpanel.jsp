<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchVenMaterial.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchVenMaterial();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVenMaterials();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
                <html:image src="images/ico_them.gif"  onclick="return venMaterialForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.vendor.material.nameVn" value='1'/>
                        <html:option key="message.vendor.material.nameEn" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchVenMaterial();"><bean:message key="message.search"/></html:submit>
                   <html:submit onclick="return false;"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='venMaterialsForm' id='venMaterialsForm'><div id='venMaterialList'></div></form>