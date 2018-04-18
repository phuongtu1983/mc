<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.InventoryBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadInventoryListSort({})" name="<%=Constants.INVENTORY_LIST%>" id="inventory" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_inventory'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_inventory'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="invId" value="<%=((InventoryBean) pageContext.getAttribute("inventory")).getInvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.inventory.stoName" sortable="true">
        <a href="#" onclick="inventoryForm(<%=((InventoryBean) pageContext.getAttribute("inventory")).getInvId()%>)"><%=((InventoryBean) pageContext.getAttribute("inventory")).getStoName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="stoName" titleKey="message.inventory.stoName" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="invDate" titleKey="message.inventory.invDate" sortable="true" headerClass="sortable"/>
    <display:column property="empName" titleKey="message.inventory.empName" sortable="true" headerClass="sortable"/>
    <display:column property="comment" titleKey="message.inventory.comment" sortable="true" headerClass="sortable"/>
</display:table>