<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequireVerifiedBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRequireVerifiedListSort({})" name="<%=Constants.REQUIREVERIFIED_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name" value=""/>
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="rvId" value="<%=((RequireVerifiedBean) pageContext.getAttribute("req")).getRvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
    <display:column titleKey="message.requireverified.rvNumber">
        <a href="#" onclick="requireVerifiedForm(<%=((RequireVerifiedBean) pageContext.getAttribute("req")).getRvId()%>)"><%=((RequireVerifiedBean) pageContext.getAttribute("req")).getRvNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rvNumber" titleKey="message.requireverified.rvNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.requirerepair.requireDate"/>
    <display:column property="orgName" titleKey="message.requirerepair.requireOrg"/>
</display:table>