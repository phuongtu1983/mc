<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.listposition.title"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchPosition.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchPosition();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delPositions();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addPosition();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.position.l_name" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchPosition();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvPositionForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='positionsForm' id='positionsForm'><div id='positionList'></div></form>