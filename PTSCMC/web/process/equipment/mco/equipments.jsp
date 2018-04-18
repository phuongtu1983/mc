<%-- 
    Document   : equipments
    Created on : Nov 7, 2009, 8:44:02 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="equipmentTbl" class="its" style="width:100%">
    <thead>
        <tr>
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.equipment.usedCode"/></th>
            <th><bean:message key="message.mcodetail.equId"/></th>
            <th><bean:message key="message.mcodetail.unit"/></th>
            <th><bean:message key="message.mcodetail.quantity"/></th>
            <th><bean:message key="message.mcodetail.spec"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="equipment" name="<%=Constants.MCO_EQUIPMENT_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="mcoEquipmentChk" value="<bean:write name="equipment" property="detId"/>">
                    <input type="hidden" name="equipment" value="<bean:write name="equipment" property="equId"/>"/>
                </td>
                <td><bean:write name="equipment" property="usedCode"/></td>
                <td><bean:write name="equipment" property="nameVn"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="unit" readonly="true"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="quantity" readonly="true"  onblur="return tryNumberFormat(this)" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="spec" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
