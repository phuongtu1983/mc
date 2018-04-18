<%-- 
    Document   : emcdetail
    Created on : Oct 23, 2009, 3:30:08 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.process.equipment.emc.EmcFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<div id="emcFormTitle"><h3><bean:message key="message.emc.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="emcForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.emc.department"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.EMC%>" property="emcId">
                    <html:text styleClass="lbl10" property="department" size="30" name="<%=Constants.EMC%>"/>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.EMC%>" property="emcId">
                    <html:text styleClass="lbl10" property="department" size="30" name="<%=Constants.EMC%>" disabled="true" />
                </logic:notEqual>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emc.emcNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="emcNumber" size="30" name="<%=Constants.EMC%>"/></td>
            <td class="lbl10"><bean:message key="message.emc.carryOutDatePlan"/></td>
            <td><html:text styleClass="lbl10" property="carryOutDatePlan" styleId="carryOutDatePlanEmc" size="15" onclick="javascript: showCalendar('carryOutDatePlanEmc')" name="<%=Constants.EMC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.EMC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.descCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOn" name="<%=Constants.EMC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.descNotCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOn" name="<%=Constants.EMC%>"/></td>
        </tr>        
        <tr>
            <td class="lbl10"><bean:message key="message.emc.status"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="status" name="<%=Constants.EMC%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.EMC_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emc.carryOnDate"/></td>
            <td><html:text styleClass="lbl10" property="carryOnHour" size="5" name="<%=Constants.EMC%>"/></td>
            <td class="lbl10"><bean:message key="message.emc.carryOnHour"/></td>
            <td><html:text styleClass="lbl10" property="carryOnMinute" size="5" name="<%=Constants.EMC%>"/></td>
            <td class="lbl10"><bean:message key="message.emc.carryOnMinute"/></td>
            <td><html:text styleClass="lbl10" property="carryOnDate" styleId="carryOnDateEmc" size="15" onclick="javascript: showCalendar('carryOnDateEmc')" name="<%=Constants.EMC%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.emc.result"/></legend>
                    <p><table width="100%">
                        <tr>
                            <td class="lbl10"><html:radio property="result" name="<%=Constants.EMC%>" value="1"></html:radio><bean:message key="message.emc.result1"/></td>
                        </tr>
                        <tr>
                            <td class="lbl10"><html:radio property="result" name="<%=Constants.EMC%>" value="2"></html:radio><bean:message key="message.emc.result2"/></td>
                        </tr>
                    </table></p>
                </fieldset>
            </td>
        </tr>        
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.emc.equipments"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delEmcDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addEmcDetail();"/>
                    <img src="images/blank.gif" width="2" height="17">
                    <bean:message key="message.emc.equipment"/>    
                    <html:text styleClass="lbl10" property="equipName" name="<%=Constants.EMC%>" size="30"/>
                    <p><div id="listEmcDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <%
        if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_EMC)) {
    %>
    <html:image onclick="return saveEmc();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <% } %>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmcList();"/>
    <html:image src="images/but_print.gif" onclick="return printEmc();" />
    <html:hidden property="emcId" name="<%=Constants.EMC%>"/>
</form>
<div id="emcDetailHideDiv" style="display:none"></div>