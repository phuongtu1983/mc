<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequireTransferBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRequireTransferListSort({})" name="<%=Constants.REQUIRETRANSFER_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name" value=""/>
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="rtId" value="<%=((RequireTransferBean) pageContext.getAttribute("req")).getRtId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
    <display:column titleKey="message.requireverified.rvNumber">
        <a href="#" onclick="requireTransferForm(<%=((RequireTransferBean) pageContext.getAttribute("req")).getRtId()%>)"><%=((RequireTransferBean) pageContext.getAttribute("req")).getRtNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rvNumber" titleKey="message.requireverified.rvNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.requirerepair.requireDate"/>
    <display:column property="orgName" titleKey="message.requirerepair.requireOrg"/>
</display:table>