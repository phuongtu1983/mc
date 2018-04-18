<%-- 
    Document   : searchadvmrir
    Created on : Sep 23, 2009, 9:42:00 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchMrirForm' name='searchMrirForm'>
    <table width="100%">
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.mrirNumber"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="mrirNumber" name="<%=Constants.MRIR_SEARCH_ADV%>"/></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.searchStartDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="searchStartDate" styleId="searchStartDate" size="10" onclick="javascript: showCalendar('searchStartDate')" name="<%=Constants.MRIR_SEARCH_ADV%>"/></td>
            <td class="lbl10"><bean:message key="message.mrir.searchEndDate"/></td>
            <td colspan="2"><html:text styleClass="lbl10" property="searchEndDate" styleId="searchEndDate" size="10" onclick="javascript: showCalendar('searchEndDate')" name="<%=Constants.MRIR_SEARCH_ADV%>"/></td>
        </tr>  
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.dnId"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="dnNumber" name="<%=Constants.MRIR_SEARCH_ADV%>" />
                
            </td>
            <td class="lbl10"><bean:message key="message.mrir.reqId"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="reqNumber" name="<%=Constants.MRIR_SEARCH_ADV%>" />
               
            </td>            
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.blNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="blNo" name="<%=Constants.MRIR_SEARCH_ADV%>" /></td>
            <td class="lbl10"><bean:message key="message.mrir.invoiceNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="invoiceNo" name="<%=Constants.MRIR_SEARCH_ADV%>" /></td>
        </tr>
        <tr>
            <td class="lbl10"><bean:message key="message.mrir.plNo"/></td>
            <td colspan="2"><html:text styleClass="lbl10" size="20" property="plNo" name="<%=Constants.MRIR_SEARCH_ADV%>" /></td>
        </tr>
    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvMrir();"/>
</form>
