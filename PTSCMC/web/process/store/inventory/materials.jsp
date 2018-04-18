<%-- 
    Document   : materials
    Created on : Oct 5, 2009, 11:13:23 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="materialTbl" class="its" style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.inventorydetail.firstCol"/></th>
            <th><bean:message key="message.inventorydetail.matName"/></th>
            <th><bean:message key="message.inventorydetail.matCode"/></th>
            <th><bean:message key="message.inventorydetail.unit"/></th>
            <th><bean:message key="message.inventorydetail.quantityActual"/></th>
            <th><bean:message key="message.inventorydetail.quantityDocument"/></th>
            <th><bean:message key="message.inventorydetail.quantityVariance"/></th>
            <th><bean:message key="message.inventorydetail.comment"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="material" name="<%=Constants.INVENTORY_MATERIAL_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="mrirMaterialChk" value="<bean:write name="material" property="invId"/>">
                    <input type="hidden" name="material" value="<bean:write name="material" property="matId"/>"/>
                </td>
                <td><bean:write name="material" property="matName"/></td>
                <td><bean:write name="material" property="matCode"/></td>
                <td><bean:write name="material" property="unit"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quantityActual"  onblur="return tryNumberFormat(this)" /></td>
                <td><bean:write name="material" property="quantityDocument"/></td>
                <td><bean:write name="material" property="quantityVariance"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="commentDetail" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>