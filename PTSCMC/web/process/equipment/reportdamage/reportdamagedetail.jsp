<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib prefix="display" uri="/tags/displaytag" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>

<h3><bean:message key="message.detailreportdamage.title"/></h3>
<div id="errorMessageDiv"></div>
<form action="addReportDamage.do" name="addReportDamage">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.reportdamage.rdNumber"/></td>
                            <td height="22" colspan="0"><html:text property="rdNumber" size="20" name="<%=Constants.REPORTDAMAGE%>"/></td>
                            <td height="22"><bean:message key="message.reportdamage.createdDate"/></td>
                            <td height="22" colspan="0"><html:text styleId="createdDate" property="createdDate" size="10" readonly="true" name="<%=Constants.REPORTDAMAGE%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.reportdamage.equId"/></td>
                            <td height="22" colspan="0">
                                <html:select style="width:500px"  property="equId"  name="<%=Constants.REPORTDAMAGE%>">
                                    <html:options collection="<%=Constants.EQUIPMENT_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.reportdamage.createdTime"/></td>
                            <td height="22" colspan="3"><html:text property="createdTime" size="100" name="<%=Constants.REPORTDAMAGE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.reportdamage.createdLocation"/></td>
                            <td height="22" colspan="3"><html:text property="createdLocation" size="100" name="<%=Constants.REPORTDAMAGE%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.reportdamage.managerEmp"/></td>
                            <td height="22" colspan="0">
                                <html:select  property="managerEmp"  name="<%=Constants.REPORTDAMAGE%>">
                                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>

                                <td height="22"><bean:message key="message.reportdamage.usedEmp"/></td>
                            <td height="22" colspan="0">
                                <html:select  property="usedEmp"  name="<%=Constants.REPORTDAMAGE%>">
                                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>

                            <tr>
                                <td height="22"><bean:message key="message.reportdamage.managerEquipmentEmp"/></td>
                            <td height="22" colspan="0">
                                <html:select  property="managerEquipmentEmp"  name="<%=Constants.REPORTDAMAGE%>">
                                    <html:options collection="<%=Constants.EMPLOYEE_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>

                                <td height="22"><bean:message key="message.reportdamage.status"/></td>
                            <td height="22" colspan="0">
                                <html:select  property="status"  name="<%=Constants.REPORTDAMAGE%>">
                                    <html:options collection="<%=Constants.STATUS_LIST%>" property="value" labelProperty="label"/> </html:select>
                                </td>
                            </tr>
                            <tr>
                                <td height="22"><bean:message key="message.reportdamage.comment"/></td>
                            <td colspan="3"><html:textarea rows="3" cols="80" property="comment" name="<%=Constants.REPORTDAMAGE%>"/></td>
                        </tr>
                        <!-- file attachment -->
                        <logic:greaterThan name="<%=Constants.REPORTDAMAGE%>" value="0" property="rdId">
                            <tr>
                                <td colspan="4"><div id='divAttachFileSystem' ><img src="img/indicator.gif"/></div></td>
                            </tr>
                        </logic:greaterThan>
                    </table>
                    <p style="margin-top: 0; margin-bottom: 0" align="left">
                        <%
                                    if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_EQUIPMENT_FACTREPORT)) {
                        %>
                        <logic:greaterThan name="<%=Constants.REPORTDAMAGE%>" value="0" property="rdId">
                            <html:image  onclick="return printReportDamage();" src="images/but_print.gif"/>
                        </logic:greaterThan>
                        <html:image onclick="return saveReportDamage();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                        <%}%>
                        <html:image property="Back" value="Back" src="images/but_back.gif" onclick="return loadReportDamageList();"/>
                </div></td></tr></table>
                <html:hidden property="rdId" name="<%=Constants.REPORTDAMAGE%>" />
</form>