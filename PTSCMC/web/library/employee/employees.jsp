<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.bean.EmployeeBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmployees({})" name="<%=Constants.EMPLOYEE_LIST%>" id="employee" class="its" defaultsort="4">
    <display:setProperty name="paging.banner.items_name" value='nhân viên'/>
    <display:setProperty name="paging.banner.item_name" value="nhân viên"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="empId" value="<%=((EmployeeBean)pageContext.getAttribute("employee")).getEmpId()%>"></div>
    </display:column>
    <display:column property="orgName" titleKey="message.employee.orgName" sortable="true" headerClass="sortable"/>
    <display:column property="posName" titleKey="message.employee.posName" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_SYSTEM)) {%>
        <display:column titleKey="message.employee.fullname" sortable="true" headerClass="sortable">
        <a href="#" name="empId" onclick="addEmployee(<%=((EmployeeBean)pageContext.getAttribute("employee")).getEmpId()%>)"><%=((EmployeeBean)pageContext.getAttribute("employee")).getFullname()%></a>
    </display:column>
    <%}else{%>
    <display:column property="fullname" titleKey="message.employee.fullname" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="nickname" titleKey="message.employee.nickname" />	
    <display:column property="email" titleKey="message.employee.email"/>
    <display:column property="statusDetail" titleKey="message.employee.status" />	
</display:table>
