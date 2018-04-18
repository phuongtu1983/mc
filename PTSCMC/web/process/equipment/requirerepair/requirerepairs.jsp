<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequireRepairBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRequireRepairListSort({})" name="<%=Constants.REQUIREREPAIR_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.requirerepair'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.requirerepair'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="rrId" value="<%=((RequireRepairBean) pageContext.getAttribute("req")).getRrId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
    <display:column titleKey="message.requirerepair.rrNumber" sortable="true">
        <a href="#" onclick="requirerepairForm(<%=((RequireRepairBean) pageContext.getAttribute("req")).getRrId()%>)"><%=((RequireRepairBean) pageContext.getAttribute("req")).getRrNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rrNumber" titleKey="message.requirerepair.rrNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="requireDate" titleKey="message.requirerepair.requireDate" sortable="true"/>
    <display:column property="requireOrg" titleKey="message.requirerepair.requireOrg" sortable="true"/>
</display:table>