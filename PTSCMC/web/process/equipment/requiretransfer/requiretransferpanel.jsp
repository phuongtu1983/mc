<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.requiretransfer.listtitle"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchRequireTransfer.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchRequireTransfer();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delRequireTransfers();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
                <html:image src="images/ico_them.gif"  onclick="return requireTransferForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.request.number" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchRequireTransfer();"><bean:message key="message.search"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='requireTransferListForm' id='requireTransferListForm'><div id='requireTransferList'></div></form>