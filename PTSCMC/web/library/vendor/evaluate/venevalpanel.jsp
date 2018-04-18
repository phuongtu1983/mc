<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<div id="errorMessageDiv"></div>
<html:form action="searchVenEval.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchVenEval();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delVenEvals();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
                <html:image src="images/ico_them.gif"  onclick="return venEvalForm();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.vendor.evaluate.lastResult" value='1'/>
                        <html:option key="message.vendor.evaluate.organization" value='2'/>
                        <html:option key="message.vendor.evaluate.employee" value='3'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchVenEval();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return false;"><bean:message key="message.detailSearch"/></html:submit>
            </div></td>
        </tr>
    </table>
</html:form>
<form name='venEvalsForm' id='venEvalsForm'><div id='venEvalList'></div></form>