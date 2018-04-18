<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listematerial.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEmaterial.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchEmaterial();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_OUT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delEmaterials();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_MATERIAL_OUT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addEmaterial();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.ematerial.code" value='1'/>
                    <html:option key="message.ematerial.nameVn" value='2'/>
                    <html:option key="message.ematerial.nameEn" value='3'/>
                </html:select>
                <html:text property="searchvalue" size="60"/>
                <html:submit onclick="return searchEmaterial();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvEmaterialForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='ematerialsForm' id='ematerialsForm'><div id='ematerialList'></div></form>