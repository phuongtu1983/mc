<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.DmvMaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<display:table style="width:100%" name="<%=Constants.MATERIAL_LIST%>" id="dmvMaterial" class="its" decorator="com.venus.mc.process.store.dmv.decorator.DmvMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <!-- display:column property="delId" titleKey="message.del"/ -->
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="unit" titleKey="message.material.unit"/>
    <display:column property="quantity" titleKey="message.material.quantity"/>
    <display:column property="price" titleKey="message.material.price"/>    
</display:table>