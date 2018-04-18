<%-- 
    Document   : materialdetails
    Created on : Oct 16, 2009, 3:24:16 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<table id="materialDetailTbl">
    <tr>
        <td width="20px">
            <input type="checkbox" name="emrirMaterialChk" value="0">
            <input type="hidden" name="material" value="<bean:write name="<%=Constants.EMRIR_MATERIAL%>" property="ematId"/>"/>
        </td>
        <td><bean:write name="<%=Constants.EMRIR_MATERIAL%>" property="matName"/></td>
        <td><bean:write name="<%=Constants.EMRIR_MATERIAL%>" property="unit"/></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="quantity" /></td>        
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="manufacture" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="heatSerial" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="inspection" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="original" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="quality" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="warranty" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="insurance" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="approvalType" /></td>
        <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="<%=Constants.EMRIR_MATERIAL%>" property="complCert" /></td>
    </tr>
</table>