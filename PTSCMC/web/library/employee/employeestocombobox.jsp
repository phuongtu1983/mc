<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<logic:iterate id="emplyee" name="<%=Constants.EMPLOYEE_LIST%>">
    <option value="${emplyee.empId}">${emplyee.fullname}</option>
</logic:iterate>