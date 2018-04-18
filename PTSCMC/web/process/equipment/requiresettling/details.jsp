<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="requireSettlingTbl" class="its" style="width:100%" >
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.requiresettling.workPlan"/></th>
            <th><bean:message key="message.requiresettling.contentWork"/></th>
            <th><bean:message key="message.requiresettling.location"/></th>
            <th><bean:message key="message.requiresettling.quantity"/></th>
            <th><bean:message key="message.requiresettling.startTimePlan"/></th>
            <th><bean:message key="message.requiresettling.endTimePlan"/></th>
            <th><bean:message key="message.requiresettling.startTimeReality"/></th>
            <th><bean:message key="message.requiresettling.endTimeReality"/></th>
            <th><bean:message key="message.requiresettling.explanation"/></th>
            <th><bean:message key="message.requiresettling.comment"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="requireSettling" name="<%=Constants.REQUIRESETTLING_DETAIL_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="rsChk" value="<bean:write name="requireSettling" property="detId"/>"/>
                    <input type="hidden" name="reqDetId" value="<bean:write name="requireSettling" property="detId"/>"/>
                    <input type="hidden" name="delRsId" value="${requireSettling.rsId}"/>
                </td>
                <td><input type="textbox" size="12" name="workPlan" value="${requireSettling.workPlan}" /></td>
                <td><input type="textbox" size="25" name="contentWork" value="${requireSettling.contentWork}" /></td>
                <td><input type="textbox" size="15" name="location" value="${requireSettling.location}" /></td>
                <td><input type="textbox" size="10" name="quantity" value="${requireSettling.quantity}" /></td>
                <td><input type="textbox" size="10" name="startTimePlan" value="${requireSettling.startTimePlan}" id="startTimePlan_${requireSettling.rsId}" onclick="javascript:showCalendar('startTimePlan_${requireSettling.rsId}')"/></td>
                <td><input type="textbox" size="10" name="endTimePlan" value="${requireSettling.endTimePlan}" id="endTimePlan_${requireSettling.rsId}" onclick="javascript:showCalendar('endTimePlan_${requireSettling.rsId}')"/></td>
                <td><input type="textbox" size="10" name="startTimeReality" value="${requireSettling.startTimeReality}" id="startTimeReality_${requireSettling.rsId}" onclick="javascript:showCalendar('startTimeReality_${requireSettling.rsId}')"/></td>
                <td><input type="textbox" size="10" name="endTimeReality" value="${requireSettling.endTimeReality}" id="endTimeReality_${requireSettling.rsId}" onclick="javascript:showCalendar('endTimeReality_${requireSettling.rsId}')"/></td>
                <td><input type="textbox" size="25" name="explanation" value="${requireSettling.explanation}" /></td>
                <td><input type="textbox" size="30" name="comment" value="${requireSettling.comment}" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>