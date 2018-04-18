<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.util.Constants"%>
<td><bean:message key="message.order"/></td>
<td>
<select style="width: 300px;" name="parentId" onchange="return orderContractChanged(this)">
     <logic:iterate id="contract" name="<%=Constants.CONTRACT_LIST%>">
            <option value="${contract.conId}">${contract.contractNumber}</option>
        </logic:iterate>
</select>
</td>
<td colspan="2"><div id="contractTenderPlanDiv"></div></td>