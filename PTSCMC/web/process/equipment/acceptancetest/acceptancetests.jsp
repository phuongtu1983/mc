<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.AcceptanceTestBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadAcceptanceTestListSort({})" name="<%=Constants.ACCEPTANCETEST_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.acceptancetest'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.acceptancetest'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="atId" value="<%=((AcceptanceTestBean) pageContext.getAttribute("req")).getAtId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_SURVEYREPORT)) {%>
    <display:column titleKey="message.acceptancetest.atNumber" sortable="true">
        <a href="#" onclick="acceptancetestForm(<%=((AcceptanceTestBean) pageContext.getAttribute("req")).getAtId()%>)"><%=((AcceptanceTestBean) pageContext.getAttribute("req")).getAtNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="atNumber" titleKey="message.acceptancetest.atNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="testDate" titleKey="message.acceptancetest.testDate" sortable="true"/>
    <display:column property="usedOrg" titleKey="message.acceptancetest.usedOrg" sortable="true"/>
</display:table>