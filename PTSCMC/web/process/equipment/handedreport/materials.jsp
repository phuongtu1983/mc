<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib prefix="display" uri="/tags/displaytag"%>

<display:table htmlId="handedReportMaterialTable" style="width:100%" name="<%=Constants.EQUIPMENT_LIST%>" id="detail" class="its" decorator="com.venus.mc.process.equipment.handedreport.decorator.HandedReportEquimentDecoratorEx">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:setProperty name="basic.msg.empty_list"  value="" />        
    <display:setProperty name="basic.msg.empty_list_row"  value="" />     
    <display:setProperty name="css.tr.even"  value="" />    
    <display:setProperty name="css.tr.odd"  value="" /> 
    <display:column property="delId" titleKey="message.del"/>
    <display:column property="useCode" titleKey="message.requiretransfer.usedCode"/>
    <display:column property="equipmentName" titleKey="message.requiretransfer.equId"/>     
    <display:column property="unit" titleKey="message.requiretransfer.unit"/>
    <display:column property="quantity" titleKey="message.requiretransfer.quantity"/>       
    <display:column property="specifications" titleKey="message.handedreport.specifications"/>           
    <display:column property="comment" titleKey="message.handedreport.comment"/>           
</display:table>