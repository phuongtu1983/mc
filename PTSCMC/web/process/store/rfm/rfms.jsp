<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.process.store.rfm.RfmFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRfms({})" name="<%=Constants.RFM_LIST%>" id="rfm" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.rfm.rfmNumber1'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.rfm.rfmNumber1'/></display:setProperty>
    <display:column titleKey="message.del">
        <% if (((RfmFormBean) pageContext.getAttribute("rfm")) != null)
                 if (((RfmFormBean) pageContext.getAttribute("rfm")).getCanDelete() == true) {%>
        <input type="checkbox" name="rfmId" value="<%=((RfmFormBean) pageContext.getAttribute("rfm")).getRfmId()%>">
        <%}else{%>
        <input type="checkbox" disabled name="rfmId" value="<%=((RfmFormBean) pageContext.getAttribute("rfm")).getRfmId()%>">
        <%}%>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STOCK_RFM)) {%>
    <display:column titleKey="message.rfm.rfmNumber1" sortable="true">
        <a href="#" onclick="addRfm(<%=((RfmFormBean) pageContext.getAttribute("rfm")).getRfmId()%>,<%=((RfmFormBean) pageContext.getAttribute("rfm")).getKind()%>)"><%=((RfmFormBean) pageContext.getAttribute("rfm")).getRfmNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rfmNumber" titleKey="message.rfm.rfmNumber1" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.rfm.createdDate" sortable="true"/>
    <display:column property="forName" titleKey="message.rfm.for"/>
    <display:column property="deliveryDate" titleKey="message.rfm.deliveryDate" sortable="true"/>
    <display:column property="stoName" titleKey="message.rfm.stoId"/>
    <display:column property="reqName" titleKey="message.rfm.request1"/>
    <display:column property="empName" titleKey="message.rfm.empName"/>
    <display:column property="orgName" titleKey="message.rfm.orgName"/>
</display:table>