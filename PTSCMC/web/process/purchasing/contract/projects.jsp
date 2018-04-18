<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select name="project">
     <logic:iterate id="project" name="<%=Constants.PROJECT_LIST%>">
            <option value="${project.proId}">${project.name}</option>
        </logic:iterate>
</select>