<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<form id='searchEquipmentForm' name='searchEquipmentForm'>
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.mivId"/></td>
                            <td height="22"><html:text property="mivNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.manageCode"/></td>
                            <td height="22"><html:text property="manageCode" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <logic:equal value="1" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <td height="22"><bean:message key="message.equipment.equipmentName"/></td>
                            </logic:equal>
                            <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <td height="22"><bean:message key="message.asset.assetName"/></td>
                            </logic:equal>
                            <td height="22" colspan="3"><html:text property="equipmentName" size="112" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.requestNumber"/></td>
                            <td height="22"><html:text property="requestNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.contractNumber"/></td>
                            <td height="22"><html:text property="contractNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <logic:equal value="1" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <tr>
                                <td height="22"><bean:message key="message.equipment.testNumber"/></td>
                                <td height="22"><html:text property="testNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                                <td height="22"><bean:message key="message.equipment.unit"/></td>
                                <td height="22"><html:text property="unit" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            </tr>
                        </logic:equal>
                        <logic:equal value="2" name="<%=Constants.EQUIPMENT%>" property="kind">
                            <tr>
                                <td height="22"><bean:message key="message.asset.decisionNumber"/></td>
                                <td height="22"><html:text property="decisionNumber" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                                <td height="22"><bean:message key="message.asset.testNumber"/></td>
                                <td height="22"><html:text property="test" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.equipment.unit"/></td>
                                <td height="22"><html:text property="unit" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.usedCode"/></td>
                            <td height="22"><html:text property="usedCode" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                            <td height="22"><bean:message key="message.equipment.colorCode"/></td>
                            <td height="22"><html:text property="colorCode" size="40" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.specCerts"/></td>
                            <td height="22" colspan="3"><html:textarea property="specCerts"  cols="90" rows="10" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.fuelLevel"/></td>
                            <td height="22" colspan="3"><html:textarea property="fuelLevel"  cols="90" rows="10" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.status"/></td>
                            <td height="22" colspan="3">
                                <html:select  property="status" name="<%=Constants.EQUIPMENT%>">
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td><bean:message key="message.equipment.appearedDate"/></td>
                            <td><html:text property="appearedDate" readonly="true" size="10" styleId="appearedDate" onclick="javascript: showCalendar('appearedDate')" name="<%=Constants.EQUIPMENT%>" /></td>
                            <td><bean:message key="message.equipment.usedDate"/></td>
                            <td><html:text property="usedDate" readonly="true" size="10" styleId="usedDate" onclick="javascript: showCalendar('usedDate')" name="<%=Constants.EQUIPMENT%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.comment"/></td>
                            <td height="22" colspan="3"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.EQUIPMENT%>"/></td>
                        </tr>
                    </table>
                </div></td></tr></table>
                <html:image property="Submit" value="Submit" src="images/but_find.gif" style="cursor: hand;" onclick="return searchAdvEquipment();"/>
                <html:hidden property="equId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="mivId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="matId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="conId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="reqDetailId" name="<%=Constants.EQUIPMENT%>" />
                <html:hidden property="kind" name="<%=Constants.EQUIPMENT%>" />
</form>