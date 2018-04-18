<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.requestadd.title"/>/<bean:message key="message.list.s"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchRequest.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchRequest();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_REQUEST)) {%> 
                <html:image src="images/ico_xoa.gif" onclick="return delRequests();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
                <html:image src="images/ico_them.gif"  onclick="return requestForm();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_PURCHASING_REQUEST_SHORTCUT)) {%>
                <html:submit onclick="return requestForm(null,null,true);">Shortcut</html:submit>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.request.number" value='1'/>
                        <html:option key="message.request.organization" value='2'/>
                        <html:option key="message.request.whichUse" value='3'/>
                        <html:option key="message.request.bomAgreeDate" value='4'/>
                        <html:option key="message.request.assignedEmp" value='5'/>
                        <html:option key="message.status" value='6'/>
                        <html:option key="message.material.nameVn" value='7'/>
                    </html:select>
                    <html:text property="searchvalue" size="40"/>
                    <html:submit onclick="return searchRequest();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvRequestForm();"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='requestsForm' id='requestsForm'><div id='requestList'></div></form>