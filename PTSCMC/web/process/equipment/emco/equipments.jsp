<%-- 
    Document   : equipments
    Created on : Nov 8, 2009, 11:18:35 PM
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
            <th><bean:message key="message.emcodetail.equipment"/></th>
            <th><bean:message key="message.emcodetail.unit"/></th>
            <th><bean:message key="message.emcodetail.quantity"/></th>
            <th><bean:message key="message.emcodetail.spec"/></th>
            <th><bean:message key="message.emcodetail.emcDetailId"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="equipment" name="<%=Constants.EMCO_EQUIPMENT_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="emcoEquipmentChk" value="<bean:write name="equipment" property="detId"/>">
                    <input type="hidden" name="equipment" value="<bean:write name="equipment" property="equipment"/>"/>
                    <input type="hidden" name="emcIdHidden" value="<bean:write name="equipment" property="emcId"/>"/>
                </td>
                <td><bean:write name="equipment" property="equipment"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="unit"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="quantity"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="spec"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="emcDetailId" readonly="true"/></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>