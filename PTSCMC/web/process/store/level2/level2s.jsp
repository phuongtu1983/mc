<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.bean.StoreLevel2Bean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadStoreLevel2List({})" name="<%=Constants.STORE_LIST%>" id="store" class="its" defaultsort="1">
    <display:setProperty name="paging.banner.items_name"><bean:message key="message.storelevel2"/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key="message.storelevel2"/></display:setProperty>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STOCK_STORE2)) {%>
    <display:column titleKey="message.storelevel2.name" sortable="true" headerClass="sortable">
        <a href="#" onclick="loadMaterialStoreUsedList(<%=((StoreLevel2Bean)pageContext.getAttribute("store")).getSto2Id()%>)"><%=((StoreLevel2Bean)pageContext.getAttribute("store")).getName()%></a>
    </display:column>
    <display:column titleKey="message.umsdetail.title_l">
        <a href="#" onclick="loadMaterialStoreUsedList(<%=((StoreLevel2Bean)pageContext.getAttribute("store")).getSto2Id()%>)"><%=((StoreLevel2Bean)pageContext.getAttribute("store")).getUmsQuantityText()%></a>
    </display:column>
    <display:column titleKey="message.rmsdetail.title_l">
        <a href="#" onclick="loadMaterialStoreReturnedList(<%=((StoreLevel2Bean)pageContext.getAttribute("store")).getSto2Id()%>)"><%=((StoreLevel2Bean)pageContext.getAttribute("store")).getRmsQuantityText()%></a>
    </display:column>
    <%}else{%>
    <display:column property="name" titleKey="message.storelevel2.name" sortable="true" headerClass="sortable"/>
    <display:column property="umsQuantity" titleKey="message.umsdetail.title_l"/>
    <display:column property="rmsQuantity" titleKey="message.rmsdetail.title_l"/>
    <%}%>
</display:table>