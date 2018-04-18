<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.ReportDamageBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadReportDamageListSort({})" name="<%=Constants.REPORTDAMAGE_LIST%>" id="reportdamage" class="its" >
    <display:setProperty name="paging.banner.items_name" value='BBSV'/>
    <display:setProperty name="paging.banner.item_name" value="BBSV"/>
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="rdId" value="<%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getRdId()%>"></div>
        </display:column>
        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_LIBRARY_VENDOR)) {%>
        <display:column titleKey="message.reportdamage.rdNumber" sortable="true" headerClass="sortable">
        <a href="#" name="rdId" onclick="addReportDamage(<%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getRdId()%>)"><%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getRdNumber()%></a>
    </display:column>
    <display:column titleKey="message.reportdamage.equId" sortable="true" headerClass="sortable">
        <a href="#" name="rdId" onclick="addReportDamage(<%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getRdId()%>)"><%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getEquName()%></a>
    </display:column> 
    <display:column titleKey="message.reportdamage.usedOrg" sortable="true" headerClass="sortable">
        <a href="#" name="rdId" onclick="addReportDamage(<%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getRdId()%>)"><%=((ReportDamageBean)pageContext.getAttribute("reportdamage")).getUsedOrg()%></a>
    </display:column> 
    <%}else{%>
    <display:column property="rdNumber" titleKey="message.reportdamage.rdNumber" sortable="true" headerClass="sortable"/>	
    <display:column property="equId" titleKey="message.reportdamage.equId" sortable="true" headerClass="sortable"/>
    <display:column property="usedOrg" titleKey="message.reportdamage.usedOrg" sortable="true" headerClass="sortable"/>
    <%}%>

</display:table>

