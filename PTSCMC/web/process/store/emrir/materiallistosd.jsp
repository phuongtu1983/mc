<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<display:table style="width:100%" name="<%=Constants.MATERIAL_LIST%>" id="eosDMaterial" class="its" decorator="com.venus.mc.process.store.emrir.decorator.EosDMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:column property="delId" titleKey="message.del"/>    
    <display:column property="matName" titleKey="message.material.nameVn"/>    
    <display:column property="unit" titleKey="message.material.unit"/>
    <display:column property="quantity" titleKey="message.material.quantity"/>
</display:table>
    
            