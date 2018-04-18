<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.Constants"%>
<logic:iterate id="emp" name="<%=Constants.EMPLOYEE_LIST%>">
    <option value="${emp.empId}">${emp.fullname}</option>
</logic:iterate>
