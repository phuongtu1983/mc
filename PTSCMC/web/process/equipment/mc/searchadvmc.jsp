<%-- 
    Document   : searchadvmc
    Created on : Oct 23, 2009, 3:32:25 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchMcForm' name='searchMcForm'>
     <table width="100%">
         <tr>
            <td class="lbl10"><bean:message key="message.mc.orgId"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="orgId" name="<%=Constants.MC_SEARCH_ADV%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.mc.kind"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="kind" name="<%=Constants.MC_SEARCH_ADV%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MC_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mc.mcNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="mcNumber" size="30" name="<%=Constants.MC_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.mc.carryOnDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="carryOnDate" styleId="carryOnDateMc" size="30" onclick="javascript: showCalendar('carryOnDateMc')" name="<%=Constants.MC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.MC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.descCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOn" name="<%=Constants.MC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mc.descNotCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOn" name="<%=Constants.MC_SEARCH_ADV%>"/></td>
        </tr>
    </table>
    <p style="margin-top: 0; margin-bottom: 0" align="right">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvMc();"/>
    </p>
</form>
