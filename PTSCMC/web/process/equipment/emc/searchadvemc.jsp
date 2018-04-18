<%-- 
    Document   : searchadvemio
    Created on : Oct 23, 2009, 3:31:26 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchEmcForm' name='searchEmcForm'>
     <table width="100%">
         <tr>
            <td class="lbl10"><bean:message key="message.emc.department"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="department" size="20" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emc.emcNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="emcNumber" size="20" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.emc.carryOnDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="carryOnDate" styleId="carryOnDateEmc" size="20" onclick="javascript: showCalendar('carryOnDateEmc')" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.descCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOn" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emc.descNotCarryOn"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOn" name="<%=Constants.EMC_SEARCH_ADV%>"/></td>
        </tr>
    </table>
    <p style="margin-top: 0; margin-bottom: 0" align="right">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEmc();"/>
    </p>
</form>
