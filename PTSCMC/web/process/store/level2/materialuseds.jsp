<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.UsedMaterialDiaryBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialStoreUsedList({})" name="<%=Constants.STORE_LIST%>" id="store" class="its" defaultsort="1">
    <display:setProperty name="paging.banner.items_name"><bean:message key="message.materialused"/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key="message.materialused"/></display:setProperty>
    <display:column titleKey="message.del">
        <logic:equal name="store" property="canNotDelete" value="0">
            <input type="checkbox" name="umsId" value="<%=((UsedMaterialDiaryBean)pageContext.getAttribute("store")).getUmsId()%>">
        </logic:equal>
    </display:column>
    <display:column titleKey="message.materialused.number" sortable="true">
        <a href="#" onclick="materialUsedForm(<%=((UsedMaterialDiaryBean)pageContext.getAttribute("store")).getUmsId()%>)"><%=((UsedMaterialDiaryBean)pageContext.getAttribute("store")).getUsmNumber()%></a>
    </display:column>
    <display:column titleKey="message.materialused.date" sortable="true" headerClass="sortable" property="updateDate"/>
    <display:column titleKey="message.miv.project" sortable="true" headerClass="sortable" property="projectName"/>
    <display:column titleKey="message.employee.orgName" sortable="true" headerClass="sortable" property="orgName"/>
    <display:column titleKey="message.umsdetail.employee" sortable="true" headerClass="sortable" property="employeeName"/>
</display:table>