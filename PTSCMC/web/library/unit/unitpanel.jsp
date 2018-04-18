<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listunit.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchUnit.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchUnit();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_MATERIAL_UNIT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delUnits();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_MATERIAL_UNIT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addUnit();"/>
                <%}%>
            </td>
            <td><div>
                <html:select property="searchid">
                    <html:option key="message.all" value='0'/>
                    <html:option key="message.unit.unitEn" value='1'/>
                    <html:option key="message.unit.unitVn" value='2'/>
                </html:select>
                <html:text property="searchvalue" size="60"/>
                <html:submit onclick="return searchUnit();"><bean:message key="message.search"/></html:submit>
                <html:submit onclick="return searchAdvUnitForm();"><bean:message key="message.detailSearch"/></html:submit>
        </div></td>
        </tr>
    </table>
</html:form>
<form name='unitsForm' id='unitsForm'><div id='unitList'></div></form>