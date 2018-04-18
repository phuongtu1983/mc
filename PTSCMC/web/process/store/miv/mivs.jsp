<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MivBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMivListSort({})" name="<%=Constants.MIV_LIST%>" id="miv" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_miv'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_miv'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="mivId" value="<%=((MivBean)pageContext.getAttribute("miv")).getMivId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STOCK_MIV)) {%>
    <display:column titleKey="message.miv.number" sortable="true" headerClass="sortable">
        <a href="#" onclick="mivForm(<%=((MivBean)pageContext.getAttribute("miv")).getMivId()%>)"><%=((MivBean)pageContext.getAttribute("miv")).getMivNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="mivNumber" titleKey="message.miv.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.miv.createdDate" sortable="true"/>
</display:table>