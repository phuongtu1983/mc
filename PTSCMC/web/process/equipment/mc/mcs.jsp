<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.McBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMcListSort({})" name="<%=Constants.MC_LIST%>" id="mc" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_mc'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_mc'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="mcId" value="<%=((McBean) pageContext.getAttribute("mc")).getMcId()%>">
    </display:column>
    <display:column property="orgName" titleKey="message.mc.orgId" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_MC)) {%>
    <display:column titleKey="message.mc.mcNumber" sortable="true">
        <a href="#" onclick="mcForm(<%=((McBean) pageContext.getAttribute("mc")).getMcId()%>)"><%=((McBean) pageContext.getAttribute("mc")).getMcNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="mcNumber" titleKey="message.mc.mcNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="kindString" titleKey="message.mc.kind" sortable="true" headerClass="sortable"/>    
    <display:column property="carryOnDate" titleKey="message.mc.carryOnDate" sortable="true" headerClass="sortable"/>
</display:table>
