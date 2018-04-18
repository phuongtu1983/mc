<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select name="mcReportProject" onchange="return reportProjectChanged(this)"  style="width: 170px;">
    <logic:iterate id="project_iter" name="<%=Constants.PRO_LIST%>">
        <option class="lbl9" value="${project_iter.value}">${project_iter.label}</option>
    </logic:iterate>
</select> 