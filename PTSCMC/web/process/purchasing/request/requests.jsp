<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RequestBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRequestListSort({})" name="<%=Constants.REQUEST_LIST%>" id="req" class="its">
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_request'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_request'/></display:setProperty>
    <display:column titleKey="message.del">
        <% if (((RequestBean) pageContext.getAttribute("req")) != null)
                 if (((RequestBean) pageContext.getAttribute("req")).getCanDelete() == true) {%>
        <input type="checkbox" name="reqId" value="<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>">
        <%}else{%>
        <input type="checkbox" disabled name="reqId" value="<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>">
        <%}%>
    </display:column>
    <display:column property="bomAgreeDate" titleKey="message.request.bomAgreeDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <% if (((RequestBean) pageContext.getAttribute("req")) != null)
                    if (((RequestBean) pageContext.getAttribute("req")).getHighlight() == 1) {%>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
    <display:column titleKey="message.request.number" sortable="true">
        <div style="width: 100%; height: 100%;background-color: yellow;">
            <a href="#" onclick="requestForm(<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>)"><%=((RequestBean) pageContext.getAttribute("req")).getRequestNumber()%></a>
        </div>
    </display:column>
    <%}else{%>
    <display:column property="requestNumber" titleKey="message.request.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <% } else if (((RequestBean) pageContext.getAttribute("req")).getHighlight() == 0) {%>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_REQUEST)) {%>
    <display:column titleKey="message.request.number" sortable="true">
        <a href="#" onclick="requestForm(<%=((RequestBean) pageContext.getAttribute("req")).getReqId()%>)"><%=((RequestBean) pageContext.getAttribute("req")).getRequestNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="requestNumber" titleKey="message.request.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <% }%>
    <display:column property="createdDate" titleKey="message.request.createdDate" sortable="true" headerClass="sortable" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="whichUseName" titleKey="message.request.whichUse" sortable="true"/>
    <display:column property="createdOrganizationName" titleKey="message.request.organization"/>
    <display:column property="ros" titleKey="message.request.ros"/>
    <display:column property="employeeName" titleKey="message.request.assignedEmp" sortable="true" headerClass="sortable"/>
    <display:column property="statusText" titleKey="message.status"/>
</display:table>