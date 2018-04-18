<%-- 
    Document   : equipmentdetail
    Created on : Nov 9, 2009, 9:20:44 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="equipmentDetailTbl">
    <tr>
        <td width="20px">
            <input type="checkbox" name="emcEquipmentChk" value="0">
            <input type="hidden" name="equipment" value="<bean:write name="<%=Constants.EMC_EQUIPMENT%>" property="equipment"/>"/>
        </td>
        <td><bean:write name="<%=Constants.EMC_EQUIPMENT%>" property="equipment"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.EMC_EQUIPMENT%>" property="unit"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.EMC_EQUIPMENT%>" property="quantity"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.EMC_EQUIPMENT%>" property="spec"/></td>
    </tr>
</table>
