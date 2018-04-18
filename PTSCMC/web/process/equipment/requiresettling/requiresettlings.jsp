<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequireSettlingBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRequireSettlingListSort({})" name="<%=Constants.REQUIRESETTLING_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.requiresettling'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.requiresettling'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="rsId" value="<%=((RequireSettlingBean) pageContext.getAttribute("req")).getRsId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT)) {%>
    <display:column titleKey="message.requiresettling.rsNumber" sortable="true">
        <a href="#" onclick="requiresettlingForm(<%=((RequireSettlingBean) pageContext.getAttribute("req")).getRsId()%>)"><%=((RequireSettlingBean) pageContext.getAttribute("req")).getRsNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rsNumber" titleKey="message.requiresettling.rsNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="requireDate" titleKey="message.requiresettling.requireDate" sortable="true"/>
    <display:column property="usedOrg" titleKey="message.requiresettling.requireOrg" sortable="true"/>
</display:table>