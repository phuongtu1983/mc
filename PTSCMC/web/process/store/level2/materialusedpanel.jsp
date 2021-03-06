<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.materialusedlist.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchMaterialStoreUsed.do">
    <table>            
        <tr>
            <td>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_STOCK_STORE2)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delMaterialUseds();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_STOCK_STORE2)) {%>
                <html:image src="images/ico_them.gif"  onclick="return materialUsedForm();"/>
                <%}%>
                <input type="hidden" id="materialUsedStoIdHidden">
            </td>
            <td><div>
                    <select name="searchid">
                        <option value='0'/>Tất cả</option>
                        <option value='1'/>Số phiếu</option>
                        <option value='2'/>Bộ phận</option>
                        <option value='3'/>Dự án</option>
                    </select>
                    <input name="searchvalue" size="40" value="" type="text">
                    <input value="<bean:message key="message.search"/>" onclick="return searchMaterialStoreUsed();" type="submit">
                </div></td>
        </tr>
    </table>
</html:form>
<form name='materialUsedsForm' id='materialUsedsForm'><div id='materialUsedsList'></div></form>