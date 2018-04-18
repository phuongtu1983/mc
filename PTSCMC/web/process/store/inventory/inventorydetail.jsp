<%-- 
    Document   : inventorydetail
    Created on : Oct 5, 2009, 11:12:41 PM
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
<%@ page import="com.venus.mc.process.store.inventory.InventoryFormBean"%>

<div id="inventoryFormTitle"><h3><bean:message key="message.inventory.title"/></h3></div>
<div id="errorMessageDiv"></div>
<form name="inventoryForm">
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.inventory.stoId"/></td>
            <td colspan="2">
                <logic:equal value="0" name="<%=Constants.INVENTORY%>" property="invId">
                    <html:select styleClass="lbl10" property="stoId" name="<%=Constants.INVENTORY%>" onchange="return stoIdChangedInventory(this);">
                        <html:options styleClass="lbl10" collection="<%=Constants.STORE_LIST%>" property="stoId" labelProperty="name"/>
                    </html:select>
                </logic:equal>
                <logic:greaterThan value="0" name="<%=Constants.INVENTORY%>" property="invId">
                    <html:select styleClass="lbl10" property="stoId" name="<%=Constants.INVENTORY%>"  disabled="true">
                        <html:options styleClass="lbl10" collection="<%=Constants.STORE_LIST%>" property="stoId" labelProperty="name"/>
                    </html:select>
                    <html:hidden property="stoId" name="<%=Constants.INVENTORY%>"/>
                </logic:greaterThan>
            </td>
            <td class="lbl10"><bean:message key="message.inventory.invDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="invDate" styleId="invDateInventory" size="30" onclick="javascript: showCalendar('invDateInventory')" name="<%=Constants.INVENTORY%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.inventory.createdEmp"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="createdEmp" name="<%=Constants.INVENTORY%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.EMPLOYEE_LIST%>" property="empId" labelProperty="fullname"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.inventory.comment"/>
            <br/><html:textarea cols="100" rows="2" property="comment" name="<%=Constants.INVENTORY%>"/></td>
        </tr>
        <tr>
            <td colspan="6">
                <fieldset>
                    <legend class="lbl10b"><bean:message key="message.inventory.materials"/></legend>
                    <p><div id="listInventoryDetailDataDiv"></div></p>
                </fieldset>
            </td>
        </tr>        
    </table>
    <html:image onclick="return saveInventory();" src="images/but_save.gif"/><img src="images/blank.gif" width="2" height="17"><html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadInventoryList();"/>
    <html:hidden property="invId" name="<%=Constants.INVENTORY%>"/>
</form>