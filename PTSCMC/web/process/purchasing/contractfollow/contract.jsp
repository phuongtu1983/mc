<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select style="font-size: 8pt; font-family: Tahoma; border: 1px solid #D0CCC4;" property="spe2Id" name="<%=Constants.CONTRACT_FOLLOW%>">
    <html:options collection="<%=Constants.CONTRACT_LIST%>" property="value" labelProperty="label"/>
</html:select>
