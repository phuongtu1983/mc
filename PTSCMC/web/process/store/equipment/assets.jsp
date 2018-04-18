<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EquipmentBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEquipmentList2({})" name="<%=Constants.EQUIPMENT_LIST%>" id="equipment" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.asset'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.asset'/></display:setProperty>
    <%--
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="equId" value="<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>"></div>
        </display:column>
        <display:column titleKey="message.equipment.usedCode" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getUsedCode()%></a>
    </display:column>
    --%>
    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_VIEW, PermissionUtil.PER_STORE)) {%>
    <display:column titleKey="message.asset.assetName" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquipmentName()%></a>
    </display:column> 
    <display:column titleKey="message.equipment.mivId" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getMivNumber()%></a>
    </display:column>     
    <display:column titleKey="message.equipment.status0" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getStatusName()%></a>
    </display:column> 
    <display:column titleKey="message.equipment.usedDate" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getUsedDate()%></a>
    </display:column>
    <%}else{%>
    <display:column property="assetName" titleKey="message.equipment.assetName" sortable="true" headerClass="sortable"/>	
    <display:column property="mivId" titleKey="message.equipment.mivId" sortable="true" headerClass="sortable"/>
    <display:column property="status0" titleKey="message.equipment.status0" sortable="true" headerClass="sortable"/>
    <display:column property="usedDate" titleKey="message.equipment.usedDate" sortable="true" headerClass="sortable"/>
    <%}%>
</display:table>

