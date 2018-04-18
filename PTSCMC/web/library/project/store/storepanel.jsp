<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<html:form action="searchStore.do">
    <table width="100%" border="1">            
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delProjectStores();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addProjectStore();"/>
                <%}%>
            </td>
        </tr>
    </table>
</html:form>
<form name='storesForm' id='storesForm'><div id='projectStoreList'></div></form>