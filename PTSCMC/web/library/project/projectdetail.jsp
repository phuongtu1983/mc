<%-- 
    Document   : projectdetail
    Created on : Jun 27, 2009, 11:29:15 PM
    Author     : kngo
--%>

<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.venus.mc.util.MCUtil"%>
<%@ page import="com.venus.mc.util.Constants"%>
<%@ page import="com.venus.mc.util.PermissionUtil"%>
<%
    String proId = MCUtil.getParameter("proId", request);
%>
<h3><bean:message key="message.detailproject.title"/></h3>
<div id="errorMessageDiv"></div>
<html:form action="addProject.do">
    <table border="0" width="100%" cellspacing="0" cellpadding="0" >
        <tr><td><div align="center">
                    <table width="90%" border="0" cellspacing="0" cellpadding="0" style="border-width: 0px">
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.name"/></td>
                            <td height="22" colspan="5"><html:text property="name" size="98" name="<%=Constants.PROJECT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.name_en"/></td>
                            <td height="22" colspan="15"><html:text property="name_en" size="98" name="<%=Constants.PROJECT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.comments"/></td>
                            <td height="22"><html:text size="10" property="abbreviate" name="<%=Constants.PROJECT%>" /></td>
                        </tr>  
                        <tr>
                            <td height="22"><bean:message key="message.project.startDate"/></td>
                            <td height="22" colspan="5"><html:text property="startDate" size="98" styleId="startDate" onclick="javascript: showCalendar('startDate')" name="<%=Constants.PROJECT%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.endDate"/></td>
                            <td height="22" colspan="5"><html:text property="endDate" size="98" styleId="endDate" onclick="javascript: showCalendar('endDate')" name="<%=Constants.PROJECT%>" /></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.investors_name"/></td>
                            <td height="22" colspan="5"><html:text property="investorsName" size="98" name="<%=Constants.PROJECT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.qc_name"/></td>
                            <td height="22" colspan="5"><html:text property="qc_name" size="98" name="<%=Constants.PROJECT%>"/></td>
                        </tr>
                        <tr>
                            <td height="22"><bean:message key="message.project.status"/></td>
                            <td height="22" colspan="5">
                                <html:select property="status" name="<%=Constants.PROJECT%>">
                                    <html:options collection="<%=Constants.PROJECT_STATUS_LIST%>" property="value" labelProperty="label"/>
                                </html:select>
                            </td>
                        </tr>  
                    </table>
                    
                    <%if (PermissionUtil.hasPermission(request, PermissionUtil.FUNC_EDIT, PermissionUtil.PER_LIBRARY_PROJECT)) {%>
                    <html:image align="left" onclick="return saveProject();" src="images/but_save.gif" style="cursor: hand;"/><img border="0" src="images/blank.gif" width="2" height="17">
                    <%}%>
                    <html:image align="left" property="Back" value="Back" src="images/but_back.gif" onclick="return loadProjectList();"/>
                </div></td></tr></table>
                <html:hidden property="proId" name="<%=Constants.PROJECT%>" />
            </html:form>
