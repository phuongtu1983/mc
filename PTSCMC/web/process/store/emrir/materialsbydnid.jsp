<%-- 
    Document   : materialsbydnid
    Created on : Oct 18, 2009, 12:45:18 AM
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

<select name="materialList">
    <logic:iterate id="material" name="<%=Constants.EMRIR_MATERIAL_LIST%>">
        <option value="<bean:write name="material" property="ematId"/>"><bean:write name="material" property="matName"/></option>
    </logic:iterate>
</select>
