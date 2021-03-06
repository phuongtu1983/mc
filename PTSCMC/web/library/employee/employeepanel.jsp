<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<h3><bean:message key="message.listemployee.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="searchEmployee.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchEmployee();"/>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delEmployees();"/>
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_SYSTEM)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addEmployee();"/>
                <%}%>
            </td>
            <td><div>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.employee.l_name" value='1'/>
                        <html:option key="message.employee.orgName" value='2'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchEmployee();"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvEmployeeForm();"><bean:message key="message.detailSearch"/></html:submit>
                    </div></td>
            </tr>
        </table>
</html:form>
<form name='employeesForm' id='employeesForm'><div id='employeeList'></div></form>