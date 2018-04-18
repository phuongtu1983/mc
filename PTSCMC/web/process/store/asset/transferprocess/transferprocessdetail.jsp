<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@page import="com.venus.mc.util.PermissionUtil"%>
<%--<h3><bean:message key="message.detailtransferprocess.title"/></h3>--%>
<div id="errorMessageDiv"></div>
<form action="addAssetTransferProcess.do" name="addAssetTransferProcess">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.asset.assetName"/></td>
                            <td height="22" colspan="3"><input readonly type="textbox" id="assetName" size="112" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.equipment.usedCode"/></td>
                            <td height="22"><input readonly type="textbox" id="usedCode" size="40" /></td>
                            <td height="22"><bean:message key="message.equipment.colorCode"/></td>
                            <td height="22"><input readonly type="textbox" id="colorCode" size="40" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.transferprocess.receiveOrg"/></td>
                            <td height="22">
                                <html:select  property="receiveOrg" onchange="orgChangeAssetTransferProcess(this, this.selectedIndex);" name="<%=Constants.TRANSFERPROCESS%>">
                                    <html:options collection="<%=Constants.TRANSFERPROCESS_ORG_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                                <td height="22"><bean:message key="message.transferprocess.receiveEmp"/></td>
                            <td height="22" id="empListCbxAssetTransferProcess"><html:select styleClass="lbl9"  property="receiveEmp" name="<%=Constants.TRANSFERPROCESS%>">
                                    <html:options styleClass="lbl9" collection="<%=Constants.EMP_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.transferprocess.project"/></td>
                            <td height="22" colspan="4">
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
                            <td height="22" colspan="4"><html:textarea property="comment"  cols="90" rows="10" name="<%=Constants.TRANSFERPROCESS%>"/></td>
                        </tr>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_ASSET)) {%>
                        <html:image onclick="return saveAssetTransferProcess();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadAssetTransferProcess();"/>
                </div></td></tr></table>
                <html:hidden property="tpId" name="<%=Constants.TRANSFERPROCESS%>" />
                <html:hidden property="equId" name="<%=Constants.TRANSFERPROCESS%>" />
                <html:hidden property="assId" name="<%=Constants.TRANSFERPROCESS%>" />
</form>