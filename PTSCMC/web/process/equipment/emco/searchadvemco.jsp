<%-- 
    Document   : searchadveemcoo
    Created on : Nov 5, 2009, 9:30:36 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchEmcoForm' name='searchEmcoForm'>
     <table width="100%">
         <tr>
            <td class="lbl10"><bean:message key="message.emco.department"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="department" size="30" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emco.emcoNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="emcoNumber" size="30" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.emco.carryOutDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="carryOutDate" styleId="carryOutDateEmco" size="30" onclick="javascript: showCalendar('carryOutDateEmco')" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.explanation"/>
            <br/><html:textarea cols="100" rows="2" property="explanation" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.descCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descCarryOut" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td colspan="6" class="lbl10"><bean:message key="message.emco.descNotCarryOut"/>
            <br/><html:textarea cols="100" rows="2" property="descNotCarryOut" name="<%=Constants.EMCO_SEARCH_ADV%>"/></td>
        </tr>
    </table>
    <p style="margin-top: 0; margin-bottom: 0" align="right">
        <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEmco();"/>
    </p>
</form>
