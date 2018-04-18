<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MrirDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<display:table style="width:100%" name="<%=Constants.MATERIAL_LIST%>" id="osDMaterial" class="its" decorator="com.venus.mc.process.store.mrir.decorator.OsDMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:column property="delId" titleKey="message.del"/>
    <display:column property="reqNumber" titleKey="message.mrir.reqId"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>    
    <display:column property="unit" titleKey="message.material.unit"/>
    <display:column paramId="matNameTemp{osDMaterial.detId}" property="matNameTemp" titleKey="message.material.nameTemp"/>    
    <display:column property="unitTemp" titleKey="message.material.unitTemp"/>
    <display:column property="quantity" titleKey="message.material.quantity"/>
    <display:column title="A'">
        <img src="images/ico_them.gif" onclick="return selectMaterialOsD('setSelectedMaterialOsD','<bean:message key="message.osdadd.title"/>/<bean:message key="message.material.add"/>',${osDMaterial.detId},${osDMaterial.osdId},${osDMaterial.matIdTemp});"/>
    </display:column>
    <display:column title="A'">
        <img src="images/ico_themvt.gif" onclick="return newMaterialOsD('setSelectedMaterialOsD',null,'<bean:message key="message.osdadd.title"/>/<bean:message key="message.material.add"/>',${osDMaterial.detId},${osDMaterial.osdId},${osDMaterial.matIdTemp});"/>
    </display:column>
</display:table>
        