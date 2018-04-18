<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ taglib prefix="display" uri="/tags/displaytag"%>

<display:table htmlId="requireVerifiedMaterialTable" style="width:100%" name="<%=Constants.EQUIPMENT_LIST%>" id="detail" class="" decorator="com.venus.mc.process.equipment.requireverified.decorator.RequireVerifiedEquimentDecoratorEx">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:setProperty name="css.tr.even"  value="" />    
    <display:setProperty name="css.tr.odd"  value="" />        
    <display:column property="delId" titleKey="message.del"/>
    <display:column property="useCode" titleKey="message.requirerepair.usedCode"/>
    <display:column property="equipmentName" titleKey="message.requirerepair.equId"/>        
    <display:column property="timeEstimate" titleKey="message.requireverified.timeEstimate"/>
    <display:column property="comment" titleKey="message.requireverified.comment"/>
</display:table>
