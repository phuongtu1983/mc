<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RepairNotPlanBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadRepairNotPlanListSort({})" name="<%=Constants.REPAIRNOTPLAN_LIST%>" id="repairnotplan" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.repairnotplan'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.repairnotplan'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="rnpId" value="<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>"></div>
    </display:column>        
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.repairnotplan.rrNumber" sortable="true" headerClass="sortable">
        <a href="#" name="rnpId" onclick="addRepairNotPlan(<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>)"><%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRrNumber()%></a>
    </display:column>
    <display:column titleKey="message.repairnotplan.rdNumber" sortable="true" headerClass="sortable">
        <a href="#" name="rnpId" onclick="addRepairNotPlan(<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>)"><%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRdNumber()%></a>
    </display:column>
    <display:column titleKey="message.repairnotplan.srNumber" sortable="true" headerClass="sortable">
        <a href="#" name="rnpId" onclick="addRepairNotPlan(<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>)"><%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getSrNumber()%></a>
    </display:column>
    <display:column titleKey="message.repairnotplan.atNumber" sortable="true" headerClass="sortable">
        <a href="#" name="rnpId" onclick="addRepairNotPlan(<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>)"><%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getAtNumber()%></a>
    </display:column>
    <display:column titleKey="message.repairnotplan.cost" sortable="true" headerClass="sortable">
        <a href="#" name="rnpId" onclick="addRepairNotPlan(<%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getRnpId()%>)"><%=((RepairNotPlanBean)pageContext.getAttribute("repairnotplan")).getCost()%></a>
    </display:column>
    <%}else{%>
    <display:column property="rrNumber" titleKey="message.repairnotplan.rrNumber" sortable="true" headerClass="sortable"/>	
    <display:column property="rdNumber" titleKey="message.repairnotplan.rdNumber" sortable="true" headerClass="sortable"/>	
    <display:column property="srNumber" titleKey="message.repairnotplan.srNumber" sortable="true" headerClass="sortable"/>	
    <display:column property="atNumber" titleKey="message.repairnotplan.atNumber" sortable="true" headerClass="sortable"/>	
    <display:column property="cost" titleKey="message.repairnotplan.cost" sortable="true" headerClass="sortable"/>	
    <%}%>

</display:table>

