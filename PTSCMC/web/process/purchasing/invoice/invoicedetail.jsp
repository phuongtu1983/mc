<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.InvoiceContractFormBean"%>
<h3><bean:message key="message.invoice.title"/>/
    <logic:equal property="icId" name="<%=Constants.INVOICE%>" value="0"><bean:message key="message.add.s"/></logic:equal>
    <logic:notEqual property="icId" name="<%=Constants.INVOICE%>" value="0"><bean:message key="message.detail.s"/></logic:notEqual>
</h3>
<div id="errorMessageDiv"></div>
<form name="invoiceForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.contract.bill.number"/></td>
            <td><html:text styleClass="lbl10" size="20" property="invoice" name="<%=Constants.INVOICE%>"/></td>
            <td class="lbl10"><bean:message key="message.contract.bill.invoiceDate"/></td>
            <td><html:text styleClass="lbl10" property="invoiceDate" styleId="invoiceDate" onclick="javascript:showCalendar(this)" size="10" name="<%=Constants.INVOICE%>"/></td>
        </tr>
        <tr>
            <logic:notEqual property="icId" name="<%=Constants.INVOICE%>" value="0">
                <td class="lbl10"><bean:write name="<%=Constants.INVOICE%>" property="contractKind"/></td>
                <td colspan="3">
                    <html:text styleClass="lbl10" size="20" property="contractNumber" name="<%=Constants.INVOICE%>" readonly="true"/>
                    <html:hidden name="<%=Constants.INVOICE%>" property="conId"/>
                </td>
            </logic:notEqual>
            <logic:equal property="icId" name="<%=Constants.INVOICE%>" value="0">
                <td class="lbl10"><bean:message key="message.contract.number"/></td>
                <td colspan="3">
                    <span id="invoiceContractIdSpan">
                        <html:select style="width: 170px;" styleClass="lbl10" styleId="invoiceContractDiv" property="conId" name="<%=Constants.INVOICE%>" onchange="invoiceContractChanged(this)">
                            <html:options styleClass="lbl10" collection="<%=Constants.CONTRACT_LIST%>" property="conId" labelProperty="contractNumber"/>
                        </html:select>
                        <input type="button" onclick="return comboTextIdClick('invoiceForm','contractNumberText','conId','invoiceContractIdSpan','invoiceContractNameSpan');" value='<>'/>
                    </span>
                    <span  style="display:none" id="invoiceContractNameSpan">
                        <input type="textbox" size="29" name="contractNumberText"/>
                        <input type="button" onclick="return comboTextNameClick('invoiceForm','getContractOfInvoiceToCombobox.do',null,'contractNumberText','conId','invoiceContractDiv','invoiceContractIdSpan','invoiceContractNameSpan');" value='<>'/>
                    </span>
                    <!--<input type="button" onclick="return searchContractFromPayment('invoiceContractSearched','<bean:message key="message.invoice"/>/<bean:message key="message.contract.bill.searchContract"/>','searchInvoiceContract.do');" value="..."/>-->
                </td>
            </logic:equal>
        </tr>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <tr>
            <td><bean:message key="message.contract.currency"/></td>
            <td>
                <html:select disabled="true" property="currency" name="<%=Constants.INVOICE%>">
                    <html:options collection="<%=Constants.CURRENCY_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
            <td><bean:message key="message.comevalmaterial.rates"/></td>
            <td><html:text onblur="tryNumberFormat(this);caculateInvoice()" styleClass="lbl10" size="20" property="rates" name="<%=Constants.INVOICE%>"/></td>
        </tr>
        <tr style="display:none">
            <td class="lbl10"><bean:message key="message.contract.amount"/></td>
            <td><input name="invoiceContractAmount" onblur="tryNumberFormat(this);caculateInvoice()" size="20" class="lbl10" type="text"></td>
            <td class="lbl10"><bean:message key="message.contract.VAT"/></td>
            <td><input name="invoiceContractVAT" onblur="tryNumberFormat(this);caculateInvoice()" size="20" class="lbl10" type="text"></td>
        </tr>
        <tr style="display:none">
            <td class="lbl10"><bean:message key="message.contract.totalAmount"/></td>
            <td><input name="invoiceContractTotalAmount" size="20" class="lbl10" type="text"></td>
        </tr>
        <%} else {%>
        <html:hidden property="currency" name="<%=Constants.INVOICE%>"/>
        <html:hidden property="rates" name="<%=Constants.INVOICE%>"/>
        <input name="invoiceContractAmount" hidden="" type="text">
        <input name="invoiceContractVAT" hidden="" type="text">
        <input name="invoiceContractTotalAmount" hidden="" type="text">
        <%}%>
        <tr>
            <td><bean:message key="message.contract.bill.status"/></td>
            <td>
                <html:select property="status" name="<%=Constants.INVOICE%>" >
                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.contract.bill.receiveDate"/></td>
            <td><html:text styleClass="lbl10" property="receiveDate" styleId="invoiceReceiveDate" onclick="javascript:showCalendar(this)" onblur="updateOtherDateValue(this.value,document.forms['invoiceForm'].contractInvoicePaymentDate,30)" size="10" name="<%=Constants.INVOICE%>"/></td>
            <td class="lbl10"><bean:message key="message.contract.bill.payment.date"/></td>
            <td><html:text styleClass="lbl10" property="contractPaymentDate" styleId="contractInvoicePaymentDate" onclick="javascript:showCalendar(this)" size="10" name="<%=Constants.INVOICE%>"/></td>            
            <!--<td class="lbl10"><bean:message key="message.contract.bill.date"/></td>
            <td><html:text styleClass="lbl10" property="paymentDate" styleId="invoicePaymentDate" onclick="javascript:showCalendar(this)" size="10" name="<%=Constants.INVOICE%>"/></td>-->
        </tr>        
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
        <tr>            
            <td class="lbl10"><bean:message key="message.contract.bill.otherAmount"/></td>
            <td><html:text styleClass="lbl10" size="20"  onblur="tryNumberFormat(this);caculateInvoice()" property="otherAmount" name="<%=Constants.INVOICE%>"/></td>
            <td class="lbl10"><bean:message key="message.contract.transport"/></td>
            <td><html:text styleClass="lbl10" size="20" onblur="tryNumberFormat(this);caculateInvoice()" property="transportAmount" name="<%=Constants.INVOICE%>"/></td>
        </tr>

        <tr>
            <td><bean:message key="message.contract.bill.amount"/></td>
            <td><html:text styleClass="lbl10" size="20" readonly="true" property="amount" name="<%=Constants.INVOICE%>"/></td>
            <td><bean:message key="message.contract.sumVAT"/></td>
            <td><html:text styleClass="lbl10" size="20" readonly="true" property="sumVAT" name="<%=Constants.INVOICE%>"/></td>
        </tr>
        <%} else {%>
        <html:hidden property="otherAmount" name="<%=Constants.INVOICE%>"/>
        <html:hidden property="amount" name="<%=Constants.INVOICE%>"/>
        <html:hidden property="sumVAT" name="<%=Constants.INVOICE%>"/>
        <%}%>
        <!--<tr>
        <td><bean:message key="message.contract.bill.note"/></td>
            <td colspan="3"><html:textarea rows="3" cols="80" property="note" name="<%=Constants.INVOICE%>"/></td>
        </tr>-->
        <tr>
            <td colspan="4">
                <fieldset>
                    <legend><bean:message key="message.contract.materials"/></legend>
                    <div><input type="checkbox" name="chooseAll"><bean:message key="message.chooseAll"/></input></div>
                    <div>
                        <html:image src="images/ico_xoa.gif" onclick="return delInvoiceDetails();"/>
                        <html:image src="images/ico_them.gif" onclick="return setSelectedInvoiceMaterial();"/>
                        <span id="listInvoiceMaterialDataSpan"><select name="material"></select></span>
                    </div>
                    <div id="listInvoiceMaterialDataDiv" style="width:700px;height:300px;overflow:auto"><%@include  file="/process/purchasing/invoice/details.jsp" %></div>
                </fieldset>
            </td>
        </tr>
    </table>
    <%
            //InvoiceContractFormBean form = (InvoiceContractFormBean) request.getAttribute(Constants.INVOICE);
            //if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_PURCHASING_INVOICE, form.getCreatedEmp())) {
    %>
    <logic:present name="<%=Constants.CAN_SAVE%>">
        <html:image onclick="return saveInvoice();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"></img>
    </logic:present>
    <%//}%>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_PURCHASING_INVOICE)) {%>
    <logic:present name="<%=Constants.CAN_DELETE%>">
        <html:image src="images/ico_xoa.gif" onclick="return delInvoices();"/>
    </logic:present>
    <%}%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadInvoiceList();"/>
    <html:hidden property="icId" name="<%=Constants.INVOICE%>"/>
</form>
<div id="invoiceMaterialHideDiv" style="display:none"></div>