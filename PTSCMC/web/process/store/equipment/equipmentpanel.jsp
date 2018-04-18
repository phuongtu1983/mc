<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%
            int kind = Integer.parseInt(MCUtil.getParameter("kind", request));
%>
<h3><% if (kind == 2) {%>
    <bean:message key="message.listequipment.title"/>
    <% }%>
    <% if (kind == 3) {%>
    <bean:message key="message.listasset.title"/>
    <% }%></h3>
<div id="errorMessageDiv"></div>
<html:form styleId="searchEquipment" action="searchEquipment.do">
    <table>            
        <tr>
            <td>
                <html:image src="images/blank.png" onclick="return searchEquipment();"/>
                <%--
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_DELETE, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_xoa.gif" onclick="return delEquipments();"/> 
                <%}%>
                <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_ADD, PermissionUtil.PER_EQUIPMENT)) {%>
                <html:image src="images/ico_them.gif"  onclick="return addEquipment();"/>
                <%}%>
                --%>
            </td>
            <td><div>
                    
                    <% if (kind == 2) {%>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.equipment.mivId" value='1'/>
                        <html:option key="message.equipment.equipmentName" value='2'/>
                        <html:option key="message.equipment.requestNumber" value='3'/>
                        <html:option key="message.equipment.contractNumber" value='4'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchEquipment(2);"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvEquipmentForm(2);"><bean:message key="message.detailSearch"/></html:submit>
                    <% }%>
                    <% if (kind == 3) {%>
                    <html:select property="searchid">
                        <html:option key="message.all" value='0'/>
                        <html:option key="message.equipment.mivId" value='1'/>
                        <html:option key="message.asset.assetName" value='2'/>
                        <html:option key="message.equipment.requestNumber" value='3'/>
                        <html:option key="message.equipment.contractNumber" value='4'/>
                    </html:select>
                    <html:text property="searchvalue" />
                    <html:submit onclick="return searchEquipment(3);"><bean:message key="message.search"/></html:submit>
                    <html:submit onclick="return searchAdvEquipmentForm(3);"><bean:message key="message.detailSearch"/></html:submit>
                    <% }%>
                </div></td>
        </tr>
    </table>
</html:form>
<form name='equipmentsForm' id='equipmentsForm'><div id='equipmentList'></div><input type="hidden" name="kind" value="<%=kind%>"/></form>