<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.payment.PaymentFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadPaymentListSort({})" name="<%=Constants.PAYMENT_LIST%>" id="pay" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_payment'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_payment'/></display:setProperty>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_PAYMENT)) {%>
    <display:column titleKey="message.payment.number" sortable="true">
        <a href="#" onclick="paymentForm(<%=((PaymentFormBean) pageContext.getAttribute("pay")).getPayId()%>)"><%=((PaymentFormBean) pageContext.getAttribute("pay")).getPayNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="payNumber" titleKey="message.payment.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="contractNumber" titleKey="message.contract.number" sortable="true"/>
    <display:column property="vendorName" titleKey="message.payment.vendor" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_LIST, PermissionUtil.PER_LIBRARY_MATERIAL_PRICE)) {%>
    <display:column property="totalPayText" titleKey="message.payment.totalPay" sortable="true" headerClass="sortable"/>
    <display:column property="currency" titleKey="message.contract.currency" sortable="true" headerClass="sortable"/>
    <%}%>
    <display:column property="createdDate" titleKey="message.payment.createdDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="moveAccountingDate" titleKey="message.payment.moveAccountingDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="payDate" titleKey="message.payment.payDate"/>
    <display:column property="location" titleKey="message.payment.location"/>
    <display:column property="handleEmpName" titleKey="message.payment.handleEmp"/>
</display:table>