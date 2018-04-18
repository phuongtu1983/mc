<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmcoBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmcoListSort({})" name="<%=Constants.EMCO_LIST%>" id="emco" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_emco'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_emco'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="emcoId" value="<%=((EmcoBean) pageContext.getAttribute("emco")).getEmcoId()%>">
    </display:column>
    <display:column property="department" titleKey="message.emco.department" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_EMCO)) {%>
    <display:column titleKey="message.emco.emcoNumber" sortable="true">
        <a href="#" onclick="emcoForm(<%=((EmcoBean) pageContext.getAttribute("emco")).getEmcoId()%>)"><%=((EmcoBean) pageContext.getAttribute("emco")).getEmcoNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="emcoNumber" titleKey="message.emco.emcoNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="carryOutDate" titleKey="message.emco.carryOutDate" sortable="true" headerClass="sortable"/>
</display:table>