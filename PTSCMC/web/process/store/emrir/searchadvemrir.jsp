<%-- 
    Document   : searchadveemrir
    Created on : Oct 16, 2009, 3:23:04 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchEmrirForm' name='searchEmrirForm'>
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.emrirNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="emrirNumber" name="<%=Constants.EMRIR_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.searchStartDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="searchStartDate" styleId="searchStartDate" size="10" onclick="javascript: showCalendar('searchStartDate')" name="<%=Constants.EMRIR_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.emrir.searchEndDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="searchEndDate" styleId="searchEndDate" size="10" onclick="javascript: showCalendar('searchEndDate')" name="<%=Constants.EMRIR_SEARCH_ADV%>"/></td>
        </tr>  
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.ednId"/></td>
            <td colspan="2">
                <html:select property="ednId" name="<%=Constants.EMRIR_SEARCH_ADV%>">
                    <html:options collection="<%=Constants.EMRIR_DELIVERY_NOTICE_LIST%>" property="ednId" labelProperty="ednNumber"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.blNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="blNo" name="<%=Constants.EMRIR_SEARCH_ADV%>" /></td>
            <td class="lbl10"><bean:message key="message.emrir.invoiceNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="invoiceNo" name="<%=Constants.EMRIR_SEARCH_ADV%>" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.emrir.plNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="plNo" name="<%=Constants.EMRIR_SEARCH_ADV%>" /></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEmrir();"/>
</form>
