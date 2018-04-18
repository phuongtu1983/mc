<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchAssetForm' name='searchAssetForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.decisionNumber"/></td>
                            <td height="22"><html:text property="decisionNumber" size="40" name="<%=Constants.ASSET%>"/></td>
                            <td height="22" width="10%"><bean:message key="message.asset.manageCode"/></td>
                            <td height="22"><html:text property="manageCode" size="40" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.assetName"/></td>
                            <td height="22" colspan="3"><html:text property="assetName" size="107" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.requestNumber"/></td>
                            <td height="22"><html:text property="requestNumber" size="40" name="<%=Constants.ASSET%>"/></td>
                            <td height="22"><bean:message key="message.asset.contractNumber"/></td>
                            <td height="22"><html:text property="contractNumber" size="40" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.testNumber"/></td>
                            <td height="22"><html:text property="testNumber" size="40" name="<%=Constants.ASSET%>"/></td>
                            <td height="22"><bean:message key="message.asset.unit"/></td>
                            <td height="22"><html:text property="unit" size="40" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.usedCode"/></td>
                            <td height="22"><html:text property="usedCode" size="40" name="<%=Constants.ASSET%>"/></td>
                            <td height="22"><bean:message key="message.asset.colorCode"/></td>
                            <td height="22"><html:text property="colorCode" size="40" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.specCerts"/></td>
                            <td height="22" colspan="3"><html:textarea property="specCerts"  cols="90" rows="10" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.fuelLevel"/></td>
                            <td height="22" colspan="3"><html:textarea property="fuelLevel"  cols="90" rows="10" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.status"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="status" name="<%=Constants.ASSET%>">
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="message.asset.appearedDate"/></td>
                            <td><html:text property="appearedDate" readonly="true" size="10" styleId="appearedDate" onclick="javascript: showCalendar('appearedDate')" name="<%=Constants.ASSET%>" /></td>
                            <td><bean:message key="message.asset.usedDate"/></td>
                            <td><html:text property="usedDate" readonly="true" size="10" styleId="usedDate" onclick="javascript: showCalendar('usedDate')" name="<%=Constants.ASSET%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.ASSET%>"/></td>
                        </tr>
                    </table>
                </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvAsset();"/>
                <html:hidden property="assId" name="<%=Constants.ASSET%>" />
</form>