<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<html:select styleClass="lbl10" property="proId" name="<%=Constants.REQUEST%>">
    <html:options styleClass="lbl10" collection="<%=Constants.REQUEST_WHICHUSE_LIST%>" property="value" labelProperty="label"/>
</html:select>