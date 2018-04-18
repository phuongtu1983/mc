<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.SurveyReportBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadSurveyReportListSort({})" name="<%=Constants.SURVEYREPORT_LIST%>" id="req" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.surveyreport'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.surveyreport'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="srId" value="<%=((SurveyReportBean) pageContext.getAttribute("req")).getSrId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_EQUIPMENT)) {%>
    <display:column titleKey="message.surveyreport.srNumber" sortable="true">
        <a href="#" onclick="surveyreportForm(<%=((SurveyReportBean) pageContext.getAttribute("req")).getSrId()%>)"><%=((SurveyReportBean) pageContext.getAttribute("req")).getSrNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="srNumber" titleKey="message.surveyreport.srNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="surveyDate" titleKey="message.surveyreport.surveyDate" sortable="true"/>
    <display:column property="usedOrg" titleKey="message.surveyreport.usedOrg" sortable="true"/>
</display:table>