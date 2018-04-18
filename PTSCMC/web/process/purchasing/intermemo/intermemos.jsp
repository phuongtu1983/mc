<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequestBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadIntermemoListSort({})" name="<%=Constants.REQUEST_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.intermemo'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.intermemo'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="reqId" value="<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_INTERMEMO)) {%>
    <display:column titleKey="message.request.number" sortable="true">
        <a href="#" onclick="intermemoForm(<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>)"><%=((RequestBean) pageContext.getAttribute("req")).getRequestNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="requestNumber" titleKey="message.request.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="employeeName" titleKey="message.request.createdEmp" sortable="true"/>
    <display:column property="statusName" titleKey="message.request.statusSuggest" sortable="true"/>
    <display:column property="intermemoSubject" titleKey="message.intermemo.title"/>
</display:table>