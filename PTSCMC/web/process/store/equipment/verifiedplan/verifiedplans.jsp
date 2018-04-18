<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.VerifiedPlanBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadVerifiedPlanListSort({})" name="<%=Constants.VERIFIEDPLAN_LIST%>" id="verifiedplan" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.verifiedplan'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.verifiedplan'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="vpId" value="<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.verifiedplan.timePlan" sortable="true" headerClass="sortable">
        <a href="#" name="vpId" onclick="addVerifiedPlan(<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>)"><%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getTimePlan()%></a>
    </display:column>
    <display:column titleKey="message.verifiedplan.timeNext" sortable="true" headerClass="sortable">
        <a href="#" name="vpId" onclick="addVerifiedPlan(<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>)"><%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getTimeNext()%></a>
    </display:column> 
    <display:column titleKey="message.verifiedplan.content" sortable="true" headerClass="sortable">
        <a href="#" name="vpId" onclick="addVerifiedPlan(<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>)"><%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getContent()%></a>
    </display:column>     
    <display:column titleKey="message.verifiedplan.cost" sortable="true" headerClass="sortable">
        <a href="#" name="vpId" onclick="addVerifiedPlan(<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>)"><%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getCost()%></a>
    </display:column> 
    <display:column titleKey="message.verifiedplan.status" sortable="true" headerClass="sortable">
        <a href="#" name="vpId" onclick="addVerifiedPlan(<%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getVpId()%>)"><%=((VerifiedPlanBean)pageContext.getAttribute("verifiedplan")).getStatusName()%></a>
    </display:column>
    <%}else{%>
    <display:column property="timePlan" titleKey="message.verifiedplan.timePlan" sortable="true" headerClass="sortable"/>	
    <display:column property="timeNext" titleKey="message.verifiedplan.timeNext" sortable="true" headerClass="sortable"/>	
    <display:column property="content" titleKey="message.verifiedplan.content" sortable="true" headerClass="sortable"/>	
    <display:column property="cost" titleKey="message.verifiedplan.cost" sortable="true" headerClass="sortable"/>	
    <display:column property="status" titleKey="message.verifiedplan.status" sortable="true" headerClass="sortable"/>	
    <%}%>
</display:table>

