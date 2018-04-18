<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.process.purchasing.edeliverynotice.EdnFormBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEdnListSort({})" name="<%=Constants.DN_LIST%>" id="dn" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.dn.dnNumber'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.dn.dnNumber'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="dnId" value="<%=((EdnFormBean) pageContext.getAttribute("dn")).getDnId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_DELIVERYNOTICE)) {%>
    <display:column titleKey="message.dn.dnNumber" sortable="true">
        <a href="#" onclick="addEdn(<%=((EdnFormBean) pageContext.getAttribute("dn")).getDnId()%>)"><%=((EdnFormBean) pageContext.getAttribute("dn")).getDnNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="dnNumber" titleKey="message.dn.dnNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="contractNumber" titleKey="message.dn.contractNumber" sortable="true"/>
    <display:column property="createdDate" titleKey="message.dn.createdDate" sortable="true"/>
    <display:column property="expectedDate" titleKey="message.dn.expectedDate" sortable="true"/>
</display:table>