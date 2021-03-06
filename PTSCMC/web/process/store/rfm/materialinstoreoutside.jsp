<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RfmDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialInStore1({})" name="<%=Constants.MATERIAL_LIST%>" id="mat" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty>
    <display:column titleKey="message.empty">
        <input type="checkbox" name="matId" value="<%=((RfmDetailBean) pageContext.getAttribute("mat")).getMatId()%>">
        <input type="hidden" name="matCodeHidden" value="<%=((RfmDetailBean)pageContext.getAttribute("mat")).getMatCode()%>">
        <input type="hidden" name="matNameHidden" value="<%=((RfmDetailBean)pageContext.getAttribute("mat")).getMatName()%>">
        <input type="hidden" name="matQuantityHidden" value="<%=((RfmDetailBean)pageContext.getAttribute("mat")).getQuantity()%>">        
        <input type="hidden" name="storeName" value="<%=((RfmDetailBean)pageContext.getAttribute("mat")).getStoreName()%>">
        <input type="hidden" name="stoId" value="<%=((RfmDetailBean)pageContext.getAttribute("mat")).getStoId()%>">
    </display:column>    
    <display:column property="matCode" titleKey="message.material.code" sortable="true"/>
    <display:column property="matName" titleKey="message.material.nameVn" sortable="true"/>
    <display:column property="unit" titleKey="message.material.uniId" sortable="true"/>    
    <display:column property="reserveQuantity" titleKey="message.rfm.store.reserveQuantity" sortable="true"/>
    <display:column property="availableQuantity" titleKey="message.rfm.store.availableQuantity" sortable="true"/>
    <display:column property="actualQuantity" titleKey="message.rfm.store.actualQuantity" sortable="true"/>    
    <display:column property="storeName" titleKey="message.rfm.storeName" sortable="true"/>
</display:table>