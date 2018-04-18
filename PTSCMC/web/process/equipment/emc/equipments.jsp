<%-- 
    Document   : equipments
    Created on : Nov 8, 2009, 11:38:09 PM
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
            <th><bean:message key="message.emcdetail.equipment"/></th>
            <th><bean:message key="message.emcdetail.unit"/></th>
            <th><bean:message key="message.emcdetail.quantity"/></th>
            <th><bean:message key="message.emcdetail.spec"/></th>
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="equipment" name="<%=Constants.EMC_EQUIPMENT_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="emcEquipmentChk" value="<bean:write name="equipment" property="detId"/>">
                    <input type="hidden" name="equipment" value="<bean:write name="equipment" property="equipment"/>"/>
                </td>
                <td><bean:write name="equipment" property="equipment"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="unit" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="equipment" property="spec" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>

