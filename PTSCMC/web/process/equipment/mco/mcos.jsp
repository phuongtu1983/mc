<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.McoBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMcoListSort({})" name="<%=Constants.MCO_LIST%>" id="mco" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_mco'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_mco'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="mcoId" value="<%=((McoBean) pageContext.getAttribute("mco")).getMcoId()%>">
    </display:column>
    <display:column property="orgName" titleKey="message.mco.orgId" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_MCO)) {%>
    <display:column titleKey="message.mco.mcoNumber" sortable="true">
        <a href="#" onclick="mcoForm(<%=((McoBean) pageContext.getAttribute("mco")).getMcoId()%>)"><%=((McoBean) pageContext.getAttribute("mco")).getMcoNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="mcoNumber" titleKey="message.mco.mcoNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="kindString" titleKey="message.mco.kind" sortable="true" headerClass="sortable"/>    
    <display:column property="carryOutDate" titleKey="message.mco.carryOutDate" sortable="true" headerClass="sortable"/>
</display:table>

