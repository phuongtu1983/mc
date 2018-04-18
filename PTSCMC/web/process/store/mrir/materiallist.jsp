<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MrirDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<display:table style="width:1100px;" name="<%=Constants.MATERIAL_LIST%>" id="mrirMaterial" class="its" decorator="com.venus.mc.process.store.mrir.decorator.MrirMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    
    <display:column property="delId" titleKey="message.del"/>
    <display:column property="reqNumber" titleKey="message.mrir.reqId"/>
    <display:column property="matName" titleKey="message.material.nameVn"/>        
    <display:column property="timeUse" titleKey="message.mrirdetail.timeusing"/>
    <display:column property="unit" titleKey="message.material.unit"/>
    <display:column property="quantity" titleKey="message.material.quantity"/>
    <display:column property="manufacture" titleKey="message.mrirdetail.manufacture"/>
    <display:column property="heatSerial" titleKey="message.mrirdetail.heatSerial***" headerClass="" />
    <display:column property="inspection" titleKey="message.mrirdetail.inspection***"/>
    <display:column property="original" titleKey="message.mrirdetail.original***"/>
    <display:column property="quality" titleKey="message.mrirdetail.quality***"/>
    <display:column property="warranty" titleKey="message.mrirdetail.warranty***"/>
    <display:column property="insurance" titleKey="message.mrirdetail.insurance***"/>
    <display:column property="approvalType" titleKey="message.mrirdetail.approvalType***"/>  
    <display:column property="complCert" titleKey="message.mrirdetail.complCert***"/>    
    <display:column property="location" titleKey="message.mrirdetail.location***"/>
    </display:table>
