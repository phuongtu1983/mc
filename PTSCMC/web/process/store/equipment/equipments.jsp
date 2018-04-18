<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.EquipmentBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadEquipmentListSort({})" name="<%=Constants.EQUIPMENT_LIST%>" id="equipment" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.equipment'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.equipment'/></display:setProperty>
    <%--
    <display:column titleKey="message.del">
        <div align="center"><input type="checkbox" name="equId" value="<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>"></div>
        </display:column>
        <display:column titleKey="message.equipment.usedCode" sortable="true" headerClass="sortable">
        <a href="#" name="equId" onclick="addEquipment(<%=((EquipmentBean)pageContext.getAttribute("equipment")).getEquId()%>)"><%=((EquipmentBean)pageContext.getAttribute("equipment")).getUsedCode()%></a>
    </display:column>
    --%>
    <display:column titleKey="message.equipment.equipmentName" sortable="true" headerClass="sortable">
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
</display:table>

