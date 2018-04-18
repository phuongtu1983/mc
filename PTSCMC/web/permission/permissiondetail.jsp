<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div id="permissionFormTitle"><h3><bean:message key="message.permissionadd.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="permissionForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.permission.name"/></td>
            <td><html:text styleClass="lbl10" size="70" property="name" name="<%=Constants.PERMISSION%>"/></td>
        </tr>  
        <tr>
            <td colspan="2">
                <div><%@include  file="/permission/permits.jsp" %></div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <fieldset>
                    <legend><bean:message key="message.employee.orgName"/></legend>
                    <table>
                        <tr>
                            <td>
                                <html:image src="images/ico_xoa.gif" onclick="return delPermissionOrg();"/>
                                <html:image src="images/ico_them.gif" onclick="return addPermissionOrg();"/>
                                <select name="orgList">
                                    <logic:iterate id="org" name="<%=Constants.ORG_LIST%>">
                                        <option value="<bean:write name="org" property="orgId"/>"><bean:write name="org" property="name"/></option>
                                    </logic:iterate>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div><%@include  file="/permission/organizations.jsp" %></div>
                </fieldset>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <fieldset>
                    <legend><bean:message key="message.menu51.text"/></legend>
                    <table>
                        <tr>
                            <td>
                                <html:image src="images/ico_xoa.gif" onclick="return delPermissionEmp();"/>
                                <html:image src="images/ico_them.gif" onclick="return addPermissionEmp();"/>
                                <span id="permissionEmpIdSpan">
                                    <select name="empList" id="permissionEmpIdCmb">
                                        <logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
                                            <option value="<bean:write name="emp" property="empId"/>"><bean:write name="emp" property="fullname"/></option>
                                        </logic:iterate>
                                    </select>
                                    <input type="button" onclick="return comboTextIdClick('permissionForm','empName','empList','permissionEmpIdSpan','permissionEmpNameSpan');" value='<>'/>
                                </span>
                                <span  style="display:none" id="permissionEmpNameSpan">
                                    <input type="textbox" size="29" name="empName"/>
                                    <input type="button" onclick="return comboTextNameClick('permissionForm','getEmployeeToCombobox.do',null,'empName','empList','permissionEmpIdCmb','permissionEmpIdSpan','permissionEmpNameSpan');" value='<>'/>
                                </span>
                            </td>
                        </tr>
                    </table>
                    <div><%@include  file="/permission/employees.jsp" %></div>
                </fieldset>
            </td>
        </tr>
    </table>
    <html:image onclick="return savePermission();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadPermissionList();"/>
    <html:hidden property="perId" name="<%=Constants.PERMISSION%>"/>
</form>