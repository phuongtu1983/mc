<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.criterionlist.title"/></h3>
<div id="errorMessageDiv"></div>
<form>
    <table><tr><td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delCriterions();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_VENDOR_EVAL)) {%>
                <html:image src="images/ico_them.gif"  onclick="return criterionForm();"/>
                <%}%>
    </td></tr></table>
</form>
<form name='criterionsForm' id='criterionsForm'><div id='criterionList'></div></form>