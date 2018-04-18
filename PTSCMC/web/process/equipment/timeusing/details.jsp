<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag"%>
<%@ page import="com.venus.mc.util.Constants"%>

<display:table htmlId="timeUsingDetailTable" style="width:100%" name="<%=Constants.TIMEUSING_DETAIL_LIST%>" id="detail" class="its" decorator="com.venus.mc.process.equipment.timeusing.decorator.TimeUsingEquimentDecorator">
    <display:setProperty name="paging.banner.items_name" value="" />
    <display:setProperty name="paging.banner.item_name"  value="" />    
    <display:setProperty name="basic.msg.empty_list"  value="" />        
    <display:setProperty name="basic.msg.empty_list_row"  value="" />     
    <display:setProperty name="css.tr.even"  value="" />    
    <display:setProperty name="css.tr.odd"  value="" /> 
    <display:column property="no" titleKey="message.timeusing.no"/>
    <display:column property="equipmentName" titleKey="message.timeusing.equipment"/>
    <display:column property="useCode" titleKey="message.timeusing.usedcode"/>    
    <display:column property="unit" titleKey="message.timeusing.unit"/>
    <display:column property="appearedDate" titleKey="message.timeusing.appdate"/>       
    <display:column property="manageOrgName" titleKey="message.timeusing.useorg"/>           
    <display:column property="nextSchedule" titleKey="message.timeusing.nextschedule"/>
    <display:column property="totalTimeUsed" titleKey="message.timeusing.totaltimeused"/>
    <display:column property="timeUsed" titleKey="message.timeusing.timeused"/>
    <display:column property="timeRemain" titleKey="message.timeusing.timeremain"/>
</display:table>
