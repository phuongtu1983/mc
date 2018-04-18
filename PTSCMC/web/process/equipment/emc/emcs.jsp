<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmcBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmcListSort({})" name="<%=Constants.EMC_LIST%>" id="emc" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_emc'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_emc'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="emcId" value="<%=((EmcBean) pageContext.getAttribute("emc")).getEmcId()%>">
    </display:column>
    <display:column property="department" titleKey="message.emc.department" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_EMC)) {%>
    <display:column titleKey="message.emc.emcNumber" sortable="true">
        <a href="#" onclick="emcForm(<%=((EmcBean) pageContext.getAttribute("emc")).getEmcId()%>)"><%=((EmcBean) pageContext.getAttribute("emc")).getEmcNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="emcNumber" titleKey="message.emc.emcNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="carryOnDate" titleKey="message.emc.carryOnDate" sortable="true" headerClass="sortable"/>
</display:table>