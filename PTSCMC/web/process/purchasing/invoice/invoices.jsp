<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.contract.InvoiceContractFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadInvoiceListSort({})" name="<%=Constants.INVOICE_LIST%>" id="inv" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_invoice'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_invoice'/></display:setProperty>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_INVOICE)) {%>
    <% if (((InvoiceContractFormBean) pageContext.getAttribute("inv")) != null)
    if (((InvoiceContractFormBean) pageContext.getAttribute("inv")).getHighlight() == 1) {%>
    <display:column style="background-color: red;" titleKey="message.payment.invoice" sortable="true">
        <a href="#" onclick="invoiceForm(<%=((InvoiceContractFormBean) pageContext.getAttribute("inv")).getIcId()%>)"><%=((InvoiceContractFormBean) pageContext.getAttribute("inv")).getInvoice()%></a>
    </display:column>
    <% }else{%>
    <display:column style="background-color: white;" titleKey="message.payment.invoice" sortable="true">
        <a href="#" onclick="invoiceForm(<%=((InvoiceContractFormBean) pageContext.getAttribute("inv")).getIcId()%>)"><%=((InvoiceContractFormBean) pageContext.getAttribute("inv")).getInvoice()%></a>
    </display:column>
    <%}%>
    <%}else{%>
    <display:column property="invoice" titleKey="message.payment.invoice" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="contractNumber" titleKey="message.contract.number" sortable="true"/>
    <display:column property="vendor" titleKey="message.u_vendor" sortable="true"/>
    <display:column property="invoiceDate" titleKey="message.contract.bill.invoiceDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column property="amountString" titleKey="message.contract.bill.amount" sortable="true" headerClass="sortable"/>
    <display:column property="currency" titleKey="message.contract.currency" sortable="true" headerClass="sortable"/>
    <%}%>
    <display:column property="contractPaymentDate" titleKey="message.contract.bill.payment.date" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="statusName" titleKey="message.contract.bill.status"/>
    <display:column property="restDay" titleKey="message.contract.bill.restDay"/>
    <display:column property="responseEmp" titleKey="message.payment.handleEmp"/>
</display:table>
