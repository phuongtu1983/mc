<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MsvBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadDmvListSort({})" name="<%=Constants.DMV_LIST%>" id="dmv" class="its" >
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name" value="" />
    <display:column titleKey="message.del">
        <input type="checkbox" name="msvId" value="<%=((MsvBean)pageContext.getAttribute("dmv")).getMsvId()%>">
    </display:column>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.msv.number">
        <a href="#" onclick="dmvForm(<%=((MsvBean)pageContext.getAttribute("dmv")).getMsvId()%>)"><%=((MsvBean)pageContext.getAttribute("dmv")).getMsvNumber()%></a>
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
