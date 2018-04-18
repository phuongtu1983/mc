<%-- 
    Document   : materialcarryoutdetail
    Created on : Nov 5, 2009, 8:51:12 PM
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
<%@ page import="com.venus.mc.process.equipment.mco.McoFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<div id="mcoFormTitle"><h3><bean:message key="message.mco.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="mcoForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.mco.orgId"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="orgId" name="<%=Constants.MCO%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.mco.kind"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="kind" name="<%=Constants.MCO%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MCO_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mco.mcoNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="mcoNumber" size="30" name="<%=Constants.MCO%>"/></td>
            <td class="lbl10"><bean:message key="message.mco.carryOnDatePlan"/></td>
            <td><html:text styleClass="lbl10" property="carryOnDatePlan" styleId="carryOnDatePlanMco" size="15" onclick="javascript: showCalendar('carryOnDatePlanMco')" name="<%=Constants.MCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.MCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.descCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOut" name="<%=Constants.MCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.descNotCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOut" name="<%=Constants.MCO%>"/></td>
        </tr>        
        <tr>
            <td class="lbl10"><bean:message key="message.mco.status"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="status" name="<%=Constants.MCO%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MCO_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mco.carryOutDate"/></td>
            <td><html:text styleClass="lbl10" property="carryOutHour" size="5" name="<%=Constants.MCO%>"/></td>
            <td class="lbl10"><bean:message key="message.mco.carryOutHour"/></td>
            <td><html:text styleClass="lbl10" property="carryOutMinute" size="5" name="<%=Constants.MCO%>"/></td>
            <td class="lbl10"><bean:message key="message.mco.carryOutMinute"/></td>
            <td><html:text styleClass="lbl10" property="carryOutDate" styleId="carryOutDateMco" size="15" onclick="javascript: showCalendar('carryOutDateMco')" name="<%=Constants.MCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mco.result"/></legend>
                    <p><table width="100%">
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.MCO%>" value="1"></html:radio><bean:message key="message.mco.result1"/></td>
                            </tr>
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.MCO%>" value="2"></html:radio><bean:message key="message.mco.result2"/></td>
                            </tr>
                    </table></p>
                </fieldset>
            </td>
        </tr>        
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mco.equipments"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delMcoDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addMcoDetail();"/>
                    <p><div id="listMcoDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_MCO)) {
    %>    
    <html:image onclick="return saveMco();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <% }%> 
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMcoList();"/>
    <html:image src="images/but_print.gif" onclick="return printMco();" />
    <html:hidden property="mcoId" name="<%=Constants.MCO%>"/>
</form>