<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.EquipmentBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="15" requestURI="javascript:searchEquipmentSr({})" name="<%=Constants.EQUIPMENT_LIST%>" id="material" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="equId" value="<%=((EquipmentBean)pageContext.getAttribute("material")).getEquId()%>">
            <input type="hidden" name="matCodeHidden" value="<%=((EquipmentBean)pageContext.getAttribute("material")).getUsedCode()%>">
            <input type="hidden" name="matNameHidden" value="<%=((EquipmentBean)pageContext.getAttribute("material")).getEquipmentName()%>">
        </div>
    </display:column>
    <display:column property="usedCode" titleKey="message.requirerepair.usedCode"/>
    <display:column property="equipmentName" titleKey="message.requirerepair.equId"/>
</display:table>
