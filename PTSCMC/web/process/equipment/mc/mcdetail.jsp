<%-- 
    Document   : mcdetail
    Created on : Oct 23, 2009, 3:31:44 PM
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
<%@ page import="com.venus.mc.process.equipment.mc.McFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<div id="mcFormTitle"><h3><bean:message key="message.mc.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="mcForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.mc.orgId"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.MC%>" property="mcId">
                    <html:select styleClass="lbl10" property="orgId" name="<%=Constants.MC%>" onchange="return orgIdChangedMc(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                    </html:select>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.MC%>" property="mcId">
                    <html:select styleClass="lbl10" property="orgId" name="<%=Constants.MC%>" onchange="return orgIdChangedMc(this);" disabled="true">
                        <html:options styleClass="lbl10" collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                    </html:select>  
                </logic:notEqual>
            </td>
            <td class="lbl10"><bean:message key="message.mc.kind"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="kind" name="<%=Constants.MC%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MC_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mc.mcNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="mcNumber" size="30" name="<%=Constants.MC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.MC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.descCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOn" name="<%=Constants.MC%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.descNotCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOn" name="<%=Constants.MC%>"/></td>
        </tr>        
        <tr>
            <td class="lbl10"><bean:message key="message.mc.status"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="status" name="<%=Constants.MC%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MC_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mc.carryOnDate"/></td>
            <td><html:text styleClass="lbl10" property="carryOnHour" size="5" name="<%=Constants.MC%>"/></td>
            <td class="lbl10"><bean:message key="message.mc.carryOnHour"/></td>
            <td><html:text styleClass="lbl10" property="carryOnMinute" size="5" name="<%=Constants.MC%>"/></td>
            <td class="lbl10"><bean:message key="message.mc.carryOnMinute"/></td>
            <td><html:text styleClass="lbl10" property="carryOnDate" styleId="carryOnDateMc" size="15" onclick="javascript: showCalendar('carryOnDateMc')" name="<%=Constants.MC%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mc.result"/></legend>
                    <p><table width="100%">
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.MC%>" value="1"></html:radio><bean:message key="message.mc.result1"/></td>
                            </tr>
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.MC%>" value="2"></html:radio><bean:message key="message.mc.result2"/></td>
                            </tr>
                    </table></p>
                    
                </fieldset>
            </td>
        </tr>        
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.mc.equipments"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delMcDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addMcDetail();"/>
                    <img src="images/blank.gif" width="2" height="17">
                    <span id="listMcoByOrgIdDiv"></span>
                    <img src="images/blank.gif" width="2" height="17">
                    <span id="listEquipmentByMcoIdDiv"></span>
                    <p><div id="listMcDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_MC)) {
    %>
    <html:image onclick="return saveMc();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <% }%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadMcList();"/>
    <html:image src="images/but_print.gif" onclick="return printMc();" />
    <html:hidden property="mcId" name="<%=Constants.MC%>"/>
</form>
<div id="mcDetailHideDiv" style="display:none"></div>