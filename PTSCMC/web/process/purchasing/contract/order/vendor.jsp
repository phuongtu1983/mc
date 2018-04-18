<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.util.Constants"%>
<span><bean:write name="<%=Constants.VENDOR%>" property="name"/></span>
<input type="hidden" name="venId" value="<bean:write name="<%=Constants.VENDOR%>" property="venId"/>"/>