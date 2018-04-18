<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<html:select property="conId" name="<%=Constants.DN%>" >
    <html:options collection="<%=Constants.DN_WHICHUSE%>" property="conId" labelProperty="contractNumber"/>
</html:select>