<%-- 
    Document   : equipments
    Created on : Nov 8, 2009, 7:22:19 PM
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

<bean:message key="message.mcdetail.equId"/>
<select name="equipmentList">
    <logic:iterate id="equipment" name="<%=Constants.EQUIPMENT_LIST%>">
    <option value="<bean:write name="equipment" property="equId"/>"><bean:write name="equipment" property="equName"/></option>
    </logic:iterate>
</select>

