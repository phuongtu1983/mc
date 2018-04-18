<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadOrders({})" name="<%=Constants.CONTRACT_LIST%>" id="cont" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_order'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_order'/></display:setProperty>
    <display:column titleKey="message.del">
        <% if (((ContractFormBean) pageContext.getAttribute("cont")) != null) {
                if (((ContractFormBean) pageContext.getAttribute("cont")).getPaymentStatus() == ContractFormBean.STATUS_APPROVED) {%>
        <input type="checkbox" disabled="disabled" name="conId" value="<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>">
        <%}else{%>
        <input type="checkbox" name="conId" value="<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>">
        <%}}%>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_ORDER)) {%>
    <display:column titleKey="message.order.number" sortable="true">
        <a href="#" onclick="contractForm(<%=((ContractFormBean) pageContext.getAttribute("cont")).getKind()%>,<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>)"><%=((ContractFormBean) pageContext.getAttribute("cont")).getContractNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="contractNumber" titleKey="message.contract.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="effectedDate" titleKey="message.contract.effectedDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="note" titleKey="message.order.note" />
    <display:column property="vendorName" titleKey="message.contract.vendor" sortable="true" />
    <% if (((ContractFormBean) pageContext.getAttribute("cont")) != null)
        if (((ContractFormBean) pageContext.getAttribute("cont")).getColor().equals("red")) {%>
    <display:column style="background-color: red;" property="deliveryDate" titleKey="message.contract.material.deliveryDate"/>
    <%} else if (((ContractFormBean) pageContext.getAttribute("cont")).getColor().equals("yellow")) {%>
    <display:column style="background-color: yellow;" property="deliveryDate" titleKey="message.contract.material.deliveryDate"/>
    <% } else {%>
    <display:column style="background-color: white;" property="deliveryDate" titleKey="message.contract.material.deliveryDate"/>
    <%}%>
    <display:column property="processStatusString" titleKey="message.contract.process.status" sortable="true" />
    <display:column property="payStatus" titleKey="message.contract.status.payments" sortable="true" />
    <display:column property="employeeName" titleKey="message.contract.responseEmp" sortable="true" />
    <display:column property="followEmpName" titleKey="message.contract.followEmp" sortable="true" />
</display:table>
