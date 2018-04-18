<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EdmvBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEdmvListSort({})" name="<%=Constants.EDMV_LIST%>" id="edmv" class="its" >
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="edmvId" value="<%=((EdmvBean)pageContext.getAttribute("edmv")).getEdmvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.msv.number">
        <a href="#" onclick="edmvEdnForm(<%=((EdmvBean)pageContext.getAttribute("edmv")).getEdmvId()%>)"><%=((EdmvBean)pageContext.getAttribute("edmv")).getEdmvNumber()%></a>
    </display:column>
    <%}else{%>
    <display:column property="number" titleKey="message.msv.number" sortable="true" headerClass="sortable"/>	
    <%}%>
    <display:column property="createdDate" titleKey="message.msv.createddate" />   
    <display:column property="receiveEmpName" titleKey="message.msv.receiver" />   
    <display:column property="orgName" titleKey="message.msv.oreceiver" />
    <display:column property="dmvOrder" titleKey="message.msv.commandorder" />  
    <display:column property="stoName" titleKey="message.msv.expstore" />       
</display:table>
