<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.process.purchasing.deliverynotice.DnFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadDnListSort({})" name="<%=Constants.DN_LIST%>" id="dn2" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.dn2.dn2Number'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.dn2.dn2Number'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="dnId" value="<%=((DnFormBean) pageContext.getAttribute("dn2")).getDnId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STOCK_YCKT_STORE2)) {%>
    <display:column titleKey="message.dn2.dn2Number" sortable="true">
        <a href="#" onclick="addDn2(<%=((DnFormBean) pageContext.getAttribute("dn2")).getDnId()%>)"><%=((DnFormBean) pageContext.getAttribute("dn2")).getDnNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="dn2Number" titleKey="message.dn2.dn2Number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="contractNumber" titleKey="message.dn2.proId" sortable="true"/>
    <display:column property="createdDate" titleKey="message.dn2.createdDate" sortable="true"/>
</display:table>