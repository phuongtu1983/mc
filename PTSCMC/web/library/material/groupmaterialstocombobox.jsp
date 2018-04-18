<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select style="width: 300px;" name="materialGroup">
    <logic:iterate id="group" name="<%=Constants.VENDOR_GROUP_MATERIAL_LIST%>">
        <option value="${group.groId}">${group.name}</option>
    </logic:iterate>
</select>