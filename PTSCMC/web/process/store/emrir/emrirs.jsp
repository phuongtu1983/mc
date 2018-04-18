<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EmrirBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEmrirListSort({})" name="<%=Constants.EMRIR_LIST%>" id="emrir" class="its"  >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.l_emrir'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.l_emrir'/></display:setProperty>
    <display:column titleKey="message.del">
        <input type="checkbox" name="emrirId" value="<%=((EmrirBean) pageContext.getAttribute("emrir")).getEmrirId()%>">
    </display:column>
    <display:column property="createdDate" titleKey="message.emrir.createdDate" sortable="true" headerClass="sortable"/>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.emrir.emrirNumber" sortable="true">
        <a href="#" onclick="emrirForm(<%=((EmrirBean) pageContext.getAttribute("emrir")).getEmrirId()%>)"><%=((EmrirBean) pageContext.getAttribute("emrir")).getEmrirNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="emrirNumber" titleKey="message.emrir.emrirNumber" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="ednNumber" titleKey="message.emrir.ednId" sortable="true" headerClass="sortable"/>
    <display:column property="packingListNo" titleKey="message.mrir.packinglistno" sortable="true" headerClass="sortable"/>    
    <display:column property="invoiceNo" titleKey="message.mrir.invoiceNo" sortable="true" headerClass="sortable"/>    
</display:table>



