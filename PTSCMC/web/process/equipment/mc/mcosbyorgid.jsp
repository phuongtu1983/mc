<%-- 
    Document   : mcosbyorgid
    Created on : Nov 13, 2009, 9:16:53 PM
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

<bean:message key="message.mco.mcoNumber"/>
<html:select styleClass="lbl10" property="mcoId" name="<%=Constants.MC%>" onchange="return mcoIdChangedMc(this);">
    <html:options styleClass="lbl10" collection="<%=Constants.MCO_LIST%>" property="mcoId" labelProperty="mcoNumber"/>
</html:select>

