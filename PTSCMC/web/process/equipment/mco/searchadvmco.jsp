<%-- 
    Document   : searchadvmaterialcarryout
    Created on : Nov 5, 2009, 8:52:26 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchMcoForm' name='searchMcoForm'>
     <table width="100%">
         <tr>
            <td class="lbl10"><bean:message key="message.mco.orgId"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="orgId" name="<%=Constants.MCO_SEARCH_ADV%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.ORGANIZATION_LIST%>" property="orgId" labelProperty="name"/>
                </html:select>
            </td>
            <td class="lbl10"><bean:message key="message.mco.kind"/></td>
            <td colspan="2">
                <html:select styleClass="lbl10" property="kind" name="<%=Constants.MCO_SEARCH_ADV%>">
                    <html:options styleClass="lbl10" collection="<%=Constants.MCO_KIND_LIST%>" property="value" labelProperty="label"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mco.mcoNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="mcoNumber" size="20" name="<%=Constants.MCO_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.mco.carryOutDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="carryOutDate" styleId="carryOutDateMco" size="20" onclick="javascript: showCalendar('carryOutDateMco')" name="<%=Constants.MCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.MCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.descCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOut" name="<%=Constants.MCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.mco.descNotCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOut" name="<%=Constants.MCO_SEARCH_ADV%>"/></td>
        </tr>
    </table>
    <p style="margin-top: 0; margin-bottom: 0" align="right">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvMco();"/>
    </p>
</form>