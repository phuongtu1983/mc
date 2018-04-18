<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.bean.ReturnedMaterialDiaryBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialStoreUsedList({})" name="<%=Constants.STORE_LIST%>" id="store" class="its" defaultsort="1">
    <display:setProperty name="paging.banner.items_name"><bean:message key="message.materialused"/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key="message.materialused"/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="rmsId" value="<%=((ReturnedMaterialDiaryBean)pageContext.getAttribute("store")).getRmsId()%>">
    </display:column>
    <display:column titleKey="message.materialreturned.number" sortable="true">
        <a href="#" onclick="materialReturnedForm(<%=((ReturnedMaterialDiaryBean)pageContext.getAttribute("store")).getRmsId()%>)"><%=((ReturnedMaterialDiaryBean)pageContext.getAttribute("store")).getRsmNumber()%></a>
    </display:column>
    <display:column titleKey="message.materialused.date" sortable="true" headerClass="sortable" property="updateDate"/>
    <display:column titleKey="message.miv.project" sortable="true" headerClass="sortable" property="projectName"/>
    <display:column titleKey="message.employee.orgName" sortable="true" headerClass="sortable" property="orgName"/>
    <display:column titleKey="message.umsdetail.employee" sortable="true" headerClass="sortable" property="employeeName"/>
</display:table>