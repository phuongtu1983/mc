<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>

<select name="reqId" onchange="dmvReqChanged(this, this.selectedIndex);">
    <option class="lbl9" value="0"><bean:message key="message.select"/></option>
    <logic:iterate id="req_iter" name="<%=Constants.REQUEST_LIST%>">
        <option class="lbl9" value="${req_iter.value}">${req_iter.label}</option>
    </logic:iterate>
</select>
