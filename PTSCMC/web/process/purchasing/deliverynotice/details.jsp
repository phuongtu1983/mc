<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table  style="width:100%" class="its" id="dnDetailTable">
    <thead>
        <tr>
            <th><bean:message key="message.del"/></th>            
            <th><bean:message key="message.dn.matName"/></th>
            <th><bean:message key="message.dn.unit"/></th>
            <th><bean:message key="message.dn.quantity"/></th>
            <th>
                <bean:message key="message.dn.note"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateDnDetail('note');"/>
            </th>
            <th><bean:message key="message.dn.requestNumber"/></th>
            <th>
                <bean:message key="message.dn.moveCreateMrir"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateDnDetail('moveCreateMrir');"/>
            </th>
            <th><bean:message key="message.dn.mrirNumber"/></th>
            <th><bean:message key="message.dn.mrirCreatedDate"/></th>
            <th>
                <bean:message key="message.dn.receiveMrir"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateDnDetail('receiveMrir');"/>
            </th>
            <th>
                <bean:message key="message.dn.moveCreateMsv"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateDnDetail('moveCreateMsv');"/>
            </th>
            <th><bean:message key="message.dn.msvNumber"/></th>
            <th><bean:message key="message.dn.msvCreatedDate"/></th>
            <th>
                <bean:message key="message.dn.receiveMsv"/>
                <html:image src="images/arrow2.gif" onclick="return duplicateDnDetail('receiveMsv');"/>
            </th>
        </tr>
    </thead>
    <tbody>
        <logic:present name="<%=Constants.DN_DETAIL_LIST%>">
            <logic:iterate id="detail" name="<%=Constants.DN_DETAIL_LIST%>">
                <tr>
                    <td width="30px">
                        <div align="center"><input type="checkbox" name="detId" value="<bean:write name="detail" property="detId"/>"/></div>
                        <input type="hidden" name="delDetId" value="<bean:write name="detail" property="detId"/>"/>
                        <html:hidden name="detail" property="matId"/>
                    </td>
                    <td><div style="width:200px;"><span><bean:write name="detail" property="matName"/></span></div></td>
                    <td>
                        <div style="width:50px"><span><bean:write name="detail" property="unit"/></span></div>
                        <html:hidden name="detail" property="unit"/>
                        <html:hidden name="detail" property="reqDetailId"/>
                        <html:hidden name="detail" property="conDetId"/>
                        <html:hidden name="detail" property="price"/>
                        <html:hidden name="detail" property="quantity" styleId="detqt${detail.detId}${detail.reqDetailId}"/>
                        <html:hidden name="detail" property="qtTemp" styleId="qtTemp${detail.detId}${detail.reqDetailId}"/>
                        <html:hidden name="detail" property="qtDn" styleId="qtDn${detail.detId}${detail.reqDetailId}"/>
                    </td>
                    <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" onblur="dnCheckQt('${detail.detId}${detail.reqDetailId}');"  id="detquantity${detail.detId}${detail.reqDetailId}" value="${detail.quantity}" /></td>
                    <td width="20px"><input type="textbox" size="20" name="note" value="${detail.note}"/></td>
                    <td><span><bean:write name="detail" property="requestNumber"/></span></td>
                    <td><input type="textbox" size="10" name="moveCreateMrir" value="${detail.moveCreateMrir}" id="moveCreateMrir${detail.detId}_${detail.dnId}" onclick="javascript:showCalendar('moveCreateMrir${detail.detId}_${detail.dnId}')"/></td>
                    <td><span><bean:write name="detail" property="mrirNumber"/></span></td>
                    <td><span><bean:write name="detail" property="mrirCreatedDate"/></span></td>
                    <td><input type="textbox" size="10" name="receiveMrir" value="${detail.receiveMrir}" id="receiveMrir${detail.detId}_${detail.dnId}" onclick="javascript:showCalendar('receiveMrir${detail.detId}_${detail.dnId}')"/></td>
                    <td><input type="textbox" size="10" name="moveCreateMsv" value="${detail.moveCreateMsv}" id="moveCreateMsv${detail.detId}_${detail.dnId}" onclick="javascript:showCalendar('moveCreateMsv${detail.detId}_${detail.dnId}')"/></td>
                    <td><span><bean:write name="detail" property="msvNumber"/></span></td>
                    <td><span><bean:write name="detail" property="msvCreatedDate"/></span></td>
                    <td><input type="textbox" size="10" name="receiveMsv" value="${detail.receiveMsv}" id="receiveMsv${detail.detId}_${detail.dnId}" onclick="javascript:showCalendar('receiveMsv${detail.detId}_${detail.dnId}')"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </tbody>
</table>