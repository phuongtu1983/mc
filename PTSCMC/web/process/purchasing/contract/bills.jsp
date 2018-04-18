<%@page import="com.venus.mc.contract.ContractFormBean"%>
<%@page import="com.venus.mc.util.PermissionUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<div>
    <%
        ContractFormBean form = (ContractFormBean) request.getAttribute(Constants.CONTRACT);
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_DELIVERYREQUEST, form.getOwnerId())) {
    %>
    <html:image src="images/ico_xoa.gif" onclick="return delContractBills();"/>
    <html:image src="images/ico_them.gif" onclick="return setSelectedContractBill();"/>
    <%}%>
    <input type="text" class="lbl10" style="border-width:1px;text-align:right;display:none" size="9" id="inputBillTemplate" onclick="javascript: showCalendar(this)"/>
    <input type="text" class="lbl10" style="border-width:1px;text-align:right;display:none" size="9" id="inputBillDateTemplate" onclick="javascript: showCalendar(this)"/>
    <input type="text" class="lbl10" style="border-width:1px;text-align:right;display:none" size="10" id="inputBillAmountTemplate"  onblur="tryNumberFormat(this)" />
    <input type="text" class="lbl10" style="border-width:1px;text-align:right;display:none" size="30" id="inputBillNoteTemplate"/>
</div>
<table style="width:100%" id="contractBillTable" class="its">
    <thead>
        <tr>
            <th width="30px"></th>
            <th width="100px"><bean:message key="message.contract.bill.number"/></th>
            <th width="90px"><bean:message key="message.contract.bill.date"/></th>
            <th width="80px"><bean:message key="message.contract.bill.invoiceDate"/></th>
            <th width="120px"><bean:message key="message.contract.bill.amount"/></th>
            <th><bean:message key="message.note"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.CONTRACT_BILL_LIST%>">
            <logic:iterate id="detail" indexId="counter" name="<%=Constants.CONTRACT_BILL_LIST%>">
                <bean:define id="mod2" value="${counter}"/>
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="icId" value="<bean:write name="detail" property="icId"/>"/></div>
                        <input type="hidden" name="conInvId" value="<bean:write name="detail" property="icId"/>"/>
                    </td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="30" name="detail" property="invoice"/></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="9" name="detail" property="paymentDate" styleId="paymentDate${counter}" onclick="javascript: showCalendar(this)"/></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="9" name="detail" property="invoiceDate" styleId="invoiceDate${counter}" onclick="javascript: showCalendar(this)"/></td>
                    <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="15" name="detail" property="amount" styleId="amount${counter}"  onblur="tryNumberFormat(this)" /></td>
                    <td><input type="text" class="lbl10" style="border-width:1px;text-align:right" size="30" name="invoiceNote" styleId="invoiceNote${counter}" value="<bean:write name="detail" property="note"/>"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>