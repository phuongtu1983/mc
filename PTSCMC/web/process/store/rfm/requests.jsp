<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select id="reqId" name="requests" onchange="return reqChanged(this);">
     <logic:iterate id="requests" name="<%=Constants.REQUEST_LIST%>">
        <option value="${requests.reqId}">${requests.requestNumber}</option>
    </logic:iterate>
</select>