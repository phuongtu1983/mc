<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select onchange="return spe2aChanged(this);" property="spe2Id" name="<%=Constants.SPE%>" style="width: 400px">
    <html:options collection="<%=Constants.SPE2_LIST%>" property="value" labelProperty="label"/>
</html:select>