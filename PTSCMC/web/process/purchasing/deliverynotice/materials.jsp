<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="dnMaterialTable">
    <logic:iterate id="detail" name="<%=Constants.MATERIAL_LIST%>">
        <tr>
            <td width="30px">
                <div align="center"><input type="checkbox" name="detId" value="0"/></div>
                <input type="hidden" name="delDetId" value="0"/>
                <html:hidden name="detail" property="matId"/>
            </td>
            <td><span><bean:write name="detail" property="matName"/></span></td>
            <td>
                <span><bean:write name="detail" property="unit"/></span>
                <html:hidden name="detail" property="unit"/>
                <html:hidden name="detail" property="conDetId"/>
                <html:hidden name="detail" property="price"/>
                <html:hidden name="detail" property="reqDetailId"/>
                <html:hidden name="detail" property="quantity" styleId="detqt${detail.detId}${detail.reqDetailId}"/>
                <html:hidden name="detail" property="qtTemp" styleId="qtTemp${detail.detId}${detail.reqDetailId}"/>
                <html:hidden name="detail" property="qtDn" styleId="qtDn${detail.detId}${detail.reqDetailId}"/>
            </td>
            <td width="7px"><input type="textbox" styleClass="lbl10" style="border-width:1px;text-align:right" size="7" name="quantity" onblur="dnCheckQt('${detail.detId}${detail.reqDetailId}');"  id="detquantity${detail.detId}${detail.reqDetailId}" value="${detail.quantity}" /></td>
            <td width="120px"><input type="textbox" size="20" name="note" id="detTotal${detail.detId}" value="${detail.note}"/></td>
            <td><span><bean:write name="detail" property="requestNumber"/></span></td>
            <td><input type="textbox" size="10" name="moveCreateMrir" value="${detail.moveCreateMrir}" id="moveCreateMrir${detail.matId}_${detail.dnId}" onclick="javascript:showCalendar('moveCreateMrir${detail.matId}_${detail.dnId}')"/></td>
            <td><span><bean:write name="detail" property="mrirNumber"/></span></td>
            <td><span><bean:write name="detail" property="mrirCreatedDate"/></span></td>
            <td><input type="textbox" size="10" name="receiveMrir" value="${detail.receiveMrir}" id="receiveMrir${detail.matId}_${detail.dnId}" onclick="javascript:showCalendar('receiveMrir${detail.matId}_${detail.dnId}')"/></td>
            <td><input type="textbox" size="10" name="moveCreateMsv" value="${detail.moveCreateMsv}" id="moveCreateMsv${detail.matId}_${detail.dnId}" onclick="javascript:showCalendar('moveCreateMsv${detail.matId}_${detail.dnId}')"/></td>
            <td><span><bean:write name="detail" property="msvNumber"/></span></td>
            <td><span><bean:write name="detail" property="msvCreatedDate"/></span></td>
            <td><input type="textbox" size="10" name="receiveMsv" value="${detail.receiveMsv}" id="receiveMsv${detail.matId}_${detail.dnId}" onclick="javascript:showCalendar('receiveMsv${detail.matId}_${detail.dnId}')"/></td>
        </tr>
    </logic:iterate>
</table>