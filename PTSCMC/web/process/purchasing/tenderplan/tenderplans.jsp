<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.TenderPlanBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadTenderPlanListSort({})" name="<%=Constants.TENDERPLAN_LIST%>" id="tender" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_tenderplan'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_tenderplan'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="tenId" value="<%=((TenderPlanBean)pageContext.getAttribute("tender")).getTenId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_PURCHASING_TENDERPLAN)) {%>
    <% if (((TenderPlanBean) pageContext.getAttribute("tender")) != null)
       if (((TenderPlanBean) pageContext.getAttribute("tender")).getIsNeedHighLight() == 1){ %>
    <display:column style="background-color: red;" titleKey="message.tenderplan.number" sortable="true" headerClass="sortable">
        <a href="#" onclick="tenderPlanForm(<%=((TenderPlanBean)pageContext.getAttribute("tender")).getTenId()%>)"><%=((TenderPlanBean)pageContext.getAttribute("tender")).getTenderNumber()%></a>
    </display:column>
    <% } else { %>
    <display:column style="" titleKey="message.tenderplan.number" sortable="true" headerClass="sortable">
        <a href="#" onclick="tenderPlanForm(<%=((TenderPlanBean)pageContext.getAttribute("tender")).getTenId()%>)"><%=((TenderPlanBean)pageContext.getAttribute("tender")).getTenderNumber()%></a>
    </display:column>
    <% } %>
    <%}else{%>

    <% if (((TenderPlanBean) pageContext.getAttribute("tender")) != null)
        if (((TenderPlanBean) pageContext.getAttribute("tender")).getIsNeedHighLight() == 1){ %>
    <display:column style="background-color: red;" property="name" titleKey="message.vendor.name" sortable="true" headerClass="sortable"/>
    <% } else { %>
    <display:column property="name" titleKey="message.vendor.name" sortable="true" headerClass="sortable"/>
    <% } %>
    <%}%>
    <display:column property="createdDate" titleKey="message.tenderplan.createdDate" sortable="true" comparator="org.displaytag.util.DateComparator"/>
    <display:column property="packName" titleKey="message.tenderplan.packName" sortable="true"/>
    <% if (((TenderPlanBean) pageContext.getAttribute("tender")) != null)
        if (((TenderPlanBean) pageContext.getAttribute("tender")).getColor().equals("red")) {%>
    <display:column style="background-color: red;"  property="bidDeadline" titleKey="message.tenderplan.plan.bidDeadline" comparator="org.displaytag.util.DateComparator"/>
    <%} else if (((TenderPlanBean) pageContext.getAttribute("tender")).getColor().equals("yellow")) {%>
    <display:column style="background-color: yellow;" property="bidDeadline" titleKey="message.tenderplan.plan.bidDeadline" comparator="org.displaytag.util.DateComparator"/>
    <% } else {%>
    <display:column style="background-color: white;" property="bidDeadline" titleKey="message.tenderplan.plan.bidDeadline" comparator="org.displaytag.util.DateComparator"/>
    <%}%>
    <display:column property="handleEmpName" titleKey="message.tenderplan.handleEmployee"/>
    <display:column property="financial" titleKey="message.tenderplan.financial"/>
    <display:column property="status" titleKey="message.tenderplan.status"/>
</display:table>
