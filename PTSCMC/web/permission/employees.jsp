<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmployeeBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<table id="permissionEmpTbl" class="its"  style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.employee.fullname"/></th>
            <th><bean:message key="message.organization.name"/></th>
            <th><bean:message key="message.employee.email"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="emp" name="<%=Constants.PERMISSION_EMP_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="permissionEmpChk" value="<%=((EmployeeBean)pageContext.getAttribute("emp")).getEmpId()%>">
                    <input type="hidden" name="permissionEmpId" value="<bean:write name="emp" property="empId"/>"/>
                </td>
                <td><span name="permissionEmp"><bean:write name="emp" property="fullname"/></span></td>
                <td><span name="permissionEmp"><bean:write name="emp" property="orgName"/></span></td>
                <td><span name="permissionEmp"><bean:write name="emp" property="email"/></span></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>