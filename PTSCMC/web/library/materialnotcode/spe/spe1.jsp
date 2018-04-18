<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select onchange="return spe1aChanged(this);" property="spe1Id" name="<%=Constants.SPE%>">
    <html:options collection="<%=Constants.SPE1_LIST%>" property="value" labelProperty="label"/>
</html:select>