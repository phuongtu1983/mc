<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<html:select property="proId" name="<%=Constants.REQUEST%>">
    <html:options collection="<%=Constants.WHICHUSE_LIST%>" property="value" labelProperty="label"/>
</html:select>