<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="dnInfoTbl" class="its" style="width:100%" >
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.dn.moveCreateMrir"/></th>
            <th><bean:message key="message.dn.receiveMrir"/></th>
            <th><bean:message key="message.dn.moveCreateMsv"/></th>
            <th><bean:message key="message.dn.receiveMsv"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="dnInfo" name="<%=Constants.DN_INFO_LIST%>">
            <tr>
                <td>
                    <input type="checkbox" name="dnInfoChk" value="<bean:write name="dnInfo" property="dniId"/>"/>
                    <input type="hidden" name="delDniId" value="${dnInfo.dniId}"/>
                </td>
                <td><input type="textbox" size="30" name="moveCreateMrir" value="${dnInfo.moveCreateMrir}" id="moveCreateMrir${dnInfo.dniId}_${dnInfo.dnId}" onclick="javascript:showCalendar('moveCreateMrir${dnInfo.dniId}_${dnInfo.dnId}')"/></td>
                <td><input type="textbox" size="30" name="receiveMrir" value="${dnInfo.receiveMrir}" id="receiveMrir${dnInfo.receiveMrir}_${dnInfo.dnId}" onclick="javascript:showCalendar('receiveMrir${dnInfo.receiveMrir}_${dnInfo.dnId}')"/></td>
                <td><input type="textbox" size="30" name="moveCreateMsv" value="${dnInfo.moveCreateMsv}" id="moveCreateMsv${dnInfo.moveCreateMsv}_${dnInfo.dnId}" onclick="javascript:showCalendar('moveCreateMsv${dnInfo.moveCreateMsv}_${dnInfo.dnId}')"/></td>
                <td><input type="textbox" size="30" name="receiveMsv" value="${dnInfo.receiveMsv}" id="receiveMsv${dnInfo.receiveMsv}_${dnInfo.dnId}" onclick="javascript:showCalendar('receiveMsv${dnInfo.receiveMsv}_${dnInfo.dnId}')"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>