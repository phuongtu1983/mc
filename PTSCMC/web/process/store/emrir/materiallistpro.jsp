<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.bean.MrirDetailBean"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<display:table style="width:1600px;" name="<%=Constants.MATERIAL_LIST%>" id="emrirMaterial" class="its" decorator="com.venus.mc.process.store.emrir.decorator.EmrirMaterialDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:setProperty name="css.tr.odd" value ="" />
    <display:setProperty name="css.tr.even" value ="" /> 
    <display:column style="width:10px;" property="delId" titleKey="message.del"/>    
    <display:column style="width:53px;" property="systemNo" titleKey="message.mrirdetail.systemno"/> 
    <display:column style="width:53px;" property="itemNo" titleKey="message.mrirdetail.itemno"/> 
    <display:column property="matName" titleKey="message.mrirdetail.description"/>             
    <display:column style="width:53px;" property="materialGrade" titleKey="message.mrirdetail.materialgrade"/>        
    <display:column style="width:53px;" property="materialType" titleKey="message.mrirdetail.materialtype"/>
    <display:column style="width:53px;" property="rating" titleKey="message.mrirdetail.rating"/>
    <display:column style="width:53px;" property="size1" titleKey="message.mrirdetail.size1"/>
    <display:column style="width:53px;" property="size2" titleKey="message.mrirdetail.size2"/>
    <display:column style="width:53px;" property="size3" titleKey="message.mrirdetail.size3"/>    
    <display:column style="width:53px;" property="unit" titleKey="message.mrirdetail.unit.en"/>        
    <display:column style="width:53px;" property="quantity" titleKey="message.mrirdetail.quantity.en"/>
    <display:column style="width:53px;" property="heatSerial" titleKey="message.mrirdetail.heatSerial"/>
    <display:column style="width:53px;" property="traceNo" titleKey="message.mrirdetail.traceno"/>
    <display:column style="width:53px;" property="certNo" titleKey="message.mrirdetail.certno"/>
    <display:column style="width:53px;" property="colourCode" titleKey="message.mrirdetail.colourcode"/>
    <display:column style="width:124px;" property="location" titleKey="message.mrirdetail.location"/>
    <display:column style="width:222px;" property="comment" titleKey="message.mrirdetail.comment"/>    
    <display:column style="width:157px;" property="matKind" titleKey="message.mrirdetail.matkind"/>     
    </display:table>
