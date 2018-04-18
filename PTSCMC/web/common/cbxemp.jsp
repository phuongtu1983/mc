<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>

<option class="lbl9" value="0"><bean:message key="message.employee.select"/></option>
 <logic:iterate id="emp_iter" name="<%=Constants.EMP_LIST%>">
    <option class="lbl9" value="${emp_iter.value}">${emp_iter.label}</option>
</logic:iterate>
