<%-- 
    Document   : materials
    Created on : Oct 16, 2009, 3:23:57 PM
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
            <th width="20px"><bean:message key="message.del"/></th>
            <th><bean:message key="message.emrirdetail.ematId"/></th>
            <th><bean:message key="message.emrirdetail.unit"/></th>
            <th><bean:message key="message.emrirdetail.quantity"/></th>
            <th><bean:message key="message.emrirdetail.manufacture"/></th>
            <th><bean:message key="message.emrirdetail.heatSerial"/></th>
            <th><bean:message key="message.emrirdetail.inspection"/></th>
            <th><bean:message key="message.emrirdetail.original"/></th>
            <th><bean:message key="message.emrirdetail.quality"/></th>
            <th><bean:message key="message.emrirdetail.warranty"/></th>
            <th><bean:message key="message.emrirdetail.insurance"/></th>
            <th><bean:message key="message.emrirdetail.approvalType"/></th>
            <th><bean:message key="message.emrirdetail.complCert"/></th>            
        </tr>
    </thead>
    <tbody>
        <logic:iterate id="material" name="<%=Constants.EMRIR_MATERIAL_LIST%>">
            <tr>
                <td width="20px">
                    <input type="checkbox" name="emrirMaterialChk" value="<bean:write name="material" property="detId"/>">
                    <input type="hidden" name="material" value="<bean:write name="material" property="ematId"/>"/>
                </td>
                <td><bean:write name="material" property="matName"/></td>
                <td><bean:write name="material" property="unit"/></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quantity"  onblur="return tryNumberFormat(this)" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="manufacture" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="heatSerial" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="inspection" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="original" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="quality" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="warranty" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="insurance" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="approvalType" /></td>
                <td><html:text styleClass="lbl10" style="border-width:1px;text-align:right" size="4" name="material" property="complCert" /></td>
            </tr>
        </logic:iterate>
    </tbody>
</table>
