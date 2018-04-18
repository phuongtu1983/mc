<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.HandedReportBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadHandedReportListSort({})" name="<%=Constants.HANDEDREPORT_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name" value=""/>
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="hrId" value="<%=((HandedReportBean) pageContext.getAttribute("req")).getHrId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT_REPAIRREQUEST)) {%>
    <display:column titleKey="message.handedreport.number">
        <a href="#" onclick="handedReportForm(<%=((HandedReportBean) pageContext.getAttribute("req")).getHrId()%>)"><%=((HandedReportBean) pageContext.getAttribute("req")).getHrNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="hrNumber" titleKey="message.handedreport.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.handedreport.createddate"/>
    <display:column property="orgName" titleKey="message.handedreport.orgdelivery"/>
    <display:column property="orgReceiveName" titleKey="message.handedreport.orgreceive"/>
</display:table>