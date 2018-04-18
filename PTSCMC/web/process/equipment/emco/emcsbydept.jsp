<%-- 
    Document   : emcosbydept
    Created on : Nov 13, 2009, 11:03:13 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>

<bean:message key="message.emc.emcNumber"/>
<html:select styleClass="lbl10" property="emcId" name="<%=Constants.EMCO%>" onchange="return emcIdChangedEmco(this);">
    <html:options styleClass="lbl10" collection="<%=Constants.EMC_LIST%>" property="emcId" labelProperty="emcNumber"/>
</html:select>

