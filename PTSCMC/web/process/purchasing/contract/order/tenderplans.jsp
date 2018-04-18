<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.util.Constants"%>
<td><bean:message key="message.contract.tender.number"/></td>
<td>
    <select style="width: 100px;" name="tenId" onchange="return contractTenderPlanChanged(this)">
        <logic:iterate id="tender" name="<%=Constants.TENDERPLAN_LIST%>">
            <option value="${tender.tenId}">${tender.tenderNumber}</option>
        </logic:iterate>
    </select>
</td>
<td><bean:message key="message.contract.vendor"/></td>
<td>
    <div id="contractTenderPlanDiv">
        <select name="venId"></select>
    </div>
</td>