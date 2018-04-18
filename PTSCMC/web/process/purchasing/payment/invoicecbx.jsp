<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<logic:present name="<%=Constants.INVOICE_LIST%>">
    <select name="invoice" style="width:170px">
        <logic:iterate id="invoice" name="<%=Constants.INVOICE_LIST%>">
            <option value="${invoice.icId}">${invoice.invoice}</option>
        </logic:iterate>
    </select>
</logic:present>
<logic:notPresent name="<%=Constants.INVOICE_LIST%>">
    <select name="invoice" style="width:170px"></select>
</logic:notPresent>