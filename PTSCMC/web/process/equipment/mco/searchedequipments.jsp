<%-- 
    Document   : searchedequipments
    Created on : Nov 7, 2009, 10:23:18 PM
    Author     : kngo
--%>

<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.venus.mc.bean.McoDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<display:table style="width:100%;" pagesize="8" requestURI="javascript:searchMcoEquipment({})" name="<%=Constants.EQUIPMENT_LIST%>" id="equipment" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.equipment'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.equipment'/></display:setProperty>
    <display:column titleKey="message.choose">
        <div align="center">
            <input type="checkbox" name="equId" value="<%=((McoDetailBean)pageContext.getAttribute("equipment")).getEquId()%>">
            <input type="hidden" name="nameVnHidden" value="<%=((McoDetailBean)pageContext.getAttribute("equipment")).getNameVn()%>">
            <input type="hidden" name="usedCodeHidden" value="<%=((McoDetailBean)pageContext.getAttribute("equipment")).getUsedCode()%>">
        </div>
    </display:column>
    <display:column property="usedCode" titleKey="message.equipment.usedCode"/>
    <display:column property="nameVn" titleKey="message.equipment.nameVn"/>
</display:table>
