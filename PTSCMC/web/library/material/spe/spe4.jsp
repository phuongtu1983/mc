<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>

<html:select  onchange="return spe4aChanged(this);" property="spe4Id" name="<%=Constants.SPE%>" style="width: 400px">
    <html:options collection="<%=Constants.SPE4_LIST%>" property="value" labelProperty="label"/>
</html:select>
