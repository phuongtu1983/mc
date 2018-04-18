<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listmaterialnotcode.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMaterialNotCode.do">
    <table>            
        <tr>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.material.code" value='1'/>
                    <html:option key="message.material.nameVn" value='2'/>
                    <html:option key="message.material.nameEn" value='3'/>
                    <html:option key="message.request" value='5'/>
                </html:select>
                <html:text property="searchvalue" size="60" />
                <html:submit onclick="return searchMaterialNotCode();"><bean:message key="message.search"/></html:submit>
                <!--<html:submit onclick="return searchAdvMaterialFormNotCode();"><bean:message key="message.detailSearch"/></html:submit>-->
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_COMPANY)) {%>
                <html:submit title="Xoá vật tư không cần thiết!" onclick="return delMaterialsNotCode();"><bean:message key="message.material.delete"/></html:submit>
                <%}%>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='materialsForm' id='materialsForm'><div id='materialList'></div></form>