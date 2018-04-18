<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MsvMaterialBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<display:table style="width:100%" name="<%=Constants.MATERIAL_LIST%>" id="emsvMaterial" class="its" decorator="com.venus.mc.process.store.emsv.decorator.EmsvMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:column property="matName" titleKey="message.material.nameVn"/>
    <display:column property="matCode" titleKey="message.material.code"/>
    <display:column property="unit" titleKey="message.material.unit"/>
    <display:column property="quantity" titleKey="message.material.quantity"/>
    <display:column property="price" titleKey="message.material.price"/>
    
</display:table>