<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select  onchange="return spe5aChanged(this);" property="spe5Id" name="<%=Constants.SPE%>" style="width: 400px">
    <html:options collection="<%=Constants.SPE5_LIST%>" property="value" labelProperty="label"/>
</html:select>
