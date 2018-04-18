<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.process.purchasing.deliverynotice.DnFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.ContractBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadDnListSort({})" name="<%=Constants.DN_LIST%>" id="dn" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.dn.dnNumber'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.dn.dnNumber'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="dnId" value="<%=((DnFormBean) pageContext.getAttribute("dn")).getDnId()%>">
    </display:column>
    <% if (((DnFormBean) pageContext.getAttribute("dn")) != null)
                    if (((DnFormBean) pageContext.getAttribute("dn")).getHighlight() == 1) {%>
    <display:column style="background-color: red;" titleKey="message.dn.dnNumber" sortable="true">
        <a href="#" onclick="addDn(<%=((DnFormBean) pageContext.getAttribute("dn")).getDnId()%>,<%=((DnFormBean) pageContext.getAttribute("dn")).getKind()%>)"><%=((DnFormBean) pageContext.getAttribute("dn")).getDnNumber()%></a>
    </display:column>
    <% } else if (((DnFormBean) pageContext.getAttribute("dn")).getHighlight() == 2) {%>
    <display:column style="" titleKey="message.dn.dnNumber" sortable="true">
        <a href="#" onclick="addDn(<%=((DnFormBean) pageContext.getAttribute("dn")).getDnId()%>,<%=((DnFormBean) pageContext.getAttribute("dn")).getKind()%>)"><%=((DnFormBean) pageContext.getAttribute("dn")).getDnNumber()%></a>
    </display:column>
    <% } else if (((DnFormBean) pageContext.getAttribute("dn")).getHighlight() == 0) {%>
    <display:column style="background-color: yellow;" titleKey="message.dn.dnNumber" sortable="true">
        <a href="#" onclick="addDn(<%=((DnFormBean) pageContext.getAttribute("dn")).getDnId()%>,<%=((DnFormBean) pageContext.getAttribute("dn")).getKind()%>)"><%=((DnFormBean) pageContext.getAttribute("dn")).getDnNumber()%></a>
    </display:column>
    <% }%>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE)) {%>
    <display:column titleKey="message.dn.contractNumber" sortable="true">
        <%if (((DnFormBean) pageContext.getAttribute("dn")).getConKind() == ContractBean.KIND_APPENDIX) {%>
        <a href="#" onclick="contractForm(<%=ContractBean.KIND_CONTRACT%>,<%=((DnFormBean) pageContext.getAttribute("dn")).getParentId()%>,null,true,<%=ContractBean.KIND_APPENDIX%>,null,null,<%=((DnFormBean) pageContext.getAttribute("dn")).getConId()%>)"><%=((DnFormBean) pageContext.getAttribute("dn")).getContractNumber()%></a>
        <%} else {%>
        <a href="#" onclick="contractForm(<%=((DnFormBean) pageContext.getAttribute("dn")).getConKind()%>,<%=((DnFormBean) pageContext.getAttribute("dn")).getConId()%>)"><%=((DnFormBean) pageContext.getAttribute("dn")).getContractNumber()%></a>
        <%}%>
    </display:column>
    <%}else{%>
    <display:column property="contractNumber" titleKey="message.dn.contractNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.dn.createdDate" sortable="true"/>
    <display:column property="expectedDate" titleKey="message.dn.expectedDate" sortable="true"/>
    <display:column property="createdDateMrir" titleKey="message.dn.mrirCreatedDate" sortable="true"/>
    <display:column property="receiveDateMrir" titleKey="message.dn.receiveMrir" sortable="true"/>
    <display:column property="createdDateMsv" titleKey="message.dn.msvCreatedDate" sortable="true"/>
    <display:column property="receiveDateMsv" titleKey="message.dn.receiveMsv" sortable="true"/>
    <%
                if (((DnFormBean) pageContext.getAttribute("dn")) != null)
                    if (((DnFormBean) pageContext.getAttribute("dn")).getKind() < 5) {%>
    <display:column property="restDay" titleKey="message.contract.bill.restDay" sortable="true"/>
    <% if (((DnFormBean) pageContext.getAttribute("dn")).getWhichUse() == 1) {%>
    <display:column style="background-color: red;" property="invoice" titleKey="message.payment.invoice" sortable="true"/>
    <% }else {%>
    <display:column property="invoice" titleKey="message.payment.invoice" sortable="true"/>
    <% }%>
    <display:column property="statusInvoice" titleKey="message.dn.statusInvoice"/>
    <% }%>
    <display:column property="followEmp" titleKey="message.contract.followEmp"/>
    <display:column property="proName" titleKey="message.project"/>
</display:table>