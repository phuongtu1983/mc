<%-- 
    Document   : eemcodetail
    Created on : Nov 5, 2009, 9:29:55 PM
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
<%@ page import="com.venus.mc.process.equipment.emco.EmcoFormBean"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<div id="emcoFormTitle"><h3><bean:message key="message.emco.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="emcoForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.emco.department"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.EMCO%>" property="emcoId">
                    <html:select styleClass="lbl10" property="department" name="<%=Constants.EMCO%>" onchange="return departmentChangedEmco(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.EMCO_DEPARMENT_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:notEqual value="0" name="<%=Constants.EMCO%>" property="emcoId">
                    <html:select styleClass="lbl10" property="department" name="<%=Constants.EMCO%>" disabled="true">
                        <html:options styleClass="lbl10" collection="<%=Constants.EMCO_DEPARMENT_LIST%>" property="value" labelProperty="label"/>
                    </html:select>
                </logic:notEqual>
            </td>
        </tr>
        <tr>            
            <td class="lbl10"><bean:message key="message.emco.emcoNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="emcoNumber" size="30" name="<%=Constants.EMCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.EMCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.descCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOut" name="<%=Constants.EMCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.descNotCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOut" name="<%=Constants.EMCO%>"/></td>
        </tr>        
        <tr>
            <td class="lbl10"><bean:message key="message.emco.status"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="status" name="<%=Constants.EMCO%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.EMCO_STATUS_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emco.carryOutDate"/></td>
            <td><html:text styleClass="lbl10" property="carryOutHour" size="5" name="<%=Constants.EMCO%>"/></td>
            <td class="lbl10"><bean:message key="message.emco.carryOutHour"/></td>
            <td><html:text styleClass="lbl10" property="carryOutMinute" size="5" name="<%=Constants.EMCO%>"/></td>
            <td class="lbl10"><bean:message key="message.emco.carryOutMinute"/></td>
            <td><html:text styleClass="lbl10" property="carryOutDate" styleId="carryOutDateEmco" size="15" onclick="javascript: showCalendar('carryOutDateEmco')" name="<%=Constants.EMCO%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.emco.result"/></legend>
                    <p><table width="100%">
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.EMCO%>" value="1"></html:radio><bean:message key="message.emco.result1"/></td>
                            </tr>
                            <tr>
                                <td class="lbl10"><html:radio property="result" name="<%=Constants.EMCO%>" value="2"></html:radio><bean:message key="message.emco.result2"/></td>
                            </tr>
                    </table></p>
                </fieldset>
            </td>
        </tr>        
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.emco.equipments"/></legend>
                    <html:image src="images/ico_xoa.gif" onclick="return delEmcoDetails();"/>
                    <html:image src="images/ico_them.gif" onclick="return addEmcoDetail();"/>
                    <img src="images/blank.gif" width="2" height="17">
                    <span id="listEmcByDeptDiv"></span>
                    <img src="images/blank.gif" width="2" height="17">
                    <span id="listEquipmentByEmcoIdDiv"></span>
                    <p><div id="listEmcoDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <%
            if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_EMCO)) {
    %>
    <html:image onclick="return saveEmco();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17">
    <% }%>
    <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadEmcoList();"/>
    <html:image src="images/but_print.gif" onclick="return printEmco();" />
    <html:hidden property="emcoId" name="<%=Constants.EMCO%>"/>
</form>
<div id="emcoDetailHideDiv" style="display:none"></div>
