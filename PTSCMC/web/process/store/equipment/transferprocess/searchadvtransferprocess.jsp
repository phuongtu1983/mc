<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchTransferProcessForm' name='searchTransferProcessForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.transferprocess.receiveOrg"/></td>
                            <td height="22">
                                <html:select  property="receiveOrg" name="<%=Constants.TRANSFERPROCESS%>">
                                    <html:options collection="<%=Constants.TRANSFERPROCESS_ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                                <td height="22"><bean:message key="message.transferprocess.receiveEmp"/></td>
                            <td height="22">
                                <html:select  property="receiveEmp" name="<%=Constants.TRANSFERPROCESS%>">
                                    <html:options collection="<%=Constants.TRANSFERPROCESS_EMP_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.transferprocess.project"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="project" name="<%=Constants.TRANSFERPROCESS%>">
                                    <html:options collection="<%=Constants.TRANSFERPROCESS_PROJECT_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="message.transferprocess.receiveDate"/></td>
                            <td><html:text property="receiveDate" readonly="true" size="10" styleId="receiveDate" onclick="javascript: showCalendar('receiveDate')" name="<%=Constants.TRANSFERPROCESS%>" /></td>
                            <td><bean:message key="message.transferprocess.returnDate"/></td>
                            <td><html:text property="returnDate" readonly="true" size="10" styleId="returnDate" onclick="javascript: showCalendar('returnDate')" name="<%=Constants.TRANSFERPROCESS%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.transferprocess.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.TRANSFERPROCESS%>"/></td>
                        </tr>
                    </table>
                </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvTransferProcess();"/>
                <html:hidden property="tpId" name="<%=Constants.TRANSFERPROCESS%>" />
                <html:hidden property="equId" name="<%=Constants.TRANSFERPROCESS%>" />
                <html:hidden property="assId" name="<%=Constants.TRANSFERPROCESS%>" />
</form>