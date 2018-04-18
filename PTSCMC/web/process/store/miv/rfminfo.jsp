<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<td><bean:message key="message.miv.whichUse"/></td>
<td><html:text size="30" property="whichUseName" name="<%=Constants.RFM%>" readonly="true"/></td>
<td><bean:message key="message.miv.store"/></td>
<td>
    <html:text size="20" property="storeName" name="<%=Constants.RFM%>" disabled="true"/>
    <html:hidden property="stoId" name="<%=Constants.RFM%>"/>
</td>