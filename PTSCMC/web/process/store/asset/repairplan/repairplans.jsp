<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RepairPlanBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadAssetRepairPlanList({})" name="<%=Constants.REPAIRPLAN_LIST%>" id="repairplan" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.repairplan'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.repairplan'/></display:setProperty>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="rpId" value="<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>"></div>
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.repairplan.timeRepair" sortable="true" headerClass="sortable">
        <a href="#" name="rpId" onclick="addAssetRepairPlan(<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>)"><%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getTimeRepair()%></a>
    </display:column>
    <display:column titleKey="message.repairplan.timeTrue" sortable="true" headerClass="sortable">
        <a href="#" name="rpId" onclick="addAssetRepairPlan(<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>)"><%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getTimeTrue()%></a>
    </display:column> 
    <display:column titleKey="message.repairplan.repairPart" sortable="true" headerClass="sortable">
        <a href="#" name="rpId" onclick="addAssetRepairPlan(<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>)"><%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRepairPart()%></a>
    </display:column>     
    <display:column titleKey="message.repairplan.cost" sortable="true" headerClass="sortable">
        <a href="#" name="rpId" onclick="addAssetRepairPlan(<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>)"><%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getCost()%></a>
    </display:column> 
    <display:column titleKey="message.repairplan.repairKind" sortable="true" headerClass="sortable">
        <a href="#" name="rpId" onclick="addAssetRepairPlan(<%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRpId()%>)"><%=((RepairPlanBean)pageContext.getAttribute("repairplan")).getRepairKind()%></a>
    </display:column>
    <%}else{%>
    <display:column property="timeRepair" titleKey="message.repairplan.timeRepair" sortable="true" headerClass="sortable"/>	
    <display:column property="timeTrue" titleKey="message.repairplan.timeTrue" sortable="true" headerClass="sortable"/>
    <display:column property="repairPart" titleKey="message.repairplan.repairPart" sortable="true" headerClass="sortable"/>
    <display:column property="cost" titleKey="message.repairplan.cost" sortable="true" headerClass="sortable"/>
    <display:column property="repairKind" titleKey="message.repairplan.repairKind" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>

