<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>

<display:table htmlId="requireVerifiedDetailTable" style="width:100%" name="<%=Constants.REQUIREVERIFIED_DETAIL_LIST%>" id="detail" class="its" decorator="com.venus.mc.process.equipment.requireverified.decorator.RequireVerifiedEquimentDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:setProperty name="basic.msg.empty_list"  value="" />        
    <display:setProperty name="basic.msg.empty_list_row"  value="" />            
    <display:column property="delId" titleKey="message.del"/>
    <display:column property="useCode" titleKey="message.requirerepair.usedCode"/>
    <display:column property="equipmentName" titleKey="message.requirerepair.equId"/>        
    <display:column property="timeEstimate" titleKey="message.requireverified.timeEstimate"/>
    <display:column property="comment" titleKey="message.requireverified.comment"/>
</display:table>
