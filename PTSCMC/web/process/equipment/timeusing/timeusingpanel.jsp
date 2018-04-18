<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<h3><bean:message key="message.timeusing.listtitle"/></h3>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchTimeUsing.do">
    <table>
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchTimeUsing();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delTimeUsings();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
                <html:image src="images/ico_them.gif"  onclick="return timeUsingForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.request.number" value='1'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchTimeUsing();"><bean:message key="message.search"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='timeUsingListForm' id='timeUsingListForm'><div id='timeUsingList'></div></form>