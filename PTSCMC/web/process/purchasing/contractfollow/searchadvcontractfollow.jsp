<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchContractFollowForm' name='searchContractFollowForm'>

    <table border="0" cellspacing="5" cellpadding="0" style="border-width: 0px">
        <tr>
            <td height="22"><p align="right"><bean:message key="message.contractFollow.folNumber"/>&nbsp;</p></td>
            <td colspan="2"><html:text size="14" property="folNumber" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
            <td><p align="right"><bean:message key="message.contractFollow.createdDate"/>&nbsp;</p></td>
            <td colspan="2"><html:text property="createdDate" styleId="createdDateRequest" onclick="javascript: showCalendar('createdDateRequest')" size="10" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
        </tr>
        <tr>
            <td height="22"><p align="right"><bean:message key="message.contractFollow.conId"/>&nbsp;</p></td>
            <td height="22" colspan="2">
                <html:select property="conId" name="<%=Constants.CONTRACT_FOLLOW%>">
                    <html:options collection="<%=Constants.CONTRACT_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>

            </tr>

            <tr>
                <td height="22"><p align="right"><bean:message key="message.contractFollow.proId"/>&nbsp;</p></td>
            <td height="22" colspan="4">
                <html:select property="proId" name="<%=Constants.CONTRACT_FOLLOW%>">
                    <html:options collection="<%=Constants.PROJECT_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>
            <tr>
                <td height="22"><p align="right"><bean:message key="message.contractFollow.serviceType"/>&nbsp;</p></td>
            <td height="22" colspan="2">
                <html:select property="serviceType" name="<%=Constants.CONTRACT_FOLLOW%>">
                    <html:options collection="<%=Constants.SERVICE_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
                <td height="22"><p align="right"><bean:message key="message.contractFollow.orgId"/>&nbsp;</p></td>
            <td height="32" colspan="2">
                <html:select property="orgId" name="<%=Constants.CONTRACT_FOLLOW%>">
                    <html:options collection="<%=Constants.ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                </td>
            </tr>

            <tr>
                <td height="22"><p align="right"><bean:message key="message.contractFollow.comments"/>&nbsp;</p></td>
            <td height="22" colspan="5">
            <html:textarea rows="4" cols="80" property="comments" name="<%=Constants.CONTRACT_FOLLOW%>"/></td>
        </tr>

    </table>
    <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvContractFollow();"/>
</form>