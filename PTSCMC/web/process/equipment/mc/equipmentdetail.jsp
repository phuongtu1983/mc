<%-- 
    Document   : equipmentdetail
    Created on : Nov 8, 2009, 8:33:22 PM
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
            <input type="checkbox" name="mcEquipmentChk" value="0">
            <input type="hidden" name="equipment" value="<bean:write name="<%=Constants.MC_EQUIPMENT%>" property="equId"/>"/>
            <input type="hidden" name="mcoIdHidden" value="<bean:write name="<%=Constants.MC_EQUIPMENT%>" property="mcoId"/>"/>
        </td>
        <td><bean:write name="<%=Constants.MC_EQUIPMENT%>" property="usedCode"/></td>
        <td><bean:write name="<%=Constants.MC_EQUIPMENT%>" property="nameVn"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.MC_EQUIPMENT%>" property="unit" readonly="true"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.MC_EQUIPMENT%>" property="quantity" readonly="true"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.MC_EQUIPMENT%>" property="spec"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="20" name="<%=Constants.MC_EQUIPMENT%>" property="mcoDetailId" readonly="true"/></td>
    </tr>
</table>
