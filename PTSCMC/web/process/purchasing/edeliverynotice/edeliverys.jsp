<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<html:select property="drId" name="<%=Constants.DN%>" >
    <html:options collection="<%=Constants.DN_WHICHUSE%>" property="drId" labelProperty="deliveryNumber"/>
</html:select>
