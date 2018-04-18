<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select  onchange="return spe5Changed(this);" property="spe5Id" name="<%=Constants.SPE%>">
    <html:options collection="<%=Constants.SPE5_LIST%>" property="value" labelProperty="label"/>
</html:select>
