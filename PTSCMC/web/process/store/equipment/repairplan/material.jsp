<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select name="material">
     <logic:iterate id="material" name="<%=Constants.MATERIAL_LIST%>">
        <option value="${material.matId}">${material.nameVn}</option>
    </logic:iterate>
</select>