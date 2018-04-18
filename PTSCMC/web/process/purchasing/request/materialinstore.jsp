<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.RfmDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" pagesize="15" requestURI="javascript:loadMaterialInStore({})" name="<%=Constants.MATERIAL_LIST%>" id="mat" class="its" >
    <display:setProperty name="paging.banner.items_name"><bean:message key='message.material'/></display:setProperty>
    <display:setProperty name="paging.banner.item_name"><bean:message key='message.material'/></display:setProperty> 
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="unit" titleKey="message.material.uniId"/>    
    <display:column property="quantity" titleKey="message.request.material.additionalQuantity"/>
    <display:column property="actualQuantity" titleKey="message.rfm.store.actualQuantity"/>
    <display:column property="availableQuantity" titleKey="message.rfm.store.availableQuantity"/>
    <display:column property="reserveQuantity" titleKey="message.rfm.store.reserveQuantity"/>
    <display:column property="storeName" titleKey="message.rfm.storeName"/>
</display:table>