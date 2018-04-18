<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.contract.ContractFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadAppendixList({})" name="<%=Constants.CONTRACT_LIST%>" id="cont" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_appendix'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_appendix'/></display:setProperty>
    <display:column titleKey="message.del">
        <% if (((ContractFormBean) pageContext.getAttribute("cont")) != null) {
                if (((ContractFormBean) pageContext.getAttribute("cont")).getPaymentStatus() == ContractFormBean.STATUS_APPROVED) {%>
        <input type="checkbox" disabled="disabled" name="conId" value="<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>">
        <%}else{%>
        <input type="checkbox" name="conId" value="<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>">
        <%}}%>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_CONTRACT)) {%>
    <display:column titleKey="message.appendix.number">
        <a href="#" onclick="appendixForm(<%=((ContractFormBean) pageContext.getAttribute("cont")).getKind()%>,<%=((ContractFormBean) pageContext.getAttribute("cont")).getConId()%>)"><%=((ContractFormBean) pageContext.getAttribute("cont")).getContractNumber()%></a>
    </display:column>
    <%} else {%>
    <display:column property="contractNumber" titleKey="message.appendix.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="parentNumber" titleKey="message.contract.number" sortable="true" />
    <display:column property="effectedDate" titleKey="message.contract.effectedDate" />
    <display:column property="expireDate" titleKey="message.contract.expireDate" />
    <display:column property="totalText" titleKey="message.contract.total" />
    <display:column property="employeeName" titleKey="message.contract.responseEmp" sortable="true" />
</display:table>