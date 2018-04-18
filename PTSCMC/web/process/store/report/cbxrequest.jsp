<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<select name="mcReportRequest" style="width: 170px;">
    <logic:present name="<%=Constants.REQUEST_LIST%>">
        <logic:iterate id="request_iter" name="<%=Constants.REQUEST_LIST%>">
            <option class="lbl9" value="${request_iter.reqId}">${request_iter.requestNumber}</option>
        </logic:iterate>
    </logic:present>
</select> 