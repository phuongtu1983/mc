<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table style="width:100%" id="paymentBillTable" class="its">
    <thead>
        <tr>
            <th width="50px"></th>
            <th width="200px"><bean:message key="message.contract"/></th>
            <th><bean:message key="message.contract.bill.number"/></th>
            <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
            <th width="150px"><bean:message key="message.contract.bill.amount"/></th>
            <%}%>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_BILL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_BILL_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="payBillId" value="<bean:write name="detail" property="detId"/>"/>
                        <input type="hidden" name="payConId" value="<bean:write name="detail" property="conId"/>"/>
                        <input type="hidden" name="invId" value="<bean:write name="detail" property="icId"/>"/>                 
                    </td>
                    <td><span><bean:write name="detail" property="contractNumber"/></span></td>
                    <td><span><bean:write name="detail" property="invoice"/></span></td>
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
                    <td width="60px"><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="detail" property="amount" styleId="detamount${detail.conId}_${detail.icId}" onblur="tryNumberFormat(this);caculatePaymentDetail('${detail.conId}_${detail.icId}')"/></td>
                    <%} else {%>
                    <html:hidden property="amount" name="detail"/>
                    <%}%>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>