<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.payment.PaymentFormBean"%>
<h3><bean:message key="message.payment.title"/>/
    <logic:greaterThan name="<%=Constants.PAYMENT%>" value="0" property="payId">
        <bean:message key="message.detail.s"/>
    </logic:greaterThan>
    <logic:equal name="<%=Constants.PAYMENT%>" value="0" property="payId">
        <bean:message key="message.add.s"/>
    </logic:equal>
</h3>
<div id="errorMessageDiv"></div>
<form name="paymentForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.payment.number"/></td>
            <td><html:text styleClass="lbl10" size="20" property="payNumber" name="<%=Constants.PAYMENT%>"/></td>
            <td class="lbl10"><bean:message key="message.payment.status"/></td>
            <td>
                <html:select styleClass="lbl10" property="status" name="<%=Constants.PAYMENT%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.payment.vendor"/></td>
            <td colspan="3">
                <span id="paymentVendorIdSpan">
                    <html:select style="width:170px" styleClass="lbl10" styleId="invoiceVendorDiv" property="venId" name="<%=Constants.PAYMENT%>" onchange="return paymentVendorChanged(this)">
                        <html:options styleClass="lbl10" collection="<%=Constants.VENDOR_LIST%>" property="venId" labelProperty="name"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('paymentForm','vendorName','venId','paymentVendorIdSpan','paymentVendorNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="paymentVendorNameSpan">
                    <input type="textbox" size="29" name="vendorName"/>
                    <input type="button" onclick="return comboTextNameClick('paymentForm','getVendorHasInvoiceToCombobox.do',null,'vendorName','venId','invoiceVendorDiv','paymentVendorIdSpan','paymentVendorNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.contract.number"/></td>
            <td>
                <html:select style="width:170px" styleClass="lbl10" styleId="invoiceContractDiv" property="conId" name="<%=Constants.PAYMENT%>" onchange="return paymentContractChanged(this)">
                    <html:options styleClass="lbl10" collection="<%=Constants.CONTRACT_LIST%>" property="conId" labelProperty="contractNumber"/>
                </html:select>
            </td>
            <td><bean:message key="message.payment.invoice"/></td>
            <td>
                <span id="spanPaymentInvoices">
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
                </span>
            </td>
        </tr>
        <tr>
            <td><bean:message key="message.payment.handleEmp"/></td>
            <td colspan="3">
                <span id="paymentHandleEmpIdSpan">
                    <html:select style="width:170px" property="handleEmp" name="<%=Constants.PAYMENT%>" styleId="paymentHandleEmpIdCmb">
                        <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                    </html:select>
                    <input type="button" onclick="return comboTextIdClick('paymentForm','handleEmpName','handleEmp','paymentHandleEmpIdSpan','paymentHandleEmpNameSpan');" value='<>'/>
                </span>
                <span  style="display:none" id="paymentHandleEmpNameSpan">
                    <input type="textbox" size="29" name="handleEmpName"/>
                    <input type="button" onclick="return comboTextNameClick('paymentForm','getEmployeeToCombobox.do',null,'handleEmpName','handleEmp','paymentHandleEmpIdCmb','paymentHandleEmpIdSpan','paymentHandleEmpNameSpan');" value='<>'/>
                </span>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.payment.payDate"/></td>
            <td><html:text styleClass="lbl10" property="payDate" styleId="createdDatePayment" onclick="javascript:showCalendar('createdDatePayment')" size="10" name="<%=Constants.PAYMENT%>"/></td>
            <td><bean:message key="message.payment.moveAccountingDate"/></td>
            <td><html:text property="moveAccountingDate" size="10" styleId="moveAccountingDate" onclick="javascript: showCalendar('moveAccountingDate')" name="<%=Constants.PAYMENT%>" /></td>
        </tr>
        <tr>
            <!--<td><bean:message key="message.contract.volume"/></td>
            <td><html:text property="volume" size="22" name="<%=Constants.PAYMENT%>" /></td>-->
            <td class="lbl10"><bean:message key="message.payment.location"/></td>
            <td><html:text styleClass="lbl10" size="20" property="location" name="<%=Constants.PAYMENT%>"/></td>
            <td class="lbl10"><bean:message key="message.payment.documentNumber"/></td>
            <td><html:text styleClass="lbl10" size="20" property="document" name="<%=Constants.PAYMENT%>"/></td>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <tr>
            <td><bean:message key="message.payment.punish"/></td>
            <td><html:text styleClass="lbl10" size="20"  onblur="tryNumberFormat(this);caculatePayment()" property="punish" name="<%=Constants.PAYMENT%>"/></td>
            <td><bean:message key="message.payment.total"/></td>
            <td><html:text styleClass="lbl10" size="20" readonly="true" property="total" name="<%=Constants.PAYMENT%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.payment.totalPay"/></td>
            <td><html:text styleClass="lbl10" size="20" readonly="true" property="totalPay" name="<%=Constants.PAYMENT%>"/>
            <td class="lbl10"><bean:message key="message.comevalmaterial.rates"/></td>
            <td><html:text styleClass="lbl10" size="20" property="rates" name="<%=Constants.PAYMENT%>"/>
        </tr>
        <%} else { %>
        <html:hidden property="punish" name="<%=Constants.PAYMENT%>"/>
        <html:hidden property="total" name="<%=Constants.PAYMENT%>"/>
        <html:hidden property="totalPay" name="<%=Constants.PAYMENT%>"/>
        <%}%>
        <tr>
            <td><bean:message key="message.contract.bill.note"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="note" name="<%=Constants.PAYMENT%>"/></td>
        </tr>
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.payment.invoice"/></legend>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delPaymentBills();"/>
                        <html:image src="images/ico_them.gif" onclick="return setSelectedPaymentBill();"/>
                    </div>
                    <div id="divPaymentInvoiceList" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/payment/bills.jsp" %></div>
                </fieldset>
            </td>
        </tr>
    </table>
    <logic:greaterThan name="<%=Constants.PAYMENT%>" value="0" property="payId">
        <img onclick="return printPayment();" src="images/but_print.gif"/>
    </logic:greaterThan>
    <%
                PaymentFormBean form = (PaymentFormBean) request.getAttribute(Constants.PAYMENT);
                if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_PAYMENT, form.getCreatedEmp())) {
    %>
    <html:image onclick="return savePayment();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <%}%>
    <logic:present name="<%=Constants.CAN_DELETE%>">
        <html:image src="images/ico_xoa.gif" onclick="return delPayments();"/>
    </logic:present>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadPaymentList();"/>
    <html:hidden property="payId" name="<%=Constants.PAYMENT%>"/>
    <span id="handleEmpSpan"></span>
</form>
<div id="paymentInvoiceHideDiv" style="display:none"></div>